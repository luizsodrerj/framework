package framework.business.delegate;

import javax.ejb.EJBHome;

import framework.business.facade.BaseSessionFacadeImpl;
import framework.ejb.EJBHomeFactory;

/**
 * <p>Descrição:    Delegate base de negócios. </p>
 * 
 * @author          Luiz Alberto
 * @date            22/10/2004
 */
public class BaseDelegate extends BaseSessionFacadeImpl {

	
    /**
     * Localiza uma Home Interface de acordo com o nome e a classe passados
     * 
     * @param homeClass	Classe da home
     * @param jndiName 	JNDI name da home
     * @return         	Home Interface
     */
    protected EJBHome getHome(Class homeClass, String jndiName) {
    	EJBHomeFactory factory = EJBHomeFactory.getInstance();
    	
    	return factory.getHome(jndiName, homeClass);
    }
    
    
}












