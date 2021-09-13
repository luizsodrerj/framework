package bijus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import bijus.entity.Peca;
import bijus.service.BijusService;

@ManagedBean
@RequestScoped
public class ListPecasController {

	private BijusService bijusService = new BijusService();

	private List<Peca>pecas = new ArrayList<Peca>();
	
	
	
	public String list() {
		pecas = bijusService.getPecas();
		
		return "/estoque/ListPecas.xhtml";
	}
	
	
	public List<Peca> getPecas() {
		return pecas;
	}
}







