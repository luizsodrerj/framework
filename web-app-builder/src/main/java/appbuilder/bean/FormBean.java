package appbuilder.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import appbuilder.entity.ComponentType;
import appbuilder.entity.DataType;
import appbuilder.entity.FormField;

public class FormBean {

	private List<ComponentType>compTypes	= new ArrayList<>();
	private List<DataType>dataTypes 		= new ArrayList<>();
	private List<FormField>fields 			= new ArrayList<>();
	private List<SelectItem>typesItems   	= new ArrayList<>();
	private List<SelectItem>compItems    	= new ArrayList<>();
	private ComponentType componentType;
	private DataType dataType;
	
	private FormField field = new FormField();
	
	
	
	public List<ComponentType> getCompTypes() {
		return compTypes;
	}

	public List<DataType> getDataTypes() {
		return dataTypes;
	}

	public List<FormField> getFields() {
		return fields;
	}

	public ComponentType getComponentType() {
		return componentType;
	}

	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public void setCompTypes(List<ComponentType> compTypes) {
		this.compTypes = compTypes;
	}

	public void setDataTypes(List<DataType> dataTypes) {
		this.dataTypes = dataTypes;
	}

	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}

	public List<SelectItem> getTypesItems() {
		return typesItems;
	}

	public void setTypesItems(List<SelectItem> typesItems) {
		this.typesItems = typesItems;
	}

	public List<SelectItem> getCompItems() {
		return compItems;
	}

	public void setCompItems(List<SelectItem> compItems) {
		this.compItems = compItems;
	}

	public FormField getField() {
		return field;
	}

	public void setField(FormField field) {
		this.field = field;
	}
	
}
