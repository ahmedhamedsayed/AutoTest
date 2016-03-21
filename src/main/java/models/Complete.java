package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositories.CompleteRepository;
import repositories.QuestionRepository;
import ui.CompleteUI;
import ui.QuestionUI;

@Entity
@Table(name = "complete")
@PrimaryKeyJoinColumn(name = "question_id")
public class Complete extends Question {

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
		return CompleteUI.getInstance();
	}

	public QuestionRepository getQuestionRepository() {
		return CompleteRepository.getInstance();
	}

	@Override
    public String toString() {
        return "ID = " + String.valueOf(getId()) + "\nMark = " + String.valueOf(getMark()) + "\nAnswer = " + getAnswer() + "\nDescription = " + getDescription() + "\n";
    }
}
