package framework.persistence;


/**
 * Utilitário para recuperação de queries de recursos
 * @author  Luiz Alberto
 * @date    04/03/2005
 */
public class QueryUtil  {

    /**
     * Construtor privado para evitar instancias desnecessarias da classe
     */
    private QueryUtil() {
    }
    
    /**
     * 
     * @param key
     * @return 
     */
    public static String getQuery(String key) {
        QueryFactory queryFactory = QueryFactory.newInstance();
        
        return queryFactory.getQuery(key);
    }
}