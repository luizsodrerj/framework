package framework.util;

import java.math.BigDecimal;

/**
 *
 * Utilit�rio para formata��o de neg�cios
 *
 * <p>Date:	08/12/2005 </p>
 * @author 	Luiz Alberto
 */
public class BusinessFormatUtil {

	
    /**
     * Construtor vazio para evitar instancias
     * desnecessarias da classe
     */
	private BusinessFormatUtil() {
	}
	
    /**
     * Aplica a m�scara de cnpj a uma string passada como par�metro 
     * @param 	cnpj	string a ser formatada
     * @return	string formatada com a m�scara de cnpj 
     */
    public static String formatCnpj(String cnpj) {
    	StringBuffer formattedCnpj = new StringBuffer();
    	
    	if (cnpj != null && !"".equals(cnpj.trim()) && !"null".equals(cnpj)) {
    		try {
				new BigDecimal(cnpj);
			} 
    		catch (NumberFormatException e) {
				return cnpj;
			}
    		cnpj = StringUtil.lPad(cnpj, 14, "0");
    		    		
    		for (int i = 0; i < cnpj.length(); i++) {
    			if (i == 2 || i == 5) {
    				formattedCnpj.append(".");
				}
    			if (i == 8) {
    				formattedCnpj.append("/");
				}
    			if (i == 12) {
    				formattedCnpj.append("-");
				}
    			formattedCnpj.append(cnpj.charAt(i));
			}
		}
    	String numCnpj = formattedCnpj.toString();
    	
    	return numCnpj != null && !"".equals(numCnpj) ? numCnpj : cnpj;
    }

    /**
     * Remove formata��o de CPF e CNPJ
     * @param 	value valor formatado
     * @return	valor sem formata��o
     */
    public static String removeFormatCpfCnpj(String value) {
    	int 			i 			= 0;
    	StringBuffer	noFormat	= new StringBuffer();
		
    	if (value == null || "".equals(value)) {
        	return value;
		}
    	
    	while (i < value.length()) {
    		boolean isNotEqualsPonto = value.charAt(i) != '.';
    		boolean isNotEqualsTraco = value.charAt(i) != '-';
    		boolean isNotEqualsBarra = value.charAt(i) != '/';
    		
    		if (isNotEqualsPonto && isNotEqualsTraco && isNotEqualsBarra) {
    			noFormat.append(value.charAt(i));
			}
    		i++;
		}
    	return noFormat.toString();
    }

    /**
     * Remove formata��o de CEP
     * @param 	value valor formatado
     * @return	valor sem formata��o
     */
    public static String removeFormatCep(String value) {
    	int 			i 			= 0;
    	StringBuffer	noFormat	= new StringBuffer();
		
    	if (value == null || "".equals(value)) {
        	return value;
		}
    	
    	while (i < value.length()) {
    		boolean isNotEqualsPonto = value.charAt(i) != '.';
    		boolean isNotEqualsTraco = value.charAt(i) != '-';
    		
    		if (isNotEqualsPonto && isNotEqualsTraco) {
    			noFormat.append(value.charAt(i));
			}
    		i++;
		}
    	return noFormat.toString();
    }

}
