package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class Exam {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "hour")
	private int hour;

	@Column(name = "minute")
	private int minute;

	@Column(name = "mark")
	private int mark;

	@Column(name = "questions_number")
	private int questionsNumber;

	@Column(name = "password")
	private String password;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "exam")
	private List<ExamPercentage> examPercentages;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "exam_question", joinColumns = @JoinColumn(name = "exam_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
	private List<Question> questions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getQuestionsNumber() {
		return questionsNumber;
	}

	public void setQuestionsNumber(int questionsNumber) {
		this.questionsNumber = questionsNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ExamPercentage> getExamPercentages() {
		return examPercentages;
	}

	public void setExamPercentages(List<ExamPercentage> examPercentages) {
		this.examPercentages = examPercentages;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public double getPercentage() {
		double percentage = 100.0;
		for (ExamPercentage examPercentage : examPercentages)
			percentage -= examPercentage.getPercentage();
		return percentage;
	}

	@Override
	public String toString() {
		return "ID = " + String.valueOf(getId());
	}
}
