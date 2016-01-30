package questionPackage.question.modelproblem.modelproblemanswer;

import org.hibernate.Session;

import configuration.databaseConfiguration.DatabaseEngine;

/**
 * Created by Ahmed Hamed on 8/23/2015.
 */
public class ModelProblemAnswerRepository {

    private static ModelProblemAnswerRepository modelProblemAnswerRepository;

    public static synchronized ModelProblemAnswerRepository getInstance() {
        if (modelProblemAnswerRepository == null)
            return modelProblemAnswerRepository = new ModelProblemAnswerRepository();
        return modelProblemAnswerRepository;
    }

    public ModelProblemAnswer save(ModelProblemAnswer modelProblemAnswer) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        modelProblemAnswer.setId((Integer) session.save(modelProblemAnswer));
        session.getTransaction().commit();
        session.close();
        return modelProblemAnswer;
    }

    public void update(ModelProblemAnswer modelProblemAnswer) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.update(modelProblemAnswer);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(ModelProblemAnswer modelProblemAnswer) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.delete(modelProblemAnswer);
        session.getTransaction().commit();
        session.close();
    }
}
