package link.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import link.entity.Co;
import link.entity.Link;

@ManagedBean(name = "linkBean")
@SessionScoped
public class LinkBean extends BaseBean {

	private List<SelectItem>cos = new ArrayList<SelectItem>();
	private List<Link>links = new ArrayList<Link>();
	
	private String searchParameter;
	
	private Link link = new Link();
	

	
	public String newLink() {
		cos.clear();
		
		List<Co>list = persistence.findAll(Co.class);
		for (Co co : list) {
			cos.add(
				new SelectItem(
					co.getNome(), 
					co.getNome()
				)	
			);
		}
		return "/linkForm.xhtml";		
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
	
	public List<SelectItem> getCos() {
		return cos;
	}	
	
	
	
}
