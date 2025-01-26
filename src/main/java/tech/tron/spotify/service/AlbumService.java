package tech.tron.spotify.service;

import org.springframework.stereotype.Service;
import tech.tron.spotify.entity.album.Album;
import tech.tron.spotify.entity.album.AlbumSpotifyClient;
import tech.tron.spotify.entity.login.AuthSpotifyClient;
import tech.tron.spotify.entity.login.LoginRequest;

import java.util.List;

@Service
public class AlbumService {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumService(AuthSpotifyClient authSpotifyClient,
                        AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    public List<Album> getAlbums() {
        var request = new LoginRequest(
                "client_credentials",
                "07ddc27e1cdb427bba639ea25672e4c2",
                "47eb04ceb317404d9c6d96387c217928"
        );
        var token = authSpotifyClient.login(request).getAccessToken();
        var response = albumSpotifyClient.getReleases("Bearer " + token);
        return response.getAlbums().getItems();
    }
}