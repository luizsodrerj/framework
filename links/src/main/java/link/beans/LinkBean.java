package link.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import link.entity.Link;
import link.persistence.PersistenceService;

@ManagedBean(name = "linkBean")
@SessionScoped
public class LinkBean {

	private PersistenceService persistence = new PersistenceService();
	
	private List<Link>links = new ArrayList<Link>();

	private Link link = new Link();
	
	
	
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
	
	
	
	
	
}
