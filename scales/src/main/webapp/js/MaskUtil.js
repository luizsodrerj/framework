/**
 * Exemplos formata��o valores monet�rios
 * 
 * <input	type="text" 
  			name="valor" 
  			onkeypress="return validateKeyNumber(this, 10, event);"
  			onkeyup="return formatMoney(this, event);"
  			onblur="return formatDecimal(this);">
  			
 * <input	type="text" 
  			name="valor" 
  			onkeypress="return validateKeyNumber(this, 10, event);"
  			onkeyup="return maskValorMonetario(this, event, 3);"
  			onblur="return formataDecimais(this, 3);">
  			
  			
 */

/**
* Para ser utilizado no evento onKeyPress
* ex: <input type="text" name="xyz" onkeypress="return maskField(this, '99999-999', event);">
* CEP    -> 99999-999
* CPF    -> 999.999.999-99
* CNPJ   -> 99.999.999/9999-99
* C/C    -> 999999-!
* Tel    -> (99) 9999-9999
* Hora   -> 99:99:99 ou 99:99
* Numero -> R$ 99.999,99
*/
function maskField(campo, sMask, event) {

	var i, nCount, sValue, fldLen, mskLen,bolMask, sCod, nTecla;
	
	if(document.all) { // Internet Explorer
		nTecla = event.keyCode;
	} else { // Nestcape
		nTecla = event.charCode;
	}
	
	if(nTecla == 39) return false;

	sValue = campo.value;
	fldLen = sValue.length;
	mskLen = sMask.length;

	if (nTecla >= 32) { // Caracteres acima de espa�o
		if (fldLen < mskLen) {
			if ((sMask.charAt(fldLen) != "!") && (sMask.charAt(fldLen) != "9")) {
				sValue = sValue + sMask.charAt(fldLen);
				campo.value = sValue;
				if ((nTecla < 48) || (nTecla > 57)) {
					return false;
				}
			} else {
				if (sMask.charAt(fldLen) == "9") {
					if ((nTecla < 48) || (nTecla > 57)) {
						return false;
					}
				}
			}
			return true;
		} else {
			return false;
		}
	} else { // Caracteres de controle
		return true; 
	}

}

	function onKeyPressPhoneMask(campo, event) {
		var sValue, fldLen, mskLen, nTecla;
		
		if(document.all) { // Internet Explorer
			nTecla = event.keyCode;
		} else { // outros
			nTecla = event.charCode;
		}
		if (nTecla == 39) { 
			return false;
		}
		nineDgtMsk	= '99999-9999';
		eightDgtMsk	= '9999-9999';
		sValue		= campo.value;
		fldLen 		= sValue.length;
		sMask		= '';
	
		if (nTecla >= 32) { // Caracteres acima de espaco
			if (fldLen < 9) {
				return maskField(campo, eightDgtMsk, event);
			} else {
				sMask	= nineDgtMsk;
				mskLen 	= sMask.length;
				
				if (fldLen < mskLen) {
					if ((nTecla < 48) || (nTecla > 57)) {
						return false;
					} else {
						caracter	= String.fromCharCode(nTecla);
						sValue 		= removeTraco(sValue);
						sValue 		= sValue + caracter;
						campo.value = mask(sValue, sMask);
					}
				} 
				return false;
			}
		} else { // Caracteres de controle
			return true; 
		}
	}

	function onKeyUpPhoneMask(campo, event) {
		var sValue, fldLen, mskLen, nTecla;
		
		nTecla = event.keyCode;
		
		if (nTecla == 39) { 
			return false;
		}
		nineDgtMsk	= '99999-9999';
		eightDgtMsk	= '9999-9999';
		sValue		= campo.value;
		fldLen 		= sValue.length;
		sMask		= '';
		delKey 		= 46;  
		backKey		= 8;  
		
		if (nTecla == delKey || nTecla == backKey) { 
			if (fldLen == 9) {
				sValue 	= removeTraco(sValue);
				campo.value = mask(sValue, eightDgtMsk);
			} 
		}
		return true;
	}
	
	function mask(value, sMask) {
		sValue = '';
		
		if (value == null) {
			return value;
		}
		sValue = value;
		
		if (sValue == '') {
			return sValue;
		}
		vlLen 	= sValue.length;
		masked	= '';
		count	= 0;
		
		masked = masked + sValue.charAt(count); 
		
		while (masked.length < sMask.length) {
			mskLen	= masked.length;
			cmsk 	= sMask.charAt(mskLen);
			
			if (cmsk != '9') {
				masked = masked + cmsk;	
			} 
			count++;
			
			if (count < vlLen) {
				masked = masked + sValue.charAt(count);
			} else {
				break;
			}
		}
		return masked;
	}
	
	
	/**
	 * Use o evento onKeyPress para executar esta fun��o
	 * ex.: <input type="text" name="xyz" onkeypress="return validateKeyNumber(this, 10, event);">
	 * 
	 * @param field					campo a ser mascarado
	 * @param maxCharBeforDecimal	maximo de caracteres antes da virgula, incluindo os pontos separadores de milhar
	 * @param event					evento do teclado capturado	
	 * 
	 */
	function validateKeyNumber(field, maxCharBeforDecimal, event) {
		KEY_VIRGULA = 44;
		KEY_NOVE 	= 57;
		KEY_ZERO 	= 48;
		
		var valor = field.value;
		var key;

		if(document.all) { // Internet Explorer
			key = event.keyCode;
		}  else { // Nestcape
			key = event.charCode;
		}

		if (key == KEY_VIRGULA) {
			if (valor == '') {
				return false;
			}  else if (findVirgula(valor)) {
				return false;
			}
		}  else if (key < KEY_ZERO || key > KEY_NOVE) {
			if (key > 0) {
				return false;
			}
		}
		if (valor.length == maxCharBeforDecimal && !findVirgula(valor)) {
			if(key != KEY_VIRGULA && key != 0) {
				return false;
			}
		}
		if (valor.length == 1 && valor.substring(0, 1) == '0') {
			if(key != KEY_VIRGULA && key != 0) {
				return false;
			}
		}
		return true;
	}

	function validateIntNumber(field, event) {
		var KEY_VIRGULA = 44;
		var KEY_NOVE 	= 57;
		var KEY_ZERO 	= 48;
		var key			= getKeyCode(event);
		var valor		= field.value;

		if (key == KEY_VIRGULA) {
			return false;
		}
		if (key < KEY_ZERO || key > KEY_NOVE) {
			if (key > 0) {
				return false;
			}
		}
		if (valor.length == 0 && key == KEY_ZERO) {
			return false;
		}
		return true;
	}
	
	/**
	 * Use o evento onKeyUp para executar esta fun��o
	 * ex.: <input type="text" name="xyz" onkeyup="return formatMoney(this, event);">
	 *
	 * @param field		campo a ser mascarado
	 * @param event		evento do teclado capturado	
	 *
	 */
	function formatMoney(field, event)	{
		var key;
		var valor = field.value;

		key = document.all ? event.keyCode : event.charCode;
		
		if (valor.length >= 3 && valor.substring(0, 1) == '0') {
			if (valor.substring(1, 2) != ',') {
				field.value = valor.substring(1, valor.length);
			}
		}
		indiceVirgula = valor.indexOf(',');
		
		if (indiceVirgula > -1) {
			decimal = valor.substring(indiceVirgula + 1, valor.length);
			inteiro = valor.substring(0, indiceVirgula);
			
			if (decimal.length > 2) {
				decimal = valor.substring(indiceVirgula, indiceVirgula + 3);
				field.value = inteiro + decimal;
			}
		}
		if (key != 36 && key != 37 && key != 39 && key != 38 &&	key != 40 ) {
			formatNumber(field);
		}
		return true;
	}

	/**
	 * Use o evento onKeyUp para executar esta fun��o
	 * ex.: <input type="text" name="xyz" onkeyup="return maskValidaValorMonetario(this, event, 15);">
	 *
	 * @param field			campo a ser mascarado
	 * @param event			evento do teclado capturado	
	 * @param tamanhoMaximo tamanho m�ximo do valor	incluindo os pontos
	 *
	 */
	function maskValidaValorMonetario(field, event, tamanhoMaximo)	{
		var key;
		var valor = field.value;

		key = document.all ? event.keyCode : event.charCode;
		
		if (valor.length >= 3 && valor.substring(0, 1) == '0') {
			if (valor.substring(1, 2) != ',') {
				field.value = valor.substring(1, valor.length);
			}
		}
		indiceVirgula = valor.indexOf(',');
		
		if (indiceVirgula > -1) {
			decimal = valor.substring(indiceVirgula + 1, valor.length);
			inteiro = valor.substring(0, indiceVirgula);
			
			if (decimal.length > 2) {
				decimal = valor.substring(indiceVirgula, indiceVirgula + 3);
				field.value = inteiro + decimal;
			}
		}
		if (key != 36 && key != 37 && key != 39 && key != 38 &&	key != 40 ) {
			formataValidaValorMonetario(field, tamanhoMaximo);
		}
		return true;
	}
	
	
	/**
	 * Use o evento onKeyUp para executar esta fun��o
	 * ex.: <input type="text" name="xyz" onkeyup="return maskValorMonetario(this, event, 3);">
	 *
	 * @param field			campo a ser mascarado
	 * @param event			evento do teclado capturado	
	 * @param casasDecimais n�mero de casas decimais
	 *
	 */
	function maskValorMonetario(field, event, casasDecimais) {
		var key;
		var valor = field.value;

		key = document.all ? event.keyCode : event.charCode;
		
		if (valor.length >= 3 && valor.substring(0, 1) == '0') {
			if (valor.substring(1, 2) != ',') {
				field.value = valor.substring(1, valor.length);
			}
		}
		indiceVirgula = valor.indexOf(',');
		
		if (indiceVirgula > -1) {
			decimal = valor.substring(indiceVirgula + 1, valor.length);
			inteiro = valor.substring(0, indiceVirgula);
			
			if (decimal.length > casasDecimais) {
				decimal = valor.substring(indiceVirgula, indiceVirgula + casasDecimais + 1);
				field.value = inteiro + decimal;
			}
		}
		if (key != 36 && key != 37 && key != 39 && key != 38 &&	key != 40 ) {
			formatNumber(field);
		}
		return true;
	}
	
	function formataValidaValorMonetario(field, tamanhoMaximo) {
		arrNum 	= new Array();
		valor	= field.value;
		dec 	= '';

		if (findVirgula(valor)) {
			indVirg	= valor.indexOf(',');
			dec 	= valor.substring(indVirg, valor.length);
			valor   = valor.substring(0, indVirg);
		}
		valor = removePonto(valor);

		if (dec != '' && dec.indexOf('.') > -1) {
			dec = removePonto(dec);
		}
		div = valor / 1000;

		if (div >= 1) {
			valorFmt	= formataMilhar(valor, arrNum, dec);
			vlrInt 		= getValorInteiro(valorFmt);
			
			if (vlrInt.length > tamanhoMaximo) {
				while (vlrInt.length > tamanhoMaximo) {
					vlrInt 		= removePonto(vlrInt);
					arrNum 		= new Array();
					vlrInt		= vlrInt.substring(0, vlrInt.length - 1);
					valorFmt	= formataMilhar(vlrInt, arrNum, dec);
					vlrInt 		= getValorInteiro(valorFmt);
				}
			}
			field.value = valorFmt;
		} else if (valor.length > 3) {
			int = removeZerosAEsquerda(valor);
			field.value = int + dec;
			
			if (field.value.indexOf('.') > -1) {
				valor = removeQualquerPonto(valor);
				valor = removeZerosAEsquerda(valor);
				field.value = valor + dec;
			}
		} else if (valor.length <= 3) {
			if (field.value.indexOf('.') > -1) {
				field.value = valor + dec;
			}
		} 
	}

	function getValorInteiro(valor) {
		inteiro = '';
		
		if (findVirgula(valor)) {
			indVirg	= valor.indexOf(',');
			inteiro = valor.substring(0, indVirg);
		} else {
			inteiro = valor;
		}
		return inteiro;
	}
	
	function formatNumber(field)  {
		arrNum 	= new Array();
		valor	= field.value;
		dec 	= '';

		if (findVirgula(valor)) {
			indVirg	= valor.indexOf(',');
			dec 	= valor.substring(indVirg, valor.length);
			valor   = valor.substring(0, indVirg);
		}
		valor = removePonto(valor);

		if (dec != '' && dec.indexOf('.') > -1) {
			dec = removePonto(dec);
		}
		div = valor / 1000;

		if (div >= 1) {
			valorFmt	= formataMilhar(valor, arrNum, dec);
			field.value = valorFmt;
		} else if (valor.length > 3) {
			int = removeZerosAEsquerda(valor);
			field.value = int + dec;
			
			if (field.value.indexOf('.') > -1) {
				valor = removeQualquerPonto(valor);
				valor = removeZerosAEsquerda(valor);
				field.value = valor + dec;
			}
		} else if (valor.length <= 3) {
			if (field.value.indexOf('.') > -1) {
				field.value = valor + dec;
			}
		} 
	}

	function formataMilhar(valor, arrNum, dec) {
		var formattedValue = '';
		var j 			   = 0;

		for(i = valor.length; i > 0; i = i - 3) {
			if (i - 3 > 0) {
				aux = valor.substring(i - 3, i);
				arrNum[j++] = '.' + aux;
			} else {
				aux = valor.substring(0, i);
				arrNum[j++] = aux;
			}
		}
		for(k = j - 1; k >= 0; k--) {
			formattedValue += arrNum[k];
		}
		return formattedValue + dec;
	}
	
	function removeZerosAEsquerda(valor) {
		firstChar = valor.substring(0, 1);
		
		while (firstChar == '0') {
			valor = valor.substring(1, valor.length);
			firstChar = valor.substring(0, 1);
		}
		return valor;
	}
	
	function removePonto(value)	{
		for(j = 0; j < value.length; j++) {
			if (value.indexOf('.') > 0) {
				j 	 	= value.indexOf('.');
				aux1 	= value.substring(0, j);
				aux2 	= value.substring(j + 1, value.length);
				value	= aux1 + aux2;
			}
		}
		return value;
	}

	function removeTraco(value)	{
		for(j = 0; j < value.length; j++) {
			if (value.indexOf('-') > 0) {
				j 	 	= value.indexOf('-');
				aux1 	= value.substring(0, j);
				aux2 	= value.substring(j + 1, value.length);
				value	= aux1 + aux2;
			}
		}
		return value;
	}
	
	function removeQualquerPonto(value)	{
		for(j = 0; j < value.length; j++) {
			if (value.indexOf('.') > -1) {
				j 	 	= value.indexOf('.');
				aux1 	= value.substring(0, j);
				aux2 	= value.substring(j + 1, value.length);
				value	= aux1 + aux2;
			}
		}
		return value;
	}
	
	function findVirgula(pDado)	{
		if(pDado.indexOf(',') > -1) {
			return true;
		}
		return false;
	}

	/**
	 * Use esta fun��o no evento onblur
	 * ex.: <input type="text" name="xyz" onblur="return formatDecimal(this);">
	 *
	 * @param textField	 campo a ser mascarado
	 *
	 */
	function formatDecimal(textField) {
		value 	= textField.value;
		indVirg	= value.indexOf(',');
		isEmpty	= value == '';
		
		if (isEmpty) {
			return true;
		}
		if (indVirg == -1) {
			value 			= value + ',00';
			textField.value = value;
		} else if (indVirg == value.length - 1) {
			value 			= value + '00';
			textField.value = value;
		} if (indVirg == 0) {
			textField.value = '0' + value;
		} else {
			decimal = value.substring(indVirg + 1, value.length);
			
			if (decimal.length < 2) {
				textField.value = value + '0';
			}
		}
	}

	/**
	 * Use esta fun��o no evento onblur
	 * ex.: <input type="text" name="xyz" onblur="return formataDecimais(this, 4);">
	 *
	 * @param textField	 	campo a ser mascarado
	 * @param casasDecimais	quantidade de casas decimais
	 *
	 */
	function formataDecimais(textField, casasDecimais) {
		value 	= textField.value;
		indVirg	= value.indexOf(',');
		isEmpty	= value == '';
		
		if (isEmpty) {
			return true;
		}
		
		qtZeros = preencheZeros(casasDecimais);
		
		if (indVirg == -1) {
			value 			= value + ',' + qtZeros;
			textField.value = value;
		} else if (indVirg == value.length - 1) {
			value 			= value + qtZeros;
			textField.value = value;
		} if (indVirg == 0) {
			textField.value = '0' + value;
		} else {
			decimal = value.substring(indVirg + 1, value.length);
			
			if (decimal.length < casasDecimais) {
				qtZeros = preencheZeros(casasDecimais - decimal.length);
				textField.value = value + qtZeros;
			}
		}
	}
	
	function preencheZeros(qtdZeros) {
		zeros = '';
		
		for (var i = 0; i < qtdZeros; i++) {
			zeros = zeros + '0'; 
		}
		return zeros;
	}
	
	/**
	 * Use esta fun��o no evento onblur
	 * ex.: <input type="text" name="xyz" onblur="return formatDecimal1(this);">
	 *
	 * @param textField	 campo a ser mascarado
	 *
	 */
	function formatDecimal1(textField) {
		value 	= textField.value;
		indVirg	= value.indexOf(',');
		isEmpty	= value == '';
		
		if (isEmpty) {
			return true;
		}
		if (indVirg == -1) {
			value 			= value + ',0';
			textField.value = value;
		}
		else if (indVirg == value.length - 1) {
			value 			= value + '0';
			textField.value = value;
		}
	}


	function getKeyCode(event) {
		return	document.all ? 
				event.keyCode :
				event.charCode;
	}









