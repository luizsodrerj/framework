package agendaweb.faces;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import agendaweb.persistence.PersistenceService;

/**
 * Servlet implementation class StartupServlet
 */
public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		PersistenceService persistence = new PersistenceService();
		try {
			persistence.connect();
		} finally {
			persistence.close();
		}
	}

}
