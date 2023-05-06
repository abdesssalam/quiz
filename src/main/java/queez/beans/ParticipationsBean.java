package queez.beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import queez.dao.Apprenant;
import queez.dao.Participation;
import queez.dao.User;
import queez.metier.ParticipationService;
import queez.metier.UserService;

@ManagedBean
@RequestScoped
public class ParticipationsBean {
	private Apprenant apprenant;
	private List<Participation> participations;
	@EJB
	ParticipationService participationService;
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
		//get user
		setApprenant(userService.getApprenet(u.getId()));
		participations=apprenant.getParticipations();
		
		
	}
	public Apprenant getApprenant() {
		return apprenant;
	}
	public void setApprenant(Apprenant apprenant) {
		this.apprenant = apprenant;
	}
	public List<Participation> getParticipations() {
		return participations;
	}
	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}
	
	public List<Participation> getParticipationsApprent() {
		return participationService.getAllParticipationsByApprenant(apprenant);
	}

}
