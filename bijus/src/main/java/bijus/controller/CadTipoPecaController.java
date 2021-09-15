package bijus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import bijus.entity.TipoPeca;
import framework.persistence.jpa.PersistenceServiceUtil;
import util.FacesUtil;

@ManagedBean
@RequestScoped
public class CadTipoPecaController {

	private PersistenceServiceUtil persis = new PersistenceServiceUtil();

	private List<TipoPeca>tipos = new ArrayList<TipoPeca>();
	private TipoPeca tipo = new TipoPeca();
	

	public void persistTipoPeca() {
		tipo.setId(null);
		
		try {
			persis.beginTransaction();
			persis.persist(tipo);
			persis.commit();

			tipos = persis.findAll(TipoPeca.class,null);
			tipo  = new TipoPeca();
			
		} finally {
			persis.close();
		}
	}

	public void updateTipoPeca() {
		try {
			persis.beginTransaction();

			String id = FacesUtil.getRequest().getParameter("id");
			
			TipoPeca tipo = persis.findObject(TipoPeca.class,Integer.valueOf(id));
			tipo.setTipo(this.tipo.getTipo());
			
			persis.merge(tipo);
			persis.commit();
			
		} finally {
			persis.close();
		}
	}

	public String getSelectedTipo() {
		try {
			String id = FacesUtil.getRequest().getParameter("id");
			tipo = persis.findObject(TipoPeca.class,Integer.valueOf(id));
			
		} finally {
			persis.close();
		}
		return "/estoque/CadTipoPeca.xhtml";
	}
	
	
	public String initialize() {
		try {
			tipos = persis.findAll(TipoPeca.class,null);
		} finally {
			persis.close();
		}
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
