package queez.metier;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;

import queez.dao.Participation;

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

	
}
