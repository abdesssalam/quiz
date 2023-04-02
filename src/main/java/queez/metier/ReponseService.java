package queez.metier;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import queez.dao.Reponse;

@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class ReponseService  extends AbstractRemoteService<Reponse> {

	public ReponseService() {
		super(Reponse.class);
		
	}

	@Override
	protected EntityManager getEntityManager() {
		
		return this.em;
	}
	
	

}
