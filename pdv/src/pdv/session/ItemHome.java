package pdv.session;

import pdv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("itemHome")
public class ItemHome extends EntityHome<Item> {

	private static final long serialVersionUID = 7720778909714245793L;

	public void setItemId(Long id) {
		setId(id);
	}

	public Long getItemId() {
		return (Long) getId();
	}

	@Override
	protected Item createInstance() {
		Item item = new Item();
		return item;
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

	public Item getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
