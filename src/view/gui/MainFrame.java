package view.gui;

import controller.Controller;
import model.Genre;
import model.Library;
import model.Track;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private static Library library;
    public MainFrame() throws HeadlessException {
        super();
        setTitle("Music Manager");
        setBackground(Color.darkGray);
        setPreferredSize(new Dimension(500, 400));
        setSize(500, 400);
        //addComponents(getContentPane());
        //pack();
        setVisible(true);
    }

    public static void main(String[] args) throws TransformerException, ParserConfigurationException {
        initXml();
        library = Controller.getLibrary();
        new MainFrame();
    }

    /*private void addComponents(Container contentPane) {
        ArrayList<Track> tracks = library.getTracks();
        ArrayList<Genre> genres = library.getGenresStore();
        JList jList=new JList();
        for (int i =0; i<library.getTracksQuantity(); i++){
            Track track = tracks.get(i);
            jList
            jList.append("Track Name: "+track.getTrackName()+"\n");
            jList.append("Track Artist: "+track.getTrackArtist()+"\n");
            jList.append("Track Album: "+track.getTrackAlbum()+"\n");
            int minutes = track.getTrackLength()/60;
            int seconds = track.getTrackLength() - minutes*60;
            jList.append("Track Length: "+minutes+":"+seconds+"\n");
            jList.append("Track Genre: "+track.getTrackGenre().getGenreName()+"\n");
            jList.append("-----------------\n\n");
        }

        jList.append("Some");
        contentPane.add(jList);
    }*/

    //Read library from xml. If there's no file, it will create with the first track adding
    private static void initXml() throws TransformerException, ParserConfigurationException {
        try {
            Controller.createDocument();
            Controller.readXml();
        } catch (ParserConfigurationException e) {

        } catch (IOException e) {
            System.err.println("You library is empty");
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
