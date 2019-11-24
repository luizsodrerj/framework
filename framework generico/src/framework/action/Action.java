package framework.action;

import java.awt.Component;

/**
 * Interface para classes Action
 * @author  Luiz Alberto
 * @date    10/02/2005
 */
public interface Action {

    /**
     * Executa uma ação para uma classe Action 
     * @param component Componente que originou a ação
     * @throws java.lang.Exception em caso de erro
     */
    public void executeAction(Component component) throws Exception;
}
