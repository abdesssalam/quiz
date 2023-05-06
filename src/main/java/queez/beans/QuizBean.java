package queez.beans;


import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import queez.dao.Poseur;
import queez.dao.Question;
import queez.dao.Quiz;
import queez.dao.User;
import queez.helper.CustomHelper;
import queez.metier.QuizService;
import queez.metier.UserService;

@ManagedBean(name="quizBean")
@RequestScoped
public class QuizBean {
	
	private Quiz quiz=new Quiz();
	private Poseur poseur;
	@EJB
	QuizService quizService;
	@EJB
	UserService userService;
	
	@PostConstruct
	public void init() throws IOException {
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User u=(User)session.getAttribute("user");
		if(u==null) {
			 FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
		}
		poseur=CustomHelper.getInstance().toPoseur(u);
	}
	public QuizBean() {}
	
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public List<Quiz> getListQuiz() {
		
		return quizService.findAll();
	}
	
	public List<Quiz> getPoseurQuiz() {
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User u=(User)session.getAttribute("user");
		System.out.println("poseur ======>"+poseur.getId());
		return quizService.getQuizesBy(u.getId());
	}
	
	
	

	

	

	public String Save() {
		
		
		this.quiz.setPoseur(poseur);
		quizService.ajouter(quiz);
		return "quiz.xhtml?faces-redirect=true";
	}
	
	
}
