package framework.struts.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 *
 * Utilitário para classes do struts
 *
 * <p>Date:	13/12/2005 </p>
 * @author 	Luiz Alberto
 */
public class FormUtil {

	/**
	 * Contrutor privado para evitar instancias
	 * desnecessárias da classe  
	 */
	private FormUtil() {
	}
	
	/**
	 * Limpa todos os atributos do form 
	 * 
	 * @param mapping		actionMapping
	 * @param request		request do ActionServlet
	 * @param form			form do struts
	 * @throws Exception	em caso de erro
	 */
	public static void resetFields(ActionMapping mapping, HttpServletRequest request, ActionForm form) throws Exception {
		PropertyDescriptor[] descriptors	= PropertyUtils.getPropertyDescriptors(form);
		int					 arrayLength	= descriptors.length; 
		
		for (int i = 0; i < arrayLength; i++) {
			PropertyDescriptor	descriptor 	= descriptors[i];
			Method				writeMethod	= descriptor.getWriteMethod(); 
			
			if (writeMethod != null) {
				if (!"setPage".equals(writeMethod.getName())) {
					writeMethod.invoke(form, new Object[]{null});	
				}
			}
		}
		form.reset(mapping, request);
	}

	/**
	 * Limpa todos os campos de um DynaActionForm
	 *  
	 * @param form DynaActionForm a ser limpo
	 */
	public static void clearDynaActionForm(DynaActionForm form) {
		Set fields = form.getMap().keySet();
		
		for (Iterator iter = fields.iterator(); iter.hasNext();) {
			String fieldName = (String) iter.next();
			form.set(fieldName, null);
		}
	}
	
}














