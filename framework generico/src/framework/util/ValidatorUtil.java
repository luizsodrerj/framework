/*
 * Created on 21/06/2005
 */
package framework.util;

/**
 * Classe utilitaria para valida��o
 * 
 * @author Luiz Alberto
 */
public class ValidatorUtil {

	/**
	 * Valida numero de cnpj
	 * @param 	CNPJ cnpj a ser validado
	 * @return	resultado da valida��o
	 */
	public static boolean validateCnpj(String CNPJ) {
		if (CNPJ.length() < 18) {
			return false;
		}

		CNPJ 	= BusinessFormatUtil.removeFormatCpfCnpj(CNPJ);
		int g 	= CNPJ.length() - 2;

		if (testCnpj(CNPJ, g) == 1) {
			g = CNPJ.length() - 1;
			
		    if(testCnpj(CNPJ, g) == 1) {
		    	return true;
		    }
		    else {
		    	return false;
		    }
	    }
		else {
			return false;
	    }
	}
	
	public static int testCnpj(String CNPJ, int g)	{
		int verCNPJ 	= 0;
		int ind		= 2;
		 
		for(int f = g; f > 0; f--) {
		 	verCNPJ += Integer.parseInt(CNPJ.charAt(f - 1) + "") * ind;
			 	
			if(ind > 8) {
			   ind = 2;
			}
			else {
			   ind++;
			}
	  	}
	  	verCNPJ %= 11;
	  	
		if(verCNPJ==0 || verCNPJ==1) {
			verCNPJ=0;
		} else {
		   verCNPJ=11-verCNPJ;
		}

		if(verCNPJ != Integer.parseInt(CNPJ.charAt(g)+"")) {
		  	return 0;
		} else {
		  	return 1;
		}
	 }
	
	/**
	 * Verifica se o email passado como par�metro � v�lido
	 * @param 	email email a ser validado
	 * @return	true se v�lido ou false se inv�lido
	 */
	public static boolean validateEmail(String email) {
		if (email != null && !"".equals(email.trim())) {
			if (email.indexOf("@") == -1) {
				return false;
			}
			if (email.indexOf(".") == -1) {
				return false;
			}
			if (email.indexOf(",") > -1) {
				return false;
			}
			if (email.indexOf("/") > -1) {
				return false;
			}
			if (email.indexOf("\\") > -1) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Verifica se a data passada como par�metro � v�lida
	 * @param date data a ser validada
	 * @return true se v�lida ou false se inv�lida
	 */
	public static boolean validateDate(String date) {
		try {
			if (date != null && !"".equals(date.trim())) {
				if (date.length() != 10) {
					return false;
				}
				for (int i = 0; i < date.length(); i++) {
					if (date.charAt(i) == ' ') {
						return false;
					}
				}
				String newDate = new DateUtil(date).getDate(DateUtil.dd_MM_yyyy);
				
				if (!newDate.equals(date)) {
					return false;
				}
			}
		} 
		catch (Exception e) {
			return false;
		}
		return true;
	}
}









