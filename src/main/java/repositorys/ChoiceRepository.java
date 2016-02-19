package repositorys;

import models.Choice;

import org.hibernate.Session;

import configuration.databaseConfiguration.DatabaseEngine;

public class ChoiceRepository {

    private static ChoiceRepository choiceRepository;

    public static synchronized ChoiceRepository getInstance() {
        if (choiceRepository == null)
            return choiceRepository = new ChoiceRepository();
        return choiceRepository;
    }

    public Choice save(Choice choice) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        choice.setId((Integer) session.save(choice));
        session.getTransaction().commit();
        session.close();
        return choice;
    }

    public void delete(int questionId) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Choice choice WHERE choice.choose.id = :questionId").setParameter("questionId", questionId).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
