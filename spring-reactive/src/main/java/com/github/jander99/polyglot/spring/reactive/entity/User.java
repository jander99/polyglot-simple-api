package com.github.jander99.polyglot.spring.reactive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Data
@Builder
public class User {

    @JsonIgnore
    private Integer userId;

    private String name;

    private String username;

    private String email;

    private List<Album> albums;
}
