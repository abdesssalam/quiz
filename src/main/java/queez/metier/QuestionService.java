package queez.metier;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import queez.dao.Question;
import queez.dao.Reponse;

@Stateful
@TransactionManagement(TransactionManagementType.BEAN)

@Path("questions")
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
	
	public List<Question> getQuestionsBy(int id){
    	CriteriaBuilder cb=getEntityManager().getCriteriaBuilder();
    	CriteriaQuery cq = cb.createQuery();
    	Root<Question> root=cq.from(Question.class);
    	cq.select(root).where(cb.equal(root.get("poseur"), id));
        return getEntityManager().createQuery(cq).getResultList();
	}
	
	@GET
	@Path("all")
	@Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	public List<Question> getALL() {
		return super.findAll();
	}
	
	
}
