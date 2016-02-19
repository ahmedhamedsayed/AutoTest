package repositorys;

import models.ModelProblemDescription;

import org.hibernate.Session;

import configuration.databaseConfiguration.DatabaseEngine;

/**
 * Created by Ahmed Hamed on 8/23/2015.
 */
public class ModelProblemDescriptionRepository {

    private static ModelProblemDescriptionRepository modelProblemDescriptionRepository;

    public static synchronized ModelProblemDescriptionRepository getInstance() {
        if (modelProblemDescriptionRepository == null)
            return modelProblemDescriptionRepository = new ModelProblemDescriptionRepository();
        return modelProblemDescriptionRepository;
    }

    public ModelProblemDescription save(ModelProblemDescription modelProblemDescription) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        modelProblemDescription.setId((Integer) session.save(modelProblemDescription));
        session.getTransaction().commit();
        session.close();
        return modelProblemDescription;
    }

    public void update(ModelProblemDescription modelProblemDescription) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.update(modelProblemDescription);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(ModelProblemDescription modelProblemDescription) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.delete(modelProblemDescription);
        session.getTransaction().commit();
        session.close();
    }
}
