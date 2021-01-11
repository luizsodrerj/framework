package appbuilder.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import appbuilder.entity.ComponentType;
import appbuilder.entity.DataType;
import framework.persistence.jpa.PersistenceServiceUtil;

@ManagedBean(name = "formController")
@SessionScoped
public class FormController {

	public static final String FIELDS_VIEW = "/form/formFields.xhtml";
	public static final String FORM_VIEW = "/form/form.xhtml";
	
	private PersistenceServiceUtil persis = new PersistenceServiceUtil();

	private FormBean formBean = new FormBean();

	

	public String loadForm() {
		return FORM_VIEW;
	}
	
	public String loadFormFields() {
		try {
			formBean.setCompTypes(
				persis.findAll(
					ComponentType.class, 
					null
				)
			);
			formBean.setDataTypes(
				persis.findAll(
					DataType.class, 
					null
				)
			);
			loadSelectItems();
			
		} finally {
			persis.close();
		}
		return FIELDS_VIEW;
	}

	private void loadSelectItems() {
		List<ComponentType>compTypes = formBean.getCompTypes();
		List<DataType>dataTypes 	 = formBean.getDataTypes();
		List<SelectItem>typesItems   = new ArrayList<>();
		List<SelectItem>compItems    = new ArrayList<>();
		
		for (DataType type: dataTypes) {
			typesItems.add(
				new SelectItem(
					type.getId().toString(), 
					type.getDescricao()
				)	
			);
		}
		for (ComponentType compType: compTypes) {
			compItems.add(
				new SelectItem(
					compType.getId().toString(), 
					compType.getTipo()
				)	
			);
		}
		formBean.setTypesItems(typesItems);
		formBean.setCompItems(compItems);
	}
	
	public FormBean getFormBean() {
		return formBean;
	}

	public void setFormBean(FormBean formBean) {
		this.formBean = formBean;
	}


	
}










