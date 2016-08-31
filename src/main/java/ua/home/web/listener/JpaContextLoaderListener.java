package ua.home.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.home.web.dao.AlbumDao;
import ua.home.web.dao.jpa.AlbumDaoJpaImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class JpaContextLoaderListener implements ServletContextListener {

	private static final Logger LOG = LoggerFactory.getLogger(JpaContextLoaderListener.class);

	private EntityManagerFactory entityManagerFactory;


	public void contextInitialized(ServletContextEvent servletContextEvent) {

		LOG.info("Creating EntityManagerFactory...");

		entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.hsqldb.music-unit");

		AlbumDao albumDao = new AlbumDaoJpaImpl(entityManagerFactory);

		servletContextEvent.getServletContext().setAttribute("ua.home.web.dao.jpa.AlbumDaoJpaImpl", albumDao);
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {

		LOG.info("Closing EntityManagerFactory...");

		if (entityManagerFactory != null) {
			try {
				entityManagerFactory.close();
			} catch (Exception e) {
				LOG.error("Error close EntityManagerFactory!", e);
			}
		}
	}
}
