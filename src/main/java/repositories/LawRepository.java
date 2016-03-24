package repositories;

import models.Law;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class LawRepository {

	private static LawRepository lawRepository;

	public static synchronized LawRepository getInstance() {
		if (lawRepository == null)
			return lawRepository = new LawRepository();
		return lawRepository;
	}

	public Law save(Law law) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			law.setId((Integer) session.save(law));
			session.getTransaction().commit();
			session.close();
			return law;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.SAVE_QUESTION_ERROR.getValue());
		}
		return null;
	}

	public void delete(Law law) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(law);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_QUESTION_ERROR.getValue());
		}
	}
}
