package questionPackage.question.mathproblem.lawanswer;

import org.hibernate.Session;

import configuration.databaseConfiguration.DatabaseEngine;

/**
 * Created by Ahmed Hamed on 8/23/2015.
 */
public class LawAnswerRepository {

    private static LawAnswerRepository lawAnswerRepository;

    public static synchronized LawAnswerRepository getInstance() {
        if (lawAnswerRepository == null)
            return lawAnswerRepository = new LawAnswerRepository();
        return lawAnswerRepository;
    }

    public LawAnswer save(LawAnswer lawAnswer) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        lawAnswer.setId((Integer) session.save(lawAnswer));
        session.getTransaction().commit();
        session.close();
        return lawAnswer;
    }

    public void update(LawAnswer lawAnswer) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.update(lawAnswer);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(LawAnswer lawAnswer) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.delete(lawAnswer);
        session.getTransaction().commit();
        session.close();
    }
}
