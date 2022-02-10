package bijus.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;

import bijus.beans.PecaBean;
import bijus.entity.Categoria;
import bijus.entity.Peca;
import bijus.entity.TipoPeca;
import bijus.service.BijusService;
import framework.persistence.jpa.PersistenceServiceUtil;
import util.FacesUtil;

@ManagedBean
@SessionScoped
public class CtrlEstoqueController {
	
	private BijusService service = new BijusService();

	private PecaBean peca = new PecaBean();

	

	public String detail() {
		this.peca = new PecaBean();

		Integer id = Integer.valueOf(FacesUtil.getRequest().getParameter("id"));
		Peca peca  = service.findObject(Peca.class, id); 
		this.peca.copy(this,peca);
		
		loadCategoria();
		loadTipos();
		
		return "/estoque/CtlEstoquePeca.xhtml";
	}

	public void resetImage() {
		peca.setFileBytes(null);
		peca.setFile(null);
	}

	public void persistPeca(ActionEvent event) {
		Peca peca = this.peca.getCopy();
		
		if (this.peca.getId() != null) {
			peca.setId(this.peca.getId());
			service.merge(peca);
		} else {
			service.persistPeca(peca);	
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		peca.setFile(event.getFile());
		setImageViewer(peca.getFile().getContent());
    }
	
	public void setImageViewer(byte[] imageBytes) {
		List<Peca>list	= new ArrayList<Peca>();
		Peca peca 		= new Peca();
		
		peca.setImagem(imageBytes);
		peca.setId(1);
		list.add(peca);
		
		HttpSession session = FacesUtil.getSession();
		session.setAttribute("imageList",list);
	}
	
	public String showCtrlEstoqueForm() {
		peca = new PecaBean();

		loadCategoria();
		loadTipos();
		
		return "/estoque/CtlEstoquePeca.xhtml";
	}

	public void upload() {
	}
	
	private void loadCategoria() {
		List<Categoria>list 	   = Arrays.asList(Categoria.values());
		List<SelectItem>categorias = new ArrayList<SelectItem>();

		for (Categoria categoria: list) {
			categorias.add(
				new SelectItem(
					categoria.getDescricao(), 
					categoria.getDescricao()
				)
			);		
		}
		peca.setCategorias(categorias);
	}

	private void loadTipos() {
		List<SelectItem>tipos = new ArrayList<SelectItem>();
		List<TipoPeca>list 	  = getTiposPeca();

		for (TipoPeca tipo: list) {
			tipos.add(
				new SelectItem(
					tipo.getTipo(), 
					tipo.getTipo()
				)
			);		
		}
		peca.setTipos(tipos);
	}
	
	public List<TipoPeca> getTiposPeca() {
		PersistenceServiceUtil persis = new PersistenceServiceUtil();
		try {
			return persis.findAll(TipoPeca.class,null);
		} finally {
			persis.close();
		}
	}
	
	public PecaBean getPeca() {
		return peca;
	}

	public void setPeca(PecaBean peca) {
		this.peca = peca;
	}

	
	
	
}
