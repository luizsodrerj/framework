package bijus.service;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import bijus.entity.Bijuteria;
import bijus.entity.Joia;
import bijus.entity.Peca;
import framework.persistence.jpa.PersistenceServiceUtil;

public class BijusService extends BaseService {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();

	private EstoqueService estoqueService = new EstoqueService();
	
	private List<Bijuteria>bijus;
	private List<Peca>semijoias;
	private List<Joia>joias;

	

	public void merge(Peca peca) {
		try {
			persistence.beginTransaction();
			
			Peca persistedPeca = persistence.findObject(Peca.class,peca.getId());
			
			BeanUtils.copyProperties(persistedPeca, peca);

			persistence.merge(persistedPeca);
			persistence.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			persistence.rollbackTransaction();
			throw new RuntimeException(e);
		} finally {
			persistence.close();
		}
	}
	
	public void persistPeca(Peca peca) {
		try {
			persistence.beginTransaction();
			persistence.persist(peca);
			persistence.commit();
		} finally {
			persistence.close();
		}
	}
	
	 
	public List<Peca> getPecas() {
		try {
			return persistence.findAll(Peca.class, null);
		} finally {
			persistence.close();
		}
	}
	
	public List<Joia> getJoias() {
		if (joias.isEmpty()) {
			loadJoias();
		}
		return joias;
	}

	public List<Peca> getSemiJoias() {
		if (semijoias.isEmpty()) {
			loadSemiJoias();;
		}
		return semijoias;
	}
	
	public List<Bijuteria> getBijus() {
		if (bijus.isEmpty()) {
			loadBijus();
		}
		return bijus;
	}
	
	private void loadBijus() {
		List<Peca>pecas = estoqueService.getBijus();
		
		for (Peca peca : pecas) {
			Bijuteria biju = new Bijuteria();
			populatePeca(bijus, biju, peca);
		}
	}

	private void loadSemiJoias() {
		semijoias = estoqueService.getSemiJoias();
	}

	private void loadJoias() {
		List<Peca>pecas = estoqueService.getJoias();
		
		for (Peca peca : pecas) {
			Joia joia = new Joia();
			populatePeca(joias, joia, peca);
		}
	}
	
	private void populatePeca(
					List list, 
					Peca destino, 
					Peca origem
				) {
		destino.setDescricao(origem.getDescricao());
		destino.setImagem(origem.getImagem());
		destino.setPreco(origem.getPreco());
		list.add(destino);
	}
	
	
}






