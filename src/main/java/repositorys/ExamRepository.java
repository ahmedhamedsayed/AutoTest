package repositorys;

import java.util.List;

import models.Exam;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

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
			Error.reportErrorMessageWithException(e, Message.FIND_ALL_EXAMS_ERROR.getValue());
		}
		return null;
	}

	public Exam findOneById(int id) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			Exam exam = (Exam) session.createQuery("FROM Exam exam WHERE exam.id = :id").setParameter("id", id).uniqueResult();
			session.close();
			return exam;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_EXAM_BY_ID_ERROR.getValue());
		}
		return null;
	}

	public Exam findOneByName(String name) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			Exam exam = (Exam) session.createQuery("FROM Exam exam WHERE exam.name = :name").setParameter("name", name).uniqueResult();
			session.close();
			return exam;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_EXAM_BY_NAME_ERROR.getValue());
		}
		return null;
	}

	public Exam save(Exam exam) {
		try {
			Exam examExist = findOneByName(exam.getName());
			if (examExist != null) {
				Error.reportErrorMessage(Message.EXAM_NAME_ALLREADY_EXIST_ERROR.getValue());
				return null;
			}
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			exam.setId((Integer) session.save(exam));
			session.getTransaction().commit();
			session.close();
			return exam;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.SAVE_EXAM_ERROR.getValue());
		}
		return null;
	}

	public Exam update(Exam exam) {
		try {
			Exam examExist = findOneByName(exam.getName());
			if (examExist != null) {
				Error.reportErrorMessage(Message.EXAM_NAME_ALLREADY_EXIST_ERROR.getValue());
				return null;
			}
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.update(exam);
			session.getTransaction().commit();
			session.close();
			return exam;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.UPDATE_EXAM_ERROR.getValue());
		}
		return null;
	}

	public void delete(Exam exam) {
		try {
			ExamPercentageRepository.getInstance().delete(exam.getId());
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(exam);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_EXAM_ERROR.getValue());
		}
	}
}
