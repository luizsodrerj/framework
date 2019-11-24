package framework.util;
 
import java.math.BigDecimal;

/**
 * Faz todos os tratamentos relacionados a n�meros.
 * @author  Luiz Alberto Sodr�
 * @date    24/01/2005
 */
public class NumberUtil {

  /**
   * Se a String recebida for nula, retorna a String retornada
   * @param pStrRecebida   String a ser verificada
   * @param pIntRetornada  String a ser retornada em caso da primeira ser nula
   * @return  A String recebida, se esta for diferente de nulo. String Retornada, caso contrario.
   * @throws java.lang.NumberFormatException Lan�ada quando a String a ser verificada n�o � um n�mero inteiro
   */
  public static int nvl(String pStrRecebida, int pIntRetornada) throws NumberFormatException {
    if ((pStrRecebida == null) || (pStrRecebida.equals("null")) || (pStrRecebida.equals(""))) {
        return pIntRetornada;
    } else {
        return Integer.parseInt(pStrRecebida);
    }
  }
  
  /**
   * Troca virgula (',') por ponto ('.') 
   * @param texto a ser formatado
   * @return numero com virgula (',') por ponto ('.') 
   */
  	public static String removeFormat(String texto) {
		if (texto != null) {
			int 	n 		= 0;
			String 	temp	= "";

			while (n < texto.length()) {
				char c = texto.charAt(n);

				if (c == ',') {
					temp = temp + ".";
				} else {
					if (c == '.') {
						n++;
						temp = temp + texto.charAt(n);
					} else
						temp = temp + c;
				}
				n++;
			}
			return temp;
		}
		return null;
	}

  /**
	 * Trunca um n�mero de acordo com o n�mero de casas passado como par�metro
	 * 
	 * @param pDblNumber
	 *            N�mero a ser truncado
	 * @param pIntNumCasas
	 *            N�mero de casas
	 * @return N�mero truncado
	 */
  public static double trunca(double pDblNumber, int pIntNumCasas) {
    double fator    = Math.pow(10, pIntNumCasas);     
    double valorAux = pDblNumber * fator;
    valorAux        = Math.floor(valorAux);
    
    return          valorAux / fator;
  }
  
  /**
   * Trunca um n�mero de acordo com o n�mero de casas passado
   * como par�metro       
   * @param pBgdNum       N�mero a ser truncado
   * @param pIntNumCasas  N�mero de casas
   * @return              N�mero truncado
   */
  public static BigDecimal trunca(BigDecimal pBgdNum, int pIntNumCasas) {
    double fator    = Math.pow(10, pIntNumCasas);     
    double valorAux = pBgdNum.doubleValue() * fator;
    valorAux        = Math.floor(valorAux);
    valorAux        = valorAux / fator;
    
    return new BigDecimal(valorAux);
  }

  /**
   * Arredonda valores de acordo com o numero de casas decimais passados por parametro
   * @param plnNum             double a ser arredondado
   * @param pIntCasasDecimais  int numero de casas decimais
   * @return  o valor arredondado.
   */
  public static double arredonda(double plnNum, int pIntCasasDecimais) {
  	double lnMult = Math.pow(10,pIntCasasDecimais);
    return (Math.round(plnNum * lnMult)/(lnMult));
  }
  
  /**
   * Trunca um n�mero de acordo com o n�mero de casas passado
   * como par�metro
   * @param pStrNumber    N�mero a ser truncado
   * @param pIntNumCasas  N�mero de casas
   * @return              N�mero truncado
   */
  public static double trunca(String pStrNumber, int pIntNumCasas) {
    double fator    = Math.pow(10, pIntNumCasas);     
    double valorAux = Double.parseDouble(pStrNumber);
    valorAux        = valorAux * fator;
    valorAux        = Math.floor(valorAux);
    
    return          valorAux / fator;
  }

    /**
     * Trunca um n�mero em duas casas decimais
     * @param value valor a ser truncado
     * @return n�mero truncado em duas casas decimais
     */
    public static double truncaDuasCasas(double value) {
        String  doubleValue = Double.toString(value);
        int     indicePonto = doubleValue.indexOf(".");
        String  integer     = doubleValue.substring(0, indicePonto);
        String  decimal     = doubleValue.substring(indicePonto + 1, doubleValue.length());
        
        if (decimal.length() > 2)  {
            doubleValue = integer + "." + decimal.substring(0, 2);
            
            return Double.valueOf(doubleValue).doubleValue();
        }
        return value;
    }

    /**
     * Trunca um n�mero em duas casas decimais
     * @param value valor a ser truncado
     * @return n�mero truncado em duas casas decimais
     */
    public static double truncaDuasCasas(Double value) {
        String  doubleValue = value.toString();
        int     indicePonto = doubleValue.indexOf(".");
        String  integer     = doubleValue.substring(0, indicePonto);
        String  decimal     = doubleValue.substring(indicePonto + 1, doubleValue.length());
        
        if (decimal.length() > 2)  {
            doubleValue = integer + "." + decimal.substring(0, 2);
            
            return Double.valueOf(doubleValue).doubleValue();
        }
        return value.doubleValue();
    }

    /**
     * Retira a formata��o de uma String double 
     * e lhe aplica um parsing para Double 
     * @param   value     valor formatado
     * @return  valor parseado para Double
     * @throws  NumberFormatException em caso de erro
     */
	public static Double parseFormatedDouble(String value) throws NumberFormatException {
		Double parsedDouble = null;
		
		if (value != null && !"".equals(value)) {
			parsedDouble = Double.valueOf(removeFormat(value));
		}
		
		return parsedDouble;
	}

	   /**
	    * Retorna um Integer dada uma string
	    * @param    integer    string a ser parseada
	    * @return   um Integer dada uma string
	    * @throws   NumberFormatException em caso de erro
	    */
	   public static Integer parseInteger(String integer) throws NumberFormatException {
	       Integer value = null;
	       
	       if (integer != null && !"".equals(integer.trim())) {
	           value = Integer.valueOf(integer);
	       }
	       
	       return value;
	   }

}



