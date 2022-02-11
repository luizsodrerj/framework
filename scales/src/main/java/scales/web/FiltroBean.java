package scales.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import framework.persistence.jpa.PersistenceServiceUtil;
import scales.domain.Escala;

public class FiltroBean {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();
	
	private HttpServletRequest request;


	
	public FiltroBean() {
		super();
	}

	public List<Escala> getEscalas() {
		List<Escala>list = Collections.EMPTY_LIST;
		String mesAno 	 = request.getParameter("data");
		
		if (StringUtils.isNotEmpty(mesAno)) {
			//DateUtil d = new DateUtil(DateUtil.parse("02/2022", DateUtil.MM_yyyy));
			try {
				persistence.connect();
				
				list = persistence.findByNamedQuery(
							"Escala.findByMesAno", 
							new Object[]{mesAno}
						);
			} finally {
				persistence.close();
			}
		}
		return list;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	
}






