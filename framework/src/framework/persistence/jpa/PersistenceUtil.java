package framework.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe utilizaria para obter o serviço de persistência
 * 
 * @author Luiz Alberto
 *
 */
public class PersistenceUtil {

	private static EntityManagerFactory entityFactory;
	
	
	/**
	 * Cria um Gerenciador de Entidades JPA
	 * 
	 * @return Gerenciador de Entidades JPA
	 */
	public static final EntityManager createEntityManager() {
		try {
			
			entityFactory = entityFactory == null ? Persistence.createEntityManagerFactory("Persist") : entityFactory;
 
			return entityFactory.createEntityManager();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Cria um Gerenciador de Entidades JPA baseado no nome da PersisnceUnit
	 * 
	 * @param persistenceUnit	Nome da PersisnceUnit
	 * @return					Gerenciador de Entidades JPA					
	 */
	public static final EntityManager createEntityManager(String persistenceUnit) {
		try {
			
			entityFactory = entityFactory == null ? 
							Persistence.createEntityManagerFactory(persistenceUnit) : 
							entityFactory;
 
			return entityFactory.createEntityManager();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	
}


















