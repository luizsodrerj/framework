package scales.web;

import java.util.Collections;
import java.util.Date;
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
		String byRange	 = request.getParameter("filterByRange");
		String mesAno 	 = request.getParameter("data");
		String ini 	 	 = request.getParameter("ini");
		String fim 	 	 = request.getParameter("fim");
		
		if (StringUtils.isNotEmpty(mesAno) || StringUtils.isNotEmpty(byRange)) {
			try {
				persistence.connect();
				
				list =	StringUtils.isNotEmpty(byRange) ? 
						findByRange(ini, fim) :
						findByMonthYear(mesAno);	
			} finally {
				persistence.close();
			}
		}
		return list;
	}

	private List<Escala> findByMonthYear(String mesAno) {
		return persistence.findByNamedQuery(
							 "Escala.findByMesAno", 
							 new Object[]{
								getMes(mesAno),
								getAno(mesAno)
							 }
						   );
	}

	private List<Escala> findByRange(String ini, String fim) {
		try {
			Date dtIni = DateUtil.parse(ini, DateUtil.dd_MM_yyyy);
			Date dtFim = DateUtil.parse(fim, DateUtil.dd_MM_yyyy);
			
			return persistence.findByNamedQuery(
								 "Escala.findByPeriodo", 
								 new Object[]{
									dtIni,
									dtFim
								 }
							   );
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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






