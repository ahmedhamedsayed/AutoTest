package questionPackage.question.mathproblem.law;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import questionPackage.question.mathproblem.MathProblem;

@Entity
@Table(name = "law")
public class Law {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private MathProblem mathProblem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MathProblem getMathProblem() {
		return mathProblem;
	}

	public void setMathProblem(MathProblem mathProblem) {
		this.mathProblem = mathProblem;
	}

	@Override
	public String toString() {
		return "ID = " + String.valueOf(getId()) + "\nDescription = " + getDescription() + "\n";
	}
}
