package repositories;

import java.util.List;

import models.Complete;

import org.hibernate.Session;

import configuration.databaseConfiguration.DatabaseEngine;

/**
 * Created by Ahmed Hamed on 8/23/2015.
 */
public class CompleteRepository {

    private static CompleteRepository completeRepository;

    public static synchronized CompleteRepository getInstance() {
        if (completeRepository == null)
            return completeRepository = new CompleteRepository();
        return completeRepository;
    }

    public List<Complete> findAll() {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<Complete> list = session.createQuery("FROM Complete").list();
        session.close();
        return list;
    }

    public Complete findOneById(int id) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<Complete> list = session.createQuery("FROM Complete complete WHERE complete.id = :id").setParameter("id", id).list();
        session.close();
        return (list.size() == 0) ? null : list.get(0);
    }

    public Complete findOneByDescription(String description) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<Complete> list = session.createQuery("FROM Complete complete WHERE complete.description = :description").setParameter("description", description).list();
        session.close();
        return (list.size() == 0) ? null : list.get(0);
    }

    public Complete save(Complete complete) {
        Complete completeExist = findOneByDescription(complete.getDescription());
        if (completeExist != null)
            return completeExist;
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        complete.setId((Integer) session.save(complete));
        session.getTransaction().commit();
        session.close();
        return complete;
    }

    public void update(Complete complete) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.update(complete);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Complete complete) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.delete(complete);
        session.getTransaction().commit();
        session.close();
    }
}
