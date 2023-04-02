package queez.metier;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import queez.dao.Quiz;

@Stateful
@TransactionManagement(value=TransactionManagementType.BEAN)
public class QuizService extends AbstractRemoteService<Quiz>{
	
	
	
	public QuizService() {
		super(Quiz.class);
		
	}

	
	
	@Override
	protected EntityManager getEntityManager() {
		
		return this.em;
	}
	
	
}
