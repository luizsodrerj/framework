package bijus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bijus.entity.Peca;
import bijus.service.BijusService;

@ManagedBean
@SessionScoped
public class ListPecasController {

	private BijusService bijusService = new BijusService();

	private List<Peca>pecas = new ArrayList<Peca>();
	
	
	public String list() {
		pecas = bijusService.getPecas();
	
		HttpSession session = ((HttpServletRequest)
								FacesContext.getCurrentInstance()
											.getExternalContext()
											.getRequest()
										   ).getSession();
		session.setAttribute("imageList",pecas);
		
		return "/estoque/ListPecas.xhtml";
	}
	
	
	public List<Peca> getPecas() {
		return pecas;
	}
}







