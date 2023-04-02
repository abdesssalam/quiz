package queez.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import queez.dao.Participation;
import queez.metier.ParticipationService;

@ManagedBean(name="participationBean",eager = true)

@SessionScoped
public class ParticipationBean {

	private Participation participation;
	
	@EJB
	ParticipationService participationService;
	
	public Participation getParticipation() {
		return participation;
	}

	public void setParticipation(Participation participation) {
		this.participation = participation;
	}
	
}
