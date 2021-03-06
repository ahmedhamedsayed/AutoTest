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
@Table(name = "law_answer")
public class LawAnswer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "description")
	private int description;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private MathProblem mathProblem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDescription() {
		return description;
	}

	public void setDescription(int description) {
		this.description = description;
	}

	public MathProblem getMathProblem() {
		return mathProblem;
	}

	public void setMathProblem(MathProblem mathProblem) {
		this.mathProblem = mathProblem;
	}
}
