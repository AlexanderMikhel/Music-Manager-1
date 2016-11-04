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

    /**add to library numbers of tracks*/
    public void setTracks(Genre[] genres){
        for (Genre genre:genres) {
            this.genres.add(genre);
        }
    }

    /*add to library single track*/
    public void setTrack(Track track){
        tracksStore.add(track);
    }

    public void setTrack(Track track, int index){

    }

    /**the same way as methods with tracks*/
    public void setGenres(Track[] tracks) {
        tracksStore = new ArrayList<>();
        for (Track track:tracks) {
            this.tracksStore.add(track);
        }
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

    public Track getTrack(String trackName){
        return tracksStore.get(getTrackIndex(trackName));
    }

    /**find track in the library and remove them*/
    public void removeTrack(String trackName){
        tracksStore.remove(getTrackIndex(trackName));
    }

    /**change exist track for a new*/
    public void setTrack(Track newTrack, String oldTrackName){

    }

    /**methods for private usw only*/

    /**this method should realize search with rejex*/
    private int getTrackIndex(String string){

    }
}