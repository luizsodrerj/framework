package bijus.service;

import java.util.List;

import bijus.entity.Peca;
import framework.persistence.jpa.PersistenceServiceUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstoqueService {

	@PersistenceContext
	private EntityManager entityManager;

	
	public List<Peca> getBijus() {
		return new PersistenceServiceUtil(entityManager).findByNamedQuery("Peca.bijuterias", null);
	}

	public List<Peca> getSemiJoias() {
		return new PersistenceServiceUtil(entityManager).findByNamedQuery("Peca.semijoias", null);
	}

	public List<Peca> getJoias() {
		return new PersistenceServiceUtil(entityManager).findByNamedQuery("Peca.joias", null);
	}
	
}






