package repositorys;

import java.util.List;

import models.TrueOrFalse;

import org.hibernate.Session;

import configuration.databaseConfiguration.DatabaseEngine;

public class TrueOrFalseRepository {

    private static TrueOrFalseRepository trueOrFalseRepository;

    public static synchronized TrueOrFalseRepository getInstance() {
        if (trueOrFalseRepository == null)
            return trueOrFalseRepository = new TrueOrFalseRepository();
        return trueOrFalseRepository;
    }

    public List<TrueOrFalse> findAll() {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<TrueOrFalse> list = session.createQuery("FROM TrueOrFalse").list();
        session.close();
        return list;
    }

    public TrueOrFalse findOneById(int id) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<TrueOrFalse> list = session.createQuery("FROM TrueOrFalse trueOrFalse WHERE trueOrFalse.id = :id").setParameter("id", id).list();
        session.close();
        return (list.size() == 0) ? null : list.get(0);
    }

    public TrueOrFalse findOneByDescription(String description) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<TrueOrFalse> list = session.createQuery("FROM TrueOrFalse trueOrFalse WHERE trueOrFalse.description = :description").setParameter("description", description).list();
        session.close();
        return (list.size() == 0) ? null : list.get(0);
    }

    public TrueOrFalse save(TrueOrFalse trueOrFalse) {
        TrueOrFalse trueOrFalseExist = findOneByDescription(trueOrFalse.getDescription());
        if (trueOrFalseExist != null)
            return trueOrFalseExist;
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        trueOrFalse.setId((Integer) session.save(trueOrFalse));
        session.getTransaction().commit();
        session.close();
        return trueOrFalse;
    }

    public void update(TrueOrFalse trueOrFalse) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.update(trueOrFalse);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(TrueOrFalse trueOrFalse) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.delete(trueOrFalse);
        session.getTransaction().commit();
        session.close();
    }
}
