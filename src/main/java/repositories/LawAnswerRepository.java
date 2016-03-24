package repositories;

import models.LawAnswer;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class LawAnswerRepository {

	private static LawAnswerRepository lawAnswerRepository;

	public static synchronized LawAnswerRepository getInstance() {
		if (lawAnswerRepository == null)
			return lawAnswerRepository = new LawAnswerRepository();
		return lawAnswerRepository;
	}

	public LawAnswer save(LawAnswer lawAnswer) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			lawAnswer.setId((Integer) session.save(lawAnswer));
			session.getTransaction().commit();
			session.close();
			return lawAnswer;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.SAVE_QUESTION_ERROR.getValue());
		}
		return null;
	}

	public void delete(LawAnswer lawAnswer) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(lawAnswer);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_QUESTION_ERROR.getValue());
		}
	}
}
