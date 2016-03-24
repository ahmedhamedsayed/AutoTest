package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositories.ChooseRepository;
import repositories.QuestionRepository;
import ui.ChooseUI;
import ui.QuestionUI;

@Entity
@Table(name = "choose")
@PrimaryKeyJoinColumn(name = "question_id")
public class Choose extends Question {

	@Column(name = "mark")
	private int mark;

	@Column(name = "answer")
	private int answer;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "choose")
	private List<Choice> choices;

	public int getMark() {
		return mark;
	}
	
	public Integer getTotalQuestionMark() {
		return mark;
	}
	
	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public QuestionUI getQuestionUI() {
		return ChooseUI.getInstance();
	}

	public QuestionRepository getQuestionRepository() {
		return ChooseRepository.getInstance();
	}
}
