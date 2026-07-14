package bijus.controller;

import bijus.entity.Peca;
import bijus.service.ProdutoService;
import util.FacesUtil;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ShopPortifolioController {

    private String termoPesquisa;
    private List<Peca> produtos = new ArrayList<>();
    @EJB
    private ProdutoService service;

    public String redirectToHome() {
        produtos = service.pesquisar(null);
        FacesUtil.getSession().setAttribute("imageList",produtos);

        return "/shop/home/home-shop.xhtml";
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public List<Peca> getProdutos() {
        return produtos;
    }
}
