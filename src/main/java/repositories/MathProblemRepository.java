package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Law;
import models.LawAnswer;
import models.MathProblem;
import models.Question;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class MathProblemRepository implements QuestionRepository {

	private static MathProblemRepository mathProblemRepository;

	public static synchronized MathProblemRepository getInstance() {
		if (mathProblemRepository == null)
			return mathProblemRepository = new MathProblemRepository();
		return mathProblemRepository;
	}

	public List<Question> findAll() {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Question> mathProblems = session.createQuery("FROM MathProblem").list();
			session.close();
			return mathProblems;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_ALL_QUESTION_ERROR.getValue());
		}
		return null;
	}

	public Question findOneById(int id) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			MathProblem mathProblem = (MathProblem) session.createQuery("FROM MathProblem mathProblem WHERE mathProblem.id = :id").setParameter("id", id).uniqueResult();
			session.close();
			return mathProblem;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_QUESTION_BY_ID_ERROR.getValue());
		}
		return null;
	}

	public Question save(Question question) {
		try {
			MathProblem mathProblem = (MathProblem) question;
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			mathProblem.setId((Integer) session.save(mathProblem));
			session.getTransaction().commit();
			session.close();
			List<Law> laws = new ArrayList<Law>();
			for (Law law : mathProblem.getLaws()) {
				law.setMathProblem(mathProblem);
				laws.add(LawRepository.getInstance().save(law));
			}
			mathProblem.setLaws(laws);
			List<LawAnswer> lawAnswers = new ArrayList<LawAnswer>();
			for (LawAnswer lawAnswer : mathProblem.getLawAnswers()) {
				lawAnswer.setMathProblem(mathProblem);
				lawAnswers.add(LawAnswerRepository.getInstance().save(lawAnswer));
			}
			mathProblem.setLawAnswers(lawAnswers);
			return mathProblem;
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
			MathProblem mathProblem = (MathProblem) question;
			for (Law law : mathProblem.getLaws())
				LawRepository.getInstance().delete(law);
			for (LawAnswer lawAnswer : mathProblem.getLawAnswers())
				LawAnswerRepository.getInstance().delete(lawAnswer);
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(mathProblem);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_QUESTION_ERROR.getValue());
		}
	}
}
