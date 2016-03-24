package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositories.MathProblemRepository;
import repositories.QuestionRepository;
import ui.MathProblemUI;
import ui.QuestionUI;

@Entity
@Table(name = "mathproblem")
@PrimaryKeyJoinColumn(name = "question_id")
public class MathProblem extends Question {

	@Column(name = "lawmark")
	private int lawMark;

	@Column(name = "numbermark")
	private int numberMark;

	@Column(name = "answer")
	private int answer;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mathProblem")
	private List<Law> laws;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mathProblem")
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
