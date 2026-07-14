package bijus.service;

import bijus.entity.Peca;
import bijus.repository.ProdutoRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProdutoService {

    @EJB
    private ProdutoRepository repository;

    public List<Peca> pesquisar(String termo) {
        return  termo == null || termo.trim().isEmpty() ?
                repository.listarTodos() :
                repository.pesquisarPorTermo(termo);
    }
}





