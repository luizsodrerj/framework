package framework.xml;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.io.InputStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Iterator;

/**
 * Mapeia um documento xml para um bean 
 * @author  Luiz Alberto
 * @date    01/10/2004
 */
public class XMLBean {
  private ClassLoader loader;

    /**
     * Inicializa a classe
     */
    public XMLBean() {
        super();
        loader = getClass().getClassLoader();
    }

    /**
     * Mapeia um documento xml para um bean 
     * @param   objectName nome da classe do bean
     * @param   xmlFile path do documento xml
     * @return  bean mapeado
     * @throws  framework.xml.XMLException em caso de erro
     */
    public Object xmlToBean(String objectName, String xmlFile) throws XMLException {
        Object       object = null;
        XMLSaxParser xml    = new XMLSaxParser();
        try {
            object = getObjectInstance(objectName);
            Iterator iterator = xml.parse(xmlFile).iterator();
        
            while (iterator.hasNext()) {
                XMLElement element = (XMLElement)iterator.next();
                setBeanProperties(object, element);
            }
        } 
        catch (Exception ex) {
            throw new XMLException(ex.toString());
        }
        return object;
    }

    /**
     * Mapeia um documento xml para um bean 
     * @param   objectName nome da classe do bean
     * @param   xmlInput stream de entrada do documento xml
     * @return  bean mapeado
     * @throws  framework.xml.XMLException em caso de erro
     */
    public Object xmlToBean(String objectName, InputStream xmlInput) throws XMLException {
        Object       object = null;
        XMLSaxParser xml    = new XMLSaxParser();
        try {
            object = getObjectInstance(objectName);
            Iterator iterator = xml.parse(xmlInput).iterator();
            
            while (iterator.hasNext()) {
                XMLElement element = (XMLElement)iterator.next();
                setBeanProperties(object, element);
            }
        } 
        catch (Exception ex) {
            throw new XMLException(ex.toString());
        }
        return object;
    }

    /**
     * Retorna uma instancia do bean a ser mapeado
     * @param   className nome da classe do bean
     * @return  uma instancia do bean a ser mapeado
     * @throws  java.lang.ClassNotFoundException em caso de erro
     * @throws  java.lang.IllegalAccessException em caso de erro
     * @throws  java.lang.InstantiationException em caso de erro
     */
    private Object getObjectInstance(String className)throws ClassNotFoundException,
                                                              IllegalAccessException,
                                                              InstantiationException {
        Class classe  = loader.loadClass(className);
        Object object = classe.newInstance();
        
        return object;
    }

    /**
     * Invoca o metodo set do bean
     * @param object    bean
     * @param element   elemento xml
     * @throws java.beans.IntrospectionException            em caso de erro
     * @throws java.lang.IllegalAccessException             em caso de erro
     * @throws java.lang.reflect.InvocationTargetException  em caso de erro
     */
    private void setBeanProperties(Object object, XMLElement element) throws IntrospectionException,
                                                                             IllegalAccessException,
                                                                             InvocationTargetException {
        BeanInfo             beanInfo   = Introspector.getBeanInfo(object.getClass());
        PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
        String               tagName    = element.getLocalName();
        String               setMethod  = getSetMethodName(tagName);
        
        for (int i = 0; i < properties.length; i++) {
            if(properties[i].getWriteMethod() != null) {
                String metdName = properties[i].getWriteMethod().getName();
            
                if (metdName.equals(setMethod)) {
                    Method method   = properties[i].getWriteMethod();
                    method.invoke(object, new Object[]{element.getValue()});
                    break;
                }
            }
        }
    }

    /**
     * Formata um metodo set do bean
     * @param   name nome do metodo set 
     * @return  metodo set do bean formatado
     */
    private String getSetMethodName(String name) {
        String setMethod = name;
        
        String first  = setMethod.substring(0, 1).toUpperCase();
        setMethod = first + setMethod.substring(1, setMethod.length());
        
        return "set" + setMethod;
    }


}
