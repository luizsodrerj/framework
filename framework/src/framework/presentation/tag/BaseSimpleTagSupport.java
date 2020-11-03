package framework.presentation.tag;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
 
/**
 * 
 * Esta classe serve como classe base de classes gerenciadoras de tag 
 * 
 * Created on 29/09/2005
 * @author Luiz Alberto
 */
public class BaseSimpleTagSupport extends SimpleTagSupport {

	/**
	 * Retorna o contexto da pagina corrente
	 * @return o contexto da pagina corrente
	 */
	protected PageContext getPageContext() {
		JspContext	context	= getJspContext();
		return (PageContext) context;
	}

	/**
	 * Retorna a requisição HTTP corrente
	 * @return a requisição HTTP corrente
	 */
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest)getPageContext().getRequest();
	}

	/**
	 * Retorna a Sessão corrente
	 * @return a Sessão corrente
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * Retorna o contexto Servlet da aplicação
	 * @return o contexto Servlet da aplicação
	 */
	protected ServletContext getApplication() {
		return getSession().getServletContext();
	}
	
}









