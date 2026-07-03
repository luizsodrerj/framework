package agenda.repo;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import agenda.entity.Contato;
import framework.persistence.jpa.PersistenceServiceUtil;

public class AgendaRepository {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();
	
	

	
	public void update(Contato contato) {
		try {
			persistence.beginTransaction();
			
			Contato persisContato = persistence.findObject(Contato.class,contato.getId());
			PropertyUtils.copyProperties(persisContato, contato);
			
			persistence.merge(persisContato);
			persistence.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			persistence.close();
		}
	}

	public <T>T find(Class<?>entityClass, Object id) {
		try {
			return (T)persistence.findObject(entityClass, id);
			
		} finally {
			persistence.close();
		}
	}
	
	public void persist(Object entity) {
		try {
			persistence.beginTransaction();
			persistence.persist(entity);
			persistence.commit();
			
		} finally {
			persistence.close();
		}
	}

	public <T> T getSingleResultByNamedQuery(String namedQuery, Object[]params) {
		try {
			List<T>resultList = persistence.findByNamedQuery(namedQuery, params);
			
			return !resultList.isEmpty() ?
					resultList.get(0) :
					null;	
		} finally {
			persistence.close();
		}
	}

	
	public <T> List<T> findByNamedQuery(String namedQuery, Object[]params) {
		try {
			return persistence.findByNamedQuery(namedQuery, params);
			
		} finally {
			persistence.close();
		}
	}
	
	public List<Contato> getAll() {
		try {
			return persistence.findAll(Contato.class, "contato");
			
		} finally {
			persistence.close();
		}
	}
	
}
