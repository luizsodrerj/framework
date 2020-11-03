package framework.struts.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.util.MessageResources;

import framework.exceptions.BusinessException;

public class GenericExceptionHandler extends ExceptionHandler {

	
    public ActionForward execute(   Exception           exception, 
                                    ExceptionConfig     exceptionConfig,
                                    ActionMapping       mapping, 
                                    ActionForm          form, 
                                    HttpServletRequest  request, 
                                    HttpServletResponse response) throws ServletException {
        MessageResources messages = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
    	
        
        if (exception instanceof BusinessException)  {
            request.setAttribute("msgErro", messages.getMessage(request.getLocale(), exceptionConfig.getKey()));
            return new ActionForward(mapping.getInput());    
        }
        else {
            request.setAttribute("msgErro", messages.getMessage(request.getLocale(), exceptionConfig.getKey()));
            
            return new ActionForward(exceptionConfig.getPath());
        }
        
    }
}
