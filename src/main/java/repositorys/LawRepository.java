package repositorys;

import models.Law;

import org.hibernate.Session;

import configuration.databaseConfiguration.DatabaseEngine;

/**
 * Created by Ahmed Hamed on 8/23/2015.
 */
public class LawRepository {

    private static LawRepository lawRepository;

    public static synchronized LawRepository getInstance() {
        if (lawRepository == null)
            return lawRepository = new LawRepository();
        return lawRepository;
    }

    public Law save(Law law) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        law.setId((Integer) session.save(law));
        session.getTransaction().commit();
        session.close();
        return law;
    }

    public void update(Law law) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.update(law);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Law law) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.delete(law);
        session.getTransaction().commit();
        session.close();
    }
}
