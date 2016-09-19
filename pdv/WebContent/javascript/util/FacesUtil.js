
	function FacesUtil_existsComponent(componentName) {
		var component = FacesUtil_byName(componentName);
		
		return component != null;
	}

	function FacesUtil_byName(componentName) {
		var forms 	  = document.forms;
		var component = null;	 
		
		for (var i = 0; i < forms.length; i++) {
			component = FacesUtil_getByName(
							forms[i].id,
							componentName
						);
			if (component != null) {
				break;
			} else {
				component = FacesUtil_comboByName(
								forms[i].id,
								componentName
							);
				if (component != null) {
					break;
				}
			}
		}
		return component;
	}

	function FacesUtil_getByName(formName, componentName) {
		return jQuery(
				 "input[name='" + 
				 formName +":" + 
				 componentName +
				 "']"
			   )[0];
	}
	
	function FacesUtil_comboByName(formName, componentName) {
		return jQuery(
				 "select[name='" + 
				 formName +":" + 
				 componentName +
				 "']"
			   )[0];
	}	
	
	
	
	
	
	