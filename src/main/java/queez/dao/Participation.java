package queez.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="participation")

public class Participation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( updatable = false, nullable = false)
	int id;
	
	

	@ManyToOne
	@JoinColumn(name="apprenant",referencedColumnName = "id")
	Apprenant apprenant;

	@ManyToOne
	@JoinColumn(name="quiz",referencedColumnName = "id")
	Quiz quiz;
	
	float note;
	
	Date date;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
