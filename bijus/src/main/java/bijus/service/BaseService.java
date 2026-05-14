package bijus.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import framework.persistence.jpa.PersistenceServiceUtil;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public abstract class BaseService {

	protected abstract EntityManager getEntityManager();


	public <T> List<T> findAll(Class<T> type, String orderBy, int maxResults) {
		StringBuilder query = (new StringBuilder("select o from ")).append(type.getSimpleName()).append(" o ");
		orderBy = orderBy != null && orderBy.trim().length() > 0 ? " order by o." + orderBy : "";
		query.append(orderBy);
		return this.findByQuery(query.toString(), null, maxResults);
	}

	public <T> List<T> findByQuery(String query, Map<String,Object>params, int maxResults) {
		Query q = getEntityManager().createQuery(query);
		q.setMaxResults(maxResults);

		if (params != null && params.size() > 0) {
			Set<String>keys = params.keySet();

			for(String key: keys) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.getResultList();
	}


	public void persist(Object entity) {
		getPersistence().persist(entity);
	}

	public void merge(Object entity) {
		getPersistence().merge(entity);
	}

	public PersistenceServiceUtil getPersistence() {
		return new PersistenceServiceUtil(getEntityManager());
	}

	public <T> T findObject(Class<T> classe, Object id) {
		return getPersistence().findObject(classe,id);
	}

	public <T> List<T> findAll(Class<T> type, String orderBy) {
		return getPersistence().findAll(type, orderBy);
	}
	
}
