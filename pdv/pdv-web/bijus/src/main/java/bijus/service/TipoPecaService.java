package bijus.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bijus.entity.TipoPeca;

import java.util.List;

@Stateless
public class TipoPecaService extends BaseService {

	@PersistenceContext
	private EntityManager entityManager;

	public void updateTipoPeca(TipoPeca tipo, Integer id) {
		TipoPeca tp = findObject(TipoPeca.class,id);
		tp.setTipo(tipo.getTipo());
		merge(tp);
	}


	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}