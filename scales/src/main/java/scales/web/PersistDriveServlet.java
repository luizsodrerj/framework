package scales.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.persistence.jpa.PersistenceServiceUtil;
import framework.util.DateUtil;
import scales.domain.ScaleDrive;

/**
 * Servlet implementation class PersistDriveServlet
 */
public class PersistDriveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistenceServiceUtil persis = new PersistenceServiceUtil();
		String inputIni				  = request.getParameter("inputIni");
		String inputFim 			  = request.getParameter("inputFim");
		String desc 			  	  = request.getParameter("desc");
		String data 	    	  	  = request.getParameter("data");
		
		try {
			persis.beginTransaction();
			
			ScaleDrive drive = new ScaleDrive();
			drive.setDriveDate(
				DateUtil.parse(
					data, 
					DateUtil.dd_MM_yyyy
				)	
			);
			SimpleDateFormat hourMin = new SimpleDateFormat("HH:mm");
			drive.setStartTime(
				new Timestamp(
				  hourMin.parse(inputIni)
				   		 .getTime()
				)
			);
			drive.setEndTime(
				new Timestamp(
				  hourMin.parse(inputFim)
				   		 .getTime()
				)
			);
			drive.setDescr(desc);
			persis.persist(drive);
			persis.commit();
			
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

	public static void main(String[] args) {
		try {
			SimpleDateFormat f = new SimpleDateFormat("HH:mm");
			Date d = f.parse("18:00");
					//DateUtil.parse("18:00", "HH"); //DateUtil.HH_mm
					
			
			System.out.println(d);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  
	}
	
}
