package bijus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import bijus.entity.TipoPeca;
import bijus.service.TipoPecaService;
import framework.persistence.jpa.PersistenceServiceUtil;
import framework.util.StringUtil;
import util.FacesUtil;



@ManagedBean
@SessionScoped
public class CadTipoPecaController {

	@EJB
	private TipoPecaService service;

	private List<TipoPeca>tipos = new ArrayList<TipoPeca>();
	private TipoPeca tipo = new TipoPeca();
	

	public void persistTipoPeca() {
		String id = FacesUtil.getRequest().getParameter("id");
		if (id != null && !"".equals(id)) {
			updateTipoPeca();
		} else {
			tipo.setId(null);
			service.persist(tipo);
			tipos = service.findAll(TipoPeca.class,null);
			tipo  = new TipoPeca();
		}
	}

	public void updateTipoPeca() {
		service.updateTipoPeca(tipo, Integer.valueOf(FacesUtil.getRequest().getParameter("id")));

		tipos = service.findAll(TipoPeca.class,null);
		tipo = new TipoPeca();
	}

	public String selectTipo() {
		String id = FacesUtil.getRequest().getParameter("id");
		tipo = service.findObject(TipoPeca.class,Integer.valueOf(id));

		return "/estoque/CadTipoPeca.xhtml";
	}

	public String initialize() {
		tipos = service.findAll(TipoPeca.class,null);
		tipo  = new TipoPeca();

		return "/estoque/CadTipoPeca.xhtml";
	}

	public List<TipoPeca> getTipos() {
		return tipos;
	}
	
	public TipoPeca getTipo() {
		return tipo;
	}

	public void setTipo(TipoPeca tipo) {
		this.tipo = tipo;
	}
	

	
	
	
}
