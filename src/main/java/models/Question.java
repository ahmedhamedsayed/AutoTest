package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import repositories.QuestionRepository;
import ui.QuestionUI;

@Entity
@Table(name = "question")
@Inheritance(strategy = InheritanceType.JOINED)
public class Question {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;

	@ManyToMany(mappedBy = "questions")
	private List<Exam> exams;

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

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public Integer getTotalQuestionMark() {
		return null;
	}
	
	public QuestionUI getQuestionUI() {
		return null;
	}

	public QuestionRepository getQuestionRepository() {
		return null;
	}

	@Override
	public String toString() {
		return "ID = " + String.valueOf(getId());
	}
}
