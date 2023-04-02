package queez.beans;



import javax.faces.event.ActionEvent;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import queez.dao.Question;
import queez.dao.Reponse;
import queez.metier.QuestionService;
import queez.metier.ReponseService;


@ManagedBean(name = "reponseBean",eager = true)

@RequestScoped
public class ReponseBean {
	
	
	 private Reponse reponse=new Reponse();
	 
	 
	 private int QuesID;
	 private Question question;
	 private List<Reponse> listReponses;
	 
	 @ManagedProperty(value="#{param.id}")
	 private int id;
	 
	 
	 @EJB
	 ReponseService reponseService;
	 
	 @EJB
	 QuestionService questionService;
	 
	 
	 //get set
	 
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Reponse getReponse() {
		return reponse;
	}


	public Question getQuestion() {
		return question;
	}

	public int getQuesID() {
		return QuesID;
	}


	public void setQuesID(int quesID) {
		QuesID = quesID;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setReponse(Reponse reponse) {
		this.reponse = reponse;
	}
	
	
	//events and actions
	
	public List<Reponse> getListReponses() {
		return listReponses;
	}


	public void setReponses(List<Reponse> reponses) {
		this.listReponses = reponses;
	}

	@Inject
	@PostConstruct
	public void init() {
//		FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
//		Object p1 = faceletContext.getAttribute("id");
		//get questions
		
		System.out.println("\n\n\n init reponses :=> id: "+id+"\n\n");
		this.listReponses=questionService.getReponses(id);
		System.out.println("\n\n\n\n size =: "+listReponses.size()+"\n\n\n\n");
		
	}

	
	public void save() {
		this.question=questionService.find(QuesID);
		reponse.setQuestion(question);	
		reponseService.ajouter(reponse);
	}
}
