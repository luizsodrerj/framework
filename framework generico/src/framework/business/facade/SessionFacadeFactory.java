package framework.business.facade;

import framework.exceptions.BusinessRuntimeException;
import framework.util.ServiceLocator;

/**
 * <p>Descrição:    Factory de classes de negócios. </p>
 * <p>Data:         08/02/2006 </p>
 *
 * @author          Luiz Alberto
 */
public class SessionFacadeFactory {

    /**
     * Construtor privado para evitar instancias
     * desnecessárias da classe
     */
	private SessionFacadeFactory() {
	}

	/**
	 * Retorna uma instancia de uma bo 
	 * 
	 * @param 	boName nome logico ou alias que 
	 * 			retorna o nome fisico da bo no arquivo ApplicationConfig.xml	
	 * @return	uma instancia de uma bo
	 */
	public static Object get(String boName) {
		try {
			ServiceLocator	locator 	= ServiceLocator.instance(); 
			String 			boClassName = locator.getProperty(boName);
			Class			boClass		= Class.forName(boClassName);
			
			return boClass.newInstance();
			
		}  catch (Exception e) {
			throw new BusinessRuntimeException(e);
		}
	}
	
}









