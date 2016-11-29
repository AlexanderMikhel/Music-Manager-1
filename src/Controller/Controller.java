package Controller;


import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private Scanner in = new Scanner(System.in);
    private Library library = new Library();

    public Controller() {
        deSerialization();
    }

    /**
     * add track to library
     */
    public void setTrack() {
        System.out.println("Added new track");
        System.out.println("Enter track name");
        String trackName = in.nextLine();
        System.out.println("Enter name of the artist");
        String trackArtist = in.nextLine();
        System.out.println("Enter name of the album");
        String trackAlbum = in.nextLine();
        System.out.println("Enter track length\nminutes, seconds");
        int minutes = in.nextInt();
        int seconds = in.nextInt();
        int trackLength = inSeconds(minutes, seconds);

        System.out.println("Now, select genre");
        String genre = in.nextLine();
        Genre trackGenre = library.getGenre(genre);

        Track track = new Track(trackName, trackArtist, trackAlbum, trackLength, trackGenre);
        library.setTrack(track);
        System.out.println("-----------------------\n");
    }

    public void setTrackData(){
        System.out.println("Enter track that you want to change");
        library.search(in.next());

    }

    /**
     * add genre
     */
    public void setGenre() {
        System.out.println("Enter genre name");
        String genreName = in.next();
        System.out.println("Enter century when this genre was established");
        int establishingCentury = in.nextInt();

        Genre genre = new Genre(genreName, establishingCentury);
        library.setGenre(genre);
    }

    public ArrayList getGenres() {
        return library.getGenres();
    }

    public ArrayList getTracks() {
        return library.getTracks();
    }

    public ArrayList<Track> search() {
        System.out.println("Enter track name");
        String string = in.next();
        return library.search(string);
    }

    //ser
    public void serialization() {
        try {
            ObjectOutputStream saveChanges = new ObjectOutputStream(new FileOutputStream("src\\Model\\ser.dat"));
            saveChanges.writeObject(library);
            saveChanges.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deSerialization() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src\\Model\\ser.dat"));
            library = (Library) inputStream.readObject();
        } catch (IOException e) {
            System.out.println("No save libraries");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //
    private int inSeconds(int minutes, int seconds) {
        return minutes * 60 + seconds;
    }
}
