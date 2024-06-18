package de.hs_mannheim.ui;

import de.hs_mannheim.facade.Application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main("35a75437476f12302f72e55d368485db");
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
    private JButton registerBackButton;

    private JLabel menuLabel;
    private JPanel menu;
    private JPanel searchPLZ_ORT;
    private JButton searchConfirmButton;
    private JButton randDestinationsCarButton;
    private JButton randDestinationsBikeButton;
    private JPanel profile = new JPanel();
    private JButton changePasswordButton;
    private JButton changeDetailsButton;
    private JButton logOutButton;
    private JScrollPane jsp = new JScrollPane();

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

    private GridBagLayout gbl5 = new GridBagLayout();
    private GridBagConstraints gbc5 = new GridBagConstraints();
    private JPanel leftSide = new JPanel();
    private JPanel rightSide = new JPanel();


    public Main(String api_key) {
        SwingUtilities.invokeLater(() -> {
            this.facade = new Application(api_key);
            initialize();
        });
    }

    public void initialize() {
        jframe = new JFrame();
        cardLayout = new CardLayout();
        panelLayout = new JPanel(cardLayout);
        panelLayout.setLayout(cardLayout);
        jframe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // => Hauptmenü Fenster
        GridBagLayout gbl1 = new GridBagLayout();
        GridBagConstraints gbc1 = new GridBagConstraints();
        logRegScreen = new JPanel(gbl1);
        mainMenuLabel = new JLabel("Hauptmenü");
        mainMenuLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        loginButtonCreate();
        registerButtonCreate();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.ipady = 40;
        logRegScreen.add(mainMenuLabel,gbc1);
        JPanel p1 = new JPanel(new GridLayout(1,2,20,0));
        p1.add(loginButton, gbc1);
        p1.add(registerButton, gbc1);
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        gbc1.ipadx = -10;
        gbc1.ipady = 5;
        gbc1.insets = new Insets(10,0,20,0);
        logRegScreen.add(p1,gbc1);


        // => Einloggen Fenster
        GridBagLayout gbl2 = new GridBagLayout();
        GridBagConstraints gbc2 = new GridBagConstraints();
        loginScreen = new JPanel();
        loginScreen.setLayout(gbl2);
        gbl2.setConstraints(loginScreen, gbc2);
        loginLabel = new JLabel("Einloggen");
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        loginNameCreate();
        loginPasswordCreate();
        loginConfirmButtonCreate(gbc5);
        loginZurückButtonCreate();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.ipady = 40;
        gbc2.insets = new Insets(0,0,10,0);
        loginScreen.add(loginLabel,gbc2);
        gbc2.insets = new Insets(0,0,0,0);
        JPanel cnt1 = new JPanel(new GridLayout(2, 1,0,5));
        cnt1.add(loginName);
        cnt1.add(loginPassword);
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.ipadx = 60;
        gbc2.ipady = 20;
        loginScreen.add(cnt1,gbc2);
        gbc2.insets = new Insets(20,0,10,0);
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        gbc2.ipadx = 25;
        gbc2.ipady = 20;
        JPanel cnt2 = new JPanel(new GridLayout(1, 2,10,0));
        cnt2.add(loginBackButton);
        cnt2.add(loginConfirmButton);
        loginScreen.add(cnt2,gbc2);

        // => Registrieren Fenster
        GridBagLayout gbl3 = new GridBagLayout();
        GridBagConstraints gbc3 = new GridBagConstraints();
        registerScreen = new JPanel(gbl3);
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
        registerConfirmButtonCreate(gbc5);
        registerBackButtonCreate();
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.ipady = 40;
        gbc3.insets = new Insets(0,0,10,0);
        registerScreen.add(registerLabel,gbc3);
        gbc3.insets = new Insets(0,0,0,0);
        JPanel p2 = new JPanel(new GridLayout(9, 1,0,5));
        p2.add(registerName);
        p2.add(registerPassword);
        p2.add(registerPasswordTwo);
        p2.add(registerPLZ);
        p2.add(registerOrt);
        p2.add(registerCarName);
        p2.add(registerCarSpeed);
        p2.add(registerCarCO2);
        p2.add(registerBikeSpeed);
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        gbc3.ipadx = 5;
        gbc3.ipady = 20;
        registerScreen.add(p2,gbc3);
        gbc3.insets = new Insets(20,0,10,0);
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.ipadx = 25;
        gbc3.ipady = 20;
        JPanel p3 = new JPanel(new GridLayout(1, 2,10,0));
        p3.add(registerBackButton);
        p3.add(registerConfirmButton);
        registerScreen.add(p3,gbc3);


        // => Such Fenster
        menu = new JPanel(new BorderLayout());
        menuLabel = new JLabel("Travel-Buddy-App");
        menuLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        searchPLZ_ORTCreate();
        searchConfirmButtonCreate(gbc5);
        randDestinationsCarButtonCreate(gbc5);
        randDestinationsBikeButtonCreate(gbc5);
        logOutButtonCreate();
        changeDetailsButtonCreate();
        changePasswordButtonCreate();

        leftSide.setLayout(new BoxLayout(leftSide,BoxLayout.Y_AXIS));
        rightSide.setLayout(new BoxLayout(rightSide,BoxLayout.Y_AXIS));

        
        leftSide.add(Box.createRigidArea(new Dimension(0,25)));
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftSide.add(menuLabel,1); 

        searchPLZ_ORT.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPLZ_ORT.setMaximumSize(new Dimension(250,55));
        searchPLZ_ORT.setPreferredSize(new Dimension(250,55));
        leftSide.add(searchPLZ_ORT,2);

        jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
        jsp.setMaximumSize(new Dimension(250, 500));
        jsp.setPreferredSize(new Dimension(250, 500));
        leftSide.add(jsp,3);


        rightSide.add(Box.createRigidArea(new Dimension(0,80)));
        searchConfirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchConfirmButton.setMaximumSize(new Dimension(140,35));
        searchConfirmButton.setPreferredSize(new Dimension(140,35));
        rightSide.add(searchConfirmButton,1);
        

        randDestinationsCarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        randDestinationsCarButton.setMaximumSize(new Dimension(140,35));
        randDestinationsCarButton.setPreferredSize(new Dimension(140,35));
        rightSide.add(randDestinationsCarButton,2);


        randDestinationsBikeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        randDestinationsBikeButton.setMaximumSize(new Dimension(140,35));
        randDestinationsBikeButton.setPreferredSize(new Dimension(140,35));
        rightSide.add(randDestinationsBikeButton,3);
        
           
        rightSide.add(Box.createRigidArea(new Dimension(0,230)));
        profile.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightSide.add(profile,5);


        changeDetailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeDetailsButton.setMaximumSize(new Dimension(140,35));
        changeDetailsButton.setPreferredSize(new Dimension(140,35));
        rightSide.add(changeDetailsButton,6);

        
        changePasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changePasswordButton.setMaximumSize(new Dimension(140,35));
        changePasswordButton.setPreferredSize(new Dimension(140,35));
        rightSide.add(changePasswordButton,7);


        logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logOutButton.setMaximumSize(new Dimension(140,35));
        logOutButton.setPreferredSize(new Dimension(140,35));
        rightSide.add(logOutButton,8);

        menu.add(leftSide,BorderLayout.WEST);
        menu.add(rightSide,BorderLayout.EAST);

        // => Passwort ändern Fenster
        changePassword = new JPanel(gbl5);
        changePasswordLabel = new JLabel("Passwort ändern");
        changePasswordLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        changePasswordOldPasswordCreate();
        changePasswordNewPasswordCreate();
        changePasswordConfirmNewPasswordCreate();
        changePasswordBackButtonCreate();
        changePasswordConfirmButtonCreate();
        
        gbc5.gridheight = 1;
        gbc5.gridx = 0;
        gbc5.gridy = 0;
        gbc5.ipady = 40;
        gbc5.insets = new Insets(0,0,10,0);
        changePassword.add(changePasswordLabel,gbc5);
        gbc5.insets = new Insets(0,0,0,0);
        JPanel p7 = new JPanel(new GridLayout(3, 1,0,5));
        p7.add(changePasswordOldPassword);
        p7.add(changePasswordNewPassword);
        p7.add(changePasswordConfirmNewPassword);
        gbc5.gridx = 0;
        gbc5.gridy = 1;
        gbc5.ipadx = 5;
        gbc5.ipady = 20;
        changePassword.add(p7,gbc5);
        gbc5.insets = new Insets(20,0,10,0);
        gbc5.gridx = 0;
        gbc5.gridy = 2;
        gbc5.ipadx = 25;
        gbc5.ipady = 20;
        JPanel p8 = new JPanel(new GridLayout(1, 2,10,0));
        p8.add(changePasswordBackButton);
        p8.add(changePasswordConfirmButton);
        gbc5.ipadx = -45;
        gbc5.ipady = 10;
        changePassword.add(p8,gbc5);
        

        // => Nutzerdaten ändern Fenster
        changeDetails = new JPanel(gbl5);
        changeDetailsLabel = new JLabel("Nutzerdaten ändern");
        changeDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        changeNameCreate();
        changeOrtCreate();
        changePLZCreate();
        changeCarNameCreate();
        changeCarSpeedCreate();
        changeCarCO2Create();
        changeBikeSpeedCreate();
        checkChangePasswordCreate();
        changeConfirmButtonCreate(gbc5);
        changeBackButtonCreate();
        gbc5.gridheight = 1;
        gbc5.gridx = 0;
        gbc5.gridy = 0;
        gbc5.ipadx = 0;
        gbc5.ipady = 40;
        gbc5.insets = new Insets(0,0,10,0);
        changeDetails.add(changeDetailsLabel,gbc5);
        gbc5.insets = new Insets(0,0,0,0);
        JPanel p5 = new JPanel(new GridLayout(8, 1,0,5));
        p5.add(changeName);
        p5.add(changePLZ);
        p5.add(changeOrt);
        p5.add(changeCarName);
        p5.add(changeCarSpeed);
        p5.add(changeCarCO2);
        p5.add(changeBikeSpeed);
        p5.add(checkChangePassword);
        gbc5.gridx = 0;
        gbc5.gridy = 1;
        gbc5.ipadx = 5;
        gbc5.ipady = 20;
        changeDetails.add(p5,gbc5);
        gbc5.insets = new Insets(20,0,10,0);
        gbc5.gridx = 0;
        gbc5.gridy = 2;
        gbc5.ipadx = 25;
        gbc5.ipady = 20;
        JPanel p6 = new JPanel(new GridLayout(1, 2,10,0));
        p6.add(changeBackButton);
        p6.add(changeConfirmButton);
        gbc5.ipadx = -30;
        gbc5.ipady = 10;
        changeDetails.add(p6,gbc5);
        
        // cardLayout

        panelLayout.add(logRegScreen, "1");
        panelLayout.add(loginScreen, "2");
        panelLayout.add(registerScreen, "3");
        panelLayout.add(menu, "4");
        panelLayout.add(changeDetails, "5");
        panelLayout.add(changePassword, "6");
        cardLayout.show(panelLayout, "1");
        jframe.add(panelLayout);
        jframe.setResizable(false);
        jframe.setSize(400, 200);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    private void profileCreate() {
        JPanel jp = new JPanel();
        ImageIcon png = png("/profile.png", 40, 40);
        JLabel jl = new JLabel(png);
        String[] details = facade.getDetails();
        jp.setToolTipText("<html>Nutzername:<br>"+details[0]+"<br><br>Adresse:<br>"+details[2] + ", " + details[1]+"<br><br>Aktuelles Wetter:<br>"+facade.current_weather()+"</html>");
        jp.add(jl);
        profile = jp;
    }

    private void errorMessageCreate() {
        errorMessage = new JFrame();
        errorMessage.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel jp = new JPanel(new FlowLayout());
        JLabel jl = new JLabel("Überprüfen sie Ihre Eingabe!");
        JButton jb = new JButton("Bestätigen");
        jb.setFocusable(false);

        jb.addActionListener(e -> errorMessage.dispose());
        jp.add(BorderLayout.CENTER, jl);
        jp.add(BorderLayout.SOUTH, jb);
        errorMessage.add(jp);
        errorMessage.setSize(240, 100);
        errorMessage.setResizable(false);
        errorMessage.setVisible(true);
        errorMessage.setLocationRelativeTo(null);
    }


    private void loginButtonCreate() {
        loginButton = new JButton("Einloggen");
        loginButton.setPreferredSize(new Dimension(150, 50));
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "2");
                jframe.setSize(new Dimension(400, 330));
            }
        });

    }

    private void registerButtonCreate() {
        registerButton = new JButton("Registrieren");
        registerButton.setPreferredSize(new Dimension(150, 50));
        registerButton.setFocusable(false);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "3");
                jframe.setSize(new Dimension(400, 728));
            }
        });
    }

    private void loginNameCreate() {
        loginName = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Nutzername");
        JTextField tf = new JTextField(10);
        tf.setName("loginNameText");
        loginName.add(jl);
        loginName.add(tf);
    }

    private void loginPasswordCreate() {
        loginPassword = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Passwort");
        JTextField tf = new JTextField(10);
        tf.setName("loginPasswordText");
        loginPassword.add(jl);
        loginPassword.add(tf);
    }

    private void loginConfirmButtonCreate(GridBagConstraints gbc5) {
        loginConfirmButton = new JButton("Bestätigen");
        loginConfirmButton.setPreferredSize(new Dimension(80, 20));
        loginConfirmButton.setFocusable(false);
        loginConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameTXT = getTextfieldContent(loginName, "loginNameText");
                String passwordTXT = getTextfieldContent(loginPassword, "loginPasswordText");
                if(facade.sign_in_user(nameTXT, passwordTXT)) {
                    profileCreate();
                    try {
                        rightSide.remove(5);
                    } catch (Exception err) {
                        //
                    }
                    profile.setMaximumSize(new Dimension(50,50));
                    profile.setPreferredSize(new Dimension(50,50));
                    
                    rightSide.add(profile,5);
                    rightSide.revalidate();
                    menu.revalidate();
                    cardLayout.show(panelLayout, "4");
                    jframe.setSize(new Dimension(400, 600));
                }
                else{
                    errorMessageCreate();
                }
                JTextField clear = findTextFieldByName(loginName, "loginNameText");
                clear.setText("");
                clear = findTextFieldByName(loginPassword, "loginPasswordText");
                clear.setText("");
            }
        });
    }

    private void loginZurückButtonCreate() {
        loginBackButton = new JButton("Zurück");
        loginBackButton.setPreferredSize(new Dimension(80, 20));
        loginBackButton.setFocusable(false);
        loginBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "1");
                jframe.setSize(new Dimension(400, 200));
            }
        });
    }

    private void registerNameCreate() {
        registerName = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Nutzername");
        JTextField tf = new JTextField(10);
        tf.setName("registerNameText");
        registerName.add(jl);
        registerName.add(tf);
    }

    private void registerPasswordCreate() {
        registerPassword = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Passwort");
        JTextField tf = new JTextField(10);
        tf.setName("registerPasswordText");
        registerPassword.add(jl);
        registerPassword.add(tf);
    }

    private void registerPasswordTwoCreate() {
        registerPasswordTwo = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Passwort bestätigen");
        JTextField tf = new JTextField(10);
        tf.setName("registerPasswordTwoText");
        registerPasswordTwo.add(jl);
        registerPasswordTwo.add(tf);
    }

    private void registerOrtCreate() {
        registerOrt = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Heimatort");
        JTextField tf = new JTextField(10);
        tf.setName("registerOrtText");
        registerOrt.add(jl);
        registerOrt.add(tf);
    }

    private void registerPLZCreate() {
        registerPLZ = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Postleitzahl");
        JTextField tf = new JTextField(10);
        tf.setName("registerPLZText");
        registerPLZ.add(jl);
        registerPLZ.add(tf);
    }

    private void registerCarNameCreate() {
        registerCarName = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Auto Name");
        JTextField tf = new JTextField(10);
        tf.setName("registerCarNameText");
        registerCarName.add(jl);
        registerCarName.add(tf);
    }

    private void registerCarSpeedCreate() {
        registerCarSpeed = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Auto ⌀-Tempo km/h");
        JTextField tf = new JTextField(10);
        tf.setName("registerCarSpeedText");
        registerCarSpeed.add(jl);
        registerCarSpeed.add(tf);
    }

    private void registerCarCO2Create() {
        registerCarCO2 = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Kraftstoffverbrauch l/100km");
        JTextField tf = new JTextField(10);
        tf.setName("registerCarCO2Text");
        registerCarCO2.add(jl);
        registerCarCO2.add(tf);
    }

    private void registerBikeSpeedCreate() {
        registerBikeSpeed = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Fahrrad ⌀-Tempo km/h");
        JTextField tf = new JTextField(10);
        tf.setName("registerBikeSpeedText");
        registerBikeSpeed.add(jl);
        registerBikeSpeed.add(tf);
    }

    private void registerConfirmButtonCreate(GridBagConstraints gbc5) {
        registerConfirmButton = new JButton();
        registerConfirmButton = new JButton("Bestätigen");
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
                    try {
                        rightSide.remove(5);
                    } catch (Exception err) {
                        //
                    }
                    profile.setMaximumSize(new Dimension(50,50));
                    profile.setPreferredSize(new Dimension(50,50));
                    
                    rightSide.add(profile,5);
                    rightSide.revalidate();                    
                    menu.revalidate();
                   
                    cardLayout.show(panelLayout, "4");
                    jframe.setSize(new Dimension(400, 600));
                }
                else{
                    errorMessageCreate();
                }
                JTextField clear = findTextFieldByName(registerName, "registerNameText");
                clear.setText("");
                clear = findTextFieldByName(registerPassword, "registerPasswordText");
                clear.setText("");
                clear = findTextFieldByName(registerPasswordTwo, "registerPasswordTwoText");
                clear.setText("");
                clear = findTextFieldByName(registerOrt, "registerOrtText");
                clear.setText("");
                clear = findTextFieldByName(registerPLZ, "registerPLZText");
                clear.setText("");
                clear = findTextFieldByName(registerCarName, "registerCarNameText");
                clear.setText("");
                clear = findTextFieldByName(registerCarCO2, "registerCarCO2Text");
                clear.setText("");
                clear = findTextFieldByName(registerCarSpeed, "registerCarSpeedText");
                clear.setText("");
                clear = findTextFieldByName(registerBikeSpeed, "registerBikeSpeedText");
                clear.setText("");
            }
        });
    }

    private void registerBackButtonCreate() {
        registerBackButton = new JButton("Zurück");
        registerBackButton.setPreferredSize(new Dimension(80, 20));
        registerBackButton.setFocusable(false);
        registerBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelLayout, "1");
                jframe.setSize(new Dimension(400, 200));
            }
        });
    }

    private void searchPLZ_ORTCreate() {
        searchPLZ_ORT = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Postleitzahl oder Ort");
        JTextField tf = new JTextField(20);
        tf.setName("ortSuche");
        searchPLZ_ORT.add(jl);
        searchPLZ_ORT.add(tf);
    }

    private void searchConfirmButtonCreate(GridBagConstraints gbc5) {
        searchConfirmButton = new JButton();
        searchConfirmButton.setPreferredSize(new Dimension(150, 80));
        searchConfirmButton.setFocusable(false);

        ImageIcon icon = png("/magnifier.png", 25, 25);
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
                        String address = plz + ", " + ortUndPLZ[1];
                        JButton jb = new JButton(address);
                        jb.setPreferredSize(new Dimension(290, 40));
                        jb.setToolTipText(facade.distance(plz));
                        jb.setHorizontalAlignment(SwingConstants.LEFT);
                        jb.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame jf = new JFrame();
                                JPanel jp = new JPanel();
                                jp.setLayout(null);
                                String[] details = facade.destination_details(plz);
                                JLabel jlAddress = new JLabel("Zieladresse:");
                                JLabel jlAddressText = new JLabel(address);
                                JLabel jlWettervorhersage = new JLabel("Wettervorhersage:");
                                JLabel jlWettervorhersageTextOne = new JLabel(details[0]);
                                JLabel jlWettervorhersageTextTwo = new JLabel(details[1]);
                                JLabel jlWettervorhersageTextThree = new JLabel(details[2]);
                                JLabel jlEntfernung = new JLabel("Entfernung:");
                                JLabel jlEntfernungText = new JLabel(details[3]);
                                JLabel jlReisedauerUndKraftstoffverbrauch = new JLabel("Reisedauer und Kraftstoffverbrauch Auto:");
                                JLabel jlAutoDauerText = new JLabel(details[4]);
                                JLabel jlAutoVerbrauchText = new JLabel(details[5]);
                                JLabel jlReisedauerMitFahrrad = new JLabel("Reisedauer mit Fahrrad:");
                                JLabel jlReisedauerMitFahrradText = new JLabel(details[6]);
                                if(details[4].equals("")){
                                    jlAutoDauerText.setText("---");
                                }
                                if(details[5].equals("")){
                                    jlAutoVerbrauchText.setText("---");
                                }
                                if(details[6].equals("")){
                                    jlReisedauerMitFahrradText.setText("---");
                                }
                                jlAddress.setBounds(4, 0, 400, 20);
                                jlAddressText.setBounds(4, 12, 400, 20);
                                jlWettervorhersage.setBounds(4, 45, 400, 20);
                                jlWettervorhersageTextOne.setBounds(4, 57, 400, 20);
                                jlWettervorhersageTextTwo.setBounds(4, 69, 400, 20);
                                jlWettervorhersageTextThree.setBounds(4, 81, 400, 20);
                                jlEntfernung.setBounds(4, 108, 400, 20);
                                jlEntfernungText.setBounds(4, 120, 400, 20);
                                jlReisedauerUndKraftstoffverbrauch.setBounds(4, 147, 400, 20);
                                jlAutoDauerText.setBounds(4, 159, 400, 20);
                                jlAutoVerbrauchText.setBounds(4, 171, 400, 20);
                                jlReisedauerMitFahrrad.setBounds(4, 198, 400, 20);
                                jlReisedauerMitFahrradText.setBounds(4, 210, 400, 20);
                                jp.add(jlAddress);
                                jp.add(jlAddressText);
                                jp.add(jlWettervorhersage);
                                jp.add(jlWettervorhersageTextOne);
                                jp.add(jlWettervorhersageTextTwo);
                                jp.add(jlWettervorhersageTextThree);
                                jp.add(jlEntfernung);
                                jp.add(jlEntfernungText);
                                jp.add(jlReisedauerUndKraftstoffverbrauch);
                                jp.add(jlAutoDauerText);
                                jp.add(jlAutoVerbrauchText);
                                jp.add(jlReisedauerMitFahrrad);
                                jp.add(jlReisedauerMitFahrradText);

                                jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                                jf.add(jp);
                                jf.setSize(400, 270);
                                jf.setResizable(false);
                                jf.setVisible(true);
                            }
                        });
                        destinationButtonsProxy.add(jb);
                    }
                    destinationButtonsProxy.revalidate();

                    try {
                        leftSide.remove(jsp);
                    } catch (Exception jspNotYetIncluded) {
                    }
                    jsp = new JScrollPane(destinationButtonsProxy, 
                                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    
                    jsp.setMaximumSize(new Dimension(250, 450));
                    jsp.setPreferredSize(new Dimension(250, 450));
                    jsp.revalidate();
                    
                    leftSide.add(jsp,3);
                    leftSide.revalidate();
                    
                    menu.revalidate();
                }
                else {
                    destinationButtonsProxy = new JPanel();
                    JLabel jl = new JLabel("Ihre Suche liefert keine Treffer!");
                    jl.setFont(new Font("Arial", Font.PLAIN, 14));
                    destinationButtonsProxy.add(jl);
                    destinationButtonsProxy.revalidate();
                    
                    try {
                        leftSide.remove(jsp);
                    } catch (Exception jspNotYetIncluded) {
                    }
                    jsp = new JScrollPane(destinationButtonsProxy);
                    jsp.setMaximumSize(new Dimension(250, 450));
                    jsp.setPreferredSize(new Dimension(250, 450));
                    jsp.revalidate();
                    
                    leftSide.add(jsp,3);
                    leftSide.revalidate();
                    
                    menu.revalidate();
                }
            }
        });
    }


    private void randDestinationsCarButtonCreate(GridBagConstraints gbc5) { //Auto Icon hinzufügen
        randDestinationsCarButton = new JButton();
        randDestinationsCarButton.setPreferredSize(new Dimension(150, 80));
        randDestinationsCarButton.setFocusable(false);

        ImageIcon icon = png("/car.png", 25, 25);
        randDestinationsCarButton.setIcon(icon);
        randDestinationsCarButton.setText("Kurztrip");
        randDestinationsCarButton.setIconTextGap(3);

        randDestinationsCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> orte = facade.random_destinations_car();
                JPanel destinationButtonsProxy = new JPanel();
                destinationButtonsProxy.setLayout(new BoxLayout(destinationButtonsProxy, BoxLayout.Y_AXIS));
                    for (String s : orte) {
                        String[] ortUndPLZ = s.split(";");
                        String plz = ortUndPLZ[0];
                        String address = plz + ", " + ortUndPLZ[1];
                        JButton jb = new JButton(address);
                        jb.setToolTipText(facade.distance(plz));
                        jb.setHorizontalAlignment(SwingConstants.LEFT);
                        jb.setPreferredSize(new Dimension(290, 40));
                        jb.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame jf = new JFrame();
                                JPanel jp = new JPanel();
                                jp.setLayout(null);
                                String[] details = facade.destination_details(plz);
                                JLabel jlAddress = new JLabel("Adresse:");
                                JLabel jlAddressText = new JLabel(address);
                                JLabel jlWettervorhersage = new JLabel("Wettervorhersage:");
                                JLabel jlWettervorhersageTextOne = new JLabel(details[0]);
                                JLabel jlWettervorhersageTextTwo = new JLabel(details[1]);
                                JLabel jlWettervorhersageTextThree = new JLabel(details[2]);
                                JLabel jlEntfernung = new JLabel("Entfernung:");
                                JLabel jlEntfernungText = new JLabel(details[3]);
                                JLabel jlReisedauerUndKraftstoffverbrauch = new JLabel("Reisedauer und Kraftstoffverbrauch mit Auto:");
                                JLabel jlAutoDauerText = new JLabel(details[4]);
                                JLabel jlAutoVerbrauchText = new JLabel(details[5]);
                                JLabel jlReisedauerMitFahrrad = new JLabel("Reisedauer mit Fahrrad:");
                                JLabel jlReisedauerMitFahrradText = new JLabel(details[6]);
                                if(details[4].equals("")){
                                    jlAutoDauerText.setText("---");
                                }
                                if(details[5].equals("")){
                                    jlAutoVerbrauchText.setText("---");
                                }
                                if(details[6].equals("")){
                                    jlReisedauerMitFahrradText.setText("---");
                                }
                                jlAddress.setBounds(4, 0, 400, 20);
                                jlAddressText.setBounds(4, 12, 400, 20);
                                jlWettervorhersage.setBounds(4, 45, 400, 20);
                                jlWettervorhersageTextOne.setBounds(4, 57, 400, 20);
                                jlWettervorhersageTextTwo.setBounds(4, 69, 400, 20);
                                jlWettervorhersageTextThree.setBounds(4, 81, 400, 20);
                                jlEntfernung.setBounds(4, 108, 400, 20);
                                jlEntfernungText.setBounds(4, 120, 400, 20);
                                jlReisedauerUndKraftstoffverbrauch.setBounds(4, 147, 400, 20);
                                jlAutoDauerText.setBounds(4, 159, 400, 20);
                                jlAutoVerbrauchText.setBounds(4, 171, 400, 20);
                                jlReisedauerMitFahrrad.setBounds(4, 198, 400, 20);
                                jlReisedauerMitFahrradText.setBounds(4, 210, 400, 20);
                                jp.add(jlAddress);
                                jp.add(jlAddressText);
                                jp.add(jlWettervorhersage);
                                jp.add(jlWettervorhersageTextOne);
                                jp.add(jlWettervorhersageTextTwo);
                                jp.add(jlWettervorhersageTextThree);
                                jp.add(jlEntfernung);
                                jp.add(jlEntfernungText);
                                jp.add(jlReisedauerUndKraftstoffverbrauch);
                                jp.add(jlAutoDauerText);
                                jp.add(jlAutoVerbrauchText);
                                jp.add(jlReisedauerMitFahrrad);
                                jp.add(jlReisedauerMitFahrradText);

                                jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                                jf.add(jp);
                                jf.setSize(400, 270);
                                jf.setResizable(false);
                                jf.setVisible(true);
                            }
                        });
                        destinationButtonsProxy.add(jb);
                    }
                    destinationButtonsProxy.revalidate();
                    
                    try {
                        leftSide.remove(jsp);
                    } catch (Exception jspNotYetIncluded) {
                    }
                    jsp = new JScrollPane(destinationButtonsProxy);
                    jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    jsp.setMaximumSize(new Dimension(250, 450));
                    jsp.setPreferredSize(new Dimension(250, 450));
                    jsp.revalidate();

                    leftSide.add(jsp,3);
                    leftSide.revalidate();                    
                    
                    menu.revalidate();
                    
            }
        });
    }

    private void randDestinationsBikeButtonCreate(GridBagConstraints gbc5) { //Fahrrad Icon hinzufügen
        randDestinationsBikeButton = new JButton();
        randDestinationsBikeButton.setPreferredSize(new Dimension(150, 80));
        randDestinationsBikeButton.setFocusable(false);

        ImageIcon icon = png("/bike.png", 25, 25);
        randDestinationsBikeButton.setIcon(icon);
        randDestinationsBikeButton.setText("Kurztrip");
        randDestinationsBikeButton.setIconTextGap(3);

        randDestinationsBikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> orte = facade.random_destinations_bike();
                JPanel destinationButtonsProxy = new JPanel();
                destinationButtonsProxy.setLayout(new BoxLayout(destinationButtonsProxy, BoxLayout.Y_AXIS));
                for (String s : orte) {
                    String[] ortUndPLZ = s.split(";");
                    String plz = ortUndPLZ[0];
                    String address = plz + ", " + ortUndPLZ[1];
                    JButton jb = new JButton(address);
                    jb.setToolTipText(facade.distance(plz));
                    jb.setHorizontalAlignment(SwingConstants.LEFT);
                    jb.setPreferredSize(new Dimension(290, 40));
                    jb.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame jf = new JFrame();
                            JPanel jp = new JPanel();
                            jp.setLayout(null);
                            String[] details = facade.destination_details(plz);
                            JLabel jlAddress = new JLabel("Zieladresse:");
                            JLabel jlAddressText = new JLabel(address);
                            JLabel jlWettervorhersage = new JLabel("Wettervorhersage:");
                            JLabel jlWettervorhersageTextOne = new JLabel(details[0]);
                            JLabel jlWettervorhersageTextTwo = new JLabel(details[1]);
                            JLabel jlWettervorhersageTextThree = new JLabel(details[2]);
                            JLabel jlEntfernung = new JLabel("Entfernung:");
                            JLabel jlEntfernungText = new JLabel(details[3]);
                            JLabel jlReisedauerUndKraftstoffverbrauch = new JLabel("Reisedauer und Kraftstoffverbrauch Auto:");
                            JLabel jlAutoDauerText = new JLabel(details[4]);
                            JLabel jlAutoVerbrauchText = new JLabel(details[5]);
                            JLabel jlReisedauerMitFahrrad = new JLabel("Reisedauer mit Fahrrad:");
                            JLabel jlReisedauerMitFahrradText = new JLabel(details[6]);
                            if(details[4].equals("")){
                                jlAutoDauerText.setText("---");
                            }
                            if(details[5].equals("")){
                                jlAutoVerbrauchText.setText("---");
                            }
                            if(details[6].equals("")){
                                jlReisedauerMitFahrradText.setText("---");
                            }
                            jlAddress.setBounds(4, 0, 400, 20);
                            jlAddressText.setBounds(4, 12, 400, 20);
                            jlWettervorhersage.setBounds(4, 45, 400, 20);
                            jlWettervorhersageTextOne.setBounds(4, 57, 400, 20);
                            jlWettervorhersageTextTwo.setBounds(4, 69, 400, 20);
                            jlWettervorhersageTextThree.setBounds(4, 81, 400, 20);
                            jlEntfernung.setBounds(4, 108, 400, 20);
                            jlEntfernungText.setBounds(4, 120, 400, 20);
                            jlReisedauerUndKraftstoffverbrauch.setBounds(4, 147, 400, 20);
                            jlAutoDauerText.setBounds(4, 159, 400, 20);
                            jlAutoVerbrauchText.setBounds(4, 171, 400, 20);
                            jlReisedauerMitFahrrad.setBounds(4, 198, 400, 20);
                            jlReisedauerMitFahrradText.setBounds(4, 210, 400, 20);
                            jp.add(jlAddress);
                            jp.add(jlAddressText);
                            jp.add(jlWettervorhersage);
                            jp.add(jlWettervorhersageTextOne);
                            jp.add(jlWettervorhersageTextTwo);
                            jp.add(jlWettervorhersageTextThree);
                            jp.add(jlEntfernung);
                            jp.add(jlEntfernungText);
                            jp.add(jlReisedauerUndKraftstoffverbrauch);
                            jp.add(jlAutoDauerText);
                            jp.add(jlAutoVerbrauchText);
                            jp.add(jlReisedauerMitFahrrad);
                            jp.add(jlReisedauerMitFahrradText);

                            jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            jf.add(jp);
                            jf.setSize(400, 270);
                            jf.setResizable(false);
                            jf.setVisible(true);
                        }
                    });
                    destinationButtonsProxy.add(jb);
                }
                destinationButtonsProxy.revalidate();
                
                try {
                    leftSide.remove(jsp);
                } catch (Exception jspNotYetIncluded) {
                }
                jsp = new JScrollPane(destinationButtonsProxy);
                jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                jsp.setMaximumSize(new Dimension(250, 450));
                jsp.setPreferredSize(new Dimension(250, 450));
                
                jsp.revalidate();
                
                leftSide.add(jsp,3);
                leftSide.revalidate();                    
                
                menu.revalidate();
               
            }
        });
    }

    private void changeDetailsButtonCreate() {
        changeDetailsButton = new JButton("Nutzerdaten ändern");
        changeDetailsButton.setPreferredSize(new Dimension(150, 50));
        changeDetailsButton.setFocusable(false);
        changeDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] details = facade.getDetails();

                JTextField detailsText = findTextFieldByName(changeName, "changeNameText");
                detailsText.setText(details[0]);
                detailsText = findTextFieldByName(changeOrt, "changeOrtText");
                detailsText.setText(details[1]);
                detailsText = findTextFieldByName(changePLZ, "changePLZText");
                detailsText.setText(details[2]);
                detailsText = findTextFieldByName(changeCarName, "changeCarNameText");
                detailsText.setText(details[3]);
                detailsText = findTextFieldByName(changeCarCO2, "changeCarCO2Text");
                detailsText.setText(details[4]);
                detailsText = findTextFieldByName(changeCarSpeed, "changeCarSpeedText");
                detailsText.setText(details[5]);
                detailsText = findTextFieldByName(changeBikeSpeed, "changeBikeSpeedText");
                detailsText.setText(details[6]);
                detailsText = findTextFieldByName(checkChangePassword, "checkChangePasswordText");
                detailsText.setText("");

                cardLayout.show(panelLayout, "5");
                jframe.setSize(new Dimension(400, 670));
            }
        });
    }

    public void checkChangePasswordCreate(){
        checkChangePassword = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Passwortbestätigung");
        JTextField tf = new JTextField(10);
        tf.setName("checkChangePasswordText");
        checkChangePassword.add(jl);
        checkChangePassword.add(tf);
    }

    public void changeNameCreate(){
        changeName = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Nutzername");
        JTextField tf = new JTextField(10);
        tf.setName("changeNameText");
        changeName.add(jl);
        changeName.add(tf);
    }

    public void changeOrtCreate(){
        changeOrt = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Heimatort");
        JTextField tf = new JTextField(10);
        tf.setName("changeOrtText");
        changeOrt.add(jl);
        changeOrt.add(tf);
    }

    public void changePLZCreate(){
        changePLZ = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Postleitzahl");
        JTextField tf = new JTextField(10);
        tf.setName("changePLZText");
        changePLZ.add(jl);
        changePLZ.add(tf);
    }

    public void changeCarNameCreate(){
        changeCarName = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Auto Name");
        JTextField tf = new JTextField(10);
        tf.setName("changeCarNameText");
        changeCarName.add(jl);
        changeCarName.add(tf);
    }

    public void changeCarCO2Create(){
        changeCarCO2 = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Kraftstoffverbrauch l/100km ");
        JTextField tf = new JTextField(10);
        tf.setName("changeCarCO2Text");
        changeCarCO2.add(jl);
        changeCarCO2.add(tf);
    }

    public void changeCarSpeedCreate(){
        changeCarSpeed = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Auto ⌀-Tempo km/h");
        JTextField tf = new JTextField(10);
        tf.setName("changeCarSpeedText");
        changeCarSpeed.add(jl);
        changeCarSpeed.add(tf);
    }

    public void changeBikeSpeedCreate(){
        changeBikeSpeed = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Fahrrad ⌀-Tempo km/h");
        JTextField tf = new JTextField(10);
        tf.setName("changeBikeSpeedText");
        changeBikeSpeed.add(jl);
        changeBikeSpeed.add(tf);
    }

    public void changeConfirmButtonCreate(GridBagConstraints gbc5){
        changeConfirmButton = new JButton("Bestätigen");
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
                    rightSide.remove(5);
                    profileCreate();
        
                    rightSide.add(profile,5);
                    rightSide.revalidate();
                    menu.revalidate();
                    cardLayout.show(panelLayout, "4");
                    jframe.setSize(new Dimension(400, 600));
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
                jframe.setSize(new Dimension(400, 600));
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
                jframe.setSize(new Dimension(400, 400));
            }
        });
    }

    public void changePasswordOldPasswordCreate(){
        changePasswordOldPassword = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Aktuelles Passwort");
        JTextField tf = new JTextField(10);
        tf.setName("changePasswordOldPasswordText");
        changePasswordOldPassword.add(jl);
        changePasswordOldPassword.add(tf);
    }

    public void changePasswordNewPasswordCreate(){
        changePasswordNewPassword = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Neues Passwort");
        JTextField tf = new JTextField(10);
        tf.setName("changePasswordNewPasswordText");
        changePasswordNewPassword.add(jl);
        changePasswordNewPassword.add(tf);
    }

    public void changePasswordConfirmNewPasswordCreate(){
        changePasswordConfirmNewPassword = new JPanel(new GridLayout(2,1));
        JLabel jl = new JLabel(" Neues Passwort bestätigen");
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
                jframe.setSize(new Dimension(400, 600));
            }
        });
    }

    public void changePasswordConfirmButtonCreate(){
        changePasswordConfirmButton = new JButton("Bestätigen");
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
                    jframe.setSize(new Dimension(400, 600));
                }
                else{
                    errorMessageCreate();
                }
                JTextField clear = findTextFieldByName(changePasswordOldPassword, "changePasswordOldPasswordText");
                clear.setText("");
                clear = findTextFieldByName(changePasswordNewPassword, "changePasswordNewPasswordText");
                clear.setText("");
                clear = findTextFieldByName(changePasswordConfirmNewPassword, "changePasswordConfirmNewPasswordText");
                clear.setText("");
            }
        });
    }

    private void logOutButtonCreate() {
        logOutButton = new JButton("Ausloggen");
        logOutButton.setPreferredSize(new Dimension(150, 50));
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.remove(profile);
                facade.sign_out_user();
                cardLayout.show(panelLayout, "1");
                jframe.setSize(new Dimension(400, 200));
                JTextField clear = findTextFieldByName(searchPLZ_ORT, "ortSuche");
                clear.setText("");
                clear = findTextFieldByName(changeName, "changeNameText");
                clear.setText("");
                clear = findTextFieldByName(checkChangePassword, "checkChangePasswordText");
                clear.setText("");
                clear = findTextFieldByName(changeOrt, "changeOrtText");
                clear.setText("");
                clear = findTextFieldByName(changePLZ, "changePLZText");
                clear.setText("");
                clear = findTextFieldByName(changeCarName, "changeCarNameText");
                clear.setText("");
                clear = findTextFieldByName(changeCarCO2, "changeCarCO2Text");
                clear.setText("");
                clear = findTextFieldByName(changeCarSpeed, "changeCarSpeedText");
                clear.setText("");
                clear = findTextFieldByName(changeBikeSpeed, "changeBikeSpeedText");
                clear.setText("");
            }
        });
    }

    private String getTextfieldContent(JPanel panel, String name) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JTextField && name.equals(component.getName())) {
                return ((JTextField) component).getText();
            }
        }
        return null;
    }

   public JTextField findTextFieldByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (component instanceof JTextField && name.equals(component.getName())) {
                return (JTextField) component;
            } else if (component instanceof Container) {
                JTextField textField = findTextFieldByName((Container) component, name);
                if (textField != null) {
                    return textField;
                }
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
