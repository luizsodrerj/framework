package br.com.confidencecambio.javabasico.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IMCController {

	
    @RequestMapping(value = "/calcula-imc", method = RequestMethod.GET)
    public ResponseEntity<String> calculaIMC(
    								@RequestParam(value = "peso", required = true) 
    								String peso,
    								@RequestParam(value = "altura", required = true) 
    								String altura
    							  ) {
        BigDecimal quadAlt = new BigDecimal(altura).pow(2); 
        BigDecimal varPeso = new BigDecimal(peso); 
        BigDecimal result  = varPeso.divide(quadAlt, 2, RoundingMode.UP);
         
        var retorno = result.toString();

        
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }


}






