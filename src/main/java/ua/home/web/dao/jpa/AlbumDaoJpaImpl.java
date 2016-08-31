package ua.home.web.dao.jpa;

import ua.home.web.dao.AlbumDao;
import ua.home.web.domain.Album;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class AlbumDaoJpaImpl implements AlbumDao {

	private final EntityManagerFactory entityManagerFactory;

	public AlbumDaoJpaImpl(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void saveAlbum(Album album) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			entityManager.getTransaction().begin();

			entityManager.persist(album);

			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

	public List<Album> getAlbums() {

		List<Album> resultList = Collections.emptyList();

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {

			entityManager.getTransaction().begin();
			TypedQuery<Album> getAllAlbums = entityManager.createNamedQuery("getAllAlbums", Album.class);
			resultList = getAllAlbums.getResultList();
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}

		return resultList;
	}
}
