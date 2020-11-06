package appbuilder.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FORM_FIELDS database table.
 * 
 */
@Entity
@Table(name="FORM_FIELDS")
@NamedQuery(name="FormField.findAll", query="SELECT f FROM FormField f")
public class FormField implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String formato;

	private String label;

	private String tipo;

	//bi-directional many-to-one association to ComponentType
	@ManyToOne
	@JoinColumn(name="COMP_TYPE_ID")
	private ComponentType componentType;

	//bi-directional many-to-one association to Form
	@ManyToOne
	@JoinColumn(name="ID_FORM")
	private Form form;

	
	
	public FormField() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ComponentType getComponentType() {
		return this.componentType;
	}

	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		this.form = form;
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
		FormField other = (FormField) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}