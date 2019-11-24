package framework.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import framework.util.ServiceLocator;
import framework.xml.XMLElement;
import framework.xml.XMLSaxParser;

/**
 * Classe utilitaria que implementa uma
 * fabrica de recursos de SQL queries
 * @author  Luiz Alberto
 * @date    03/03/2005
 */
public class QueryFactory  {
    
    private static QueryFactory instance;
    private static final String SEPARATOR = ",";
    private        Map   cache = Collections.synchronizedMap(new HashMap());  
    
    
    /**
     * Inicializa a classe
     */
    private QueryFactory() {
        String xmlQueryResources = initQueryResources();
        loadQueries(xmlQueryResources);
    }
    
    /**
     * Retorna a instancia da fabrica
     * @return a instancia da fabrica
     */
    public static QueryFactory newInstance() {
        if (instance == null) {
            instance = new QueryFactory();
        }
        
        return instance;
    }

    /**
     * 
     * @param key
     * @return 
     */
    public String getQuery(String key) {
        return (String)cache.get(key);
    }

    /**
     * 
     * @param xmlQueryResources
     */
    private void loadQueries(String xmlQueryResources) {
        String xmlResources[] = xmlQueryResources.split(SEPARATOR);
        int    length         = xmlResources.length; 

        try {
            for (int i = 0; i < length; i++)  {
                addXmlToCache(xmlResources[i].trim());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param xmlResource
     * @throws java.lang.Exception
     */
    private void addXmlToCache(String xmlResource) throws Exception {
        XMLSaxParser xmlParser  = new XMLSaxParser();
        InputStream  xmlStream  = getClass().getResourceAsStream(xmlResource);
        
        Iterator xmlElementList = xmlParser.parse(xmlStream).iterator();
        xmlElementList.next();
        
        while (xmlElementList.hasNext())  {
            xmlElementList.next();
            String queryName   = ((XMLElement)xmlElementList.next()).getValue();
            String sqlSentence = ((XMLElement)xmlElementList.next()).getValue();
            
            cache.put(queryName, sqlSentence);
        }
    }

    /**
     * 
     * @return 
     */
    private String initQueryResources() {
        String  		xmlQueryResources   = null;
        ServiceLocator	serviceLocator      = getLocatorInstance();
    
        try  {
            xmlQueryResources = serviceLocator.getProperty("xmlQueryResources");
        } 
        catch (RuntimeException e)  {
            return getXmlQueryResources();
        } 

        return xmlQueryResources;
    }

    /**
     * 
     * @return 
     */
    private String getXmlQueryResources() {
        try {
            String      resource = "/ApplicationConfig.properties";
            InputStream stream   = getClass().getResourceAsStream(resource);
            Properties  props    = new Properties();
            props.load(stream);
            
            return props.getProperty("xmlQueryResources");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @return 
     */
    private ServiceLocator getLocatorInstance() {
        try  {
            return ServiceLocator.instance();
        } 
        catch (Exception e)  {
            throw new RuntimeException(e);
        } 
    }
    
}










