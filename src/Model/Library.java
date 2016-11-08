package Model;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class keeping tracks and provides tools for managing them
 */
public class Library implements Serializable{
    private ArrayList<Track> tracksStore = new ArrayList<>();
    private ArrayList<Genre> genres = new ArrayList<>();
    private int tracksQuantity=0;
    private int genresQuantity=0;

    public Library() {

    }

    /**add to library numbers of tracks*/
    public void setTracks(Track[] tracks){
        for (Track track:tracks) {
            this.tracksStore.add(track);
            tracksQuantity++;
        }
    }

    /*add to library single track*/
    public void setTrack(Track track){
        tracksStore.add(track);
        tracksQuantity++;
    }

    public void setTrack(Track track, int index){

    }

    /**the same way as methods with tracks*/
    public void setGenres(Genre[] genres) {
        for (Genre genre:genres) {
            this.genres.add(genre);
            genresQuantity++;
        }
    }

    public void setGenre(Genre genre){
        genres.add(genre);
        genresQuantity++;
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

    public ArrayList<Track> search(String str){
        ArrayList<Track> searchTrack= new ArrayList<>();
        Pattern p = Pattern.compile(str);
        for(int i = 0; i< tracksQuantity; i++ ) {
            Matcher trackName=p.matcher(tracksStore.get(i).getTrackName());
            Matcher trackArtist=p.matcher(tracksStore.get(i).getTrackArtist());
            Matcher trackAlbum=p.matcher(tracksStore.get(i).getTrackAlbum());
            if(trackName.find() || trackArtist.find() || trackAlbum.find()){
                int j = 0;
                searchTrack.add(j,tracksStore.get(i));
                j++;
            }
        }
        return searchTrack;
    }

    public Genre getGenre(String genre){
        Pattern pattern = Pattern.compile(genre);
        for (int i=0;i<genresQuantity;i++){
            Matcher genreName= pattern.matcher(genres.get(i).getGenreName());
            if (genreName.find()){
                return genres.get(i);
            }
        }
        return null;
    }
    /**methods for private usw only*/

    /**this method should realize search with rejex*/
    private int getTrackIndex(String string){
        return 0;
    }
}