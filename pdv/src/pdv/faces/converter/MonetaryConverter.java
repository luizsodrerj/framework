package pdv.faces.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

import framework.util.FormatNumberUtil;
import framework.util.NumberUtil;

@Name("monetaryConverter")
@BypassInterceptors
@org.jboss.seam.annotations.faces.Converter
public class MonetaryConverter implements Converter, Serializable {

	private static final long serialVersionUID = 2934993294876490536L;

	private static final String QTD_CASAS_DECIMAIS_KEY = "qtdCasasDecimais";

	
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		String unFormated = NumberUtil.removeFormat(value);
	
		return	unFormated != null && !unFormated.trim().equals("") ? 
				Double.valueOf(unFormated) : 
				null;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		int paramQtdCasasDecimais = FormatNumberUtil.DUAS_CASAS_DECIMAIS;
		
		if (component.getAttributes().containsKey(QTD_CASAS_DECIMAIS_KEY)) {
			Object casasDec = component.getAttributes().get(QTD_CASAS_DECIMAIS_KEY);
			
			if (casasDec != null && !"".equals(casasDec.toString())) {
				paramQtdCasasDecimais = Integer.parseInt(casasDec.toString());
			}
		}
		
		return	value != null ? 
				FormatNumberUtil.format(
					value.toString(), 
					paramQtdCasasDecimais
				) : 
				null; 
	}

}








