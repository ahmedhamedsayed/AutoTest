package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "modelproblemdescription")
public class ModelProblemDescription {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "row")
	private int row;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private ModelProblem modelProblem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ModelProblem getModelProblem() {
		return modelProblem;
	}

	public void setModelProblem(ModelProblem modelProblem) {
		this.modelProblem = modelProblem;
	}
}
