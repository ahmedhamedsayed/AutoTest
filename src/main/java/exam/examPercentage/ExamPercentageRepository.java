package exam.examPercentage;

import org.hibernate.Session;

import configuration.databaseConfiguration.DatabaseEngine;

public class ExamPercentageRepository {

	private static ExamPercentageRepository examPercentageRepository;

	public static synchronized ExamPercentageRepository getInstance() {
		if (examPercentageRepository == null)
			return examPercentageRepository = new ExamPercentageRepository();
		return examPercentageRepository;
	}

	public ExamPercentage save(ExamPercentage examPercentage) {
		Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
		session.beginTransaction();
		examPercentage.setId((Integer) session.save(examPercentage));
		session.getTransaction().commit();
		session.close();
		return examPercentage;
	}

	public void delete(ExamPercentage examPercentage) {
		Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
		session.beginTransaction();
		session.delete(examPercentage);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(int examId) {
		Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
		session.beginTransaction();
		session.createQuery("DELETE FROM ExamPercentage examPercentage WHERE examPercentage.exam.id = :examId").setParameter("examId", examId)
				.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
}
