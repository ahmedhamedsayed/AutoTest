package repositories;

import java.util.List;

import models.Question;
import models.TrueOrFalse;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class TrueOrFalseRepository implements QuestionRepository {

	private static TrueOrFalseRepository trueOrFalseRepository;

	public static synchronized QuestionRepository getInstance() {
		if (trueOrFalseRepository == null)
			return trueOrFalseRepository = new TrueOrFalseRepository();
		return trueOrFalseRepository;
	}

	public List<Question> findAll() {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Question> trueOrFalses = session.createQuery("FROM TrueOrFalse").list();
			session.close();
			return trueOrFalses;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_ALL_QUESTION_ERROR.getValue());
		}
		return null;
	}

	public TrueOrFalse findOneById(int id) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			TrueOrFalse trueOrFalse = (TrueOrFalse) session.createQuery("FROM TrueOrFalse trueOrFalse WHERE trueOrFalse.id = :id").setParameter("id", id).uniqueResult();
			session.close();
			return trueOrFalse;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_QUESTION_BY_ID_ERROR.getValue());
		}
		return null;
	}

	public TrueOrFalse findOneByDescription(String description) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			TrueOrFalse trueOrFalse = (TrueOrFalse) session.createQuery("FROM TrueOrFalse trueOrFalse WHERE trueOrFalse.description = :description").setParameter("description", description).uniqueResult();
			session.close();
			return trueOrFalse;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_QUESTION_BY_DESCRIPTION_ERROR.getValue());
		}
		return null;
	}

	public Question save(Question question) {
		try {
			TrueOrFalse trueOrFalse = (TrueOrFalse) question;
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			trueOrFalse.setId((Integer) session.save(trueOrFalse));
			session.getTransaction().commit();
			session.close();
			return trueOrFalse;
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
		TrueOrFalse trueOrFalse = (TrueOrFalse) question;
		Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
		session.beginTransaction();
		session.delete(trueOrFalse);
		session.getTransaction().commit();
		session.close();
	}
}
