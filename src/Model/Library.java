package Model;

import java.util.ArrayList;

/**
 * This class keeping tracks and provides tools for managing them
 */
public class Library {
    private ArrayList<Track> tracksStore = new ArrayList<>();
    private ArrayList<Genre> genres = new ArrayList<>();

    public Library() {

    }

    public void setTracks(Genre[] genres){
        for (Genre genre:genres) {
            this.genres.add(genre);
        }
    }

    public void setGenres(Track[] tracks) {
        tracksStore = new ArrayList<>();
        for (Track track:tracks) {
            this.tracksStore.add(track);
        }
    }

    public void setTrack(Track track){
        tracksStore.add(track);
    }

    public void setGenre(Genre genre){
        genres.add(genre);
    }


    public ArrayList getTracks(){
        return tracksStore;
    }

    public ArrayList getGenres(){
        return genres;
    }
}