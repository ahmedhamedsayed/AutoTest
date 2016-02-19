package repositorys;

import java.util.List;

import models.Student;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

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
			Error.reportErrorMessageWithException(e, Message.FIND_ALL_STUDENTS_ERROR.getValue());
		}
		return null;
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
			Error.reportErrorMessageWithException(e, Message.SAVE_STUDENT_ERROR.getValue());
		}
		return null;
	}
}
