package agendaweb.faces;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import agendaweb.entity.Contato;

@FacesConverter("contatoConverter")
public class ContatoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
		if (value != null && !"".equals(value)) {
			Integer id = Integer.valueOf(value);
			
			Contato contato = new Contato();
			contato.setId(id);
			
			return contato;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object object) {
		if (object != null) {
			Integer id = ((Contato)object).getId();
			
			return id != null ? id.toString() : null;
		}
		return null;
	}

}




