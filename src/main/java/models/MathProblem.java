package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import repositories.MathProblemRepository;
import repositories.QuestionRepository;
import ui.MathProblemUI;
import ui.QuestionUI;

@Entity
@Table(name = "math_problem")
@PrimaryKeyJoinColumn(name = "question_id")
public class MathProblem extends Question {

	@Column(name = "law_mark")
	private int lawMark;

	@Column(name = "number_mark")
	private int numberMark;

	@Column(name = "answer")
	private int answer;

	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "mathProblem", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@OrderBy("id ASC")
	private List<Law> laws;

	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "mathProblem", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@OrderBy("id ASC")
	private List<LawAnswer> lawAnswers;

	public int getLawMark() {
		return lawMark;
	}

	public Integer getTotalQuestionMark() {
		return lawMark + lawAnswers.size() * numberMark;
	}

	public void setLawMark(int lawMark) {
		this.lawMark = lawMark;
	}

	public int getNumberMark() {
		return numberMark;
	}

	public void setNumberMark(int numberMark) {
		this.numberMark = numberMark;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public List<Law> getLaws() {
		return laws;
	}

	public void setLaws(List<Law> laws) {
		this.laws = laws;
	}

	public List<LawAnswer> getLawAnswers() {
		return lawAnswers;
	}

	public void setLawAnswers(List<LawAnswer> lawAnswers) {
		this.lawAnswers = lawAnswers;
	}

	public QuestionUI getQuestionUI() {
		return MathProblemUI.getInstance();
	}

	public QuestionRepository getQuestionRepository() {
		return MathProblemRepository.getInstance();
	}
}
