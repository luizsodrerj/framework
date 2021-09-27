package bijus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bijus.entity.Mensagem;
import bijus.service.MensagensService;

@ManagedBean
@ViewScoped
public class MensagensController {

	private MensagensService service = new MensagensService();
	
	private List<Mensagem>mensagens = new ArrayList<>();
	private Mensagem msg = new Mensagem();

	
	
	public void persist() {
		service.persist(msg);
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




