package queez.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;
import javax.persistence.Column;
import javax.servlet.http.HttpSession;

import queez.dao.Apprenant;
import queez.dao.Poseur;
import queez.dao.User;

import queez.metier.UserService;


@ManagedBean(name = "userBean",eager = true)
@RequestScoped
public class UserBean  {
	


	private boolean isPoseur;
	
	@EJB
	 UserService userSerivce;
	
	
	private User user = new User();
	

	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserBean() {};

	

	public boolean isPoseur() {
		return isPoseur;
	}

	public void setPoseur(boolean isPoseur) {
		this.isPoseur = isPoseur;
	}

	public String inscrire()  {
//		validating email
//		Hash hash=Password.hash(user.getPassword()).withBcrypt();
//		user.setPassword(hash.getResult());
		try {

		if(isPoseur) {
			
			Poseur ps=new Poseur();
			ps.setNom(user.getNom());
			ps.setPrenom(user.getPrenom());
			ps.setLogin(user.getLogin());
			ps.setPassword(user.getPassword());
			userSerivce.ajouter(ps);
		}else {
			Apprenant ps=new Apprenant();
			ps.setNom(user.getNom());
			ps.setPrenom(user.getPrenom());
			ps.setLogin(user.getLogin());
			ps.setPassword(user.getPassword());
			userSerivce.ajouter(ps);
			
		}
		}catch (Exception e) {
			System.out.println("==================erroor==============");
			e.printStackTrace();
		}
		return "login.xhtml?faces-redirect=true";	
	}
	
	public String login() {
		User FoundUser=userSerivce.login(user.getLogin(), user.getPassword());
		
		if(FoundUser==null) {
			return "login.xhtml?faces-redirect=true";	
		}else {
			HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("user", FoundUser);
			//depend on type of user
			return "index.xhtml?faces-redirect=true";
		}
		
	}
	
	
}
