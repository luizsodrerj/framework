package scales.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import framework.persistence.jpa.PersistenceServiceUtil;
import scales.domain.ScaleDrive;

public class ScaleDrivesBean {

	private HttpServletRequest request;
	
	
	public ScaleDrivesBean() {
	}

	public List<ScaleDrive> getDrives() {
		PersistenceServiceUtil persis = new PersistenceServiceUtil();
		
		try {
			return persis.findAll(ScaleDrive.class, "driveDate");
		
		} finally {
			persis.close();
		}
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}


}











