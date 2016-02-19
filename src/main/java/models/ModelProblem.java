package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "modelproblem")
@PrimaryKeyJoinColumn(name = "question_id")
public class ModelProblem extends Question {

	@Column(name = "mark")
	private int mark;

	@Column(name = "description")
	private String description;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "modelProblem")
	private List<ModelProblemDescription> modelProblemDescriptions;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "modelProblem")
	private List<ModelProblemAnswer> modelProblemAnswers;

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ModelProblemDescription> getModelProblemDescriptions() {
		return modelProblemDescriptions;
	}

	public void setModelProblemDescriptions(List<ModelProblemDescription> modelProblemDescriptions) {
		this.modelProblemDescriptions = modelProblemDescriptions;
	}

	public List<ModelProblemAnswer> getModelProblemAnswers() {
		return modelProblemAnswers;
	}

	public void setModelProblemAnswers(List<ModelProblemAnswer> modelProblemAnswers) {
		this.modelProblemAnswers = modelProblemAnswers;
	}
}
