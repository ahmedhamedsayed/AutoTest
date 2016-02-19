package repositorys;

import java.util.ArrayList;
import java.util.List;

import models.Connect;
import models.ConnectPair;

import org.hibernate.Session;

import configuration.databaseConfiguration.DatabaseEngine;

/**
 * Created by Ahmed Hamed on 8/23/2015.
 */
public class ConnectRepository {

    private static ConnectRepository connectRepository;

    public static synchronized ConnectRepository getInstance() {
        if (connectRepository == null)
            return connectRepository = new ConnectRepository();
        return connectRepository;
    }

    public List<Connect> findAll() {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<Connect> list = session.createQuery("FROM Connect").list();
        session.close();
        return list;
    }

    public Connect findOneById(int id) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<Connect> list = session.createQuery("FROM Connect connect WHERE connect.id = :id").setParameter("id", id).list();
        session.close();
        return (list.size() == 0) ? null : list.get(0);
    }

    public Connect findOneByDescription(List<ConnectPair> connectPairs) {
        List<Connect> connects = findAll();
        for (Connect connect : connects) {
            boolean found = true;
            for (ConnectPair connectPair : connectPairs) {
                boolean flag = false;
                for (ConnectPair connectPairCur : connect.getConnectPairs())
                    if (connectPairCur.getDescription().equals(connectPair.getDescription()) && connectPairCur.getAnswer().equals(connectPair.getAnswer())) {
                        flag = true;
                        break;
                    }
                found &= flag;
            }
            if (found)
                return connect;
        }
        return null;
    }

    public Connect save(Connect connect) {
        Connect connectExist = findOneByDescription(connect.getConnectPairs());
        if (connectExist != null)
            return connectExist;
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        connect.setId((Integer) session.save(connect));
        session.getTransaction().commit();
        session.close();
        List<ConnectPair> connectPairs = new ArrayList<ConnectPair>();
        for (ConnectPair connectPair : connect.getConnectPairs()) {
            connectPair.setConnect(connect);
            connectPairs.add(ConnectPairRepository.getInstance().save(connectPair));
        }
        connect.setConnectPairs(connectPairs);
        return connect;
    }

    public void update(Connect connect) {
        for (ConnectPair connectPair : connect.getConnectPairs())
            ConnectPairRepository.getInstance().update(connectPair);
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.update(connect);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Connect connect) {
        for (ConnectPair connectPair : connect.getConnectPairs())
            ConnectPairRepository.getInstance().delete(connectPair);
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.delete(connect);
        session.getTransaction().commit();
        session.close();
    }

}
