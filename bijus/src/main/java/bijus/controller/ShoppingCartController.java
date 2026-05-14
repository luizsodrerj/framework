package bijus.controller;

import bijus.beans.PecaBean;
import bijus.entity.Peca;
import bijus.service.ShoppingCartService;
import util.FacesUtil;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean
@SessionScoped
public class ShoppingCartController {
    @EJB
    private ShoppingCartService service;
    @ManagedProperty("#{shopPortifolioController}")
    private ShopPortifolioController portifolioController;
    private List<PecaBean>carrinho = new ArrayList<>();
    private PecaBean bean = new PecaBean();


    public String prepareToAddToCart() {
        Long id = Long.valueOf(FacesUtil.getRequest().getParameter("id"));
        Peca peca = service.findObject(Peca.class,id);
        bean.setPeca(peca);

        FacesUtil.getSession().setAttribute(
            "imageList",
             new ArrayList<Peca>(Arrays.asList(new Peca[]{peca}))
        );
        return "/shop/cart/produto-detail.xhtml";
    }

    public String addProdutoToCart() {
        Peca peca = service.findObject(Peca.class,bean.getId());
        PecaBean pecaBean = new PecaBean();
        pecaBean.setQtd(this.bean.getQtd());
        pecaBean.setPeca(peca);
        carrinho.add(pecaBean);

        return portifolioController.redirectToHome();
    }

    public void setPortifolioController(ShopPortifolioController portifolioController) {
        this.portifolioController = portifolioController;
    }

    public List<PecaBean> getCarrinho() {
        return carrinho;
    }

    public PecaBean getBean() {
        return bean;
    }
}
