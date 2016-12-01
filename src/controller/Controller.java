package controller;

import model.*;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    private final static String PATH = "C:\\Users\\gavri\\Documents\\NetCracker\\Curator\\MusicManager\\data\\xml\\tracks.xml";

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
    public static void setTrack() throws IOException {
        System.out.println("Added new track");
        System.out.println("Enter track name");
        String trackName = in.nextLine();
        System.out.println("Enter name of the artist");
        String trackArtist = in.nextLine();
        System.out.println("Enter name of the album");
        String trackAlbum = in.nextLine();
        System.out.println("Enter track length\nminutes, seconds");
        int trackLength = enterTrackLength();

        System.out.println("Now, select genre");
        String genreName = in.nextLine();
        Genre trackGenre = library.getGenre(genreName);
        if (trackGenre == null) {
            System.out.println("\nThis genre does not exist");
            setGenre();
            trackGenre = library.getGenre(genreName);
        }
        Track track = new Track(trackName, trackArtist, trackAlbum, trackLength, trackGenre);
        library.setTrack(track);
        writeTrackXml(library.getTracksQuantity(), track);
        System.out.println("-----------------------\n");
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
            ObjectOutputStream saveChanges = new ObjectOutputStream(new FileOutputStream("src\\model\\ser.dat"));
            saveChanges.writeObject(library);
            saveChanges.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deSerialization() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src\\model\\ser.dat"));
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

    /**
     *
     *
     * @return
     */
    private static int enterTrackLength() {
        int minutes = 0;
        int seconds = 0;
        int failureCount = 0;

        boolean correctMinutes = false;
        boolean correctSeconds = false;
        while (!correctMinutes) {
            try {
                minutes = in.nextInt();
                correctMinutes = true;
            } catch (InputMismatchException e) {
                System.err.println("Enter minutes in numeric representation");
                in.next();
                failureCount++;
            }
        }
        /**When user entered minutes many times, he can forget that after minutes he should enter seconds*/
        if (failureCount > 1) {
            System.out.println("Now, enter seconds");
        }
        while (!correctSeconds) {
            try {
                seconds = in.nextInt();
                correctSeconds = true;
            } catch (InputMismatchException e) {
                System.err.println("Enter seconds in numeric representation");
                in.next();
            }
        }
        return inSeconds(minutes, seconds);
    }

    //XML
    public static void createDocument() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(PATH);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        xmlDoc = builder.parse(xmlFile);
    }

    //unfinished
    public static void createXmlFile() throws ParserConfigurationException, TransformerException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        Element library = document.createElement("library");
        document.appendChild(library);

        Element track = document.createElement("track");
        library.appendChild(track);

        Element trackName = document.createElement("trackName");
        track.appendChild(trackName);

        Element trackArtist = document.createElement("trackArtist");
        track.appendChild(trackArtist);

        Element trackLength = document.createElement("trackLength");
        track.appendChild(trackLength);

        Element trackAlbum = document.createElement("trackAlbum");
        track.appendChild(trackAlbum);

        Element trackGenre = document.createElement("trackAlbum");
        Element genreName = document.createElement("genreName");
        Element establishingCentury = document.createElement("establishingCentury");
        trackGenre.appendChild(genreName);
        trackGenre.appendChild(establishingCentury);

        track.appendChild(trackGenre);

        String path = "C:\\Users\\gavri\\Documents\\NetCracker\\Curator\\MusicManager\\data\\xml\\library.txt";
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        //File file = new File(System.getProperty(path));
        StreamResult result = new StreamResult("D:\\test.xml");
        transformer.transform(source, result);
    }

    public static void readXml() {
        NodeList tracks = xmlDoc.getElementsByTagName("track");
        for (int i = 0; i < tracks.getLength(); i++) {
            NamedNodeMap track = tracks.item(i).getAttributes();
            String trackName = track.getNamedItem("trackName").getNodeValue();
            String trackArtist = track.getNamedItem("trackArtist").getNodeValue();
            String trackAlbum = track.getNamedItem("trackAlbum").getNodeValue();
            int trackLength = Integer.parseInt(track.getNamedItem("trackLength").getNodeValue());
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

    public static void removeTrack(int index) {
        Element element = (Element) xmlDoc.getElementsByTagName("track").item(index);
        Node parent = element.getParentNode();
        parent.removeChild(element);
        parent.normalize();
    }

    /**
     * This method receive track or genre and index of where this object should write.
     * Length of library+1 if this object need to add into library, index<length when rewrite.
     *
     * @param index
     * @param object
     */
    private static void writeTrackXml(int index, Track object) throws IOException {
        NodeList tracks = xmlDoc.getElementsByTagName("track");
        Element newTrack = xmlDoc.createElement("track");
        //xmlDoc.appendChild(newTrack);
        //Node newTrack = xmlDoc.createElement("track");
        ((Element) newTrack).setAttribute("trackName", object.getTrackName());
        ((Element) newTrack).setAttribute("trackArtist", object.getTrackArtist());
        ((Element) newTrack).setAttribute("trackAlbum", object.getTrackAlbum());
        ((Element) newTrack).setAttribute("trackLength", String.valueOf(object.getTrackLength()));

        Node newGenre = xmlDoc.createElement("genre");
        ((Element) newGenre).setAttribute("genreName", object.getTrackGenre().getGenreName());
        ((Element) newGenre).setAttribute("establishingCentury", String.valueOf(object.getTrackGenre().getEstablishingCentury()));
        newTrack.appendChild(newGenre);
        xmlDoc.getDocumentElement().appendChild(newTrack);
        updateDocument();
    }

    private static void updateDocument() throws IOException {
        DOMImplementationLS domImplementationLS =
                (DOMImplementationLS) xmlDoc.getImplementation().getFeature("LS", "3.0");
        LSOutput lsOutput = domImplementationLS.createLSOutput();
        try (FileOutputStream outputStream = new FileOutputStream(PATH)) {
            lsOutput.setByteStream(outputStream);
            LSSerializer lsSerializer = domImplementationLS.createLSSerializer();
            lsSerializer.write(xmlDoc, lsOutput);
        }
    }
}
