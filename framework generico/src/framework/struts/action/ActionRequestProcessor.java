package framework.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.util.MessageResources;

public class ActionRequestProcessor extends RequestProcessor {

    private boolean verficaUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MessageResources messages = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
        
        if (request.getSession().getAttribute("usuarioLogado") == null) {
            request.setAttribute("msgErro", messages.getMessage(request.getLocale(), "error.usuario.naoLogado"));
            response.sendError(HttpServletResponse.SC_FORBIDDEN);

            return false;
        }

        return true;        
    }

    protected boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) throws IOException, ServletException {
         return super.processRoles(request, response, mapping);
    }
   
    protected ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response, Action action, ActionForm actionForm, ActionMapping mapping) throws IOException, ServletException {
        if (!verficaUsuario(request, response))  {
            return mapping.findForward(mapping.getInput());
        } 
        else  {
            return super.processActionPerform(request, response, action, actionForm, mapping);            
        }
    }
                  
}
