package scales.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

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
	
	public static List<String> list() {
		return list;
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











