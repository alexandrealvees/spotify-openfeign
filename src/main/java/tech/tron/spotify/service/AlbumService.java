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
                "seu_id_cliente_spotify",
                "seu_id_spotify_secret"
        );
        var token = authSpotifyClient.login(request).getAccessToken();
        var response = albumSpotifyClient.getReleases("Bearer " + token);
        return response.getAlbums().getItems();
    }
}