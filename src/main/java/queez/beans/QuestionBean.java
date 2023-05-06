package queez.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import queez.dao.Poseur;
import queez.dao.Question;
import queez.dao.User;
import queez.helper.CustomHelper;
import queez.metier.QuestionService;
import queez.metier.QuizService;
import queez.metier.UserService;

@ManagedBean(name="questionBean",eager = true )
@RequestScoped
public class QuestionBean {
	private Question question=new Question();
	
	@ManagedProperty(value = "#{param.quizID}")
	private int quizID;
	
	private int quesionID;
	private Poseur poseur;
	List<Question> questions;
	
	@EJB
	QuestionService questionService;
	
	@Resource
	@EJB
	UserService userService;
	@EJB
	QuizService quizService;
	public QuestionBean() {
		questions=new ArrayList<>();
		
	}
	
	@PostConstruct
	public void init() {
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User u=(User)session.getAttribute("user");
		if(u==null || !u.getUserType().equals("poseur")) {
			
			 try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		poseur=CustomHelper.getInstance().toPoseur(u);
	}
	//getters and setters
	public int getQuizID() {
		return quizID;
	}
	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}
	public Question getQuestion() {
		return question;
	}

	public List<Question> getQuestions() {
		
		return questionService.getQuestionsBy(poseur.getId());
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	

	
	public int getQuesionID() {
		return quesionID;
	}
	public void setQuesionID(int quesionID) {
		this.quesionID = quesionID;
	}
	public void save() {
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User u=(User)session.getAttribute("user");
		Poseur poseur=CustomHelper.getInstance().toPoseur(u);
		this.question.setPoseur(poseur);
		questionService.ajouter(question);
	}
	
	public String addReponses() {
		
		return "reponses.xhtml?faces-redirect=true";
	}
	
	//for affect.xhtml

	public List<Question> getExcluded(){
		System.out.println("getExcluded : "+quizID);
		return quizService.getExcludedQuestions(quizID,poseur);
		
	}
	
	public List<Question> getIncluded(){
		System.out.println("getIncluded : "+quizID);
		return quizService.questions(quizID);
	}
	
	public String affecter(Question rquestion,int id) {
		System.out.println("\n\n\n\n start add \n\n\n\n");
		System.out.println(rquestion.getId()+"  "+id);
			quizService.addQuestion(id, rquestion);
			return "affecter.xhtml?quizID="+id+"&faces-redirect=true";
	}
	
	public void removeQuestion(Question qq,int id) {
		System.out.println("\n\n\n\n start remove \n\n\n\n");
		System.out.println(qq.getId()+"  "+id);
		quizService.removeQuestion(id, qq);
//		return "affecteraffecter.xhtml?quizID="+id+"&faces-redirect=true";
}
	public void test() {
		System.out.println("\n\n\n\n start testt \n\n\n\n");
		//System.out.println(id);
	}
}
