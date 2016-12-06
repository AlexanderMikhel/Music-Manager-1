package view;

import controller.Controller;
import model.Genre;
import model.Track;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInterface {
    private static Scanner in = new Scanner(System.in);

    public static void startApplication() throws TransformerException, ParserConfigurationException {
        initXml();
        mainScreen();
    }

    private static void mainScreen() throws TransformerException, ParserConfigurationException {
        int close = 0;
        boolean correctEnter = false;
        while (close != 6) {
            displayInformation();
            close = enterMenuItem(6);
            switch (close) {
                case 1: {
                    switch (menuItems("add", false)) {
                        case 1: {
                            try {
                                Controller.setTrack();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                        case 2: {
                            Controller.setGenre();
                        }
                        break;
                    }
                }
                break;
                case 2: {
                    //menuItems("delete", false);
                    Controller.removeTrack(in.nextInt());
                }
                break;
                case 3: {
                    menuItems("change", false);

                }
                break;
                case 4: {
                    switch (menuItems("browse", true)) {
                        case 1: {
                            displayTracks(Controller.getTracks());
                        }
                        break;
                        case 2: {
                            displayGenres(Controller.getGenres());
                        }
                        break;
                    }
                }
                break;
                case 5: {
                    //menuItems("find");
                    //displayTracks(Controller.search());
                }
                break;
            }
        }
    }

    //Read library from xml. If there's no file, it will create with the first track adding
    private static void initXml() throws TransformerException, ParserConfigurationException {
        try {
            Controller.createDocument();
            Controller.readXml();
        } catch (ParserConfigurationException e) {

        } catch (IOException e) {
            System.err.println("You library is empty");
            mainScreen();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * print to console help for interact with the application
     */
    private static void displayInformation() {
        System.out.println("What you want to do?");
        System.out.println("1 - add new track or genre to library");
        System.out.println("2 - delete some data");
        System.out.println("3 - change some data");
        System.out.println("4 - browse library");
        System.out.println("5 - find track");
        System.out.println("6 - close application\n");
    }

    /**
     * interface for menu items 2,3,5
     */
    private static int menuItems(String action, boolean multiply) {
        String track = "track";
        String genre = "genre";
        if (multiply) {
            track += "s";
            genre += "s";
        }
        System.out.printf("1 - %s %s\n", action, track);
        System.out.printf("2 - %s %s\n", action, genre);
        System.out.println("3 - back");
        return enterMenuItem(3);
    }

    private static void displayTracks(Track[] tracks) {
        if (tracks.length == 0) {
            System.out.println("Library is empty");
            System.out.println("------------------------------\n");
        } else {
            for (Track track : tracks) {
                System.out.println("Name: " + track.getTrackName());
                System.out.println("Artist: " + track.getTrackArtist());
                System.out.println("Album: " + track.getTrackAlbum());
                System.out.println("Genre: " + track.getTrackGenre().getGenreName());
                int minutes = track.getTrackLength() / 60;
                int seconds = track.getTrackLength() - minutes * 60;
                System.out.printf("Length: %d:%d\n", minutes, seconds);
                System.out.println("-----------------------\n");
            }
        }
    }

    private static void displayGenres(Genre[] genres) {
        if (genres.length == 0) {
            System.out.println("Library is empty");
            System.out.println("------------------------------\n");
        } else {
            for (Genre genre : genres) {
                System.out.println("Genre name: " + genre.getGenreName());
                System.out.println("Establishing Century: " + genre.getEstablishingCentury());
                System.out.println("------------------------------\n");
            }
        }
    }

    /**
     * We need to be sure that user will input correct menu item
     */
    private static int enterMenuItem(int limit) {
        boolean correctEnter = false;
        int close = 0;
        while (!correctEnter) {
            try {
                close = in.nextInt();
                if (close > 0 && close <= limit) {
                    correctEnter = true;
                } else {
                    System.err.println("Wrong enter, input correct menu item");
                }
            } catch (InputMismatchException e) {
                System.err.println("Wrong enter, input correct menu item");
                in.next();
            }
        }
        return close;
    }
}
