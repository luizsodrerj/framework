package framework.util;
 
import java.math.BigDecimal;

/**
 * Faz todos os tratamentos relacionados a números.
 * @author  Luiz Alberto Sodré
 * @date    24/01/2005
 */
public class NumberUtil {

  /**
   * Se a String recebida for nula, retorna a String retornada
   * @param pStrRecebida   String a ser verificada
   * @param pIntRetornada  String a ser retornada em caso da primeira ser nula
   * @return  A String recebida, se esta for diferente de nulo. String Retornada, caso contrario.
   * @throws java.lang.NumberFormatException Lançada quando a String a ser verificada não é um número inteiro
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
	 * Trunca um número de acordo com o número de casas passado como parâmetro
	 * 
	 * @param pDblNumber
	 *            Número a ser truncado
	 * @param pIntNumCasas
	 *            Número de casas
	 * @return Número truncado
	 */
  public static double trunca(double pDblNumber, int pIntNumCasas) {
    double fator    = Math.pow(10, pIntNumCasas);     
    double valorAux = pDblNumber * fator;
    valorAux        = Math.floor(valorAux);
    
    return          valorAux / fator;
  }
  
  /**
   * Trunca um número de acordo com o número de casas passado
   * como parâmetro       
   * @param pBgdNum       Número a ser truncado
   * @param pIntNumCasas  Número de casas
   * @return              Número truncado
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
   * Trunca um número de acordo com o número de casas passado
   * como parâmetro
   * @param pStrNumber    Número a ser truncado
   * @param pIntNumCasas  Número de casas
   * @return              Número truncado
   */
  public static double trunca(String pStrNumber, int pIntNumCasas) {
    double fator    = Math.pow(10, pIntNumCasas);     
    double valorAux = Double.parseDouble(pStrNumber);
    valorAux        = valorAux * fator;
    valorAux        = Math.floor(valorAux);
    
    return          valorAux / fator;
  }

    /**
     * Trunca um número em duas casas decimais
     * @param value valor a ser truncado
     * @return número truncado em duas casas decimais
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
     * Trunca um número em duas casas decimais
     * @param value valor a ser truncado
     * @return número truncado em duas casas decimais
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
     * Retira a formatação de uma String double 
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



