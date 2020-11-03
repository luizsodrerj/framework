package framework.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import framework.persistence.util.RowSetBeanUtil;

public class BeanUtil {

	private BeanUtil() {
	}

	
    public static <T>T mapsToBeanCollection(List<Map<String,Object>>list, Class type) throws Exception {
		String 	beanClassName	= type.getName();
		List 	collection 		= new ArrayList();
	
		if (!list.isEmpty()) {
			Map<String,Object>first = list.get(0);
			
			Map[]properties = getBeanProperties(first);
			
			for (Map<String,Object> map: list) {
				Object bean = newInstance(beanClassName);
				setBeanProperties(bean, map, properties);
				collection.add(bean);
			}
		}
		return (T)collection;
    }
	

	private static void setBeanProperties(
						  Object object,
						  Map<String,Object>map,
						  Map[]beanProperties
						) throws Exception {
        BeanInfo                beanInfo    = Introspector.getBeanInfo(object.getClass());
        PropertyDescriptor[]    properties  = beanInfo.getPropertyDescriptors();
		int                     arrayLength = beanProperties.length;
        
        for (int i = 0; i < arrayLength; i++)  {
        	String propName  = beanProperties[i].keySet().iterator().next().toString();
        	String key		 = beanProperties[i].get(propName).toString();
            String setMethod = getSetMethodByProperty(propName);
            
            for (int j = 0; j < properties.length; j++) {
                if (properties[j].getWriteMethod() != null) {
                    String metdName = properties[j].getWriteMethod().getName();
                
                    if (metdName.equals(setMethod)) {
                        Method method   = properties[j].getWriteMethod();
                        invokeSetMethod(method, object, map, key);
                        break;
                    }
                }
            }
        }
	}
	
    private static void invokeSetMethod(
    					  Method method, 
    					  Object object, 
    					  Map<String,Object>map, 
    					  String key
    					) throws Exception {
        Class[] params  = method.getParameterTypes();
        Class   param   = params[0];

        if (param.getName().equals(String.class.getName())) {
        	String value = map.get(key) != null ? map.get(key).toString() : null;
        	
            method.invoke(object, new Object[]{value});
            return;
        } 
        
        if (param.getName().equals(Date.class.getName())) {
        	Date value = map.get(key) != null ? (Date)map.get(key) : null;
        	
            method.invoke(object, new Object[]{value});
            return;
        }
        
        invokePrimitiveMethod(param,method,object,map,key);        
    }

    private static void invokePrimitiveMethod (	
    					  Class param, 
                          Method method, 
                          Object object, 
      					  Map<String,Object>map, 
      					  String key
                        ) throws Exception {
        if (param.getName().equals(Long.class.getName())) {
            invokeLongMethod(method, object, map, key);
            return;
        }
        if (param.getName().equals(long.class.getName())) {
            invokeLongMethod(method, object, map, key);
            return;
        }
        if (param.getName().equals(Integer.class.getName())) {
            invokeIntegerMethod(method, object, map, key);
            return;
        }
        if (param.getName().equals(int.class.getName())) {
            invokeIntegerMethod(method, object, map, key);
            return;
        }
        if (param.getName().equals(Double.class.getName())) {
            invokeDoubleMethod(method, object, map, key);
            return;
        }
        if (param.getName().equals(double.class.getName())) {
            invokeDoubleMethod(method, object, map, key);
            return;
        }
    }

    private static void invokeDoubleMethod(
						  Method method, 
						  Object object, 
						  Map<String,Object>map, 
						  String key
						) throws Exception {
    	String value = map.get(key) != null ? map.get(key).toString() : null;
    
        if (value != null)  {
            method.invoke(object, new Object[]{Double.valueOf(value)});    
        }
    }

    private static void invokeIntegerMethod(
						  Method method, 
						  Object object, 
						  Map<String,Object>map, 
						  String key
						) throws Exception {
    	String value = map.get(key) != null ? map.get(key).toString() : null;
    
        if (value != null)  {
            method.invoke(object, new Object[]{Integer.valueOf(value)});
        }
    }

    private static void invokeLongMethod(
    					  Method method, 
    					  Object object, 
        				  Map<String,Object>map, 
          				  String key
    					) throws Exception {
    	String value = map.get(key) != null ? map.get(key).toString() : null;
    
        if (value != null)  {
            method.invoke(object, new Object[]{Long.valueOf(value)});
        }
    }
	
	
    private static String getSetMethodByProperty(String property) {
		String method	= property;
		String first	= method.substring(0, 1).toUpperCase();

		return new StringBuilder("set")
				      .append(first)
				      .append(method.substring(1, method.length()))
				      .toString();
	}
	
	private static Map[] getBeanProperties(Map<String,Object>map) {
		Map[]properties = null;
		
		if (map != null && !map.isEmpty()) {
			Set<String>keys = map.keySet();
			properties      = new Map[keys.size()];
			int count       = -1;	
			
			for (String key: keys) {
				Map<String,String>prop 	= new HashMap<String,String>();
				String propName 		= toJavaBeanProperty(key.toUpperCase());
				String name 			= key.toUpperCase();
				
				prop.put(propName, name);
				properties[++count] = prop; 
			}
		}
		return properties;
	}
	
	 private static Object newInstance(String className) throws Exception {
        Class     classe  = RowSetBeanUtil.class.getClassLoader().loadClass(className);
        Object    object  = classe.newInstance();
    
        return object;
	}
	
    private static String toJavaBeanProperty(String name)  {
        StringBuilder stringHelper = new StringBuilder("");
        
        stringHelper.append(name.toLowerCase().substring(0, 1));
        
        if (name.indexOf("_") != -1) {
            for (int i=1; i < name.length(); i++) {
                if (name.charAt(i) == '_') {
                    stringHelper.append(name.toUpperCase().substring(i+1, i+2));
                    ++i;
                } else {
                    stringHelper.append(name.toLowerCase().substring(i, i+1));
                }
            }
        } else {
            stringHelper.append(name.substring(1, name.length()).toLowerCase());
        }
        return stringHelper.toString();
    }
	
}
