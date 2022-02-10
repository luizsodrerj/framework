package link.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import link.entity.Link;
import link.persistence.PersistenceService;

public class BaseBean {

	protected PersistenceService persistence = new PersistenceService();

	
	public void remove() {
		try {
			HttpServletRequest request = (HttpServletRequest)         
										 FacesContext.getCurrentInstance()
										 			 .getExternalContext()
										 			 .getRequest();
			String linkId = request.getParameter("linkId");
			Integer id 	  = Integer.valueOf(linkId);
			
			persistence.beginTransaction();
			Link link = persistence.findObject(Link.class,id);
			persistence.remove(link);
			persistence.commit();
			
		} finally {
			persistence.close();
		}
	}
	
	
}
