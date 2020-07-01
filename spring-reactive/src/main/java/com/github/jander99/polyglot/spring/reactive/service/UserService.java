package com.github.jander99.polyglot.spring.reactive.service;

import com.github.jander99.polyglot.spring.reactive.entity.Album;
import com.github.jander99.polyglot.spring.reactive.entity.Photo;
import com.github.jander99.polyglot.spring.reactive.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final JsonPlaceholderService jsonPlaceholderService;

    public UserService(JsonPlaceholderService jsonPlaceholderService) {
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    public Flux<User> getAllUsers() {
        Flux<JsonPlaceholderService.JPAlbum> jsonAlbums = jsonPlaceholderService.getJsonPlaceholderAlbums();
        Flux<JsonPlaceholderService.JPPhoto> jsonPhotos = jsonPlaceholderService.getJsonPlaceholderPhotos();
        Flux<JsonPlaceholderService.JPUser> jsonUsers = jsonPlaceholderService.getJsonPlaceholderUsers();

        return jsonUsers.map(jpUser ->
            User.builder()
                .userId(jpUser.getId())
                .name(jpUser.getName())
                .username(jpUser.getUsername())
                .email(jpUser.getEmail())
                .albums(collectListOfAlbumsByUserId(jpUser.getId(), jsonAlbums, jsonPhotos))
                .build()
        );
    }

    public Mono<User> getSingleUser(Integer userId) {
        return getAllUsers()
            .filter(u -> u.getUserId().equals(userId))
            .next();
    }

    public Flux<Album> getSingleUserAlbums(Integer userId) {
        return getSingleUser(userId)
            .flatMapMany(u -> Flux.fromIterable(u.getAlbums()));
    }

    public Mono<Album> getSingleUserSingleAlbum(Integer userId, Integer albumId) {
        return getSingleUserAlbums(userId).filter(a -> a.getAlbumId().equals(albumId)).next();
    }

    public Flux<Photo> getSingleUserSingleAlbumPhotos(Integer userId, Integer albumId) {
        return getSingleUserSingleAlbum(userId, albumId).flatMapMany(a -> Flux.fromIterable(a.getPhotos()));
    }

    public Mono<Photo> getSingleUserSingleAlbumSinglePhoto(Integer userId, Integer albumId, Integer photoId) {
        return getSingleUserSingleAlbumPhotos(userId, albumId).filter(p -> p.getPhotoId().equals(photoId)).next();
    }

    public List<Album> collectListOfAlbumsByUserId(Integer userId, Flux<JsonPlaceholderService.JPAlbum> jpAlbums, Flux<JsonPlaceholderService.JPPhoto> jpPhotos) {

        return jpAlbums
            .filter(jpAlbum -> jpAlbum
                .getUserId()
                .equals(userId))
            .map(jpAlbum ->
                Album.builder()
                    .albumId(jpAlbum.getId())
                    .userId(jpAlbum.getUserId())
                    .title(jpAlbum.getTitle())
                    .photos(collectListOfPhotosByAlbumId(jpPhotos, jpAlbum.getId()))
                    .build())
            .subscribeOn(Schedulers.boundedElastic())
            .toStream()
            .collect(Collectors.toList());
    }

    public List<Photo> collectListOfPhotosByAlbumId(Flux<JsonPlaceholderService.JPPhoto> jpPhotos, Integer albumId) {

        return jpPhotos
            .filter(jpPhoto -> jpPhoto
                .getAlbumId()
                .equals(albumId))
            .map(jpPhoto -> Photo.builder()
                .photoId(jpPhoto.getId())
                .title(jpPhoto.getTitle())
                .url(jpPhoto.getUrl())
                .build())
            .subscribeOn(Schedulers.boundedElastic())
            .toStream()
            .collect(Collectors.toList());
    }
}
