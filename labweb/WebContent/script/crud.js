
	var MSG_REQUIRED = 'Campo Obrigat&oacute;rio.';
	
	
	function validateClickBtSalvar() {
		var arrayExigencia 	= jQuery("input[name='nome']");
		var arraySpan		= jQuery(".errorSpan");
		var erros			= 0;
		
		jQuery(".errorSpan").html('');
		
		for (var i = 0; i < arrayExigencia.length; i++) {
			if (arrayExigencia[i].value == '') {
				arraySpan[i].innerHTML = MSG_REQUIRED;
				erros++;
			}
		} 
		
		if (erros > 0) {
			return false;
		}
		return true;
	}