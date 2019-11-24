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
	 * Retorna a requisi��o HTTP corrente
	 * @return a requisi��o HTTP corrente
	 */
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest)getPageContext().getRequest();
	}

	/**
	 * Retorna a Sess�o corrente
	 * @return a Sess�o corrente
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * Retorna o contexto Servlet da aplica��o
	 * @return o contexto Servlet da aplica��o
	 */
	protected ServletContext getApplication() {
		return getSession().getServletContext();
	}
	
}









