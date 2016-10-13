package controller;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import entity.Contato;
import service.ContatoService;

@Path("controller")
public class NgController {

	@EJB
	private ContatoService contatoService;
	
	
	
	@Path("/session")
	@POST
	public void testSession(@Context HttpServletRequest request) {
		if (request != null) {
			HttpSession s = request.getSession();
			String ok = (String) s.getAttribute("sessionParam");
			
			if (ok == null) {
				s.setAttribute("sessionParam", "SESSION OK");	
			}
		}
	}
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	@POST
	public void createContato(Contato contato) {
		contatoService.addContato(contato);
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/contatos")
	@GET
	public List<Contato> getContatos() {
		List<Contato>list = contatoService.getContatos();
		
		return list;
	}
	
}







