package framework.util;



/**
 * <p>Title: Utilitario para Strings</p>
 * <p>Description: Contem metodos utilitarios para Strings</p>
 * @author  Luiz Alberto
 * @date    10/01/2003
 */
 
public class StringUtil {

    /**
     * Construtor vazio para evitar instancias
     * desnecessarias da classe
     */
    private StringUtil() {
    }

    /**
     * Insere uma string a partir de um parâmetro declarado dentro da string 
     * a ser tratada
     * 
     * @param string	string a ser tratada
     * @param param		refere-se ao parametro dentro da string
     * @return			uma string a partir de um parâmetro declarado dentro da string
     */
	public static final String insertString(String string, String param) {
		
		StringBuilder returnStr = new StringBuilder();
		
		if (string == null || string.trim().equals("")) {
			returnStr.append("");
		} else {
			int index = string.indexOf("{"); 
			
			if (index == 0) {
				String part = string.substring(3, string.length());
				returnStr.append(param).append(part);
			} else  {
				String part1 = string.substring(0, index);
				String part2 = string.substring(index + 3, string.length());
				
				returnStr.append(part1).append(param).append(part2);
			}
		}
		return returnStr.toString();
	}

    
    /**
     * Preenche uma string com espaços até o tamanho especificado
     * @param pStrNome  texto a ser completado
     * @param pNTamanho tamanho do texto
     * @return uma string com espaços até o tamanho especificado
     */
    public static String rPad(String pStrNome, int pNTamanho){
        int lNDiferencaTam;
        lNDiferencaTam = pNTamanho - pStrNome.length();
        
        while (0 < lNDiferencaTam ) {
            pStrNome = pStrNome + " ";
            lNDiferencaTam --;
        }
        return (pStrNome);
    }


    /** 
     * Troca o caractere de espaço (' ') por %20 
     * @param texto texto a ser alterado
     * @return texto alterado
     */
    public static String trocaEspaco(String texto){
        String temp = ""; 
        
        for (int i=0; i<texto.length(); i++){
            char c = texto.charAt(i);
            
            if(c == ' '){
                temp = temp + "%20";
            } 
            else {
                temp = temp + c;
            }
        }
        return temp;
    }


    /**
     * Retorna o numero de vezes que n existe em str 
     * @param  str texto a ser vasculhado
     * @param  p   caracter a ser contado
     * @return o numero de vezes que n existe em str 
     */
    public static int inStr(String str, char p){
        int ps = 0;
        
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == p) {
                ps++;
            }
        }
        return ps;
    }

    /**
     * Preenche uma string com o caracter passado até o tamanho especificado para a esquerda.
     * @param 	pStrNome	string a ser formatada
     * @param 	pNTamanho	tamanho especificado 
     * @param 	pStrCarac	caracter que sera apendado à esquerda
     * @return 	uma string com o caracter passado até o tamanho especificado para a esquerda
     */
    public static String lPad(String pStrNome, int pNTamanho, String pStrCarac){
      String lStrNome = "";

      int lIntTamanhoNome 	= nvl(pStrNome, "").length();
      int lIntDif 			= pNTamanho - lIntTamanhoNome;

      for (int i = 0; i < lIntDif; i++) {
          lStrNome += pStrCarac;
      }

      lStrNome += pStrNome;

      return lStrNome;
    }

    
    /**
     * Se a String recebida for nula, retorna a String retornada
     * @param strRecebida   String a ser verificada
     * @param strRetornada  String a ser retornada em caso da primeira ser nula
     * @return  A String recebida, se esta for diferente de nulo. String Retornada, caso contrario.
     */
    public static String nvl(String strRecebida, String strRetornada){
    	if ((strRecebida == null) || "null".equals(strRecebida)) {
			return strRetornada;
		}
    	else {
			return strRecebida;
		}
    }
    
    /**
     * Retorna uma string em caixa alta
     * @param   string string a ser formatada
     * @return  uma string em caixa alta
     */
    public static String toUpperCase(String string) {
        if (string != null) {
            return string.toUpperCase();
        }
        
        return string;
    }

}
