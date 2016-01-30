package questionPackage.question.choose.choice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import questionPackage.question.choose.Choose;

@Entity
@Table(name = "choice")
public class Choice {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Choose choose;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Choose getChoose() {
		return choose;
	}

	public void setChoose(Choose choose) {
		this.choose = choose;
	}

    @Override
    public String toString() {
        return "ID = " + String.valueOf(getId()) + "\nChoose ID = " + ((choose != null) ? String.valueOf(choose.getId()) : "Null") + "\nDescription = " + String.valueOf(getDescription()) + "\n";
    }
}
