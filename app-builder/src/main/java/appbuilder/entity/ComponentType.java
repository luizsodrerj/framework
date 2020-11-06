package appbuilder.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the COMPONENT_TYPE database table.
 * 
 */
@Entity
@Table(name="COMPONENT_TYPE")
@NamedQuery(name="ComponentType.findAll", query="SELECT c FROM ComponentType c")
public class ComponentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String tipo;

	//bi-directional many-to-one association to FormField
	@OneToMany(mappedBy="componentType")
	private List<FormField> formFields = new ArrayList<>();

	
	
	public ComponentType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<FormField> getFormFields() {
		return this.formFields;
	}

	public void setFormFields(List<FormField> formFields) {
		this.formFields = formFields;
	}

	public FormField addFormField(FormField formField) {
		getFormFields().add(formField);
		formField.setComponentType(this);

		return formField;
	}

	public FormField removeFormField(FormField formField) {
		getFormFields().remove(formField);
		formField.setComponentType(null);

		return formField;
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
		ComponentType other = (ComponentType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}