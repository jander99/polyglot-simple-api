package com.github.jander99.polyglot.spring.service;

import com.github.jander99.polyglot.spring.entity.Album;
import com.github.jander99.polyglot.spring.entity.Photo;
import com.github.jander99.polyglot.spring.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final JsonPlaceholderService jsonPlaceholderService;

    public UserService(JsonPlaceholderService jsonPlaceholderService) {
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    public List<User> getAllUsers() {
        return jsonPlaceholderService.getAllUsers();
    }

    public User getSingleUser(Integer userId) {
        return getAllUsers().stream().filter(u -> u.getUserId().equals(userId)).findFirst().orElseThrow();
    }

    public List<Album> getSingleUserAlbums(Integer userId) {
        return getSingleUser(userId).getAlbums();
    }

    public Album getSingleUserSingleAlbum(Integer userId, Integer albumId) {
        return getSingleUserAlbums(userId).stream().filter(a -> a.getAlbumId().equals(albumId)).findFirst().orElseThrow();
    }

    public List<Photo> getSingleUserSingleAlbumPhotos(Integer userId, Integer albumId) {
        return getSingleUserSingleAlbum(userId, albumId).getPhotos();
    }

    public Photo getSingleUserSingleAlbumSinglePhoto(Integer userId, Integer albumId, Integer photoId) {
        return getSingleUserSingleAlbumPhotos(userId, albumId).stream().filter(p -> p.getPhotoId().equals(photoId)).findFirst().orElseThrow();
    }
}
