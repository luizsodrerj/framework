package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Contato;

@Stateless
@SuppressWarnings("unchecked")
public class ContatoService {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void addContato(Contato contato) {
		entityManager.persist(contato);
	}
	
	public List<Contato> getContatos() {
		return entityManager.createNamedQuery("Contato.findAll")
					 		.getResultList();
	}
	
	
}





