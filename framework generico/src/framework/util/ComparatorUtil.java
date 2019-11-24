package framework.util;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;


/**
 * Classe utilitaria para comparação de objetos
 * @author Luiz Alberto
 */
public class ComparatorUtil implements Comparator {
    private String 	property;
    private	boolean isInverse;
    
    /**
     * Inicializa a classe
     * 
     * @param 	property propriedade do bean que 
     * 			sera usada na comparação
     */	
    public ComparatorUtil(String property) {
        this.property = property;
    }
    
	/**
	 * Inicializa a classe
	 * 
	 * @param property	propriedade do bean que sera usada na comparação
	 * @param isInverse	se será ordenado por ordem inversa
	 */
	public ComparatorUtil(String property, boolean isInverse) {
		this.property 	= property;
		this.isInverse	= isInverse;
	}
	
	
    /**
     * Implementa a comparação da interface Comparator
     * @param 	primObjeto		primeiro objeto da comparação
     * @param 	segundObjeto	segundo objeto da comparação
     * @return	resultado da comparação
     */
	public int compare(Object primObject, Object secondObject) {
		if (isInverse) {
			return invert(primObject, secondObject);
		}
		else {
			return sort(primObject, secondObject);
		}
	}
	
    /**
     * Implementa a comparação da interface Comparator em ordem ascendente
     * @param 	primObjeto		primeiro objeto da comparação
     * @param 	segundObjeto	segundo objeto da comparação
     * @return	resultado da comparação
     */
    private int sort(Object primObjeto, Object segundObjeto) {
        try {
            Comparable primComparable   = (Comparable)PropertyUtils.getProperty(primObjeto,   property);
            Comparable segundComparable = (Comparable)PropertyUtils.getProperty(segundObjeto, property);
            
            if (primComparable == null && segundComparable != null)  {
                return -1;
            }
            else if (primComparable == null && segundComparable == null)  {
                return 0;
            }
            else if (primComparable != null && segundComparable == null)  {
                return 1;
            }

            return primComparable.compareTo(segundComparable);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Implementa a comparação da interface Comparator em ordem descendente
     * @param 	primObject		primeiro objeto da comparação
     * @param 	secondObject	segundo objeto da comparação
     * @return	resultado da comparação
     */
    private int invert(Object primObject, Object secondObject) {
        try {
            Comparable primComparable   = (Comparable)PropertyUtils.getProperty(primObject,   property);
            Comparable secondComparable = (Comparable)PropertyUtils.getProperty(secondObject, property);
            
            if (secondComparable == null && primComparable != null)  {
                return -1;
            }
            else if (primComparable == null && secondComparable == null)  {
                return 0;
            }
            else if (secondComparable != null && primComparable == null)  {
                return 1;
            }

            return secondComparable.compareTo(primComparable);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
}













