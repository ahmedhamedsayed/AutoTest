package repositories;

import models.ExamPercentage;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class ExamPercentageRepository {

	private static ExamPercentageRepository examPercentageRepository;

	public static synchronized ExamPercentageRepository getInstance() {
		if (examPercentageRepository == null)
			return examPercentageRepository = new ExamPercentageRepository();
		return examPercentageRepository;
	}

	public ExamPercentage save(ExamPercentage examPercentage) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			examPercentage.setId((Integer) session.save(examPercentage));
			session.getTransaction().commit();
			session.close();
			return examPercentage;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.SAVE_EXAM_PERCENTAGE_ERROR.getValue());
		}
		return null;
	}

	public void delete(ExamPercentage examPercentage) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(examPercentage);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_EXAM_PERCENTAGE_ERROR.getValue());
		}
	}

	public void delete(int examId) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.createQuery("DELETE FROM ExamPercentage examPercentage WHERE examPercentage.exam.id = :examId").setParameter("examId", examId).executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_EXAMS_PERCENTAGE_BY_EXAM_ID_ERROR.getValue());
		}
	}
}
