package repositories;

import java.util.List;

import models.Complete;
import models.Question;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class CompleteRepository implements QuestionRepository {

	private static CompleteRepository completeRepository;

	public static synchronized CompleteRepository getInstance() {
		if (completeRepository == null)
			return completeRepository = new CompleteRepository();
		return completeRepository;
	}

	public List<Question> findAll() {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Question> completes = session.createQuery("FROM Complete").list();
			session.close();
			return completes;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_ALL_QUESTION_ERROR.getValue());
		}
		return null;
	}

	public Question findOneById(int id) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			Complete complete = (Complete) session.createQuery("FROM Complete complete WHERE complete.id = :id").setParameter("id", id).uniqueResult();
			session.close();
			return complete;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_QUESTION_BY_ID_ERROR.getValue());
		}
		return null;
	}

	public Question findOneByDescription(String description) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			Complete complete = (Complete) session.createQuery("FROM Complete complete WHERE complete.description = :description").setParameter("description", description).uniqueResult();
			session.close();
			return complete;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_QUESTION_BY_DESCRIPTION_ERROR.getValue());
		}
		return null;
	}

	public Question save(Question question) {
		try {
			Complete complete = (Complete) question;
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			complete.setId((Integer) session.save(complete));
			session.getTransaction().commit();
			session.close();
			return complete;
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
			Complete complete = (Complete) question;
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(complete);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_QUESTION_ERROR.getValue());
		}
	}
}
