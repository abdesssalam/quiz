package queez.metier;

import java.math.BigDecimal;

import javax.ejb.Startup;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.password4j.Password;

import queez.dao.User;


@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class UserService  extends AbstractRemoteService<User> {
	
	
	EntityManagerFactory emf= Persistence.createEntityManagerFactory("queez");
	EntityManager em=emf.createEntityManager();
	public UserService() {
		super(User.class);
		
	}
	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}
	 
	
	public User login(String email,String pass) {
		Query q;
		q=em.createQuery("SELECT COUNT(*) FROM User u where u.login = :l and u.password= :p");
		q.setParameter("l",email );
		q.setParameter("p", pass);
		long count=(long)q.getSingleResult();
		System.out.println("count "+count);
		
		if(count>0) {
			q=em.createQuery("SELECT u  FROM User u where u.login = :l and u.password= :p");
			q.setParameter("l",email );
			q.setParameter("p", pass);
			User u=(User)q.getSingleResult();
			return u;
		}else {
			return null;
		}
		
	}
	
}
