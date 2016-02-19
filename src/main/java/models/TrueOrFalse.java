package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "trueorfalse")
@PrimaryKeyJoinColumn(name = "question_id")
public class TrueOrFalse extends Question {

	@Column(name = "mark")
	private int mark;

	@Column(name = "answer")
	private String answer;

	@Column(name = "description")
	private String description;

	public int getMark() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @Override
    public String toString() {
        return "ID = " + String.valueOf(getId()) + "\nMark = " + String.valueOf(getMark()) + "\nAnswer = " + getAnswer() + "\nDescription = " + getDescription() + "\n";
    }
}
