package pdv.session;

import pdv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("pedidoVendaList")
public class PedidoVendaList extends EntityQuery<PedidoVenda> {

	private static final long serialVersionUID = 6428862884814449408L;

	private static final String EJBQL = "select pedidoVenda from PedidoVenda pedidoVenda";

	private static final String[] RESTRICTIONS = {};

	private PedidoVenda pedidoVenda = new PedidoVenda();

	public PedidoVendaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}
}
