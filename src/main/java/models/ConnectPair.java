package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "connectpair")
public class ConnectPair {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "first_description")
	private String firstDescription;

	@Column(name = "second_description")
	private String secondDescription;

	@Column(name = "answer")
	private Integer answer;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Connect connect;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstDescription() {
		return firstDescription;
	}

	public void setFirstDescription(String firstDescription) {
		this.firstDescription = firstDescription;
	}

	public String getSecondDescription() {
		return secondDescription;
	}

	public void setSecondDescription(String secondDescription) {
		this.secondDescription = secondDescription;
	}

	public Integer getAnswer() {
		return answer;
	}

	public void setAnswer(Integer answer) {
		this.answer = answer;
	}

	public Connect getConnect() {
		return connect;
	}

	public void setConnect(Connect connect) {
		this.connect = connect;
	}
}
