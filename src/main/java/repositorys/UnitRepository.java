package repositorys;

import java.util.List;

import models.Unit;

import org.hibernate.Session;

import util.shape.Error;
import configuration.databaseConfiguration.DatabaseEngine;
import constants.Message;

public class UnitRepository {

	private static UnitRepository unitRepository;

	public static synchronized UnitRepository getInstance() {
		if (unitRepository == null)
			return unitRepository = new UnitRepository();
		return unitRepository;
	}

	public List<Unit> findAll() {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			@SuppressWarnings("unchecked")
			List<Unit> units = session.createQuery("FROM Unit").list();
			session.close();
			return units;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_ALL_UNITS_ERROR.getValue());
		}
		return null;
	}

	public Unit findOneById(int id) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			Unit unit = (Unit) session.createQuery("FROM Unit unit WHERE unit.id = :id").setParameter("id", id).uniqueResult();
			session.close();
			return unit;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_UNIT_BY_ID_ERROR.getValue());
		}
		return null;
	}

	public Unit findOneByDescription(String description) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			Unit unit = (Unit) session.createQuery("FROM Unit unit WHERE unit.description = :description").setParameter("description", description).uniqueResult();
			session.close();
			return unit;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_UNIT_BY_DESCRIPTION_ERROR.getValue());
		}
		return null;
	}

	public Unit findOneByPassword(String password) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			Unit unit = (Unit) session.createQuery("FROM Unit unit WHERE unit.password = :password").setParameter("password", password).uniqueResult();
			session.close();
			return unit;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.FIND_UNIT_BY_PASSWORD_ERROR.getValue());
		}
		return null;
	}

	public Unit save(Unit unit) {
		try {
			Unit unitDescriptionExist = findOneByDescription(unit.getDescription());
			if (unitDescriptionExist != null) {
				Error.reportErrorMessage(Message.UNIT_DESCRIPTION_ALLREADY_EXIST_ERROR.getValue());
				return null;
			}
			Unit unitPasswordExist = findOneByPassword(unit.getPassword());
			if (unitPasswordExist != null) {
				Error.reportErrorMessage(Message.UNIT_PASSWORD_ALLREADY_EXIST_ERROR.getValue());
				return null;
			}
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			unit.setId((Integer) session.save(unit));
			session.getTransaction().commit();
			session.close();
			return unit;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.SAVE_UNIT_ERROR.getValue());
		}
		return null;
	}

	public Unit update(Unit unit) {
		try {
			Unit unitDescriptionExist = findOneByDescription(unit.getDescription());
			if (unitDescriptionExist != null && unitDescriptionExist.getId() != unit.getId()) {
				Error.reportErrorMessage(Message.UNIT_DESCRIPTION_ALLREADY_EXIST_ERROR.getValue());
				return null;
			}
			Unit unitPasswordExist = findOneByPassword(unit.getPassword());
			if (unitPasswordExist != null && unitPasswordExist.getId() != unit.getId()) {
				Error.reportErrorMessage(Message.UNIT_PASSWORD_ALLREADY_EXIST_ERROR.getValue());
				return null;
			}
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.update(unit);
			session.getTransaction().commit();
			session.close();
			return unit;
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.UPDATE_UNIT_ERROR.getValue());
		}
		return null;
	}

	public void delete(Unit unit) {
		try {
			Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
			session.beginTransaction();
			session.delete(unit);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			Error.reportErrorMessageWithException(e, Message.DELETE_UNIT_ERROR.getValue());
		}
	}

}
