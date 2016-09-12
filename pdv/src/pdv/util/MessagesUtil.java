package pdv.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public class MessagesUtil {

	public static final String DEFAULT_MSG_PROP_FM_PGTO = "FormaPagto.properties";
	
	
	public static Map<String,String> getAllMessages(String msgPropName) {
		try {
			Map<String,String>messages	= new HashMap<String,String>();
			InputStream stream 			= MessagesUtil.class
													  .getClassLoader()
													  .getResourceAsStream(
															msgPropName
														);
			Properties props = new Properties();
			props.load(stream);

			Set<Object> keys = props.keySet(); 
			
			for (Object objKey : keys) {
				String key	 	= objKey.toString();
				String value	= props.getProperty(key);
				
				messages.put(key, value);
			}
			return messages;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	public static String getMsg(String baseName, String key) {
		ResourceBundle bundle = ResourceBundle.getBundle(baseName);
		
		return bundle.getString(key);
	}
	
}
