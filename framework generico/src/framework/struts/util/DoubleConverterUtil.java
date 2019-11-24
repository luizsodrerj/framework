package framework.struts.util;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

import framework.util.NumberUtil;

public class DoubleConverterUtil implements Converter {
	
	public Object convert(Class classe, Object value) {
		
		Object result = null;
		
		try {
			result = NumberUtil.parseFormatedDouble((String)value);
			
		} catch (Exception e) {
			throw new ConversionException("Erro ao instaciar Double");
		}
		return result;
	}

	
}









