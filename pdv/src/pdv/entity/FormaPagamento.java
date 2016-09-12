package pdv.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import framework.persistence.jpa.PersistenceService;
import pdv.util.MessagesUtil;

@Entity
@Table(name = "Forma_Pagamento")
public class FormaPagamento implements Serializable {

	private static final long serialVersionUID = -5338772399364887815L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
 
	@Column(name = "forma")
	private String forma;
 
	
	
	public void loadInDB(EntityManager manager) {
		PersistenceService persis = new PersistenceService(manager);
		
		try {
			persis.connect();
			List list = persis.findAll(FormaPagamento.class);
			
			if (list.isEmpty()) {
				loadFormasPagto(manager);
			}
		} finally {
			persis.close();
		}
	}
	
	public void loadFormasPagto(EntityManager manager) {
		PersistenceService persis = new PersistenceService(manager);
		
		FormaPagamento dinheiro = new FormaPagamento();
		FormaPagamento cartcred = new FormaPagamento();
		FormaPagamento cartdeb = new FormaPagamento();
		FormaPagamento cheque = new FormaPagamento();
		FormaPagamento depos = new FormaPagamento();
		FormaPagamento ted = new FormaPagamento();
		FormaPagamento doc = new FormaPagamento();
		FormaPagamento trcc = new FormaPagamento();
		FormaPagamento carne = new FormaPagamento();
		long idIndex = 0L;
		
		try {
			persis.beginTransaction();
			
			dinheiro.setForma("Dinheiro");
			dinheiro.setId(++idIndex);
			
			String cred = MessagesUtil.getMsg("messages", "cart.cred");
			cartcred.setForma(cred);
			cartcred.setId(++idIndex);
			
			String deb = MessagesUtil.getMsg("messages", "cart.deb");
			cartdeb.setForma(deb);
			cartdeb.setId(++idIndex);
			
			cheque.setForma("Cheque");
			cheque.setId(++idIndex);
	 
			String dep = MessagesUtil.getMsg("messages", "deposito");
			depos.setForma(dep);
			depos.setId(++idIndex);

			String trccFrm = MessagesUtil.getMsg("messages", "transf.cont.corr");
			trcc.setForma(trccFrm);
			trcc.setId(++idIndex);

			ted.setForma("TED");
			ted.setId(++idIndex);

			doc.setForma("DOC");
			doc.setId(++idIndex);

			String labCarne = MessagesUtil.getMsg("messages", "carne");
			carne.setForma(labCarne);
			carne.setId(++idIndex);
			
			persis.persist(dinheiro);
			persis.persist(cartcred);
			persis.persist(cartdeb);
			persis.persist(cheque);
			persis.persist(depos);
			persis.persist(trcc);
			persis.persist(ted);
			persis.persist(doc);
			persis.persist(carne);
			persis.commit();
		} finally {
			persis.close();
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormaPagamento other = (FormaPagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
