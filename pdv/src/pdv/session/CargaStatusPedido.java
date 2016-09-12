package pdv.session;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.contexts.Lifecycle;

import pdv.entity.StatusPedido;
import pdv.util.MessagesUtil;

@Scope(ScopeType.APPLICATION)
@Name("cargaStatusPedido")
@AutoCreate
@Startup
public class CargaStatusPedido {

	@Create
	public void create() {
		EntityManager manager = null;
		
		try {
			Lifecycle.beginCall();

			Map<String,String>status = MessagesUtil.getAllMessages(MessagesUtil.DEFAULT_MSG_PROP_ST_PED);
			manager 				 = (EntityManager)Component.getInstance("entityManager");
			EntityTransaction tx 	 = manager.getTransaction(); 
			List<StatusPedido>list 	 = findAll(manager);
			Set<String>keys 		 = status.keySet();
			
			if (list.isEmpty()) {
				tx.begin();
				
				for (String key : keys) {
					StatusPedido statusPedido 	= new StatusPedido();
					String statPedido			= status.get(key);
					String[]statValues			= statPedido.split("-");
					
					statusPedido.setId(Integer.valueOf(statValues[0]));
					statusPedido.setDsStatus(statValues[1]);
					manager.persist(statusPedido);
				}
				tx.commit();				
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (manager != null) {
				manager.close();
			}
		}
	}
	
	private List<StatusPedido> findAll(EntityManager manager) { 
		Session session = (Session)manager.getDelegate();
		Criteria crit	= session.createCriteria(StatusPedido.class);
		
		return crit.list();
	}
	
	
}










