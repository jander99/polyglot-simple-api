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
public class Album {

    private Integer albumId;

    @JsonIgnore
    private Integer userId;

    private String title;

    private List<Photo> photos;
}
