package framework.struts.action;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.PlugInConfig;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import framework.config.ApplicationConfig;
import framework.config.ApplicationParameter;
import framework.util.ServiceLocator;

/**
 * Classe de plugin struts para configuração 
 * de aplicações web.
 * @author  Luiz Alberto
 * @date    20/01/2005
 */
public class ConfigurationPlugin implements PlugIn {

    protected PlugInConfig currentPlugInConfigObject;

    /** 
     * (non-Javadoc)
	 * @see org.apache.struts.action.PlugIn#destroy()
	 */
    public void destroy()  {
    }
   
    /**
     * Inicializa o plugin
     * @param servlet servlet
     * @param config  config
     * @throws javax.servlet.ServletException ServletException
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet, org.apache.struts.config.ModuleConfig)
     */
    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
        try {
            String      configProperties = (String)currentPlugInConfigObject.getProperties().get("configProperties");
            InputStream stream           = servlet.getServletContext().getResourceAsStream(configProperties);
            Properties  properties       = new Properties();  
            loadApplicationConfig(servlet, stream, properties);
            configureApplication(properties);
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Carrega o arquivo de configuração da aplicação
     * 
     * @param   servlet		Action Servlet do Struts
     * @param   xmlStream   fluxo de entrada do arquivo de configuração da aplicação
     * @param   properties  recurso que armazenará os parâmetros da aplicação
     * @throws  Exception   em caso de erro ao carregar o arquivo de configuração da aplicação
     */
    private void loadApplicationConfig(ActionServlet servlet, InputStream xmlStream, Properties properties) throws Exception {
    	String		xmlMappingPath	= "/WEB-INF/ApplicationConfigMapping.xml";
    	InputStream	xmlMapStream	= servlet.getServletContext().getResourceAsStream(xmlMappingPath);
    	InputSource	xmlMapping		= new InputSource(xmlMapStream);
		InputSource	xmlFile			= new InputSource(xmlStream);
		Mapping 	mapping			= new Mapping();
		
		mapping.loadMapping(xmlMapping);
		Unmarshaller 		unmarshaller	= new Unmarshaller(mapping);
		ApplicationConfig 	appConfig	 	= (ApplicationConfig)unmarshaller.unmarshal(xmlFile);
		
		for (Iterator i = appConfig.getApplicationParameters().iterator(); i.hasNext();) {
			ApplicationParameter parameter = (ApplicationParameter) i.next();
			properties.put(parameter.getParamName().trim(), parameter.getParamValue().trim());
		}
    }

    /**
     * Configura os parâmetros do sistema
     * @param properties parâmetros de configuração do sistema
     * @throws javax.servlet.ServletException em caso de erro
     */
    private void configureApplication(Properties properties) throws ServletException {
        try  {
            ServiceLocator serviceLocator = ServiceLocator.instance();
            serviceLocator.setInitParameters(properties);
        } 
        catch (Exception e)  {
            throw new ServletException(e);
        } 
    }

    /**
     * Define a instancia de PlugInConfig
     * @param plugInConfigObject plugin de configuração passado pelo ActionServlet
     */
    public void setCurrentPlugInConfigObject(PlugInConfig plugInConfigObject) {
        currentPlugInConfigObject = plugInConfigObject;
    }

}