package repositories;

import models.ConnectPair;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class ConnectPairRepository {

	private static ConnectPairRepository connectPairRepository;

	public static synchronized ConnectPairRepository getInstance() {
		if (connectPairRepository == null)
			return connectPairRepository = new ConnectPairRepository();
		return connectPairRepository;
	}

	public ConnectPair save(ConnectPair connectPair) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			connectPair.setId((Integer) session.save(connectPair));
			session.getTransaction().commit();
			session.close();
			return connectPair;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.SAVE_QUESTION_ERROR.getValue());
		}
		return null;
	}

	public void delete(ConnectPair connectPair) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(connectPair);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_QUESTION_ERROR.getValue());
		}
	}

}
