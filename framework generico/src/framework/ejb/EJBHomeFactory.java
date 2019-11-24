package framework.ejb;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import framework.util.ServiceLocator;

/**
 * Classe utilitária para obter-se beans EJB
 * @author  Luiz Alberto
 * @date    01/05/2004
 */
public class EJBHomeFactory {
    private         Map             homeCache;
    private static  EJBHomeFactory  instance;

    /**
     * Inicializa a classe
     * @throws javax.naming.NamingException em caso de erro
     */
    private EJBHomeFactory() throws NamingException  {
        homeCache  = Collections.synchronizedMap(new HashMap());
    }

    /**
     * Retorna a instancia da classe
     * @return a instancia da classe
     */
    public static EJBHomeFactory getInstance() {
        try {
            if(instance == null)  {
                instance = new EJBHomeFactory();
            }
        }
        catch(NamingException e)  {
            throw new EJBException(e);
        }
        return instance;
    }

   /**
    * Localiza uma Home Interface de acordo com o nome e a classe passados
    * @param jndiName JNDI name da home
    * @param classe   Classe da home
    * @return         Home Interface
    */
    public EJBHome getHome(String jndiName, Class classe) {
        EJBHome home = (EJBHome)homeCache.get(classe);
        try {
            if (home == null)  {
                ServiceLocator locatorInstance = ServiceLocator.instance();
                Object  object          = locatorInstance.getService(jndiName);
                home                    = (EJBHome)PortableRemoteObject.narrow(object, classe);
                homeCache.put(classe, home);
                locatorInstance.removeService(jndiName);
            }
        }
        catch (Exception e) {
            throw new EJBException(e);
        }
        return home;
    }

}











