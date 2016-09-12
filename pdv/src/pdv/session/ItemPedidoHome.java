package pdv.session;

import pdv.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("itemPedidoHome")
public class ItemPedidoHome extends EntityHome<ItemPedido> {

	private static final long serialVersionUID = 2855359758338591905L;
	
	@In(create = true)
	ItemHome itemHome;
	@In(create = true)
	PedidoVendaHome pedidoVendaHome;

	public void setItemPedidoId(Long id) {
		setId(id);
	}

	public Long getItemPedidoId() {
		return (Long) getId();
	}

	@Override
	protected ItemPedido createInstance() {
		ItemPedido itemPedido = new ItemPedido();
		return itemPedido;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Item item = itemHome.getDefinedInstance();
		if (item != null) {
			getInstance().setItem(item);
		}
		PedidoVenda pedido = pedidoVendaHome.getDefinedInstance();
		if (pedido != null) {
			getInstance().setPedido(pedido);
		}
	}

	public boolean isWired() {
		return true;
	}

	public ItemPedido getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
