package repositorys;

import models.ConnectPair;

import org.hibernate.Session;

import configuration.databaseConfiguration.DatabaseEngine;

/**
 * Created by Ahmed Hamed on 8/23/2015.
 */
public class ConnectPairRepository {

    private static ConnectPairRepository connectPairRepository;

    public static synchronized ConnectPairRepository getInstance() {
        if (connectPairRepository == null)
            return connectPairRepository = new ConnectPairRepository();
        return connectPairRepository;
    }

    public ConnectPair save(ConnectPair connectPair) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        connectPair.setId((Integer) session.save(connectPair));
        session.getTransaction().commit();
        session.close();
        return connectPair;
    }

    public void update(ConnectPair connectPair) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.update(connectPair);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(ConnectPair connectPair) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.delete(connectPair);
        session.getTransaction().commit();
        session.close();
    }

}
