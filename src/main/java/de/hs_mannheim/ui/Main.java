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

    //Wird in mehreren Labels benutzt (reg und log)
    private JPanel errorMessage; // mal gucken, vllt auch nicht
    private JPanel profile; //wird nach registrieren oder login kreiert

    private JPanel logRegScreen;
    private JButton loginButton;
    private JButton registerButton;

    private JPanel loginScreen;
    private JPanel loginName;
    private JPanel loginPassword;
    private JButton loginConfirmButton;
    private JButton loginZurückButton;

    private JPanel registerScreen; // Backend muss PLZ und Ort prüfen, sonst kann die Entfernung in Zukunft nicht berechnet werden
    private JPanel registerName;
    private JPanel registerPassword;
    private JPanel registerOrt;
    private JPanel registerPLZ;
    private JPanel registerCarName;
    private JPanel registerCarSpeed;
    private JPanel registerCarCO2;
    private JPanel registerBikeSpeed;
    private JButton registerConfirmButton;
    private JButton registerZurückButton;

    private JPanel menu;
    private JPanel searchPLZ;
    private JPanel searchOrt;
    private JButton searchConfirmButton;
    private JButton randDestinationsCarButton;
    private JButton randDestinationsBikeButton;
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

        errorMessageCreate();//wahrscheinlich muss individuell angepasst werden

        logRegScreen = new JPanel(new FlowLayout());
        loginButtonCreate();
        registerButtonCreate();
        logRegScreen.add(loginButton, BorderLayout.WEST);
        logRegScreen.add(registerButton, BorderLayout.EAST);

        loginScreen = new JPanel(new FlowLayout());
        loginNameCreate();
        loginPasswordCreate();
        loginConfirmButtonCreate();
        loginZurückButtonCreate();
        loginScreen.add(loginName);
        loginScreen.add(loginPassword);
        loginScreen.add(loginConfirmButton);
        loginScreen.add(loginZurückButton);
        loginScreen.add(loginZurückButton);

        registerScreen = new JPanel(new FlowLayout()); // flowLayout muss angepasst werden, um nicht scheiße auszusehen
        registerNameCreate();
        registerPasswordCreate();
        registerOrtCreate();
        registerPLZCreate();
        registerCarNameCreate();
        registerCarSpeedCreate();
        registerCarCO2Create();
        registerBikeSpeedCreate();
        registerConfirmButtonCreate();
        registerZurückButtonCreate();
        registerScreen.add(registerName);
        registerScreen.add(registerPassword);
        registerScreen.add(registerOrt);
        registerScreen.add(registerPLZ);
        registerScreen.add(registerCarSpeed);
        registerScreen.add(registerBikeSpeed);
        registerScreen.add(registerConfirmButton);
        registerScreen.add(registerZurückButton);
        registerScreen.add(registerZurückButton);

        menu = new JPanel(null);
        searchPLZCreate();
        searchOrtCreate();
        searchConfirmButtonCreate();
        randDestinationsCarButtonCreate();
        randDestinationsBikeButtonCreate();
        logOutButtonCreate();
        searchPLZ.setBounds(160, 0, 200, 200);
        searchOrt.setBounds(0, 0, 200, 200);
        searchConfirmButton.setBounds(360, 5, 90, 20);
        randDestinationsCarButton.setBounds(360, 40, 90, 20);
        randDestinationsBikeButton.setBounds(360, 75, 90, 20);
        logOutButton.setBounds(380, 420, 90, 30);
        menu.add(searchPLZ);
        menu.add(searchOrt);
        menu.add(searchConfirmButton);
        menu.add(randDestinationsCarButton);
        menu.add(randDestinationsBikeButton);
        menu.add(logOutButton);

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

    private void profileCreate() {
        profile = new JPanel();
        JLabel jl = new JLabel("Profil");
        searchOrt.add(jl);
        //Backend soll DatenLiefern
    }

    private void errorMessageCreate() {
        errorMessage = new JPanel();
        JLabel jl = new JLabel("Daten sind falsch oder unvollständig!");
        errorMessage.add(jl);
    }


    private void loginButtonCreate() {
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(150, 50));
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "2");
                jframe.setSize(new Dimension(500, 180));
                // implementiere einlesen der textfelder des Panels
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
                jframe.setSize(new Dimension(520, 180));
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

    private void loginZurückButtonCreate() {
        loginZurückButton = new JButton("Back");
        loginZurückButton.setPreferredSize(new Dimension(80, 20));
        loginZurückButton.setFocusable(false);
        loginZurückButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "1");
                jframe.setSize(new Dimension(450, 140));
            }
        });
    }

    private void registerNameCreate() {
        registerName = new JPanel();
        JLabel jl = new JLabel("Name");
        JTextField tf = new JTextField(10);
        tf.setName("registerPasswordText");
        registerName.add(jl);
        registerName.add(tf);
    }

    private void registerPasswordCreate() {
        registerPassword = new JPanel();
        JLabel jl = new JLabel("Passwort");
        JTextField tf = new JTextField(10);
        tf.setName("registerPasswordText");
        registerPassword.add(jl);
        registerPassword.add(tf);
    }

    private void registerOrtCreate() {
        registerOrt = new JPanel();
        JLabel jl = new JLabel("Ort");
        JTextField tf = new JTextField(10);
        tf.setName("registerOrtText");
        registerOrt.add(jl);
        registerOrt.add(tf);
    }

    private void registerPLZCreate() {
        registerPLZ = new JPanel();
        JLabel jl = new JLabel("PLZ");
        JTextField tf = new JTextField(10);
        tf.setName("registerPLZText");
        registerPLZ.add(jl);
        registerPLZ.add(tf);
    }

    private void registerCarNameCreate() {
        registerCarName = new JPanel();
        JLabel jl = new JLabel("Auto km/h");
        JTextField tf = new JTextField(10);
        tf.setName("registerCarNameText");
        registerCarName.add(jl);
        registerCarName.add(tf);
    }

    private void registerCarSpeedCreate() {
        registerCarSpeed = new JPanel();
        JLabel jl = new JLabel("Auto km/h");
        JTextField tf = new JTextField(10);
        tf.setName("registerCarSpeedText");
        registerCarSpeed.add(jl);
        registerCarSpeed.add(tf);
    }

    private void registerCarCO2Create() {
        registerCarCO2 = new JPanel();
        JLabel jl = new JLabel("Auto km/h");
        JTextField tf = new JTextField(10);
        tf.setName("registerCarCO2Text");
        registerCarCO2.add(jl);
        registerCarCO2.add(tf);
    }

    private void registerBikeSpeedCreate() {
        registerBikeSpeed = new JPanel();
        JLabel jl = new JLabel("Bike km/h");
        JTextField tf = new JTextField(10);
        tf.setName("registerBikeSpeedText");
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

    private void registerZurückButtonCreate() {
        registerZurückButton = new JButton("Back");
        registerZurückButton.setPreferredSize(new Dimension(80, 20));
        registerZurückButton.setFocusable(false);
        registerZurückButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "1");
                jframe.setSize(new Dimension(450, 140));
            }
        });
    }

    private void searchPLZCreate() {
        searchPLZ = new JPanel();
        JLabel jl = new JLabel("PLZ");
        JTextField tf = new JTextField(10);
        tf.setName("plzSuche");
        searchPLZ.add(jl);
        searchPLZ.add(tf);
    }

    private void searchOrtCreate() {
        searchOrt = new JPanel();
        JLabel jl = new JLabel("Ort");
        JTextField tf = new JTextField(10);
        tf.setName("ortSuche");
        searchOrt.add(jl);
        searchOrt.add(tf);
    }

    private void searchConfirmButtonCreate() {
        searchConfirmButton = new JButton("Suche");
        searchConfirmButton.setPreferredSize(new Dimension(150, 50));
        searchConfirmButton.setFocusable(false);
        searchConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // implementiere Änderung vom Panel
            }
        });
    }

    private void randDestinationsCarButtonCreate() { //Auto Icon hinzufügen
        randDestinationsCarButton = new JButton("Kurztrip");
        randDestinationsCarButton.setPreferredSize(new Dimension(150, 50));
        randDestinationsCarButton.setFocusable(false);
        randDestinationsCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // implementiere Änderung vom Panel
            }
        });
    }

    private void randDestinationsBikeButtonCreate() { //Fahrrad Icon hinzufügen
        randDestinationsBikeButton = new JButton("Kurztrip");
        randDestinationsBikeButton.setPreferredSize(new Dimension(150, 50));
        randDestinationsBikeButton.setFocusable(false);
        randDestinationsBikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // implementiere Änderung vom Panel
            }
        });
    }

    private void logOutButtonCreate() {
        logOutButton = new JButton("Logout");
        logOutButton.setPreferredSize(new Dimension(150, 50));
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "1");
                jframe.setSize(new Dimension(450, 120));
                // implementiere Ausloggen im Backend
            }
        });
    }

}
