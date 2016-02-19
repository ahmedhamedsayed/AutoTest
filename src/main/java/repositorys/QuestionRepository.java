package repositorys;

import java.util.List;

import models.Question;

public interface QuestionRepository {

    List<Question> findAll();

    Question findOneById(int id);

    Question findOneByDescription(String description);

    Question save(Question question);

    void update(Question question);

    void delete(Question question);
}
