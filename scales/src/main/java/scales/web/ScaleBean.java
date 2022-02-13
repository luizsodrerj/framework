package scales.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import framework.persistence.jpa.PersistenceServiceUtil;
import framework.util.DateUtil;
import scales.domain.Escala;

public class ScaleBean {

	private HttpServletRequest request;
	
	private Escala scale = new Escala(); 
	
	
	public ScaleBean() {
	}

	private void populate() {
		String id = request.getParameter("id");
		
		scale.setNomePlantonista("");
		
		if (StringUtils.isNotEmpty(id)) {
			PersistenceServiceUtil persis = new PersistenceServiceUtil();
			try {
				Escala esc = persis.findObject(Escala.class,Integer.valueOf(id));
				scale.setNomePlantonista(esc.getNomePlantonista());
				scale.setInicio(esc.getInicio());
				scale.setFim(esc.getFim());
			} finally {
				persis.close();
			}
		}
	}
	
	public String getInicio() {
		return  scale.getInicio() != null ?
				DateUtil.format(scale.getInicio(), DateUtil.dd_MM_yyyy) :
				"";	
	}

	public String getFim() {
		return  scale.getFim() != null ?
				DateUtil.format(scale.getFim(), DateUtil.dd_MM_yyyy) :
				"";	
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
		populate();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public Escala getScale() {
		return scale;
	}
}











