package pdv.session;

import pdv.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("pedidoVendaHome")
public class PedidoVendaHome extends EntityHome<PedidoVenda> {

	private static final long serialVersionUID = -1951970266417876141L;
	
	@In(create = true)
	FormaPagamentoHome formaPagamentoHome;
	
	

	public void setPedidoVendaId(Long id) {
		setId(id);
	}

	public Long getPedidoVendaId() {
		return (Long) getId();
	}

	@Override
	protected PedidoVenda createInstance() {
		PedidoVenda pedidoVenda = new PedidoVenda();
		return pedidoVenda;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		FormaPagamento formaPagamento = formaPagamentoHome.getDefinedInstance();
		if (formaPagamento != null) {
			getInstance().setFormaPagamento(formaPagamento);
		}
//		StatusPedido status = statusPedidoHome.getDefinedInstance();
//		if (status != null) {
//			getInstance().setStatus(status);
//		}
	}

	public boolean isWired() {
		return true;
	}

	public PedidoVenda getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<ItemPedido> getItens() {
		return getInstance() == null ? null : new ArrayList<ItemPedido>(getInstance().getItens());
	}

}
