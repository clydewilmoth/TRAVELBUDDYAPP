package de.hs_mannheim.ui;

import de.hs_mannheim.facade.Application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;

public class Main extends JFrame {
    public static void main(String[] args) {
        Main main = new Main("35a75437476f12302f72e55d368485db");
    }
    private Application facade;

    private JFrame jframe;
    private CardLayout cardLayout;
    private JPanel panelLayout;

    //Wird in mehreren Labels benutzt (reg und log)
    private JFrame errorMessage; // mal gucken, vllt auch nicht

    private JLabel mainMenuLabel;
    private JPanel logRegScreen;
    private JButton loginButton;
    private JButton registerButton;

    private JLabel loginLabel;
    private JPanel loginScreen;
    private JPanel loginName;
    private JPanel loginPassword;
    private JButton loginConfirmButton;
    private JButton loginBackButton;

    private JLabel registerLabel;
    private JPanel registerScreen; // Backend muss PLZ und Ort prüfen, sonst kann die Entfernung in Zukunft nicht berechnet werden
    private JPanel registerName;
    private JPanel registerPassword;
    private JPanel registerPasswordTwo;
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
    private JButton changePasswordButton;
    private JButton changeDetailsButton;
    private JButton logOutButton;
    private JScrollPane jsp;
    private JPanel destinationButtons = new JPanel();

    private JPanel changePassword;
    private JLabel changePasswordLabel;
    private JPanel changePasswordOldPassword;
    private JPanel changePasswordNewPassword;
    private JPanel changePasswordConfirmNewPassword;
    private JButton changePasswordBackButton;
    private JButton changePasswordConfirmButton;

    private JPanel changeDetails;
    private JLabel changeDetailsLabel;
    private JPanel changeName;
    private JPanel checkChangePassword;
    private JPanel changeOrt;
    private JPanel changePLZ;
    private JPanel changeCarName;
    private JPanel changeCarSpeed;
    private JPanel changeCarCO2;
    private JPanel changeBikeSpeed;
    private JButton changeConfirmButton;
    private JButton changeBackButton;

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
        loginScreen.add(loginBackButton);

        registerScreen = new JPanel(new FlowLayout()); // flowLayout muss angepasst werden, um nicht scheiße auszusehen
        registerLabel = new JLabel("Registrieren");
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        registerNameCreate();
        registerPasswordCreate();
        registerPasswordTwoCreate();
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
        registerScreen.add(registerPasswordTwo);
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
        changeDetailsButtonCreate();
        changePasswordButtonCreate();
        menuLabel.setBounds(180, -125, 300, 300);
        searchPLZ_ORT.setBounds(20, 50, 300, 100);
        searchConfirmButton.setBounds(346, 70, 110, 30);
        randDestinationsCarButton.setBounds(346, 105, 110, 30);
        randDestinationsBikeButton.setBounds(346, 140, 110, 30);
        logOutButton.setBounds(340, 430, 130, 30);
        changeDetailsButton.setBounds(340, 398, 130, 30);
        changePasswordButton.setBounds(340, 366, 130, 30);

        menu.add(menuLabel);
        menu.add(searchPLZ_ORT);
        menu.add(searchConfirmButton);
        menu.add(randDestinationsCarButton);
        menu.add(randDestinationsBikeButton);
        menu.add(logOutButton);
        menu.add(changeDetailsButton);
        menu.add(changePasswordButton);

        changePassword = new JPanel(new FlowLayout());
        changePasswordLabel = new JLabel("Password ändern");
        changePasswordLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        changePasswordOldPasswordCreate();
        changePasswordNewPasswordCreate();
        changePasswordConfirmNewPasswordCreate();
        changePasswordBackButtonCreate();
        changePasswordConfirmButtonCreate();
        changePassword.add(changePasswordLabel);
        changePassword.add(changePasswordOldPassword);
        changePassword.add(changePasswordNewPassword);
        changePassword.add(changePasswordConfirmNewPassword);
        changePassword.add(changePasswordBackButton);
        changePassword.add(changePasswordConfirmButton);

