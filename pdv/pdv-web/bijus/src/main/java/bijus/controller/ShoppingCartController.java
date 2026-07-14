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
import java.util.stream.Collectors;

@ManagedBean
@SessionScoped
public class ShoppingCartController {
    @EJB
    private ShoppingCartService service;
    @ManagedProperty("#{shopPortifolioController}")
    private ShopPortifolioController portifolioController;
    private List<PecaBean>carrinho = new ArrayList<>();
    private PecaBean bean = new PecaBean();


    public String onClickBtShoppingCart() {
        List<Peca>list = carrinho.stream().map(
                bean -> bean.getPeca()).collect(
                Collectors.toList()
            );
        populateImagelist(list);

        return "/shop/cart/shopping-cart.xhtml";
    }

    public String prepareToAddToCart() {
        Long id = Long.valueOf(FacesUtil.getRequest().getParameter("id"));
        Peca peca = service.findObject(Peca.class,id);
        bean.setQtd(1);
        bean.setPeca(peca);
        bean.setId(id);

        populateImagelist(new ArrayList<Peca>(Arrays.asList(new Peca[]{peca})));

        return "/shop/cart/produto-detail.xhtml";
    }

    private void populateImagelist(List<Peca> list) {
        FacesUtil.getSession().setAttribute(
                "imageList",
                list
        );
    }

    public String addProdutoToCart() {
        Peca peca = service.findObject(Peca.class,bean.getId());
        PecaBean pecaBean = new PecaBean();
        pecaBean.setQtd(this.bean.getQtd());
        pecaBean.setPeca(peca);
        bean.setQtd(1);
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
