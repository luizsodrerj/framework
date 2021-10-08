package bijus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import bijus.entity.Mensagem;
import bijus.service.MensagensService;
import util.FacesUtil;

@ManagedBean
@RequestScoped
public class MensagensController {

	private static final String MESSAGE_INBOX_XHTML = "/admin/MessageInbox.xhtml";
	private static final String MESSAGE_XHTML = "/admin/message.xhtml";

	private MensagensService service = new MensagensService();
	
	private List<Mensagem>mensagens = new ArrayList<>();
	private Mensagem msg = new Mensagem();

	
	
	public String showMessage() {
		Long id = Long.valueOf(FacesUtil.getRequest().getParameter("id"));
		msg 	= service.findObject(Mensagem.class, id);
		
		return MESSAGE_XHTML;
	}
	
	public String showInbox() {
		getAll();
		
		return MESSAGE_INBOX_XHTML;
	}
	
	public String persist() {
		msg.setStatus(Mensagem.MessageStatus.NOVA.getId());
		msg.setData(new Date());
		msg.setId(null);
		
		service.persist(msg);
	
		getAll();
		
		return MESSAGE_INBOX_XHTML;
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




