package repositories;

import models.ModelProblemEntry;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class ModelProblemEntryRepository {

	private static ModelProblemEntryRepository modelProblemEntryRepository;

	public static synchronized ModelProblemEntryRepository getInstance() {
		if (modelProblemEntryRepository == null)
			return modelProblemEntryRepository = new ModelProblemEntryRepository();
		return modelProblemEntryRepository;
	}

	public ModelProblemEntry save(ModelProblemEntry modelProblemEntry) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			modelProblemEntry.setId((Integer) session.save(modelProblemEntry));
			session.getTransaction().commit();
			session.close();
			return modelProblemEntry;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.SAVE_QUESTION_ERROR.getValue());
		}
		return null;
	}

	public void delete(ModelProblemEntry modelProblemEntry) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(modelProblemEntry);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_QUESTION_ERROR.getValue());
		}
	}
}
