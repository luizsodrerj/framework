package scales.web;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import framework.persistence.jpa.PersistenceServiceUtil;
import framework.util.DateUtil;
import scales.domain.Escala;

/**
 * Servlet implementation class PersistScaleServlet
 */
public class PersistScaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersistScaleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistenceServiceUtil persis = new PersistenceServiceUtil();
		String colaborador 			  = request.getParameter("colaborador");
		String inputIni 	    	  = request.getParameter("inputIni");
		String inputFim 			  = request.getParameter("inputFim");
		String id					  = request.getParameter("id");
		
		try {
			if (StringUtils.isNotEmpty(id)) {
				update(persis, colaborador, inputIni, inputFim, id);
			} else {
				persist(persis, colaborador, inputIni, inputFim);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			persis.close();
		}
		request.getRequestDispatcher("/index.jsp")
			   .forward(
				  request, 
				  response
				);
	}

	private void update(
					PersistenceServiceUtil persis, 
					String colaborador, 
					String inputIni, 
					String inputFim,
					String id
				 )  throws ParseException {
		persis.beginTransaction();
		
		Escala escala = persis.findObject(Escala.class,Integer.valueOf(id));
		escala.setNomePlantonista(colaborador);
		escala.setInicio(
			DateUtil.parse(
				inputIni, 
				DateUtil.dd_MM_yyyy
			)	
		);
		escala.setFim(
			DateUtil.parse(
				inputFim, 
				DateUtil.dd_MM_yyyy
			)	
		);
		persis.merge(escala);
		persis.commit();
	}

	private void persist(
					PersistenceServiceUtil persis, 
					String colaborador, 
					String inputIni, 
					String inputFim
				 )  throws ParseException {
		persis.beginTransaction();
		
		Escala escala = new Escala();
		escala.setNomePlantonista(colaborador);
		escala.setInicio(
			DateUtil.parse(
				inputIni, 
				DateUtil.dd_MM_yyyy
			)	
		);
		escala.setFim(
			DateUtil.parse(
				inputFim, 
				DateUtil.dd_MM_yyyy
			)	
		);
		persis.persist(escala);
		persis.commit();
	}

}









