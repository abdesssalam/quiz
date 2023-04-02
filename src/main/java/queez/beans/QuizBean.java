package queez.beans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import queez.dao.Poseur;
import queez.dao.Quiz;
import queez.dao.User;
import queez.helper.CustomHelper;
import queez.metier.QuizService;
import queez.metier.UserService;

@ManagedBean(name="quizBean")
@RequestScoped
public class QuizBean {
	
	private Quiz quiz=new Quiz();
	
	@EJB
	QuizService quizService;
	@EJB
	UserService userService;
	public QuizBean() {}
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public void Save() {
		System.out.println("start save quiz");
		System.out.println(quiz.getTheme());
		//get poseur
		User p=userService.find(25);
		Poseur poseur=CustomHelper.getInstance().toPoseur(p);
		this.quiz.setPoseur(poseur);
		quizService.ajouter(quiz);
		System.out.println(poseur.getNom());
		
		System.out.println("savee");
	}
	
}
