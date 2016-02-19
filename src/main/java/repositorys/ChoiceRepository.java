package repositorys;

import models.Choice;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class ChoiceRepository {

	private static ChoiceRepository choiceRepository;

	public static synchronized ChoiceRepository getInstance() {
		if (choiceRepository == null)
			return choiceRepository = new ChoiceRepository();
		return choiceRepository;
	}

	public Choice save(Choice choice) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			choice.setId((Integer) session.save(choice));
			session.getTransaction().commit();
			session.close();
			return choice;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.SAVE_CHOICE_ERROR.getValue());
		}
		return null;
	}

	public void delete(int questionId) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.createQuery("DELETE FROM Choice choice WHERE choice.choose.id = :questionId").setParameter("questionId", questionId).executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_CHOICE_ERROR.getValue());
		}
	}
}
