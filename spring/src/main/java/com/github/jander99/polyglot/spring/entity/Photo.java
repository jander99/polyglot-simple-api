package com.github.jander99.polyglot.spring.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@Builder
public class Photo {

    private Integer photoId;

    private String title;

    private String url;
}
