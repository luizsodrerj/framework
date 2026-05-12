package bijus.service;

import java.util.List;

import framework.persistence.jpa.PersistenceServiceUtil;
import javax.persistence.EntityManager;


public abstract class BaseService {

	protected abstract EntityManager getEntityManager();


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
