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

    public ArrayList<Track> search(String str){
        ArrayList<Track> searchTrack= new ArrayList<>();
        Pattern p = Pattern.compile(str);
        for(int i = 0; i< tracksStore.size(); i++ ) {
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
    /**methods for private usw only*/

    /**this method should realize search with rejex*/
    private int getTrackIndex(String string){
        return 0;
    }
}