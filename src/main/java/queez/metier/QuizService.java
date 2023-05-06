package queez.metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import queez.dao.Poseur;
import queez.dao.Question;
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
	
	public List<Quiz> getQuizesBy(int id){
    	CriteriaBuilder cb=getEntityManager().getCriteriaBuilder();
    	CriteriaQuery cq = cb.createQuery();
    	Root<Quiz> root=cq.from(Quiz.class);
    	cq.select(root).where(cb.equal(root.get("poseur"), id));
        return getEntityManager().createQuery(cq).getResultList();
	}
	
	public void addQuestion(int quizID,Question question) {
		Quiz currentQuiz = this.find(quizID);
		currentQuiz.getQuestions().add(question);
		em.getTransaction().begin();
		em.merge(currentQuiz);
		em.getTransaction().commit();
		System.out.println("commit");
	}
	
	public void removeQuestion(int quizID,Question question) {
		Quiz currentQuiz = this.find(quizID);
		
		currentQuiz.getQuestions().remove(question);
		em.getTransaction().begin();
		em.merge(currentQuiz);
		em.getTransaction().commit();
		System.out.println("commit remove");
	}
	
	public List<Question> questions(int id){
		Query q=em.createQuery("select q from Question q where q.id in (select qq.id from Question qq join qq.quizes quiz where quiz.id = :qid)");
		q.setParameter("qid", id);
		return q.getResultList();
	}
	
	public List<Question> getIncludedQuestions(int id,Poseur p) {
		Query query=em.createQuery("SELECT q FROM Question q WHERE q.id IN (SELECT qq.id FROM Question qq JOIN qq.quizes quiz WHERE quiz.id = :qid ) and q.poseur = :p")
				.setParameter("qid", id)
				.setParameter("p", p);
		
		return query.getResultList();
	}
	
	public List<Question> getExcludedQuestions(int id,Poseur p) {
		
		Query query=em.createQuery("select qq from Question qq WHERE NOT EXISTS (select q from Question q join q.quizes quiz where quiz.id = :qid and q.id=qq.id) and qq.poseur = :p ")
					.setParameter("qid", id).setParameter("p", p);
		
		return query.getResultList();
	}
	
}
