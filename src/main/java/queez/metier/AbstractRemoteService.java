package queez.metier;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

@Local
public abstract class AbstractRemoteService<T> {
	private final Class<T> entityClass;
	EntityManagerFactory emf= Persistence.createEntityManagerFactory("queez");
	EntityManager em=emf.createEntityManager();
    public AbstractRemoteService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();

    public String ajouter(T entity) {
    	getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
		return "added";
    }

    public void modifier(T entity) {
        getEntityManager().merge(entity);
    }

    public void supprimer(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

}
