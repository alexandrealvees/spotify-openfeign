package tech.tron.spotify.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.tron.spotify.entity.album.Album;
import tech.tron.spotify.service.AlbumService;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")

public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAlbums() {
        List<Album> albums = albumService.getAlbums();
        return ResponseEntity.ok(albums);
    }
}