        changeDetails = new JPanel(new FlowLayout());
        changeDetailsLabel = new JLabel("Daten ändern");
        changeDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        changeNameCreate();
        checkChangePasswordCreate();
        changeOrtCreate();
        changePLZCreate();
        changeCarNameCreate();
        changeCarSpeedCreate();
        changeCarCO2Create();
        changeBikeSpeedCreate();
        changeConfirmButtonCreate();
        changeBackButtonCreate();
        changeDetails.add(changeDetailsLabel);
        changeDetails.add(checkChangePassword);
        changeDetails.add(changeName);
        changeDetails.add(changeOrt);
        changeDetails.add(changePLZ);
        changeDetails.add(changeCarName);
        changeDetails.add(changeCarCO2);
        changeDetails.add(changeCarSpeed);
        changeDetails.add(changeBikeSpeed);
        changeDetails.add(changeBackButton);
        changeDetails.add(changeConfirmButton);

        panelLayout.add(logRegScreen, "1");
        panelLayout.add(loginScreen, "2");
        panelLayout.add(registerScreen, "3");
        panelLayout.add(menu, "4");
        panelLayout.add(changeDetails, "5");
        panelLayout.add(changePassword, "6");
        cardLayout.show(panelLayout, "1");
        jframe.add(panelLayout);
        jframe.setResizable(false);
        jframe.setSize(450, 160);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private void profileCreate() {
        JPanel jp = new JPanel(null);
        ImageIcon png = png("/profile.png", 40, 40);
        JLabel jl = new JLabel(png);
        String[] details = facade.getDetails();
        JLabel jlName = new JLabel("Name: " + details[0]);
        JLabel jlWohnort = new JLabel("Wohnort: " + details[1]);
        JLabel jlPLZ = new JLabel("Plz: " + details[2]);
        JLabel jlAutoName = new JLabel("Auto Name: " + details[3]);
        JLabel jlCO2 = new JLabel("CO2/100km: " + details[4]);
        JLabel jlAutoKMH = new JLabel( "Auto km/h: " + details[5]);
        JLabel jlFahrradKMH = new JLabel( "Fahrrad km/h: " + details[6]);
        JLabel jlWetter = new JLabel("Wetter: " + facade.current_weather());
        jl.setBounds(60,0, 60,60);
        jlName.setBounds(15,30, 200,60);
        jlWohnort.setBounds(15,47, 200,60);
        jlPLZ.setBounds(15,64, 200,60);
        jlAutoName.setBounds(15,81, 200,60);
        jlCO2.setBounds(15,98, 200,60);
        jlAutoKMH.setBounds(15,115, 200,60);
        jlFahrradKMH.setBounds(15,132, 200,60);
        jlWetter.setBounds(15,149, 200,60);

        jp.add(jl);
        jp.add(jlName);
        jp.add(jlWohnort);
        jp.add(jlPLZ);
        jp.add(jlAutoName);
        jp.add(jlCO2);
        jp.add(jlAutoKMH);
        jp.add(jlFahrradKMH);
        jp.add(jlWetter);

        profile = jp;
    }

