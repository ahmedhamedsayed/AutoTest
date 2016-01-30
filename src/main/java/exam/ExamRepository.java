package exam;

import java.util.List;

import org.hibernate.Session;

import questionPackage.question.Question;
import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import configuration.uiConfiguration.Message;
import exam.examPercentage.ExamPercentageRepository;

public class ExamRepository {

	private static ExamRepository examRepository;

	public static synchronized ExamRepository getInstance() {
		if (examRepository == null)
			return examRepository = new ExamRepository();
		return examRepository;
	}

	public List<Exam> findAll() {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Exam> exams = session.createQuery("FROM Exam").list();
			session.close();
			return exams;
		} catch (Exception e) {
			Error.reportErrorMessage(Message.loadAllExamsError);
			System.exit(0);
			return null;
		}

	}

	public Exam findOneById(int id) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Exam> list = session.createQuery("FROM Exam exam WHERE exam.id = :id").setParameter("id", id).list();
			session.close();
			return (list.size() == 0) ? null : list.get(0);
		} catch (Exception e) {
			System.exit(0);
			return null;
		}
	}

	public Exam findOneByName(String name) {
		Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
		@SuppressWarnings("unchecked")
		List<Exam> list = session.createQuery("FROM Exam exam WHERE exam.name = :name").setParameter("name", name).list();
		session.close();
		return (list.size() == 0) ? null : list.get(0);
	}

	public void addExamQuestion(Exam exam, Question question) {
		exam = findOneById(exam.getId());
		for (Question examQuestion : exam.getQuestions()) {
			if (examQuestion.getId() == question.getId())
				return;
		}
		try {
			exam.getQuestions().add(question);
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.update(exam);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessage(Message.addExamQuestionError);
			System.exit(0);
		}
	}

	public void deleteExamQuestion(Exam exam, Question question) {
		for (Question examQuestion : exam.getQuestions()) {
			if (examQuestion.getId() == question.getId()) {
				exam.getQuestions().remove(examQuestion);
				break;
			}
		}
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.update(exam);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessage(Message.deleteExamQuestionError);
			System.exit(0);
		}
	}

	public void deleteExamsQuestion(Question question) {
		List<Exam> exams = findAll();
		for (Exam exam : exams) {
			for (Question examQuestion : exam.getQuestions()) {
				if (examQuestion.getId() == question.getId()) {
					deleteExamQuestion(exam, question);
					break;
				}
			}
		}
	}

	public Exam save(Exam exam) {
		try {
			Exam examExist = findOneByName(exam.getName());
			if (examExist != null)
				return examExist;
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			exam.setId((Integer) session.save(exam));
			session.getTransaction().commit();
			session.close();
			return exam;

		} catch (Exception e) {
			Error.reportErrorMessage(Message.saveExamError);
			System.exit(0);
			return null;
		}
	}

	public void update(Exam exam) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.update(exam);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessage(Message.updateExamError);
			System.exit(0);
		}
	}

	public void delete(Exam exam) {
		try {
			while (!exam.getQuestions().isEmpty())
				deleteExamQuestion(exam, exam.getQuestions().get(0));
			ExamPercentageRepository.getInstance().delete(exam.getId());
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(exam);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessage(Message.deleteExamError);
			System.exit(0);
		}
	}
}
