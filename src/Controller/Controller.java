package Controller;


import Model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private Scanner in=new Scanner(System.in);
    private Library library = new Library();

    public Controller(){}

    /**add track to library*/
    public void setTrack(){
        System.out.println("Enter track name");
        String trackName=in.next();
        System.out.println("Enter name of the artist");
        String trackArtist=in.next();
        System.out.println("Enter name of the album");
        String trackAlbum=in.next();
        System.out.println("Enter track length\nminutes, seconds");
        int minutes = in.nextInt();
        int seconds = in.nextInt();
        int trackLength = inSeconds(minutes, seconds);

        System.out.println("Now, select genre");
        String genre=in.next();
        Genre trackGenre = library.getGenre(genre);

        Track track = new Track(trackName, trackArtist, trackAlbum,trackLength, trackGenre);
        library.setTrack(track);
    }

    /**add genre*/
    public void setGenre(){
        System.out.println("Enter genre name");
        String genreName=in.next();
        System.out.println("Enter century when this genre was established");
        int establishingCentury = in.nextInt();

        Genre genre=new Genre(genreName, establishingCentury);
        Library library = new Library();
        library.setGenre(genre);
    }

    public ArrayList getGenres(){
        return library.getGenres();
    }
    //
    private int inSeconds(int minutes, int seconds){
        return minutes*60+seconds;
    }
}
