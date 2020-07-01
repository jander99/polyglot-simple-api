package com.github.jander99.polyglot.spring.reactive.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class JsonPlaceholderService {

    private static final String JSON_PLACEHOLDER_URL = "https://jsonplaceholder.typicode.com";

    private static final String USERS_URL = "/users";
    private static final String ALBUMS_URL = "/albums";
    private static final String PHOTOS_URL = "/photos";

    private final WebClient webClient;

    public JsonPlaceholderService(WebClient.Builder webclientBuilder) {
        this.webClient = webclientBuilder.baseUrl(JSON_PLACEHOLDER_URL).build();
    }

    public Flux<JPUser> getJsonPlaceholderUsers() {
        return webClient.get().uri(USERS_URL).retrieve().bodyToFlux(JPUser.class);
    }

    public Flux<JPAlbum> getJsonPlaceholderAlbums() {
        return webClient.get().uri(ALBUMS_URL).retrieve().bodyToFlux(JPAlbum.class);
    }

    public Flux<JPPhoto> getJsonPlaceholderPhotos() {
        return webClient.get().uri(PHOTOS_URL).retrieve().bodyToFlux(JPPhoto.class);
    }

    @Data
    static class JPUser {
        private Integer id;
        private String name;
        private String username;
        private String email;
    }

    @Data
    static class JPAlbum {
        private Integer id;
        private Integer userId;
        private String title;
    }

    @Data
    static class JPPhoto {
        private Integer albumId;
        private Integer id;
        private String title;
        private String url;
    }
}
