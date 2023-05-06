package queez.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {
	@Id
	int id;
	
	String enonce;
	
	int note;
	
	@ManyToMany(mappedBy = "questions")
	
	List<Quiz> quizes;
	
	@OneToMany(mappedBy = "question")
	List<Reponse> reponses;

	@ManyToOne
	@JoinColumn(name="poseur")
	Poseur poseur;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public List<Quiz> getQuizes() {
		return quizes;
	}

	public void setQuizes(List<Quiz> quizes) {
		this.quizes = quizes;
	}

	public List<Reponse> getReponses() {
		return reponses;
	}

	public void setReponses(List<Reponse> reponses) {
		this.reponses = reponses;
	}

	public Poseur getPoseur() {
		return poseur;
	}

	public void setPoseur(Poseur poseur) {
		this.poseur = poseur;
	}


	
	

}
