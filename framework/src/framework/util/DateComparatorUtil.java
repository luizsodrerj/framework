/*
 * Created on 19/07/2005
 */
package framework.util;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Classe utilit�ria para compara��o de valores em datas
 * @author Luiz Alberto
 */
public class DateComparatorUtil implements Comparator {

	private String property;
	
    /**
     * Inicializa a classe
     * @param 	property propriedade do bean que 
     * 			sera usada na compara��o
     */	
	public DateComparatorUtil(String property) {
        this.property = property;
    }
	
    /**
     * Implementa a compara��o da interface Comparator
     * @param 	firstObject		primeiro objeto da compara��o
     * @param 	secondObject	segundo objeto da compara��o
     * @return	resultado da compara��o
     */
    public int compare(Object firstObject, Object secondObject) {
        try {
        	String firstDate   	= (String)PropertyUtils.getProperty(firstObject,   	property);
        	String secondDate	= (String)PropertyUtils.getProperty(secondObject,	property);
            
            if (firstDate == null && secondDate != null)  {
                return -1;
            }
            else if (firstDate == null && secondDate == null)  {
                return 0;
            }
            else if (firstDate != null && secondDate == null)  {
                return 1;
            }

            return new DateUtil(firstDate).compareTo(new DateUtil(secondDate));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}





