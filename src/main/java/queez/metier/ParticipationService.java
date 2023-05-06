package queez.metier;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import queez.dao.Apprenant;
import queez.dao.Participation;
import queez.dao.Quiz;

@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class ParticipationService extends AbstractRemoteService<Participation>{

	
	public ParticipationService() {
		super(Participation.class);
		
	}

	@Override
	protected EntityManager getEntityManager() {
		
		return this.em;
	}

	public Participation getParticipationIfExists(Quiz idq, Apprenant ida) {
	    
	    try {
	    	Query q = em.createQuery("SELECT p FROM Participation p WHERE p.apprenant = :ida AND p.quiz = :idq");
		    q.setParameter("ida", ida);
		    q.setParameter("idq", idq);
	        return (Participation) q.getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}
	public List<Participation> getAllParticipationsByApprenant(Apprenant apprenant) {
		Query q=em.createQuery("SELECT p FROM Participation p WHERE p.apprenant = :apprenant");
		
		q.setParameter("apprenant", apprenant);
		return q.getResultList();
        
    }


}
