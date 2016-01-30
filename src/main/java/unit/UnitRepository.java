package unit;

import java.util.List;

import org.hibernate.Session;

import questionPackage.question.Question;
import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import configuration.uiConfiguration.Message;

public class UnitRepository {

	private static UnitRepository unitRepository;

	public static synchronized UnitRepository getInstance() {
		if (unitRepository == null)
			return unitRepository = new UnitRepository();
		return unitRepository;
	}

	public List<Unit> findAll() {
		Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
		@SuppressWarnings("unchecked")
		List<Unit> list = session.createQuery("FROM Unit").list();
		session.close();
		return list;
	}

	public Unit findOneById(int id) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Unit> list = session.createQuery("FROM Unit unit WHERE unit.id = :id").setParameter("id", id).list();
			session.close();
			return (list.size() == 0) ? null : list.get(0);
		} catch (Exception e) {
			Error.reportErrorMessage(Message.findUnitIdError);
			System.exit(0);
			return null;
		}
	}

	public Unit findOneByDescription(String description) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Unit> list = session.createQuery("FROM Unit unit WHERE unit.description = :description").setParameter("description", description)
					.list();
			session.close();
			return (list.size() == 0) ? null : list.get(0);
		} catch (Exception e) {
			Error.reportErrorMessage(Message.findUnitByDescriptionError);
			System.exit(0);
			return null;
		}
	}

	public Unit save(Unit unit) {
		Unit unitExist = findOneByDescription(unit.getDescription());
		if (unitExist != null) {
			Error.reportErrorMessage(Message.unitExistError);
			return unitExist;
		}
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			unit.setId((Integer) session.save(unit));
			session.getTransaction().commit();
			session.close();
			return unit;
		} catch (Exception e) {
			Error.reportErrorMessage(Message.saveUnitError);
			System.exit(0);
			return null;
		}
	}

	public void update(Unit unit) {
		Unit unitExist = findOneByDescription(unit.getDescription());
		if (unitExist != null && unitExist.getId() != unit.getId()) {
			Error.reportErrorMessage(Message.unitExistError);
		} else {
			try {
				Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
				session.beginTransaction();
				session.update(unit);
				session.getTransaction().commit();
				session.close();
			} catch (Exception e) {
				Error.reportErrorMessage(Message.updateUnitError);
				System.exit(0);
			}
		}
	}

	public void delete(Unit unit) {
		for (Question question : unit.getQuestions())
			question.getQuestionRepository().delete(question);
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(unit);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessage(Message.deleteUnitError);
			System.exit(0);
		}
	}

}
