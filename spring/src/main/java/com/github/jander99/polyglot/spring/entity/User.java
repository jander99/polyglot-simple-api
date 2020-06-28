package com.github.jander99.polyglot.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@Builder
public class User {

    private Integer userId;

    private String name;

    private String username;

    private String email;

    private List<Album> albums;
}
