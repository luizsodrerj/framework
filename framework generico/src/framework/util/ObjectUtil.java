package framework.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 * @author luiz alberto
 */
public class ObjectUtil {

	/**
	 * Construtor privado
	 */
	private ObjectUtil() {
	}

	/**
	 * Limpa todos os atributos de um objeto
	 * 
	 * @param object Objeto que será limpo
	 */
	public static final void resetProperties(Object object) {
		try {
			PropertyDescriptor[] descriptors	= PropertyUtils.getPropertyDescriptors(object);
			int					 arrayLength	= descriptors.length; 
			
			for (int i = 0; i < arrayLength; i++) {
				PropertyDescriptor	descriptor 	= descriptors[i];
				Method				writeMethod	= descriptor.getWriteMethod(); 
				Class[]				parameters	= writeMethod != null ? writeMethod.getParameterTypes() : null; 
				
				if (writeMethod != null && parameters != null && parameters.length > 0) {
					writeMethod.invoke(object, new Object[]{null});	
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}






