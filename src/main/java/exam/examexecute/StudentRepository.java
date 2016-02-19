package exam.examexecute;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import configuration.uiConfiguration.Message;

public class StudentRepository {

	private static StudentRepository studentRepository;

	public static synchronized StudentRepository getInstance() {
		if (studentRepository == null)
			return studentRepository = new StudentRepository();
		return studentRepository;
	}

	public List<Student> findAll(String databasePath) {
		try {
			Session session = DatabaseEngine.getInstance().getSession(databasePath);
			@SuppressWarnings("unchecked")
			List<Student> students = session.createQuery("FROM Student").list();
			session.close();
			return students;
		} catch (Exception e) {
			return null;
		}
	}

	public Student save(Student student) {
		try {
			Session session = DatabaseEngine.getInstance().getStudentDatabaseSession();
			session.beginTransaction();
			student.setId((Integer) session.save(student));
			session.getTransaction().commit();
			session.close();
			return student;
		} catch (Exception e) {
			Error.reportErrorMessage(""/*Message.saveExamError*/);
			System.exit(0);
			return null;
		}
	}

	public void deleteAll(String databasePath) {
		Session session = DatabaseEngine.getInstance().getSession(databasePath);
		Query query = session.createQuery("DELETE FROM Student");
		query.executeUpdate();
		session.close();
	}
}
