package pdv.session;

import pdv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("itemList")
public class ItemList extends EntityQuery<Item> {

	private static final long serialVersionUID = 1702250753839288253L;

	private static final String EJBQL = "select item from Item item";

	private static final String[] RESTRICTIONS = { "lower(item.codigo) like lower(concat(#{itemList.item.codigo},'%'))",
			"lower(item.descricao) like lower(concat(#{itemList.item.descricao},'%'))", };

	private Item item = new Item();

	public ItemList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Item getItem() {
		return item;
	}
}
