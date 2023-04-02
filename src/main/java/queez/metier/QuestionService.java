package queez.metier;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import queez.dao.Question;
import queez.dao.Reponse;

@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class QuestionService extends AbstractRemoteService<Question> {
	
	public QuestionService() {
		super(Question.class);
		
	}

	@Override
	protected EntityManager getEntityManager() {
		
		return this.em;
	}
	
	public List<Reponse> getReponses(int id){
		CriteriaBuilder cb=getEntityManager().getCriteriaBuilder();
    	CriteriaQuery cq = cb.createQuery();
    	Root<Reponse> root=cq.from(Reponse.class);
    	cq.select(root).where(cb.equal(root.get("question"), id));
        return getEntityManager().createQuery(cq).getResultList();
	}

}
