package queez.metier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBContext {
	EntityManagerFactory emf= Persistence.createEntityManagerFactory("queez");
	EntityManager em=emf.createEntityManager();
	
	
	//Singleton pattern
	private static DBContext context=null;
	private DBContext() {
		
	}
	
	public static DBContext getInstance() {
		if(context==null) {
			context=new DBContext();
		}
		return context;
	}
	
}
