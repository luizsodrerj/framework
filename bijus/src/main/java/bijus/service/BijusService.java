package bijus.service;

import java.util.ArrayList;
import java.util.List;

import bijus.entity.TipoPeca;
import org.apache.commons.beanutils.BeanUtils;

import bijus.entity.Bijuteria;
import bijus.entity.Joia;
import bijus.entity.Peca;
import framework.persistence.jpa.PersistenceServiceUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BijusService {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private EstoqueService estoqueService;


	private PersistenceServiceUtil getPersistence() {
		return new PersistenceServiceUtil(entityManager);
	}


	public List<TipoPeca> findAllPecas() {
		return getPersistence().findAll(TipoPeca.class,null);
	}

	public Peca getPeca(Long id) {
		return entityManager.find(Peca.class, id);
	}

	public void merge(Peca peca) {
		try {
			PersistenceServiceUtil persistence = getPersistence();
			Peca persistedPeca = persistence.findObject(Peca.class, peca.getId());

			BeanUtils.copyProperties(persistedPeca, peca);
			persistence.merge(persistedPeca);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void persistPeca(Peca peca) {
		getPersistence().persist(peca);
	}

	public List<Peca> getPecas() {
		return getPersistence().findAll(Peca.class, null);
	}
	
	public List<Joia> getJoias() {
		return loadJoias();
	}

	public List<Peca> getSemiJoias() {
		return loadSemiJoias();
	}
	
	public List<Bijuteria> getBijus() {
		return (List<Bijuteria>)loadBijus();
	}
	
	private List<? extends Peca> loadBijus() {
		List<Peca>pecas = estoqueService.getBijus();
		List<Bijuteria>bijus = new ArrayList<Bijuteria>();

		for (Peca peca : pecas) {
			Bijuteria biju = new Bijuteria();
			populatePeca(bijus, biju, peca);
		}
		return bijus;
	}

	private List<Joia> loadJoias() {
		List<Peca>pecas = estoqueService.getJoias();
		List<Joia>joias = new ArrayList<>();

		for (Peca peca : pecas) {
			Joia joia = new Joia();
			populatePeca(joias, joia, peca);
		}
		return joias;
	}
	
	private List<Peca> loadSemiJoias() {
		return estoqueService.getSemiJoias();
	}

	private void populatePeca(
					List list, 
					Peca destino, 
					Peca origem
				) {
		destino.setDescricao(origem.getDescricao());
		destino.setImagem(origem.getImagem());
		destino.setPreco(origem.getPreco());
		destino.setId(origem.getId());
		list.add(destino);
	}
	
	
}






