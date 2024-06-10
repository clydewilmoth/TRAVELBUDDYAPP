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
        logRegScreen.add(loginButton, BorderLayout.WEST);
        logRegScreen.add(registerButton, BorderLayout.EAST);

        loginScreen = new JPanel(new BorderLayout());
        loginNameCreate();
        loginPasswordCreate();
        loginConfirmButtonCreate();
        loginScreen.add(loginName, BorderLayout.NORTH);
        loginScreen.add(loginPassword, BorderLayout.CENTER);
        loginScreen.add(loginConfirmButton, BorderLayout.SOUTH);

        registerScreen = new JPanel(new FlowLayout()); // flowLayout muss angepasst werden, um nicht scheiße auszusehen
        registerNameCreate();
        registerPasswordCreate();
        registerOrtCreate();
        registerPLZCreate();
        registerCarSpeedCreate();
        registerBikeSpeedCreate();
        registerConfirmButtonCreate();
        registerScreen.add(registerName);
        registerScreen.add(registerPassword);
        registerScreen.add(registerOrt);
        registerScreen.add(registerCarSpeed);
        registerScreen.add(registerBikeSpeed);
        registerScreen.add(registerConfirmButton);

        menu = new JPanel(new BorderLayout());
        searchPLZCreate();
        searchOrtCreate();
        searchConfirmButtonCreate();
        threeRandomDestinationsButtonCreate();
        logOutButtonCreate();
        // muss noch konzepiert werden

        panelLayout.add(logRegScreen, "1");
        panelLayout.add(loginScreen, "2");
        panelLayout.add(registerScreen, "3");
        panelLayout.add(menu, "4");
        cardLayout.show(panelLayout, "1");
        jframe.add(panelLayout);
        jframe.setResizable(false);
        jframe.setSize(500, 500);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private void loginButtonCreate() {
        loginButton = new JButton();

    }

    private void registerButtonCreate() {
        registerButton = new JButton();

    }

    private void loginNameCreate() {
        loginName = new JPanel();

    }

    private void loginPasswordCreate() {
        loginPassword = new JPanel();

    }

    private void loginConfirmButtonCreate() {
        loginConfirmButton = new JButton();

    }

    private void registerNameCreate() {
        registerName = new JPanel();

    }

    private void registerPasswordCreate() {
        registerPassword = new JPanel();

    }

    private void registerOrtCreate() {
        registerOrt = new JPanel();

    }

    private void registerPLZCreate() {
        registerPLZ = new JPanel();

    }

    private void registerCarSpeedCreate() {
        registerCarSpeed = new JPanel();

    }

    private void registerBikeSpeedCreate() {
        registerBikeSpeed = new JPanel();

    }

    private void registerConfirmButtonCreate() {
        registerConfirmButton = new JButton();

    }

    private void searchPLZCreate() {
        searchPLZ = new JPanel();

    }

    private void searchOrtCreate() {
        searchOrt = new JPanel();

    }

    private void searchConfirmButtonCreate() {
        searchConfirmButton = new JButton();

    }

    private void threeRandomDestinationsButtonCreate() {
        threeRandomDestinationsButton = new JButton();

    }

    private void logOutButtonCreate() {
        logOutButton = new JButton();

    }

}