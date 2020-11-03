package framework.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import framework.exceptions.ServiceException;

/**
 * Classe utilitária para serviços de localização
 * @author  Luiz Alberto
 * @date    01/01/2005
 */
public class ServiceLocator  {
    private static ServiceLocator instance;
    private Map services;
    private InitialContext context;

    /**
     * Inicializa a classe
     * @throws framework.exceptions.ServiceException
     */
    private ServiceLocator() throws ServiceException {
        try {
            context = new InitialContext();
        } 
        catch (NamingException e) {
            throw new ServiceException(e.toString());
        } 
    }

    /**
     * Retorna a instancia da classe 
     * @return a instancia da classe 
     * @throws framework.exceptions.ServiceException em caso de erro
     */
    public static ServiceLocator instance() throws ServiceException {
        if(instance == null) {
            instance = new ServiceLocator();
            instance.init();
        }
        return instance;
    }

    /**
     * Define o cache para armazenar os parâmetros da aplicação
     * @param properties parâmetros da aplicação
     * @throws framework.exceptions.ServiceException em caso de erro
     */
    public void setInitParameters(Properties properties) throws ServiceException {
        try {
            Enumeration keys = properties.keys();

            while (keys.hasMoreElements()) {
                String key   = (String)keys.nextElement();
                String value = properties.getProperty(key);
                services.put(key, value);
            }		
        } 
        catch(Exception ex) {
            throw new ServiceException(ex.toString());
        }        
    }


    /**
     * Retorna um serviço atraves do nome
     * @param   jndiName nome do serviço
     * @return  um serviço atraves do nome 
     * @throws  framework.exceptions.ServiceException em caso de erro 
     */
    public Object getService(String jndiName) throws ServiceException  {
        try  {
            if(!services.containsKey(jndiName)) {
                services.put(jndiName, context.lookup(jndiName));
            }
        } 
        catch(NamingException ex) {
            throw new ServiceException(ex.getMessage());
        }
        return services.get(jndiName);
    }

    /**
     * Remove um serviço do cache
     * @param jndiName nome do serviço
     */
    public void removeService(String jndiName) {
        if(services.containsKey(jndiName)) {
            services.remove(jndiName);
        }
    }

    /**
     * Retorna o nome do serviço de conexão com banco
     * @return o nome do serviço de conexão com banco
     * @throws framework.exceptions.ServiceException em caso de erro
     */
    public String getDataSourceName() throws ServiceException {
        return getServiceName("dataSourceName");
    }

    /**
     * Retorna o nome de  um serviço 
     * @param   pSName chave para o nome de  um serviço 
     * @return  o nome de  um serviço 
     * @throws  framework.exceptions.ServiceException em caso de erro
     */
    protected String getServiceName(String pSName) throws ServiceException {
        String value = null;
        value = (String)services.get(pSName);

        if(value == null) {
            throw new ServiceException("A propriedade " + pSName + " não está definida");
        } 
        else {
            return value;
        }
    }

    /**
     * Retorna uma propriedade da aplicação
     * @param   key chave da propriedade
     * @return  uma propriedade da aplicação
     */
    public String getProperty(String key) {
        String property = (String)services.get(key);
        
        if (property == null)  {
            throw new RuntimeException("A propriedade " + key + " não está definida");
        }
        return property;
    }

    /**
     * Localiza uma propriedade em um recurso
     * e o adiciona ao cache para depois retorná-lo
     * @param resourceBundle    nome do recurso
     * @param key               chave da propriedade do recurso
     * @return uma propriedade em um recurso
     */
    public String getResourceProperty(String resourceBundle, String key) {
        if (!services.containsKey(key))  {
            String value = ResourceBundle.getBundle(resourceBundle).getString(key);
            services.put(key, value);
        }
        return (String)services.get(key);
    }

    /**
     * Inicializa o cache de serviços
     */
    protected void init() {
        if (services == null)  {
            services = Collections.synchronizedMap(new HashMap());
        }
        else {
            services.clear();
        }
    }

}