package clink.webapp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import link.persistence.PersistenceService;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		PersistenceService persis = new PersistenceService();
		try {
			persis.connect();
		} finally {
			persis.close();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
