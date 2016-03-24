package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositories.QuestionRepository;
import repositories.TrueOrFalseRepository;
import ui.QuestionUI;
import ui.TrueOrFalseUI;

@Entity
@Table(name = "true_or_false")
@PrimaryKeyJoinColumn(name = "question_id")
public class TrueOrFalse extends Question {

	@Column(name = "mark")
	private int mark;

	@Column(name = "answer")
	private String answer;

	public int getMark() {
		return mark;
	}

	public Integer getTotalQuestionMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public QuestionUI getQuestionUI() {
		return TrueOrFalseUI.getInstance();
	}

	public QuestionRepository getQuestionRepository() {
		return TrueOrFalseRepository.getInstance();
	}
}
