package queez.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="quizes")

public class Quiz {
	
	@Id
	int id;
	String theme;
	int note;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="poseur")
	Poseur poseur;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name="quizquestion",
		joinColumns = @JoinColumn(name="quiz",referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name="question",referencedColumnName = "id"))
	List<Question> questions;
	
	@OneToMany(mappedBy = "quiz")
	List<Participation> participations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Poseur getPoseur() {
		return poseur;
	}

	public void setPoseur(Poseur poseur) {
		this.poseur = poseur;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}


	
	
}
