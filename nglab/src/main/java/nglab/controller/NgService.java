package nglab.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nglab.entity.Contato;

@Repository
@Transactional(readOnly = false)
public class NgService {

	@PersistenceContext
	EntityManager entityManager;
	
	
	public List<Contato> findAllContatos() {
		return entityManager.createNamedQuery(Contato.Contato_findAll)
							.getResultList();
	}
	
}
