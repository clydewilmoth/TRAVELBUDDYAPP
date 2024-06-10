package de.hs_mannheim.ui;

import de.hs_mannheim.facade.Application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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

    private JLabel mainMenuLabel;
    private JPanel logRegScreen;
    private JButton loginButton;
    private JButton registerButton;

    private JLabel loginLabel;
    private JPanel loginScreen;
    private JPanel loginName;
    private JPanel loginPassword;
    private JButton loginConfirmButton;
    private JButton loginZurückButton;

    private JLabel registerLabel;
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

    private JLabel menuLabel;
    private JPanel menu;
    private JPanel searchPLZ_ORT;
    private JButton searchConfirmButton;
    private JButton randDestinationsCarButton;
    private JButton randDestinationsBikeButton;
    private JPanel profile;
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
        mainMenuLabel = new JLabel("              Hauptmenü             ");
        mainMenuLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        loginButtonCreate();
        registerButtonCreate();
        logRegScreen.add(mainMenuLabel);
        logRegScreen.add(loginButton, BorderLayout.WEST);
        logRegScreen.add(registerButton, BorderLayout.EAST);

        loginScreen = new JPanel(new FlowLayout());
        loginLabel = new JLabel("              Einloggen             ");
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        loginNameCreate();
        loginPasswordCreate();
        loginConfirmButtonCreate();
        loginZurückButtonCreate();
        loginScreen.add(loginLabel);
        loginScreen.add(loginName);
        loginScreen.add(loginPassword);
        loginScreen.add(loginConfirmButton);
        loginScreen.add(loginZurückButton);

        registerScreen = new JPanel(new FlowLayout()); // flowLayout muss angepasst werden, um nicht scheiße auszusehen
        registerLabel = new JLabel("Registrieren");
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
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
        registerScreen.add(registerLabel);
        registerScreen.add(registerName);
        registerScreen.add(registerPassword);
        registerScreen.add(registerOrt);
        registerScreen.add(registerPLZ);
        registerScreen.add(registerCarName);
        registerScreen.add(registerCarSpeed);
        registerScreen.add(registerCarCO2);
        registerScreen.add(registerBikeSpeed);
        registerScreen.add(registerConfirmButton);
        registerScreen.add(registerZurückButton);

        menu = new JPanel(null);
        menuLabel = new JLabel("Gugl Maps");
        menuLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        searchPLZ_ORTCreate();
        searchConfirmButtonCreate();
        randDestinationsCarButtonCreate();
        randDestinationsBikeButtonCreate();
        logOutButtonCreate();
        menuLabel.setBounds(180, -125, 300, 300);
        searchPLZ_ORT.setBounds(20, 50, 300, 100);
        searchConfirmButton.setBounds(346, 70, 110, 30);
        randDestinationsCarButton.setBounds(346, 115, 110, 30);
        randDestinationsBikeButton.setBounds(346, 160, 110, 30);
        logOutButton.setBounds(346, 420, 110, 30);
        menu.add(menuLabel);
        menu.add(searchPLZ_ORT);
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
        jframe.setSize(450, 160);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

	private void profileCreate() {
		profile = new JPanel(new FlowLayout());
		ImageIcon png = png("profile.png", 30, 30);
		JLabel jl = new JLabel(png);
		String[] details = facade.getUserDetails(); // 6 bzw 7
		JLabel jlTXT = new JLabel("Name: " + details[0]
				               + "\nWohnort: " + details[1]
				               + "\nPlz: " + details[2]
				               + "\nAuto Name: " + details[3]
				               + "\nAuto km/h: " + details[4]
				               + "\nCO2/100km: " + details[5]
				               + "\nFahrrad km/h: " + details[6]
				               + "\nWetter: " + facade.current_weather());
		profile.add(jl);
		profile.add(jlTXT);
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
                jframe.setSize(new Dimension(400, 150));
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
                jframe.setSize(new Dimension(260, 390));
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
        JLabel jl = new JLabel("                   Name");
        JTextField tf = new JTextField(10);
        tf.setName("registerPasswordText");
        registerName.add(jl);
        registerName.add(tf);
    }

    private void registerPasswordCreate() {
        registerPassword = new JPanel();
        JLabel jl = new JLabel("             Passwort");
        JTextField tf = new JTextField(10);
        tf.setName("registerPasswordText");
        registerPassword.add(jl);
        registerPassword.add(tf);
    }

    private void registerOrtCreate() {
        registerOrt = new JPanel();
        JLabel jl = new JLabel("                        Ort");
        JTextField tf = new JTextField(10);
        tf.setName("registerOrtText");
        registerOrt.add(jl);
        registerOrt.add(tf);
    }

    private void registerPLZCreate() {
        registerPLZ = new JPanel();
        JLabel jl = new JLabel("                       PLZ");
        JTextField tf = new JTextField(10);
        tf.setName("registerPLZText");
        registerPLZ.add(jl);
        registerPLZ.add(tf);
    }

    private void registerCarNameCreate() {
        registerCarName = new JPanel();
        JLabel jl = new JLabel("            Auto Name");
        JTextField tf = new JTextField(10);
        tf.setName("registerCarNameText");
        registerCarName.add(jl);
        registerCarName.add(tf);
    }

    private void registerCarSpeedCreate() {
        registerCarSpeed = new JPanel();
        JLabel jl = new JLabel("             Auto km/h");
        JTextField tf = new JTextField(10);
        tf.setName("registerCarSpeedText");
        registerCarSpeed.add(jl);
        registerCarSpeed.add(tf);
    }

    private void registerCarCO2Create() {
        registerCarCO2 = new JPanel();
        JLabel jl = new JLabel("Verbrauch/100km");
        JTextField tf = new JTextField(10);
        tf.setName("registerCarCO2Text");
        registerCarCO2.add(jl);
        registerCarCO2.add(tf);
    }

    private void registerBikeSpeedCreate() {
        registerBikeSpeed = new JPanel();
        JLabel jl = new JLabel("             Bike km/h");
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

    private void searchPLZ_ORTCreate() {
        searchPLZ_ORT = new JPanel();
        JLabel jl = new JLabel("Plz oder Ort");
        JTextField tf = new JTextField(28);
        tf.setName("ortSuche");
        searchPLZ_ORT.add(jl);
        searchPLZ_ORT.add(tf);
    }

    private void searchConfirmButtonCreate() {
        searchConfirmButton = new JButton();
        searchConfirmButton.setPreferredSize(new Dimension(150, 80));
        searchConfirmButton.setFocusable(false);

        ImageIcon icon = png("lupe.png", 20, 20);
        searchConfirmButton.setIcon(icon);
        searchConfirmButton.setText(" Suche");
        searchConfirmButton.setIconTextGap(3);

        searchConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // implementiere Änderung vom Panel
            }
        });
    }

    private void randDestinationsCarButtonCreate() { //Auto Icon hinzufügen
        randDestinationsCarButton = new JButton();
        randDestinationsCarButton.setPreferredSize(new Dimension(150, 80));
        randDestinationsCarButton.setFocusable(false);

        ImageIcon icon = png("auto.png", 20, 20);
        randDestinationsCarButton.setIcon(icon);
        randDestinationsCarButton.setText("Kurztrip");
        randDestinationsCarButton.setIconTextGap(3);

        randDestinationsCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // implementiere Änderung vom Panel
            }
        });
    }

    private void randDestinationsBikeButtonCreate() { //Fahrrad Icon hinzufügen
        randDestinationsBikeButton = new JButton();
        randDestinationsBikeButton.setPreferredSize(new Dimension(150, 80));
        randDestinationsBikeButton.setFocusable(false);

        ImageIcon icon = png("fahrrad.png", 20, 20);
        randDestinationsBikeButton.setIcon(icon);
        randDestinationsBikeButton.setText("Kurztrip");
        randDestinationsBikeButton.setIconTextGap(3);

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

    public ImageIcon png(String path, int sizeX, int sizeY) { // umständlich und png nicht transparent
        ImageIcon icon = new ImageIcon(getClass().getResource(path)); // Path des PNGs muss angegeben werden
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(sizeX, sizeY, java.awt.Image.SCALE_SMOOTH); // wähle Größe des PNGs
        ImageIcon iconTwo = new ImageIcon(newimg);
        return iconTwo;
    }

}
