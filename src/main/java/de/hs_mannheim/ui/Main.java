package de.hs_mannheim.ui;

import de.hs_mannheim.facade.Application;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame {

    public static void main(String[] args) {

    }

    private Application facade;

    private JFrame jframe;
    private CardLayout cardLayout;
    private JPanel panelLayout;

    private JPanel logRegScreen;
    private JButton loginButton;
    private JButton registerButton;

    private JPanel loginScreen;
    private JPanel loginName;
    private JPanel loginPassword;
    private JButton loginConfirmButton;

    private JPanel registerScreen; // Backend muss PLZ und Ort prüfen, sonst kann die Entfernung in Zukunft nicht
    // berechnet werden
    private JPanel registerName;
    private JPanel registerPassword;
    private JPanel registerOrt;
    private JPanel registerPLZ;
    private JPanel registerCarSpeed;
    private JPanel registerBikeSpeed;
    private JButton registerConfirmButton;
    private JLabel errorMessage; // mal gucken, vllt auch nicht

    private JPanel menu;
    private JPanel searchPLZ;
    private JPanel searchOrt;
    private JButton searchConfirmButton;
    private JButton threeRandomDestinationsButton;
    private JButton logOutButton;
}

