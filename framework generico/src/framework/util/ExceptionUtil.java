package framework.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 * 
 * Created on 10/06/2006
 * @author Luiz Alberto
 */
public class ExceptionUtil {

    /**
     * Construtor privado para evitar inst�ncias
     * desnecess�rias da classe
     */
    private ExceptionUtil() {
    }


    /**
     * Retorna o estouro da pilha de erro ou exce��o
     * 
     * @param   e exce��o ocorrida
     * @return  o estouro da pilha de erro ou exce��o
     */
    public static String getStackTrace(Exception e) {
        StringWriter    stringWriter    = new StringWriter();
        PrintWriter     prtWrt          = new PrintWriter(stringWriter);
        e.printStackTrace(prtWrt);
        
        return stringWriter.toString();
    }
    
}







