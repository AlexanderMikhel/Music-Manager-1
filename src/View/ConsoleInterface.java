package View;

import Controller.Controller;
import Model.Genre;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInterface {
    private static Scanner in = new Scanner(System.in);
    private static Controller controller=new Controller();

    public static void startApplication() {
        int close = 0;
        while (close != 6) {
            displayInformation();
            close = in.nextInt();
            switch (close) {
                case 1: {
                    controller.setTrack();
                }
                break;
                case 2: {
                    menuItems("delete");

                }
                break;
                case 3: {
                    menuItems("change");

                }
                break;
                case 4: {

                }
                break;
                case 5: {
                    menuItems("find");

                }
                break;
            }
        }
    }

    /**
     * print to console help for interact with the application
     */
    private static void displayInformation() {
        System.out.println("What you want to do?");
        System.out.println("1 - add new track to library");
        System.out.println("2 - delete some data");
        System.out.println("3 - change some data");
        System.out.println("4 - browse library");
        System.out.println("5 - find track");
        System.out.println("6 - close application\n");
    }

    /**
     * interface for menu items 2,3,5
     */
    private static void menuItems(String action) {
        System.out.println("1 - " + action + " track");
        System.out.println("2 - " + action + " genre");
    }
}