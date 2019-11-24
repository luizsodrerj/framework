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
     * Construtor privado para evitar instâncias
     * desnecessárias da classe
     */
    private ExceptionUtil() {
    }


    /**
     * Retorna o estouro da pilha de erro ou exceção
     * 
     * @param   e exceção ocorrida
     * @return  o estouro da pilha de erro ou exceção
     */
    public static String getStackTrace(Exception e) {
        StringWriter    stringWriter    = new StringWriter();
        PrintWriter     prtWrt          = new PrintWriter(stringWriter);
        e.printStackTrace(prtWrt);
        
        return stringWriter.toString();
    }
    
}







