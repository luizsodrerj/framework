package pdv.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import pdv.entity.FormaPagamento;
import pdv.service.ApplicationService;
import pdv.util.MessagesUtil;

@Name("formaPagamentoHome")
public class FormaPagamentoHome extends EntityHome<FormaPagamento> {

	private static final long serialVersionUID = 7930047145508332492L;

	public static final String FM_PGTO_LIST_VIEW 	= "/pages/formapagto/FormaPagamentoList.xhtml";
	public static final String CARGA_FM_PGTO_VIEW	= "/pages/formapagto/CargaFormaPagto.xhtml";
	public static final String PARAM_SHOW_MSG_CARGA_SUC = "?showMsgCargaSucess=true";

	private List<FormaPagamento>formaPagtoList = new ArrayList<FormaPagamento>();

	@In(required = false, create = true)
	ApplicationService appService;
	
	
	
	public String onClickBtLocalizar() {
		formaPagtoList = appService.findAllByCriteria(FormaPagamento.class);
		
		return FM_PGTO_LIST_VIEW;
	}
	
	public String cargaFormasPagto() {
		List<FormaPagamento>list = appService.findAllByCriteria(FormaPagamento.class);
		
		if (list.isEmpty()) {
			EntityManager manager 		  = getEntityManager();
			Map<String,String>formasPagto = MessagesUtil.getAllMessages(
												MessagesUtil.DEFAULT_MSG_PROP_FM_PGTO
											);
			Set<String>keys = formasPagto.keySet();
			
			for (String key : keys) {
				String forma = formasPagto.get(key);
			
				FormaPagamento formaPagto = new FormaPagamento();
				formaPagto.setForma(forma);
				manager.persist(formaPagto);
			}
		}
		return CARGA_FM_PGTO_VIEW + PARAM_SHOW_MSG_CARGA_SUC;
	}
	
	
	public void setFormaPagamentoId(Long id) {
		setId(id);
	} 

	public Long getFormaPagamentoId() {
		return (Long) getId();
	}

	@Override
	protected FormaPagamento createInstance() {
		FormaPagamento formaPagamento = new FormaPagamento();
		return formaPagamento;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public FormaPagamento getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<FormaPagamento> getFormaPagtoList() {
		return formaPagtoList;
	}

	public void setFormaPagtoList(List<FormaPagamento> formaPagtoList) {
		this.formaPagtoList = formaPagtoList;
	}

}
