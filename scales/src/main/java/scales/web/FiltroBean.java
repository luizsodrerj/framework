package scales.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import framework.persistence.jpa.PersistenceServiceUtil;
import framework.util.DateUtil;
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
			try {
				persistence.connect();
				
				list = persistence.findByNamedQuery(
									 "Escala.findByMesAno", 
									 new Object[]{
										getMes(mesAno),
										getAno(mesAno)
									 }
								   );
			} finally {
				persistence.close();
			}
		}
		return list;
	}
	
	private int getMes(String mesAno) {
		try {
			DateUtil data = new DateUtil(DateUtil.parse(mesAno, DateUtil.MM_yyyy));
			
			return data.getMonth();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private int getAno(String mesAno) {
		try {
			DateUtil data = new DateUtil(DateUtil.parse(mesAno, DateUtil.MM_yyyy));
			
			return data.getYear();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	
}






