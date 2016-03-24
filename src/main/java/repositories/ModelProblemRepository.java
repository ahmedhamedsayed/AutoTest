package repositories;

import java.util.ArrayList;
import java.util.List;

import models.ModelProblem;
import models.ModelProblemEntry;
import models.Question;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class ModelProblemRepository implements QuestionRepository {

	private static ModelProblemRepository modelProblemRepository;

	public static synchronized ModelProblemRepository getInstance() {
		if (modelProblemRepository == null)
			return modelProblemRepository = new ModelProblemRepository();
		return modelProblemRepository;
	}

	public List<Question> findAll() {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Question> modelProblems = session.createQuery("FROM ModelProblem").list();
			session.close();
			return modelProblems;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_ALL_QUESTION_ERROR.getValue());
		}
		return null;
	}

	public Question findOneById(int id) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			ModelProblem modelProblem = (ModelProblem) session.createQuery("FROM ModelProblem modelProblem WHERE modelProblem.id = :id").setParameter("id", id).uniqueResult();
			session.close();
			return modelProblem;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_QUESTION_BY_ID_ERROR.getValue());
		}
		return null;
	}

	public Question save(Question question) {
		try {
			ModelProblem modelProblem = (ModelProblem) question;
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			modelProblem.setId((Integer) session.save(modelProblem));
			session.getTransaction().commit();
			session.close();
			List<ModelProblemEntry> modelProblemEntries = new ArrayList<ModelProblemEntry>();
			for (ModelProblemEntry modelProblemEntry : modelProblem.getModelProblemEntries()) {
				modelProblemEntry.setModelProblem(modelProblem);
				modelProblemEntries.add(ModelProblemEntryRepository.getInstance().save(modelProblemEntry));
			}
			modelProblem.setModelProblemEntries(modelProblemEntries);
			return modelProblem;
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
			ModelProblem modelProblem = (ModelProblem) question;
			for (ModelProblemEntry modelProblemEntry : modelProblem.getModelProblemEntries())
				ModelProblemEntryRepository.getInstance().delete(modelProblemEntry);
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(modelProblem);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_QUESTION_ERROR.getValue());
		}
	}
}
