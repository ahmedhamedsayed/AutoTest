package questionPackage.question.connect.connectpair;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import questionPackage.question.connect.Connect;

@Entity
@Table(name = "connectpair")
public class ConnectPair {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "answer")
	private String answer;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Connect connect;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Connect getConnect() {
		return connect;
	}

	public void setConnect(Connect connect) {
		this.connect = connect;
	}
}
