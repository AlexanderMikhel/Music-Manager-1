package Controller;


import Model.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private final static String PATH = "C:\\Users\\gavri\\Documents\\NetCracker\\Curator\\MusicManager\\data\\xml\\library.xml";

    private static Scanner in = new Scanner(System.in);
    private static Library library = new Library();
    private static org.w3c.dom.Document xmlDoc;

    public Controller() throws IOException, SAXException, ParserConfigurationException {
        createDocument();
        readXml();
    }

    /**
     * add track to library
     */
    public static void setTrack() {
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

    public static void setTrackData() {
        System.out.println("Enter track that you want to change");
        library.search(in.next());

    }

    /**
     * add genre
     */
    public static void setGenre() {
        System.out.println("Enter genre name");
        String genreName = in.next();
        System.out.println("Enter century when this genre was established");
        int establishingCentury = in.nextInt();

        Genre genre = new Genre(genreName, establishingCentury);
        library.setGenre(genre);
    }

    public static ArrayList getGenres() {
        return library.getGenres();
    }

    public static ArrayList getTracks() {
        return library.getTracks();
    }

    public static ArrayList<Track> search() {
        System.out.println("Enter track name");
        String string = in.next();
        return library.search(string);
    }

    //ser
    public static void serialization() {
        try {
            ObjectOutputStream saveChanges = new ObjectOutputStream(new FileOutputStream("src\\Model\\ser.dat"));
            saveChanges.writeObject(library);
            saveChanges.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deSerialization() {
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
    private static int inSeconds(int minutes, int seconds) {
        return minutes * 60 + seconds;
    }

    //XML
    public static void createDocument() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(PATH);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        xmlDoc = builder.parse(xmlFile);
    }

    public static void readXml() {
        NodeList tracks = xmlDoc.getElementsByTagName("track");
        for (int i = 0; i < tracks.getLength(); i++) {
            NamedNodeMap track = tracks.item(i).getAttributes();
            String trackName = track.getNamedItem("trackName").getNodeValue();
            String trackArtist = track.getNamedItem("trackArtist").getNodeValue();
            String trackAlbum = track.getNamedItem("trackAlbum").getNodeValue();
            ;
            int trackLength = Integer.parseInt(track.getNamedItem("trackLength").getNodeValue());
            ;
            Genre genre = new Genre("Jazz", 20);

            Track newTrack = new Track(trackName, trackArtist, trackAlbum, trackLength, genre);
            try {
                if (!(library.getGenre("Jazz").equals("Jazz"))) {
                    library.setGenre(genre);
                } else {
                    library.setTrack(newTrack);
                }
            } catch (NullPointerException e) {
                library.setTrack(newTrack);
            }
        }
    }
}
