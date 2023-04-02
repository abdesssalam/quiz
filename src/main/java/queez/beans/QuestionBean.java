package queez.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.inject.Inject;


import queez.dao.Poseur;
import queez.dao.Question;
import queez.dao.User;
import queez.helper.CustomHelper;
import queez.metier.QuestionService;
import queez.metier.UserService;

@ManagedBean(name="questionBean",eager = true )
@RequestScoped
public class QuestionBean {
	private Question question=new Question();
	
	List<Question> questions;
	
	@EJB
	QuestionService questionService;
	
	@Resource
	@EJB
	UserService userService;
	
	public QuestionBean() {
		questions=new ArrayList<>();
		
	}
	//getters and setters
	
	public Question getQuestion() {
		return question;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	@Inject
	@PostConstruct
	public void init() {
		questions=userService.getPoseurQuestion(25);
		
	}
	public void save() {
		User p=userService.find(25);
		Poseur poseur=CustomHelper.getInstance().toPoseur(p);
		this.question.setPoseur(poseur);
		questionService.ajouter(question);
	}
	
	public String addReponses() {
		
//		HttpSession ses=FacesContext.getCurrentInstance().getExternalContext().
		return "reponses.xhtml?faces-redirect=true";
	}
}
