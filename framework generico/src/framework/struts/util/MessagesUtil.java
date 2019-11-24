package framework.struts.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;

/**
 * Classe base para Actions do tipo Dispach
 * 
 * Created on 12/02/2006
 * @author Luiz Alberto
 */
public class MessagesUtil {

	/**
	 * Inicializa a classe
	 */
	private MessagesUtil() {
	}
	
	/**
	 * Retorna uma messagem localizada no MessageResources do Struts
	 * 
	 * @param request	requisição HTTP 
	 * @param key		chave para recuperar a mensagem
	 * @return			uma messagem localizada no MessageResources do Struts
	 */
	public static String getMessage(HttpServletRequest request, String key) {
    	MessageResources messages = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
    	
    	return messages.getMessage(request.getLocale(), key);
	}
	
	
}












