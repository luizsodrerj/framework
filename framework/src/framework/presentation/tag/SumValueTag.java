package framework.presentation.tag;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;


/**
 * Esta classe é uma Taglib utilitária
 * que itera uma coleção de beans fazendo 
 * o somatório de um valor retornado por 
 * uma propriedade do bean a cada iteração,
 * exibindo o valor total ao final da iteração.  
 * @author	Luiz Alberto
 * @date	18/12/2004
 */
public class SumValueTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	private String 				name;
	private String 				property;
	private String 				valueProperty;
	private String 				collection;
	private Collection			beanCollection;
	private MessageResources	messages;

	/**
	 * itera uma coleção de beans fazendo 
 	 * o somatório de um valor retornado por 
 	 * uma propriedade do bean a cada iteração,
 	 * exibindo o valor total ao final da iteração.   
	 * @return  constante que define o corpo da tag
	 * @throws	javax.servlet.jsp.JspException em caso de erro
	 */	
	public int doStartTag() throws JspException {
	    try {
			initMessages(); 
			verificaFormName();
			verificaCollectionName();
			String value = totalizaValor();
			
		    pageContext.getOut().print(value);
	  	} 
	  	catch (Exception e) {
		  	throw new JspException(e.toString());
	  	}

	  	return SKIP_BODY;
	}

	/**
	 * Inicializa o pacote de recursos de mensagens do struts.
	 */
	private void initMessages() {
		if (messages == null) {
			messages  = (MessageResources)pageContext.getAttribute(Globals.MESSAGES_KEY, 
																   PageContext.APPLICATION_SCOPE);
		}
	}

	/**
	 * Valida o nome do form bean, caso tenha sido passado
	 * como parametro. 
	 * @throws	javax.servlet.jsp.JspException em caso de erro
	 */
	private void verificaFormName() throws JspException {
		if (name != null) {
			if ("".equals(name.trim())) {
				throw new JspException(messages.getMessage("error_invalid_form"));
			}
			Object pageForm 	= pageContext.getAttribute(name, PageContext.PAGE_SCOPE);
			Object requestForm 	= pageContext.getAttribute(name, PageContext.REQUEST_SCOPE);
			Object sessionForm 	= pageContext.getAttribute(name, PageContext.SESSION_SCOPE);

			if (pageForm == null && requestForm == null && sessionForm == null) {
				throw new JspException(messages.getMessage("error_no_form_found"));
			}
			verificaFormProperty();
			
			if (pageForm != null) {
				invokeFormProperty(pageForm);
			} 
			else if (requestForm != null) {
				invokeFormProperty(requestForm);
			}
			else {
				invokeFormProperty(sessionForm);
			}
		}
	}

	/**
	 * Invoca a propriedade do form bean que contem a Collection de beans. 
	 * @param 	form fom bean que contem a Collection de beans.
	 * @throws 	JspException em caso de erro
	 */	
	private void invokeFormProperty(Object form) throws JspException {
		try {
			beanCollection = (Collection) PropertyUtils.getProperty(form, property);
		} 
		catch (Exception e) {
			throw new JspException(e.toString());
		}
	}
		
	/**
	 * Verifica a propriedade do form bean que retorna a Collection de beans. 
	 * @throws JspException em caso de erro
	 */	
	private void verificaFormProperty() throws JspException {
		if (property == null) {
			throw new JspException(messages.getMessage("error_no_property_found"));
		}
		else if ("".equals(property.trim())) {
			throw new JspException(messages.getMessage("error_invalid_property"));
		}
	}

	/**
	 * Verifica a Collection de beans, caso tenha sido passada como parametro.
	 * @throws JspException em caso de erro
	 */
	private void verificaCollectionName() throws JspException {
		if (beanCollection == null) {
			if (collection != null) {
				if ("".equals(collection.trim())) {
					throw new JspException(messages.getMessage("error_invalid_collection"));
				}

				if ("".equals(valueProperty.trim())) {
					throw new JspException(messages.getMessage("error_invalid_valueProperty"));
				}
				
				getBeanCollection();
			}
			else {
				throw new JspException(messages.getMessage("error_no_collection_attribute"));
			}
		}
	}

	/**
	 * Obtem a Collection de beans pesquisando nos escopos.
	 */
	private void getBeanCollection() {
		Object pageCollection 		= pageContext.getAttribute(collection, PageContext.PAGE_SCOPE);
		Object requestCollection	= pageContext.getAttribute(collection, PageContext.REQUEST_SCOPE);
		Object sessionCollection 	= pageContext.getAttribute(collection, PageContext.SESSION_SCOPE);

		if (pageCollection != null) {
			beanCollection = (Collection)pageCollection;
		} 
		else if (requestCollection != null) {
			beanCollection = (Collection)requestCollection;
		}		
		else {
			beanCollection = (Collection)sessionCollection;
		}
	}

	/**
	 * Faz a totalização do valor retornado pela 
 	 * propriedade do bean a cada iteração.
 	 *     
	 * @return o valor totalizado apos a iteração.
	 * @throws InvocationTargetException 	em caso de erro
	 * @throws NoSuchMethodException 		em caso de erro
	 * @throws IllegalAccessException 		em caso de erro
	 */	
	private String totalizaValor() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		Iterator	beanList	= beanCollection.iterator();
		double		sumValue	= 0;	
		
		while (beanList.hasNext()) {
			double doubleValue	= 0;
			Object bean	 		= beanList.next();
			Object value		= PropertyUtils.getProperty(bean, valueProperty);
			
			if (value != null) {
				if (value instanceof BigDecimal) {
					doubleValue = ((BigDecimal)value).doubleValue();
				} 
				else {
					doubleValue = ((Double)value).doubleValue();
				}
			}
			sumValue = sumValue + doubleValue;
		}
		
		return Double.toString(sumValue);
	}
		
	/**
	 * Define o nome da collection a ser encontrada em um dos escopos. 
	 * @param collection o nome da collection a ser encontrada em um dos escopos.
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}

	/**
	 * Define o nome form que contem a o collection.
	 * @param name o nome form que contem a o collection.
	 */
	public void setName(String name) {
		this.name = name;
	}
 
	/**
	 * Define a propriedade do form que retorna a collection.
	 * @param property a propriedade do form que retorna a collection.
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * Define a propriedade do bean da collection que retorna 
	 * o valor a ser totalizado.
	 * @param valueProperty propriedade do bean da collection que retorna o valor a ser totalizado.
	 */
	public void setValueProperty(String valueProperty) {
		this.valueProperty = valueProperty;
	}

}










