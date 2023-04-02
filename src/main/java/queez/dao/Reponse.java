package queez.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.wildfly.common.annotation.Nullable;

@Entity
@Table(name="reponses")
public class Reponse {
	@Id
	int id;
	
	String enocne;
	boolean etat;
	
	@Nullable
	String justification; 
	
	@ManyToOne
	@JoinColumn(name="question")
	Question question;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnocne() {
		return enocne;
	}

	public void setEnocne(String enocne) {
		this.enocne = enocne;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
}
