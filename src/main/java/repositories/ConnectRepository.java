package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Connect;
import models.ConnectPair;
import models.Question;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class ConnectRepository implements QuestionRepository {

	private static ConnectRepository connectRepository;

	public static synchronized ConnectRepository getInstance() {
		if (connectRepository == null)
			return connectRepository = new ConnectRepository();
		return connectRepository;
	}

	public List<Question> findAll() {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Question> connects = session.createQuery("FROM Connect").list();
			session.close();
			return connects;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_ALL_QUESTION_ERROR.getValue());
		}
		return null;
	}

	public Question findOneById(int id) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			Connect connect = (Connect) session.createQuery("FROM Connect connect WHERE connect.id = :id").setParameter("id", id).uniqueResult();
			session.close();
			return connect;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_QUESTION_BY_ID_ERROR.getValue());
		}
		return null;
	}

	public Question save(Question question) {
		try {
			Connect connect = (Connect) question;
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			connect.setId((Integer) session.save(connect));
			session.getTransaction().commit();
			session.close();
			List<ConnectPair> connectPairs = new ArrayList<ConnectPair>();
			for (ConnectPair connectPair : connect.getConnectPairs()) {
				connectPair.setConnect(connect);
				connectPairs.add(ConnectPairRepository.getInstance().save(connectPair));
			}
			connect.setConnectPairs(connectPairs);
			return connect;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.SAVE_QUESTION_ERROR.getValue());
		}
		return null;
	}

	public void update(Question question) {
		delete(question);
		save(question);
	}

	public void delete(Question question) {
		try {
			Connect connect = (Connect) question;
			for (ConnectPair connectPair : connect.getConnectPairs())
				ConnectPairRepository.getInstance().delete(connectPair);
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(connect);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_QUESTION_ERROR.getValue());
		}
	}

}
