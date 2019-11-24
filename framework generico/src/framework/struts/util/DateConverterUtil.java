package framework.struts.util;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

import framework.util.DateUtil;

/**
 * @author luiz.sodre
 *
 */
public class DateConverterUtil implements Converter {

	public Object convert(Class classe, Object value) {
		
		Object result = null;
		
		try {
			if (value != null && !"".equals(value.toString().trim())) {
				result = DateUtil.parseToDate(value.toString());
			}
		} catch (Exception e) {
			throw new ConversionException("Erro ao instaciar Date");
		}
		return result;
	}

	
}






