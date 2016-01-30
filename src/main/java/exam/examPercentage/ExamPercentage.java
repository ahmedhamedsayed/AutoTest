package exam.examPercentage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import exam.Exam;

@Entity
@Table(name = "exam_percentage")
public class ExamPercentage {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "another_exam_id")
	private int anotherExamId;

	@Column(name = "percentage")
	private double percentage = 0.0;

	@ManyToOne
	@JoinColumn(name = "exam_id")
	private Exam exam;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnotherExamId() {
		return anotherExamId;
	}

	public void setAnotherExamId(int anotherExamId) {
		this.anotherExamId = anotherExamId;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	@Override
	public String toString() {
		return "ID = " + String.valueOf(getId());
	}
}
