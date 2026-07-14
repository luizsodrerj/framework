package bijus.service;

import bijus.entity.Peca;
import bijus.repository.ProdutoRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ShoppingCartService extends BaseService {

    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    private ProdutoRepository produtoRepository;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}








