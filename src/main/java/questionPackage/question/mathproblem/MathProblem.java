package questionPackage.question.mathproblem;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import questionPackage.question.Question;
import questionPackage.question.mathproblem.law.Law;
import questionPackage.question.mathproblem.lawanswer.LawAnswer;

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

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mathProblem")
    private List<Law> laws;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mathProblem")
    private List<LawAnswer> lawAnswers;

	public int getLawMark() {
		return lawMark;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

    @Override
    public String toString() {
        return "Law Mark = " + String.valueOf(getLawMark()) + "\nNumber Mark = " + String.valueOf(numberMark) + "\nAnswer = " + getAnswer() + "\nDescription = " + getDescription() + "\n";
    }
}
