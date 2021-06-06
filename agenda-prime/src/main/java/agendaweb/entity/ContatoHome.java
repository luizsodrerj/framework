package agendaweb.entity;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;

import agendaweb.persistence.PersistenceService;

@ManagedBean(name = "contatoHome")
public class ContatoHome implements Serializable {

	private static final long serialVersionUID = 6015939349898971498L;

	private Contato selectedContato = new Contato();
	
	@ManagedProperty(value = "#{contatoBean}")
	private Contato contatoBean;

	
	
	public String delete() {
		EntityManager em = null;
		try {
			em = getEntityManager();
			Contato contato = em.find(
								Contato.class,
								selectedContato.getId()
							  );
			em.getTransaction().begin();
			em.remove(contato);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return "/index.xhtml";
	}

	public void merge() {
		EntityManager em = null;
		try {
			Contato contato = getById();
			PropertyUtils.copyProperties(contato, contatoBean);

			em = getEntityManager();
			em.getTransaction().begin();
			em.merge(contato);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			em.close();
		}
	}

	private Contato getById() {
		EntityManager em = null;
		try {
			em = getEntityManager();

			contatoBean.setId(selectedContato.getId());

			return em.find(Contato.class,contatoBean.getId());
			
		} finally {
			em.close();
		}
	}

	public void save() {
		EntityManager em = null;
		
		try {
			contatoBean.setId(null);
			
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(contatoBean);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void onItemSelect(SelectEvent event) throws Exception {
        Contato contato = (Contato) event.getObject();
        contatoBean.setId(contato.getId());
        contatoBean = getById();
    }	
	
	public List<Contato> autocompleteContato(String suggest) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			String nome = !StringUtils.isEmpty(suggest) ? suggest : "";
			Query query = em.createNamedQuery("Contato.findByContato");
			query.setParameter(1, "%" + nome.toUpperCase() + "%");
			
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public void setContatoBean(Contato contatoBean) {
		this.contatoBean = contatoBean;
	}

	public Contato getContatoBean() {
		return contatoBean;
	}
	
	private EntityManager getEntityManager() {
		PersistenceService persistence = new PersistenceService();
		persistence.connect();
		
		return persistence.geEntityManager();
	}

	public Contato getSelectedContato() {
		return selectedContato;
	}

	public void setSelectedContato(Contato selectedContato) {
		this.selectedContato = selectedContato;
	}

	
}





