package questionPackage.question.choose;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import questionPackage.question.Question;
import questionPackage.question.QuestionRepository;
import questionPackage.question.choose.choice.Choice;
import questionPackage.question.choose.choice.ChoiceRepository;
import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import configuration.uiConfiguration.Message;

public class ChooseRepository implements QuestionRepository {

	private static ChooseRepository chooseRepository;

	public static synchronized ChooseRepository getInstance() {
		if (chooseRepository == null)
			return chooseRepository = new ChooseRepository();
		return chooseRepository;
	}

	public List<Question> findAll() {
		Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
		@SuppressWarnings("unchecked")
		List<Choose> list = session.createQuery("FROM Choose").list();
		List<Question> result = new ArrayList<Question>();
		for (Choose choose : list)
			result.add(choose);
		session.close();
		return result;
	}

	public Question findOneById(int id) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Choose> list = session.createQuery("FROM Choose choose WHERE choose.id = :id").setParameter("id", id).list();
			session.close();
			return (list.size() == 0) ? null : list.get(0);
		} catch (Exception e) {
			Error.reportErrorMessage(Message.findQuestionByIdError);
			System.exit(0);
			return null;
		}
	}

	public Question findOneByDescription(String description) {
		Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
		@SuppressWarnings("unchecked")
		List<Choose> list = session.createQuery("FROM Choose choose WHERE choose.description = :description")
				.setParameter("description", description).list();
		session.close();
		return (list.size() == 0) ? null : list.get(0);
	}

	public Question save(Question question) {
		Choose choose = (Choose) question;
		Choose chooseExist = (Choose) findOneByDescription(choose.getDescription());
		if (chooseExist != null)
			return chooseExist;
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
			Error.reportErrorMessage(Message.deleteQuestionError);
			System.exit(0);
		}
	}
}
