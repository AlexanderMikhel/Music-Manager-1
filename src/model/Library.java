package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class keeps tracks and provides tools for managing them
 */
public class Library implements Serializable{
    private ArrayList<Track> tracksStore = new ArrayList<>();
    private ArrayList<Genre> genresStore = new ArrayList<>();
    private int tracksQuantity=0;

    public int getGenresQuantity() {
        return genresQuantity;
    }

    public int getTracksQuantity() {

        return tracksQuantity;
    }

    private int genresQuantity=0;

    public Library() {

    }

    public void setTrack(Track track){
        tracksStore.add(track);
        tracksQuantity++;
    }

    public void setTrack(Track track, int index){

    }

    public void setGenre(Genre genre){
        genresStore.add(genre);
        genresQuantity++;
    }

    public Track[] getTracks(){
        Track[] tracks = new Track[tracksQuantity];
        for (int i=0;i<tracksQuantity;i++){
            tracks[i]= tracksStore.get(i);
        }
        return tracks;
    }

    public Genre[] getGenresStore(){
        Genre[] genres  =new Genre[genresQuantity];
        for (int i=0;i<genresQuantity;i++){
            genres[i] = this.genresStore.get(i);
        }
        return genres;
    }

    /**find and return track*/
    public Track getTrack(String trackName){
        return tracksStore.get(getTrackIndex(trackName));
    }

    /**find track in the library and remove them*/
    public void removeTrack(String trackName){
        tracksStore.remove(getTrackIndex(trackName));
    }

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

    /*public Track search(String string){
        Pattern p = Pattern.compile(string);
        for(int i = 0; i< tracksQuantity; i++ ) {
            Matcher trackName=p.matcher(tracksStore.get(i).getTrackName());
            Matcher trackArtist=p.matcher(tracksStore.get(i).getTrackArtist());
            Matcher trackAlbum=p.matcher(tracksStore.get(i).getTrackAlbum());
            if(trackName.find() || trackArtist.find() || trackAlbum.find()){
                int j = 0;
                return tracksStore.get(i);
            }
        }
        return null;
    }*/

    public Genre getGenre(String genre){
        Pattern pattern = Pattern.compile(genre);
        for (int i=0;i<genresQuantity;i++){
            Matcher genreName= pattern.matcher(genresStore.get(i).getGenreName());
            if (genreName.find()){
                return genresStore.get(i);
            }
        }
        return null;
    }
    /**methods for private usw only*/

    /**find and return index*/
    private int getTrackIndex(String string){
        return 0;
    }
}