package de.hs_mannheim.ui;

import de.hs_mannheim.facade.Application;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame {
    public static void main(String[] args) {
        Main main = new Main("35a75437476f12302f72e55d368485db");
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

    public Main(String api_key) {
        this.facade = new Application(api_key);
        initialize();
    }

    public void initialize() {
        jframe = new JFrame();
        cardLayout = new CardLayout();
        panelLayout = new JPanel(cardLayout);
        panelLayout.setLayout(cardLayout);
        jframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        logRegScreen = new JPanel(new BorderLayout());
        loginButtonCreate();
        registerButtonCreate();

        loginScreen = new JPanel(new BorderLayout());
        loginNameCreate();
        loginPasswortCreate();
        loginConfirmButtonCreate();

        registerScreenCreate();
        registerNameCreate();
        registerPasswortCreate();
        registerOrtCreate();
        registerPLZCreate();
        registerCarSpeed();
        registerBikeSpeed();
        registerConfirmButton();

        menu = new JPanel(new BorderLayout());
        searchPLZCreate();
        searchOrtCreate();
        searchConfirmButton();
        threeRandomDestinationsButtonCreate();
        logOutButtonCreate();

    }

    private void loginButtonCreate() {
        // TODO Auto-generated method stub

    }

    private void registerButtonCreate() {
        // TODO Auto-generated method stub

    }

    private void loginNameCreate() {
        // TODO Auto-generated method stub

    }

    private void loginPasswortCreate() {
        // TODO Auto-generated method stub

    }

    private void loginConfirmButtonCreate() {
        // TODO Auto-generated method stub

    }

    private void registerScreenCreate() {
        // TODO Auto-generated method stub

    }

    private void registerNameCreate() {
        // TODO Auto-generated method stub

    }

    private void registerPasswortCreate() {
        // TODO Auto-generated method stub

    }

    private void registerOrtCreate() {
        // TODO Auto-generated method stub

    }

    private void registerPLZCreate() {
        // TODO Auto-generated method stub

    }

    private void registerCarSpeed() {
        // TODO Auto-generated method stub

    }

    private void registerBikeSpeed() {
        // TODO Auto-generated method stub

    }

    private void registerConfirmButton() {
        // TODO Auto-generated method stub

    }

    private void searchPLZCreate() {
        // TODO Auto-generated method stub

    }

    private void searchOrtCreate() {
        // TODO Auto-generated method stub

    }

    private void searchConfirmButton() {
        // TODO Auto-generated method stub

    }

    private void threeRandomDestinationsButtonCreate() {
        // TODO Auto-generated method stub

    }

    private void logOutButtonCreate() {
        // TODO Auto-generated method stub

    }

}