package link.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import link.entity.Link;

@ManagedBean(name = "corpBean")
@SessionScoped
public class CorpBean extends BaseBean {

	private List<Link>links = new ArrayList<Link>();

	private String searchParameter;
	
	private Link link = new Link();
	
	private List<SelectItem>corps = new ArrayList<SelectItem>();
	
	
	
	public String displayCorpLinks() {
		loadCorps();
		
		return "/CorpLinks.xhtml";
	}
	
	private void loadCorps() {
		try {
			persistence.connect();
			corps.clear();
			
			List<Object>result = persistence.getEntityManager()
											.createNamedQuery(Link.FILTER_BY_CORP)
										   	.getResultList();
			for (Object corp : result) {
				corps.add(
					new SelectItem(
						corp.toString(), 
						corp.toString()
					)	
				);
			}
		} finally {
			persistence.close();
		}
	}
	
	
	public String removeLink() {
		remove();
		
		searchParameter = "";
		
		return search();
	}
	
	public String search() {
		String desc = searchParameter.toUpperCase();
		String url  = searchParameter;
		
		try {
			links = persistence.findByNamedQuery(
							Link.FIND_BY_CORP, 
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
	
	public List<SelectItem> getCorps() {
		return corps;
	}
	
	
	
}
