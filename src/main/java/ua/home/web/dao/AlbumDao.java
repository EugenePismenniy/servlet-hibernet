package ua.home.web.dao;

import ua.home.web.domain.Album;

import java.util.List;

public interface AlbumDao {
	void saveAlbum(Album album);
	List<Album> getAlbums();
}
