package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import repositories.ConnectRepository;
import repositories.QuestionRepository;
import ui.ConnectUI;
import ui.QuestionUI;

@Entity
@Table(name = "connect")
@PrimaryKeyJoinColumn(name = "question_id")
public class Connect extends Question {

	@Column(name = "mark")
	private int mark;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "connect")
	private List<ConnectPair> connectPairs;

	public int getMark() {
		return mark;
	}

	public Integer getTotalQuestionMark() {
		return connectPairs.size() * mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public List<ConnectPair> getConnectPairs() {
		return connectPairs;
	}

	public void setConnectPairs(List<ConnectPair> connectPairs) {
		this.connectPairs = connectPairs;
	}

	public QuestionUI getQuestionUI() {
		return ConnectUI.getInstance();
	}

	public QuestionRepository getQuestionRepository() {
		return ConnectRepository.getInstance();
	}
}
