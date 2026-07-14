package util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FacesUtil {


	public static HttpServletRequest getRequest() {
		return ((HttpServletRequest)
				FacesContext.getCurrentInstance()
							.getExternalContext()
							.getRequest()
						   );			
	}
	
	public static HttpSession getSession() {
		return getRequest().getSession();			
	}
	
}
