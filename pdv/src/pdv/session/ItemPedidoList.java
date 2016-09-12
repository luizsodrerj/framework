package pdv.session;

import pdv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("itemPedidoList")
public class ItemPedidoList extends EntityQuery<ItemPedido> {

	private static final long serialVersionUID = -7586358305377922018L;

	private static final String EJBQL = "select itemPedido from ItemPedido itemPedido";

	private static final String[] RESTRICTIONS = {
			"lower(itemPedido.codigo) like lower(concat(#{itemPedidoList.itemPedido.codigo},'%'))",
			"lower(itemPedido.descricao) like lower(concat(#{itemPedidoList.itemPedido.descricao},'%'))", };

	private ItemPedido itemPedido = new ItemPedido();

	public ItemPedidoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public ItemPedido getItemPedido() {
		return itemPedido;
	}
}
