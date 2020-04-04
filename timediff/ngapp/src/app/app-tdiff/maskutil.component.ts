import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-mask',
    template: `<label></label>`
})
export class MaskUtilComponent implements OnInit {

    constructor() { }

    ngOnInit() {
    }
 
    
    /**
     * Exemplo formatacao valores monetarios
     * 
     * <input	type="text" 
     * 			name="valor" 
                onkeypress="return validateKeyNumber(this, 10, event);"
                onkeyup="return formatMoney(this, event);"
                onblur="return formatDecimal(this);">
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
    public maskField(campo: any, sMask: any, event: any) {

        let i: any;
        let nCount: any; 
        let sValue: any; 
        let fldLen: any; 
        let mskLen: any;
        let bolMask: any; 
        let sCod: any; 
        let nTecla: any;
        
        if (document.all) { // Internet Explorer
            nTecla = event.keyCode;
        } else { // Nestcape
            nTecla = event.charCode;
        }
        
        if (nTecla == 39) {
            event.target.disabled = true;
        }

        sValue = event.key;
        fldLen = sValue.length;
        mskLen = sMask.length;

        if (nTecla >= 32) { // Caracteres acima de espaco
            if (fldLen < mskLen) {
                if ((sMask.charAt(fldLen) != "!") && (sMask.charAt(fldLen) != "9")) {
                    sValue = sValue + sMask.charAt(fldLen);
                    campo = sValue;

                    if ((nTecla < 48) || (nTecla > 57)) {
                        event.target.disabled = true;
                    }
                } else {
                    if (sMask.charAt(fldLen) == "9") {
                        if ((nTecla < 48) || (nTecla > 57)) {
                            event.target.disabled = true;
                        }
                    }
                }
                return campo;
            } else {
                event.target.disabled = true;
            }
        } else { // Caracteres de controle
            return campo; 
        }

    }


}