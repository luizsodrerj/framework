package agendaweb.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe utilizaria para obter o serviço de persistência
 * 
 * @author Luiz Alberto
 *
 */
public final class PersistenceUtil {

	private static EntityManagerFactory entityFactory;
	
	
	/**
	 * Contrutor default
	 */
	private PersistenceUtil() {
	}
	
	/**
	 * Cria um Gerenciador de Entidades JPA
	 * 
	 * @return Gerenciador de Entidades JPA
	 */
	public static EntityManager createEntityManager() {
		try {
			String defaultPU = PersistenceService.DEFAULT_PERSISTENCE_UNIT; 
				
			entityFactory = entityFactory == null ? 
							Persistence.createEntityManagerFactory(defaultPU) : 
							entityFactory;
 
			return entityFactory.createEntityManager();
			
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Cria um Gerenciador de Entidades JPA baseado no nome da PersisnceUnit
	 * 
	 * @param persistenceUnit	Nome da PersisnceUnit
	 * @return					Gerenciador de Entidades JPA					
	 */
	public static EntityManager createEntityManager(String persistenceUnit) {
		try {
			entityFactory = entityFactory == null ? 
							Persistence.createEntityManagerFactory(persistenceUnit) : 
							entityFactory;
 
			return entityFactory.createEntityManager();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}
	}

	
}


















