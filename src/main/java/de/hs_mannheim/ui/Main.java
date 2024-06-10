package de.hs_mannheim.ui;

import de.hs_mannheim.facade.Application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

        logRegScreen = new JPanel(new FlowLayout());
        loginButtonCreate();
        registerButtonCreate();
        logRegScreen.add(loginButton, BorderLayout.WEST);
        logRegScreen.add(registerButton, BorderLayout.EAST);

        loginScreen = new JPanel(new FlowLayout());
        loginNameCreate();
        loginPasswordCreate();
        loginConfirmButtonCreate();
        loginScreen.add(loginName);
        loginScreen.add(loginPassword);
        loginScreen.add(loginConfirmButton);

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
        jframe.setSize(450, 140);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private void loginButtonCreate() {
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(150, 50));
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "2");
                jframe.setSize(new Dimension(500, 120));
                // implementiere einlesen der textfelder des Panles
            }
        });

    }

    private void registerButtonCreate() {
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(150, 50));
        registerButton.setFocusable(false);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "3");
                jframe.setSize(new Dimension(500, 120));
                // implementiere einlesen der textfelder des Panles
            }
        });
    }

    private void loginNameCreate() {
        loginName = new JPanel();
        JLabel jl = new JLabel("Name");
        JTextField tf = new JTextField(10);
        tf.setName("loginNameText");
        loginName.add(jl);
        loginName.add(tf);
    }

    private void loginPasswordCreate() {
        loginPassword = new JPanel();
        JLabel jl = new JLabel("Passwort");
        JTextField tf = new JTextField(10);
        tf.setName("loginPasswordText");
        loginPassword.add(jl);
        loginPassword.add(tf);
    }

    private void loginConfirmButtonCreate() {
        loginConfirmButton = new JButton("Confirm");
        loginConfirmButton.setPreferredSize(new Dimension(80, 20));
        loginConfirmButton.setFocusable(false);
        loginConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "4");
                jframe.setSize(new Dimension(500, 500));
                // implementiere einlesen der textfelder des Panles
            }
        });
    }

    private void registerNameCreate() {
        registerName = new JPanel();
        JLabel jl = new JLabel("Name");
        JTextField tf = new JTextField(10);
        tf.setName("loginPasswordText");
        registerName.add(jl);
        registerName.add(tf);
    }

    private void registerPasswordCreate() {
        registerPassword = new JPanel();
        JLabel jl = new JLabel("Passwort");
        JTextField tf = new JTextField(10);
        tf.setName("loginPasswordText");
        registerPassword.add(jl);
        registerPassword.add(tf);
    }

    private void registerOrtCreate() {
        registerOrt = new JPanel();
        JLabel jl = new JLabel("Ort");
        JTextField tf = new JTextField(10);
        tf.setName("loginPasswordText");
        registerOrt.add(jl);
        registerOrt.add(tf);
    }

    private void registerPLZCreate() {
        registerPLZ = new JPanel();
        JLabel jl = new JLabel("PLZ");
        JTextField tf = new JTextField(10);
        tf.setName("loginPasswordText");
        registerPLZ.add(jl);
        registerPLZ.add(tf);
    }

    private void registerCarSpeedCreate() {
        registerCarSpeed = new JPanel();
        JLabel jl = new JLabel("Auto km/h");
        JTextField tf = new JTextField(10);
        tf.setName("loginPasswordText");
        registerCarSpeed.add(jl);
        registerCarSpeed.add(tf);
    }

    private void registerBikeSpeedCreate() {
        registerBikeSpeed = new JPanel();
        JLabel jl = new JLabel("Bike km/h");
        JTextField tf = new JTextField(10);
        tf.setName("bikeSpeedText");
        registerBikeSpeed.add(jl);
        registerBikeSpeed.add(tf);
    }

    private void registerConfirmButtonCreate() {
        registerConfirmButton = new JButton();
        registerConfirmButton = new JButton("Confirm");
        registerConfirmButton.setPreferredSize(new Dimension(80, 20));
        registerConfirmButton.setFocusable(false);
        registerConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "4");
                jframe.setSize(new Dimension(500, 500));
                // implementiere einlesen der textfelder des Panles
            }
        });
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
