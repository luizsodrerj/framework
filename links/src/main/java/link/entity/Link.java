package link.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "LINK")
@NamedQueries({
	@NamedQuery(
		name  = Link.FIND_BY_FILTER,
		query =   "SELECT o "
				+ "FROM   Link o "
				+ "WHERE  (upper(o.descricao) LIKE ? "
				+ "OR     o.url LIKE ?)  "
				+ "AND 	  o.corp IS NULL "
	),
	@NamedQuery(
		name  = Link.FIND_BY_CORP,
		query =   "SELECT o "
				+ "FROM   Link o "
				+ "WHERE  (upper(o.descricao) LIKE ? "
				+ "OR     o.url LIKE ?)  "
				+ "AND 	  o.corp IS NOT NULL "
				+ "ORDER BY o.corp "
	),
	@NamedQuery(
		name  = Link.FILTER_BY_CORP,
		query =   "SELECT DISTINCT lk.corp "
				+ "FROM   Link lk "
				+ "WHERE  lk.corp IS NOT NULL "
				+ "ORDER BY lk.corp "
	)	
})
public class Link implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_FILTER = "link.findByFilter";
	public static final String FILTER_BY_CORP = "link.filterByCorp";
	public static final String FIND_BY_CORP = "link.findByCorp";
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	private String url;

	private String corp;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
