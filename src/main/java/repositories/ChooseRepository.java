package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Choice;
import models.Choose;
import models.Question;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class ChooseRepository implements QuestionRepository {

	private static ChooseRepository chooseRepository;

	public static synchronized ChooseRepository getInstance() {
		if (chooseRepository == null)
			return chooseRepository = new ChooseRepository();
		return chooseRepository;
	}

	public List<Question> findAll() {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Question> chooses = session.createQuery("FROM Choose").list();
			session.close();
			return chooses;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_ALL_QUESTION_ERROR.getValue());
		}
		return null;
	}

	public Question findOneById(int id) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			Choose choose = (Choose) session.createQuery("FROM Choose choose WHERE choose.id = :id").setParameter("id", id).uniqueResult();
			session.close();
			return choose;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_QUESTION_BY_ID_ERROR.getValue());
		}
		return null;
	}

	public Question save(Question question) {
		try {
			Choose choose = (Choose) question;
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			choose.setId((Integer) session.save(choose));
			session.getTransaction().commit();
			session.close();
			List<Choice> choices = new ArrayList<Choice>();
			for (Choice choice : choose.getChoices()) {
				choice.setChoose(choose);
				choices.add(ChoiceRepository.getInstance().save(choice));
			}
			choose.setChoices(choices);
			return choose;
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
			Choose choose = (Choose) question;
			ChoiceRepository.getInstance().delete(question.getId());
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(choose);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_QUESTION_ERROR.getValue());
		}
	}
}
