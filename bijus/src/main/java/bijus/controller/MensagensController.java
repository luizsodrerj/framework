package bijus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import bijus.entity.Mensagem;
import bijus.service.MensagensService;

@ManagedBean
@RequestScoped
public class MensagensController {

	private MensagensService service = new MensagensService();
	
	private List<Mensagem>mensagens = new ArrayList<>();
	private Mensagem msg = new Mensagem();

	
	
	public String persist() {
		msg.setStatus(Mensagem.MessageStatus.NOVA.getId());
		msg.setData(new Date());
		msg.setId(null);
		
		service.persist(msg);
	
		getAll();
		
		return "/admin/MessageInbox.xhtml";
	}
	
	
	public void getAll() {
		mensagens = service.findAll(Mensagem.class, null);
	}

	
	public List<Mensagem> getMensagens() {
		return mensagens;
	}


	public Mensagem getMsg() {
		return msg;
	}


	public void setMsg(Mensagem msg) {
		this.msg = msg;
	}
	
}




