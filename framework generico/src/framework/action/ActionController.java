package framework.action;

import java.awt.Component;

/**
 * Controlador para classes Action
 * @author  Luiz Alberto
 * @date    10/02/2005
 */
public class ActionController  {
 
    /**
     * Construtor privado para evitar instancias 
     * desnecessarias da classe
     */
    private ActionController() {
    }

    /**
     * Excuta uma ação de uma classe Action
     * @param actionName    Nome da classe Action
     * @param component     componente que originou a ação
     */
    public static void executeAction(String actionName, Component component) {
        try  {
            ActionFactory factoryInstance = ActionFactory.newInstance();
            Action        action          = factoryInstance.getAction(actionName);
            action.executeAction(component);
        } 
        catch (Throwable e)  {
            throw new RuntimeException(e);
        } 
    }
}