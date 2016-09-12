package pdv.session;

import pdv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("statusPedidoHome")
public class StatusPedidoHome extends EntityHome<StatusPedido> {

	private static final long serialVersionUID = -987386838516555621L;

	public void setStatusPedidoId(Integer id) {
		setId(id);
	}

	public Integer getStatusPedidoId() {
		return (Integer) getId();
	}

	@Override
	protected StatusPedido createInstance() {
		StatusPedido statusPedido = new StatusPedido();
		return statusPedido;
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

	public StatusPedido getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
