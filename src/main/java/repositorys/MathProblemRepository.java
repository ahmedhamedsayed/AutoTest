package repositorys;

import java.util.ArrayList;
import java.util.List;

import models.Law;
import models.LawAnswer;
import models.MathProblem;

import org.hibernate.Session;

import configuration.databaseConfiguration.DatabaseEngine;

public class MathProblemRepository {

    private static MathProblemRepository mathProblemRepository;

    public static synchronized MathProblemRepository getInstance() {
        if (mathProblemRepository == null)
            return mathProblemRepository = new MathProblemRepository();
        return mathProblemRepository;
    }

    public List<MathProblem> findAll() {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<MathProblem> list = session.createQuery("FROM MathProblem").list();
        session.close();
        return list;
    }

    public MathProblem findOneById(int id) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<MathProblem> list = session.createQuery("FROM MathProblem mathProblem WHERE mathProblem.id = :id").setParameter("id", id).list();
        session.close();
        return (list.size() == 0) ? null : list.get(0);
    }

    public MathProblem findOneByDescription(String description) {
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        @SuppressWarnings("unchecked")
		List<MathProblem> list = session.createQuery("FROM MathProblem mathProblem WHERE mathProblem.description = :description").setParameter("description", description).list();
        session.close();
        return (list.size() == 0) ? null : list.get(0);
    }

    public MathProblem save(MathProblem mathProblem) {
        MathProblem mathProblemExist = findOneByDescription(mathProblem.getDescription());
        if (mathProblemExist != null)
            return mathProblemExist;
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        mathProblem.setId((Integer) session.save(mathProblem));
        session.getTransaction().commit();
        session.close();
        List<Law> laws = new ArrayList<Law>();
        for (Law law : mathProblem.getLaws()) {
            law.setMathProblem(mathProblem);
            laws.add(LawRepository.getInstance().save(law));
        }
        mathProblem.setLaws(laws);
        List<LawAnswer> lawAnswers = new ArrayList<LawAnswer>();
        for (LawAnswer lawAnswer : mathProblem.getLawAnswers()) {
            lawAnswer.setMathProblem(mathProblem);
            lawAnswers.add(LawAnswerRepository.getInstance().save(lawAnswer));
        }
        mathProblem.setLawAnswers(lawAnswers);
        return mathProblem;
    }

    public void update(MathProblem mathProblem) {
        for (Law law : mathProblem.getLaws())
            LawRepository.getInstance().update(law);
        for (LawAnswer lawAnswer : mathProblem.getLawAnswers())
            LawAnswerRepository.getInstance().update(lawAnswer);
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.update(mathProblem);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(MathProblem mathProblem) {
        for (Law law : mathProblem.getLaws())
            LawRepository.getInstance().delete(law);
        for (LawAnswer lawAnswer : mathProblem.getLawAnswers())
            LawAnswerRepository.getInstance().delete(lawAnswer);
        Session session = DatabaseEngine.getInstance().getMainDatabaseSession();
        session.beginTransaction();
        session.delete(mathProblem);
        session.getTransaction().commit();
        session.close();
    }
}
