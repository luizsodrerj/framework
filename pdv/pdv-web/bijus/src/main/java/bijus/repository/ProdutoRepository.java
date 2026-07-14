package bijus.repository;

import bijus.config.Config;
import bijus.entity.Peca;
import bijus.service.BaseService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProdutoRepository extends BaseService {

    @PersistenceContext
    private EntityManager em;

    public List<Peca> pesquisarPorTermo(String termo) {
        return em.createQuery(
             "SELECT p       " +
                "FROM   Peca p    " +
                "WHERE  LOWER(p.descricao) LIKE LOWER(:termo) " +
                "ORDER BY p.descricao",
                Peca.class)
                .setParameter("termo", "%" + termo + "%")
                .setMaxResults(Config.MAX_RESULTS)
                .getResultList();
    }

    public List<Peca> listarTodos() {
        return findAll(Peca.class,"descricao",Config.MAX_RESULTS);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
