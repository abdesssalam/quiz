package queez.beans;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import queez.dao.Apprenant;
import queez.dao.Participation;
import queez.dao.ParticipationPK;
import queez.dao.Question;
import queez.dao.Quiz;
import queez.dao.Reponse;
import queez.dao.User;
import queez.helper.CustomHelper;
import queez.metier.ParticipationService;
import queez.metier.QuizService;
import queez.metier.ReponseService;
import queez.metier.UserService;

@ManagedBean(name="participationBean",eager = true)

@ViewScoped
public class ParticipationBean {

	private Participation participation=new Participation();
	private List<Question> listQuestions;
	private Quiz quiz;
	private int currentIndex;
	private boolean isComplted;
	private int checkedReponse;
	private int score;
	private int quizID;
	private Question currentQuestion;
	private List<Reponse> currentReponses;
	private Apprenant apprenant;
	
	@Inject
    private HttpServletRequest request;
	
	@EJB
	ReponseService reponseService;
	
	@EJB
	ParticipationService participationService;
	
	@EJB
	QuizService quizService;
	//permanents
	@EJB
	UserService userService;
	@PostConstruct
	public void init() {
		
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User u=(User)session.getAttribute("user");
		if(u==null || u.getUserType().equals("poseur")) {
			 try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.quizID=Integer.parseInt(request.getParameter("quizID"));
		quiz=quizService.find(quizID);
		listQuestions=quiz.getQuestions();
		apprenant=CustomHelper.getInstance().toApprenant(u);
		setCurrentQuestion();
		setCurrentReponses();
		
	}
	

	public Participation getParticipation() {
		
		return participation;
	}

	public void setParticipation(Participation participation) {
		this.participation = participation;
	}



	

	public List<Question> getListQuestions() {
		return listQuestions;
	}

	public int getQuizID() {
		return quizID;
	}
	
	

	public void setQuizID(int quizID) {
		this.quizID = quizID;
	}

	public void setListQuestions(List<Question> listQuestions) {
		
		this.listQuestions = listQuestions;
	}


	public Quiz getQuiz() {
		return quiz;
	}


	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}


	public int getCurrentIndex() {
		return currentIndex;
	}


	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}


	public boolean isComplted() {
		return isComplted;
	}


	public void setComplted(boolean isComplted) {
		this.isComplted = isComplted;
	}


	public int getCheckedReponse() {
		return checkedReponse;
	}


	public void setCheckedReponse(int checkedReponse) {
		this.checkedReponse = checkedReponse;
	}
	
	public void checkReponse() throws IOException {
		Reponse checkedreponse = reponseService.find(checkedReponse);
		if(checkedreponse.isEtat()) {
			score=currentQuestion.getNote();
		}
		currentIndex++;
		if(currentIndex<quiz.getQuestions().size()) {
			this.currentQuestion = quiz.getQuestions().get(currentIndex);
			this.currentReponses = this.currentQuestion.getReponses();
		}else {
			//save participation in db
			
			Apprenant ap=userService.getApprenet(apprenant.getId());
			Participation previus = participationService.getParticipationIfExists(quiz, ap);
			
			participation.setApprenant(ap);
			participation.setQuiz(quiz);
			participation.setNote(score);
			long millis=System.currentTimeMillis();  
		    java.sql.Date date=new java.sql.Date(millis);  
			participation.setDate(date);
			
			if(previus==null) {
				participationService.ajouter(participation);
				ap.getParticipations().add(participation);
			}else {
				previus.setNote(score);
				previus.setDate(date);
				participationService.modifier(previus);
			}
			int idp=participationService.getParticipationIfExists(quiz, ap).getId();
			 
			 FacesContext.getCurrentInstance().getExternalContext().redirect("result.jsf?IDp="+idp);
		}
	}
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void fetchQuestions() {
		
	}

	public Question getCurrentQuestion() {
		
		return  currentQuestion;
	}

	public void setCurrentQuestion() {
		this.currentQuestion = quiz.getQuestions().get(currentIndex);
	}

	public List<Reponse> getCurrentReponses() {
		return currentReponses;
		
	}

	public void setCurrentReponses() {
		this.currentReponses = getCurrentQuestion().getReponses();
	}


	
}
