package queez.beans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import queez.dao.Participation;
import queez.dao.User;
import queez.metier.ParticipationService;

@ManagedBean
@ViewScoped
public class ResultBean {
	private Participation participation;
    
	private int idP;
	
	@EJB
	ParticipationService participationService;
	
	@Inject
    private HttpServletRequest request;
	
	 @PostConstruct
	 public void initialize(){
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
		 this.idP = Integer.parseInt(request.getParameter("IDp"));
		 participation = participationService.find(idP);
		 System.out.println("\n\n\n\n iniiiiiiiiiit \n\n\n\n\n");
//		 try {
//	            
//	            System.out.println("idP: " + idP);
//	            participation = participationService.find(idP);
//	            if (participation == null) {
//	                System.out.println("participation is null");
//	            } else {
//	                System.out.println("participation found: " + participation);
//	            }
//	        } catch (Exception e) {
//	            System.out.println("error in init: " + e.getMessage());
//	        }
	 }
	public Participation getParticipation() {
		return participation;
	}

	public void setParticipation(Participation participation) {
		this.participation = participation;
	}
	public int getIdP() {
		return idP;
	}
	public void setIdP(int idP) {
		this.idP = idP;
	}
	
	
}
