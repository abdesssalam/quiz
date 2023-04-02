package queez.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="participation")

public class Participation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	ParticipationPK id;
	
	

	@ManyToOne
	@MapsId("apprenant_ID")
	@JoinColumn(name="apprenant",referencedColumnName = "id")
	Apprenant apprenant;

	@ManyToOne
	@MapsId("quiz_ID") //name of column in PK class
	@JoinColumn(name="quiz",referencedColumnName = "id")
	Quiz quiz;
	
	float note;
	
	Date date;

	public ParticipationPK getId() {
		return id;
	}

	public void setId(ParticipationPK id) {
		this.id = id;
	}

	public Apprenant getApprenant() {
		return apprenant;
	}

	public void setApprenant(Apprenant apprenant) {
		this.apprenant = apprenant;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
	
	
	
}
