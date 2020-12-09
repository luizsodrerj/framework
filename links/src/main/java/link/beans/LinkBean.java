package link.beans;

import java.util.ArrayList;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import link.entity.Link;
import link.persistence.PersistenceService;

@ManagedBean(name = "linkBean")
@SessionScoped
public class LinkBean {

	private PersistenceService persistence = new PersistenceService();
	
	private List<Link>links = new ArrayList<Link>();

	private String searchParameter;
	
	private Link link = new Link();
	

	
	public String removeLink() {
		try {
			HttpServletRequest request = (HttpServletRequest)         
										 FacesContext.getCurrentInstance()
										 			 .getExternalContext()
										 			 .getRequest();
			String linkId = request.getParameter("linkId");
			Integer id 	  = Integer.valueOf(linkId);
			
			persistence.beginTransaction();
			Link link = persistence.findObject(Link.class,id);
			persistence.remove(link);
			persistence.commit();
			
		} finally {
			persistence.close();
		}
		searchParameter = "";
		
		return search();
	}
	
	public String search() {
		String desc = searchParameter.toUpperCase();
		String url  = searchParameter;
		
		try {
			links = persistence.findByNamedQuery(
							Link.FIND_BY_FILTER, 
							new Object[] {
								"%" + desc + "%",
								"%" + url + "%"
							}
						);
		} finally {
			persistence.close();
		}
		return "/linksList.xhtml";
	}
	
	public String createLink() {

		link.setId(null);

		persistence.persistAndCommit(link);
		
		link = new Link();

		return findAllLinks();
	}
	
	public String findAllLinks() {
		try {
			links = persistence.findAll(Link.class);
		} finally {
			persistence.close();
		}
		return "/linksList.xhtml";
	}
	
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public String getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(String searchParameter) {
		this.searchParameter = searchParameter;
	}
	
	
	
	
	
}
