package com.github.jander99.polyglot.spring.service;

import com.github.jander99.polyglot.spring.entity.Album;
import com.github.jander99.polyglot.spring.entity.Photo;
import com.github.jander99.polyglot.spring.entity.User;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JsonPlaceholderService {
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String ALBUMS_URL = "https://jsonplaceholder.typicode.com/albums";
    private static final String PHOTOS_URL = "https://jsonplaceholder.typicode.com/photos";

    private final RestTemplate restTemplate;

    public JsonPlaceholderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {

        List<Album> albums = getAllAlbums();

        return getallJsonPlaceholderUsers().stream()
            .map(jp -> User.builder()
                .userId(jp.getId())
                .name(jp.getName())
                .username(jp.getUsername())
                .email(jp.getEmail())
                .albums(albums
                    .stream()
                    .filter(a -> a.getUserId().equals(jp.getId()))
                    .collect(Collectors.toList()))
                .build())
            .collect(Collectors.toList());
    }

    private List<Album> getAllAlbums() {
        List<JPPhoto> photoList = getallJsonPlaceholderPhotos();
        List<JPAlbum> albumList = getAllJsonPlaceholderAlbums();


        return albumList.stream().map(jpAlbum -> Album.builder()
            .albumId(jpAlbum.getId())
            .userId(jpAlbum.getUserId())
            .title(jpAlbum.getTitle())
            .photos(photoList
                .stream()
                .filter(p -> p.albumId.equals(jpAlbum.getId()))
                .map(p -> Photo
                    .builder()
                    .photoId(p.getId())
                    .title(p.getTitle())
                    .url(p.getUrl())
                    .build())
                .collect(Collectors.toList()))
            .build()).collect(Collectors.toList());
    }

    private List<JPUser> getallJsonPlaceholderUsers() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(USERS_URL, JPUser[].class)));
    }

    @Data
    static class JPUser {
        private Integer id;
        private String name;
        private String username;
        private String email;
    }

    private List<JPAlbum> getAllJsonPlaceholderAlbums() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(ALBUMS_URL, JPAlbum[].class)));
    }

    @Data
    static class JPAlbum {
        private Integer id;
        private Integer userId;
        private String title;
    }

    private List<JPPhoto> getallJsonPlaceholderPhotos() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(PHOTOS_URL, JPPhoto[].class)));
    }

    @Data
    static class JPPhoto {
        private Integer albumId;
        private Integer id;
        private String title;
        private String url;
    }
}


