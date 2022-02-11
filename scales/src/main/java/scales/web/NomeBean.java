package scales.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class NomeBean {

	private static List<String> list = Arrays.asList(
											new String[] {
												"Walter Moscardini",
												"Luiz Sodre",
												"Rhawan Brenner",
												"Américo Afonso",
												"Helder Nogueira"
											}
										);		
	
	private HttpServletRequest request;

	
	
	public NomeBean() {
	}
	
	public static List<String> list() {
		return list;
	}

	public void setRequest(HttpServletRequest request) {
		ServletContext context  = request.getSession().getServletContext(); 
		this.request 			= request;
		
		NomeBean.nomes(context);
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	
	public static List<String> nomes(ServletContext context) {
		List<String>list = (List<String>)context.getAttribute("nomes");
		
		if (list == null) {
			context.setAttribute("nomes",NomeBean.list);
			list = NomeBean.list;
		}
		return list;
	}
	
}