    private void errorMessageCreate() {
        errorMessage = new JFrame();
        errorMessage.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel jp = new JPanel(new FlowLayout());
        JLabel jl = new JLabel("Daten falsch oder unvollständig!");
        JButton jb = new JButton("OK");
        jb.setFocusable(false);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorMessage.dispose();
            }
        });
        jp.add(BorderLayout.CENTER, jl);
        jp.add(BorderLayout.SOUTH, jb);
        errorMessage.add(jp);
        errorMessage.setSize(240, 100);
        errorMessage.setResizable(false);
        errorMessage.setVisible(true);
        errorMessage.setLocationRelativeTo(null);
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
                jframe.setSize(new Dimension(260, 430));
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
                String nameTXT = getTextfieldContent(loginName, "loginNameText");
                String passwordTXT = getTextfieldContent(loginPassword, "loginPasswordText");
                if(facade.sign_in_user(nameTXT, passwordTXT)) {
                    profileCreate();
                    profile.setBounds(280, 168, 200,200);
                    menu.add(profile);
                    menu.revalidate();
                    menu.repaint();
                    cardLayout.show(panelLayout, "4");
                    jframe.setSize(new Dimension(500, 500));
                }
                else{
                    errorMessageCreate();
                }
            }
        });
    }

    private void loginZurückButtonCreate() {
        loginBackButton = new JButton("Back");
        loginBackButton.setPreferredSize(new Dimension(80, 20));
        loginBackButton.setFocusable(false);
        loginBackButton.addActionListener(new ActionListener() {
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
        tf.setName("registerNameText");
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

    private void registerPasswordTwoCreate() {
        registerPasswordTwo = new JPanel();
        JLabel jl = new JLabel("    Passwort best.");
        JTextField tf = new JTextField(10);
        tf.setName("registerPasswordTwoText");
        registerPasswordTwo.add(jl);
        registerPasswordTwo.add(tf);
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
                String nameTXT = getTextfieldContent(registerName, "registerNameText");
                String passwordTXT = getTextfieldContent(registerPassword, "registerPasswordText");
                String passwordTXTTwo = getTextfieldContent(registerPasswordTwo, "registerPasswordTwoText");
                String ortTXT = getTextfieldContent(registerOrt, "registerOrtText");
                String plzTXT = getTextfieldContent(registerPLZ, "registerPLZText");
                String carNameTXT = getTextfieldContent(registerCarName, "registerCarNameText");
                String carCO2TXT = getTextfieldContent(registerCarCO2, "registerCarCO2Text");
                String carSpeedTXT = getTextfieldContent(registerCarSpeed, "registerCarSpeedText");
                String bikeSpeedTXT = getTextfieldContent(registerBikeSpeed, "registerBikeSpeedText");
                if(facade.sign_up_user(nameTXT, passwordTXT, passwordTXTTwo, ortTXT, plzTXT,
                        carNameTXT, carCO2TXT, carSpeedTXT, bikeSpeedTXT)){
                    profileCreate();
                    profile.setBounds(280, 168, 200,200);
                    menu.add(profile);
                    menu.revalidate();
                    menu.repaint();
                    cardLayout.show(panelLayout, "4");
                    jframe.setSize(new Dimension(500, 500));
                }
                else{
                    errorMessageCreate();
                }
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

        ImageIcon icon = png("/lupe.png", 20, 20);
        searchConfirmButton.setIcon(icon);
        searchConfirmButton.setText(" Suche");
        searchConfirmButton.setIconTextGap(3);

        searchConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> orte = facade.search(getTextfieldContent(searchPLZ_ORT, "ortSuche"));
                JPanel destinationButtonsProxy = new JPanel();
                destinationButtonsProxy.setLayout(new BoxLayout(destinationButtonsProxy, BoxLayout.Y_AXIS));
                if (orte.size() > 0) {
                    for (String s : orte) {
                        String[] ortUndPLZ = s.split(";");
                        String plz = ortUndPLZ[0];
                        String address = ortUndPLZ[1] + "," + plz + "    " + facade.distance(plz);
                        JButton jb = new JButton(address);
                        jb.setPreferredSize(new Dimension(290, 40));
                        jb.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame jf = new JFrame();
                                JPanel jp = new JPanel(new FlowLayout());
                                String[] details = facade.destination_details(plz);
                                for (String ss : details) {
                                    JLabel jl = new JLabel(ss);
                                    jp.add(jl);
                                }
                                jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                                jf.add(jp);
                                jf.setSize(100, 400);
                                jf.setVisible(true);
                            }
                        });
                        destinationButtonsProxy.add(jb);
                    }
                    destinationButtons = destinationButtonsProxy;
                    destinationButtons.revalidate();
                    destinationButtons.repaint();
                    try {
                        menu.remove(jsp);
                    } catch (Exception jspNotYetIncluded) {
                    }
                    jsp = new JScrollPane(destinationButtons);
                    jsp.setBounds(40, 110, 244, 336);
                    jsp.revalidate();
                    jsp.repaint();
                    menu.add(jsp);
                    menu.revalidate();
                    menu.repaint();
                }
                else {
                    destinationButtonsProxy = new JPanel();
                    JLabel jl = new JLabel("Kein Ergebnis");
                    jl.setFont(new Font("Arial", Font.PLAIN, 38));
                    destinationButtonsProxy.add(jl);
                    destinationButtons = destinationButtonsProxy;
                    destinationButtons.revalidate();
                    destinationButtons.repaint();
                    try {
                        menu.remove(jsp);
                    } catch (Exception jspNotYetIncluded) {
                    }
                    jsp = new JScrollPane(destinationButtons);
                    jsp.setBounds(40, 110, 244, 336);
                    jsp.revalidate();
                    jsp.repaint();
                    menu.add(jsp);
                    menu.revalidate();
                    menu.repaint();
                }
            }
        });
    }


    private void randDestinationsCarButtonCreate() { //Auto Icon hinzufügen
        randDestinationsCarButton = new JButton();
        randDestinationsCarButton.setPreferredSize(new Dimension(150, 80));
        randDestinationsCarButton.setFocusable(false);

        ImageIcon icon = png("/auto.png", 20, 20);
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

        ImageIcon icon = png("/fahrrad.png", 20, 20);
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

    private void changeDetailsButtonCreate() {
        changeDetailsButton = new JButton("Daten ändern");
        changeDetailsButton.setPreferredSize(new Dimension(150, 50));
        changeDetailsButton.setFocusable(false);
        changeDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "5");
                jframe.setSize(new Dimension(260, 394));
            }
        });
    }

    public void checkChangePasswordCreate(){
        checkChangePassword = new JPanel();
        JLabel jl = new JLabel("Passwort bestätigen");
        JTextField tf = new JTextField(10);
        tf.setName("checkChangePasswordText");
        checkChangePassword.add(jl);
        checkChangePassword.add(tf);
    }

    public void changeNameCreate(){
        changeName = new JPanel();
        JLabel jl = new JLabel("                          Name");
        JTextField tf = new JTextField(10);
        tf.setName("changeNameText");
        changeName.add(jl);
        changeName.add(tf);
    }

    public void changeOrtCreate(){
        changeOrt = new JPanel();
        JLabel jl = new JLabel("                             Ort");
        JTextField tf = new JTextField(10);
        tf.setName("changeOrtText");
        changeOrt.add(jl);
        changeOrt.add(tf);
    }

    public void changePLZCreate(){
        changePLZ = new JPanel();
        JLabel jl = new JLabel("                            PLZ");
        JTextField tf = new JTextField(10);
        tf.setName("changePLZText");
        changePLZ.add(jl);
        changePLZ.add(tf);
    }

    public void changeCarNameCreate(){
        changeCarName = new JPanel();
        JLabel jl = new JLabel("                 Auto Name");
        JTextField tf = new JTextField(10);
        tf.setName("changeCarNameText");
        changeCarName.add(jl);
        changeCarName.add(tf);
    }

    public void changeCarCO2Create(){
        changeCarCO2 = new JPanel();
        JLabel jl = new JLabel("                   Auto CO2");
        JTextField tf = new JTextField(10);
        tf.setName("changeCarCO2Text");
        changeCarCO2.add(jl);
        changeCarCO2.add(tf);
    }

    public void changeCarSpeedCreate(){
        changeCarSpeed = new JPanel();
        JLabel jl = new JLabel("           Auto Geschw.");
        JTextField tf = new JTextField(10);
        tf.setName("changeCarSpeedText");
        changeCarSpeed.add(jl);
        changeCarSpeed.add(tf);
    }

    public void changeBikeSpeedCreate(){
        changeBikeSpeed = new JPanel();
        JLabel jl = new JLabel("      Fahrrad Geschw.");
        JTextField tf = new JTextField(10);
        tf.setName("changeBikeSpeedText");
        changeBikeSpeed.add(jl);
        changeBikeSpeed.add(tf);
    }

    public void changeConfirmButtonCreate(){
        changeConfirmButton = new JButton("Confirm");
        changeConfirmButton.setSize(80, 20);
        changeConfirmButton.setFocusable(false);
        changeConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameTXT = getTextfieldContent(changeName, "changeNameText");
                String passwordTXT = getTextfieldContent(checkChangePassword, "checkChangePasswordText");
                String ortTXT = getTextfieldContent(changeOrt, "changeOrtText");
                String plzTXT = getTextfieldContent(changePLZ, "changePLZText");
                String carNameTXT = getTextfieldContent(changeCarName, "changeCarNameText");
                String carCO2TXT = getTextfieldContent(changeCarCO2, "changeCarCO2Text");
                String carSpeedTXT = getTextfieldContent(changeCarSpeed, "changeCarSpeedText");
                String bikeSpeedTXT = getTextfieldContent(changeBikeSpeed, "changeBikeSpeedText");
                if(facade.change_user_details(nameTXT, passwordTXT, ortTXT, plzTXT,
                        carNameTXT, carCO2TXT, carSpeedTXT, bikeSpeedTXT)){
                    menu.remove(profile);
                    profileCreate();
                    profile.setBounds(280, 168, 200,200);
                    menu.add(profile);
                    menu.revalidate();
                    menu.repaint();
                    cardLayout.show(panelLayout, "4");
                    jframe.setSize(new Dimension(500, 500));
                }
                else{
                    errorMessageCreate();
                }
            }
        });
    }

    public void changeBackButtonCreate(){
        changeBackButton = new JButton("Zurück");
        changeBackButton.setSize(80, 20);
        changeBackButton.setFocusable(false);
        changeBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "4");
                jframe.setSize(new Dimension(500, 500));
            }
        });
    }

    public void changePasswordButtonCreate(){
        changePasswordButton = new JButton("Passwort ändern");
        changePasswordButton.setPreferredSize(new Dimension(150, 50));
        changePasswordButton.setFocusable(false);
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "6");
                jframe.setSize(new Dimension(260, 215));
            }
        });
    }

    public void changePasswordOldPasswordCreate(){
        changePasswordOldPassword = new JPanel();
        JLabel jl = new JLabel("           Altes Passwort");
        JTextField tf = new JTextField(10);
        tf.setName("changePasswordOldPasswordText");
        changePasswordOldPassword.add(jl);
        changePasswordOldPassword.add(tf);
    }

    public void changePasswordNewPasswordCreate(){
        changePasswordNewPassword = new JPanel();
        JLabel jl = new JLabel("           Neue Passowrt");
        JTextField tf = new JTextField(10);
        tf.setName("changePasswordNewPasswordText");
        changePasswordNewPassword.add(jl);
        changePasswordNewPassword.add(tf);
    }

    public void changePasswordConfirmNewPasswordCreate(){
        changePasswordConfirmNewPassword = new JPanel();
        JLabel jl = new JLabel("Best. neues Passwort");
        JTextField tf = new JTextField(10);
        tf.setName("changePasswordConfirmNewPasswordText");
        changePasswordConfirmNewPassword.add(jl);
        changePasswordConfirmNewPassword.add(tf);
    }

    public void changePasswordBackButtonCreate(){
        changePasswordBackButton = new JButton("Zurück");
        changePasswordBackButton.setSize(80, 20);
        changePasswordBackButton.setFocusable(false);
        changePasswordBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "4");
                jframe.setSize(new Dimension(500, 500));
            }
        });
    }

    public void changePasswordConfirmButtonCreate(){
        changePasswordConfirmButton = new JButton("Confirm");
        changePasswordConfirmButton.setSize(80, 20);
        changePasswordConfirmButton.setFocusable(false);
        changePasswordConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String passwordOldTXT = getTextfieldContent(changePasswordOldPassword, "changePasswordOldPasswordText");
                String passwordNewTXT = getTextfieldContent(changePasswordNewPassword, "changePasswordNewPasswordText");
                String passwordConfirmNewTXT = getTextfieldContent(changePasswordConfirmNewPassword, "changePasswordConfirmNewPasswordText");
                if(facade.change_user_password(passwordOldTXT, passwordNewTXT, passwordConfirmNewTXT)){
                    cardLayout.show(panelLayout, "4");
                    jframe.setSize(new Dimension(500, 500));
                }
                else{
                    errorMessageCreate();
                }
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
                menu.remove(profile);
                facade.sign_out_user();
                cardLayout.show(panelLayout, "1");
                jframe.setSize(new Dimension(450, 160));
            }
        });
    }

    public void jspCreate(){
        jsp = new JScrollPane();
        jsp.setSize(100,100);

    }

    private String getTextfieldContent(JPanel panel, String name) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JTextField && name.equals(component.getName())) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

    public ImageIcon png(String path, int sizeX, int sizeY) { // umständlich und png nicht transparent
        ImageIcon icon = new ImageIcon(getClass().getResource(path)); // Path des PNGs muss angegeben werden
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(sizeX, sizeY, java.awt.Image.SCALE_SMOOTH); // wähle Größe des PNGs
        ImageIcon iconTwo = new ImageIcon(newimg);
        return iconTwo;
    }

}
