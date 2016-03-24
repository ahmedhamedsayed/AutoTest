package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositories.ModelProblemRepository;
import repositories.QuestionRepository;
import ui.ModelProblemUI;
import ui.QuestionUI;

@Entity
@Table(name = "model_problem")
@PrimaryKeyJoinColumn(name = "question_id")
public class ModelProblem extends Question {

	@Column(name = "mark")
	private int mark;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "modelProblem")
	private List<ModelProblemEntry> modelProblemEntries;

	public int getMark() {
		return mark;
	}

	public Integer getTotalQuestionMark() {
		return modelProblemEntries.size() * mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public List<ModelProblemEntry> getModelProblemEntries() {
		return modelProblemEntries;
	}

	public void setModelProblemEntries(List<ModelProblemEntry> modelProblemEntries) {
		this.modelProblemEntries = modelProblemEntries;
	}

	public QuestionUI getQuestionUI() {
		return ModelProblemUI.getInstance();
	}

	public QuestionRepository getQuestionRepository() {
		return ModelProblemRepository.getInstance();
	}
}
