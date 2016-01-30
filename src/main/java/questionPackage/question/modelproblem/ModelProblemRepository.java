package questionPackage.question.modelproblem;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import questionPackage.question.modelproblem.modelproblemanswer.ModelProblemAnswer;
import questionPackage.question.modelproblem.modelproblemanswer.ModelProblemAnswerRepository;
import questionPackage.question.modelproblem.modelproblemdescription.ModelProblemDescription;
import questionPackage.question.modelproblem.modelproblemdescription.ModelProblemDescriptionRepository;
import configuration.databaseConfiguration.DatabaseEngine;

public class ModelProblemRepository {

    private static ModelProblemRepository modelProblemRepository;

    public static synchronized ModelProblemRepository getInstance() {
        if (modelProblemRepository == null)
            return modelProblemRepository = new ModelProblemRepository();
        return modelProblemRepository;
    }

    public List<ModelProblem> findAll() {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<ModelProblem> list = session.createQuery("FROM ModelProblem").list();
        session.close();
        return list;
    }

    public ModelProblem findOneById(int id) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<ModelProblem> list = session.createQuery("FROM ModelProblem modelProblem WHERE modelProblem.id = :id").setParameter("id", id).list();
        session.close();
        return (list.size() == 0) ? null : list.get(0);
    }

    public ModelProblem findOneByDescription(String description) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<ModelProblem> list = session.createQuery("FROM ModelProblem modelProblem WHERE modelProblem.description = :description").setParameter("description", description).list();
        session.close();
        return (list.size() == 0) ? null : list.get(0);
    }

    public ModelProblem save(ModelProblem modelProblem) {
        ModelProblem modelProblemExist = findOneByDescription(modelProblem.getDescription());
        if (modelProblemExist != null)
            return modelProblemExist;
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        modelProblem.setId((Integer) session.save(modelProblem));
        session.getTransaction().commit();
        session.close();
        List<ModelProblemDescription> modelProblemDescriptions = new ArrayList<ModelProblemDescription>();
        for (ModelProblemDescription modelProblemDescription : modelProblem.getModelProblemDescriptions()) {
            modelProblemDescription.setModelProblem(modelProblem);
            modelProblemDescriptions.add(ModelProblemDescriptionRepository.getInstance().save(modelProblemDescription));
        }
        modelProblem.setModelProblemDescriptions(modelProblemDescriptions);
        List<ModelProblemAnswer> modelProblemAnswers = new ArrayList<ModelProblemAnswer>();
        for (ModelProblemAnswer modelProblemAnswer : modelProblem.getModelProblemAnswers()) {
            modelProblemAnswer.setModelProblem(modelProblem);
            modelProblemAnswers.add(ModelProblemAnswerRepository.getInstance().save(modelProblemAnswer));
        }
        modelProblem.setModelProblemAnswers(modelProblemAnswers);
        return modelProblem;
    }

    public void update(ModelProblem modelProblem) {
        for (ModelProblemDescription modelProblemDescription : modelProblem.getModelProblemDescriptions())
            ModelProblemDescriptionRepository.getInstance().update(modelProblemDescription);
        for (ModelProblemAnswer modelProblemAnswer : modelProblem.getModelProblemAnswers())
            ModelProblemAnswerRepository.getInstance().update(modelProblemAnswer);
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.update(modelProblem);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(ModelProblem modelProblem) {
        for (ModelProblemDescription modelProblemDescription : modelProblem.getModelProblemDescriptions())
            ModelProblemDescriptionRepository.getInstance().delete(modelProblemDescription);
        for (ModelProblemAnswer modelProblemAnswer : modelProblem.getModelProblemAnswers())
            ModelProblemAnswerRepository.getInstance().delete(modelProblemAnswer);
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.delete(modelProblem);
        session.getTransaction().commit();
        session.close();
    }
}
