package bijus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import bijus.beans.PecaBean;
import bijus.entity.Peca;

@ManagedBean
@SessionScoped
public class CtrlEstoqueController {

	private PecaBean peca = new PecaBean();

	private UploadedFile file;
	
	public void upload() {
		file.getFileName();
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		file = event.getFile();
		List<Peca>list = new ArrayList<Peca>();
		Peca peca = new Peca();
		peca.setImagem(file.getContent());
		peca.setId(1);
		list.add(peca);
		
		HttpSession session = ((HttpServletRequest)
				FacesContext.getCurrentInstance()
							.getExternalContext()
							.getRequest()
						   ).getSession();
		session.setAttribute("imageList",list);
    }
	
	public String showCtrlEstoqueForm() {
		peca = new PecaBean();
		file = null;
		
		return "/estoque/CtlEstoquePeca.xhtml";
	}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public PecaBean getPeca() {
		return peca;
	}

	public void setPeca(PecaBean peca) {
		this.peca = peca;
	}

	
	
	
}
