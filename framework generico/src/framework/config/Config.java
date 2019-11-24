package framework.config;

import framework.util.ServiceLocator;
import framework.xml.XMLElement;
import framework.xml.XMLSaxParser;

import java.io.InputStream;

import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

/**
 * Classe de configura��o do sistema
 * <p>Date: 15/07/2005</p>
 * @author  Luiz Alberto
 */
public class Config {

    private static  Config   	instance;
    private         Properties  properties;

    
    /**
     * Inicializa a classe
     */
    private Config() {
    }

    /**
     * Retorna a instancia da classe
     * @return a instancia da classe
     */
    public static Config newInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    /**
     * Configura os par�metros do sistema
     * @param resourcesPath path do arquivo de resursos do sistema
     */
    public void config(String resourcesPath) {
        if (properties == null) {
            try {
                properties                      = new Properties();
                InputStream propertiesStream    = getClass().getResourceAsStream(resourcesPath);
                loadApplicationConfig(propertiesStream, properties);
                ServiceLocator.instance().setInitParameters(properties);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Carrega o arquivo de configura��o da aplica��o
     * @param   stream      fluxo de entrada do arquivo de configura��o da aplica��o
     * @param   properties  recurso que armazenar� os par�metros da aplica��o
     * @throws  Exception   em caso de erro ao carregar o arquivo de configura��o da aplica��o
     */
    private void loadApplicationConfig(InputStream stream, Properties properties) throws Exception {
        XMLSaxParser    saxParser       = new XMLSaxParser();
        Collection      elementsList    = saxParser.parse(stream);
        Iterator        elements        = elementsList.iterator();    
        
        while (elements.hasNext())  {
            XMLElement element = (XMLElement)elements.next();
            
            if ("applicationParameter".equals(element.getQualifiedName()))  {
                XMLElement paramName    = (XMLElement)elements.next();
                XMLElement paramValue   = (XMLElement)elements.next();
                String key              = paramName.getValue();
                String value            = paramValue.getValue();
                
                properties.put(key, value);
            }
        }
    }

}





