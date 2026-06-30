package link.repo;

import framework.persistence.jpa.PersistenceServiceUtil;
import link.entity.Link;

public class LinksRepository {

	private PersistenceServiceUtil persistence = new PersistenceServiceUtil();
	


	public void removeLink(Link link) {
		try {
			persistence.beginTransaction();
			link = persistence.findObject(Link.class, link.getId());
			persistence.remove(link);
			persistence.commit();
			
		} finally {
			persistence.close();
		}
	}

	
	public void persistLink(String descricao, String url) {
		try {
			persistence.beginTransaction();

			Link link = new Link();
			link.setDescricao(descricao);
			link.setUrl(url);
			
			persistence.persist(link);
			persistence.commit();
			
		} finally {
			persistence.close();
		}
	}
	
/*
	public void persistLink(String descricao, String url) {
		try {
			persistence.beginTransaction();

			EntityManager entityManager = persistence.getEntityManager();
			Session session = entityManager.unwrap(Session.class);
			session.doWork(new Work() {
			    @Override
			    public void execute(Connection connection) throws SQLException {
			    	String sql = "INSERT INTO LINK(descricao,url) VALUES(?, ?)";
			        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			        	stmt.setString(1, descricao);
			        	stmt.setString(2, url);
			            stmt.executeUpdate();
			        }
			    }
			});			
			persistence.commit();
			
		} finally {
			persistence.close();
		}
	}
 * 
 */	
	
	
	
}
