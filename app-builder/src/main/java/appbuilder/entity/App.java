package appbuilder.entity;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * App entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP")
public class App implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -7223529662430141356L;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NOME_APP", nullable = false, length = 300)	
	private String nomeApp;

	
	
	// Constructors

	/** default constructor */
	public App() {
	}

	/** full constructor */
	public App(Integer id, String nomeApp) {
		this.id = id;
		this.nomeApp = nomeApp;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeApp() {
		return this.nomeApp;
	}

	public void setNomeApp(String nomeApp) {
		this.nomeApp = nomeApp;
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
		App other = (App) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}