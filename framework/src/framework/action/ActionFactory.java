package framework.action;

import framework.util.ServiceLocator;

import java.io.InputStream;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Fábrica de classes Action
 * @author  Luiz Alberto
 * @date    10/02/2005
 */
public class ActionFactory {

    private static  ActionFactory   instance;
    private         ClassLoader     loader;
    private         Map             namesCache   = Collections.synchronizedMap(new HashMap());  
    private         Map             actionsCache = Collections.synchronizedMap(new HashMap());  
    private         Properties      actions;

    /**
     * Inicializa a classe
     */
    private ActionFactory() {
        try {
            loader               = getClass().getClassLoader();
            actions              = new Properties();
            String      resource = ServiceLocator.instance().getProperty("actionsResource");
            InputStream stream   = getClass().getResourceAsStream(resource);
            loadActions(stream);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Carrega as actions para o cache
     * @param stream Fluxo de bytes do recurso que contem as actions
     */
    private void loadActions(InputStream stream) {
        try  {
            actions.load(stream);
            Enumeration keys = actions.keys();

            while (keys.hasMoreElements()) {
                String key   = (String)keys.nextElement();
                String value = actions.getProperty(key);
                namesCache.put(key, value);
            }		
        } 
        catch (Exception e)  {
            throw new RuntimeException(e);
        } 
    }

    /**
     * Retorna a instancia da fabrica
     * @return a instancia da fabrica
     */
    public static ActionFactory newInstance() {
        if (instance == null) {
            instance = new ActionFactory();
        }
        
        return instance;
    }

    /**
     * Obtem a instacia da classe Action a 
     * partir do nome da classe
     * @param   actionName nome da classe Action
     * @return  a instacia da classe Action 
     */
    public Action getAction(String actionName) {
        Action action = (Action)actionsCache.get(actionName);
        try {
            if (action == null) {
                String actionClassName = (String)namesCache.get(actionName);
                Class  actionClass     = loader.loadClass(actionClassName);
                action                 = (Action)actionClass.newInstance();
                actionsCache.put(actionName, action);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return action;
    }

}













