package com.github.jander99.polyglot.spring.controller;

import com.github.jander99.polyglot.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.of(Optional.of(userService.getAllUsers()));
    }

    @RequestMapping(path = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getSingleUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity.of(Optional.of(userService.getSingleUser(userId)));
    }

    @RequestMapping(path = "/users/{userId}/albums", method = RequestMethod.GET)
    public ResponseEntity<?> getSingleUserAlbums(@PathVariable("userId") Integer userId) {
        return ResponseEntity.of(Optional.of(userService.getSingleUserAlbums(userId)));
    }

    @RequestMapping(path = "/users/{userId}/albums/{albumId}", method = RequestMethod.GET)
    public ResponseEntity<?> getSingleUserSingleAlbum(@PathVariable("userId") Integer userId, @PathVariable("albumId") Integer albumId) {
        return ResponseEntity.of(Optional.of(userService.getSingleUserSingleAlbum(userId, albumId)));
    }

    @RequestMapping(path = "/users/{userId}/albums/{albumId}/photos", method = RequestMethod.GET)
    public ResponseEntity<?> getSingleUserSingleAlbumPhotos(@PathVariable("userId") Integer userId, @PathVariable("albumId") Integer albumId) {
        return ResponseEntity.of(Optional.of(userService.getSingleUserSingleAlbumPhotos(userId, albumId)));
    }

    @RequestMapping(path = "/users/{userId}/albums/{albumId}/photos/{photoId}", method = RequestMethod.GET)
    public ResponseEntity<?> getSingleUserSingleAlbumPhotos(
        @PathVariable("userId") Integer userId,
        @PathVariable("albumId") Integer albumId,
        @PathVariable("photoId") Integer photoId) {
        return ResponseEntity.of(Optional.of(userService.getSingleUserSingleAlbumSinglePhoto(userId, albumId, photoId)));
    }

}
