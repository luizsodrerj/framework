package pdv.session;

import pdv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("statusPedidoList")
public class StatusPedidoList extends EntityQuery<StatusPedido> {

	private static final long serialVersionUID = 2974029731515291727L;

	private static final String EJBQL = "select statusPedido from StatusPedido statusPedido";

	private static final String[] RESTRICTIONS = {
			"lower(statusPedido.dsStatus) like lower(concat(#{statusPedidoList.statusPedido.dsStatus},'%'))", };

	private StatusPedido statusPedido = new StatusPedido();

	public StatusPedidoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
}
