package queez.helper;

import queez.dao.Apprenant;
import queez.dao.Poseur;
import queez.dao.User;

public class CustomHelper {
	
	private static CustomHelper helper=null;
	private CustomHelper() {
		
	}
	
	public static CustomHelper getInstance() {
		if(helper==null) {
			helper=new CustomHelper();
			
		}
			return helper;
		
	}
	
	public  Poseur toPoseur(User user) {
		
		Poseur p=new Poseur();
		p.setId(user.getId());
		p.setLogin(user.getLogin());
		p.setNom(user.getNom());
		p.setPrenom(user.getPrenom());
		p.setPassword(user.getPassword());
		
		return p;
	}
	
	public  Apprenant toApprenant(User user) {
			
			Apprenant p=new Apprenant();
			p.setId(user.getId());
			p.setLogin(user.getLogin());
			p.setNom(user.getNom());
			p.setPrenom(user.getPrenom());
			p.setPassword(user.getPassword());
			
			return p;
		}
}
