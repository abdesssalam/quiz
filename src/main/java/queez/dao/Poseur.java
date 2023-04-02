package queez.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("poseur")
public class Poseur extends User   implements Serializable{


	private static final long serialVersionUID = 1L;
	

	
	@OneToMany(fetch = FetchType.LAZY,targetEntity = Quiz.class,mappedBy = "poseur")
	private List<Quiz> quizes;
	
	@OneToMany(fetch = FetchType.LAZY,targetEntity = Quiz.class,mappedBy = "poseur")
	private List<Question> questions;


	public List<Quiz> getQuizes() {
		return quizes;
	}



	public void setQuizes(List<Quiz> quizes) {
		this.quizes = quizes;
	}



	public List<Question> getQuestions() {
		return questions;
	}



	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	
	
}
