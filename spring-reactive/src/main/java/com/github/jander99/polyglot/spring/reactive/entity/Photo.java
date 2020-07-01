package com.github.jander99.polyglot.spring.reactive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@Builder
public class Photo {

    @JsonIgnore
    private Integer photoId;

    private String title;

    private String url;
}
