package org.UninaDelivery;

import org.UninaDelivery.Exception.CampiVuotiException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginForm extends JFrame {

    private JTextField TextLoginMatricola;
    private JPasswordField TextLoginPassword;
    private JButton loginButton;
    private JButton resetButton;
    private JPanel loginPanel;
    private JLabel imageUnina;
    private JButton ButtonMostraPassword;
    private JLabel imageLogo;
    private Boolean isPasswordVisibile = false;
    private GestoreFinestre gestoreFinestre;
    ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");
    
    public LoginForm(JFrame parent, GestoreFinestre gestoreFinestre) {
        
        setImpostazioniLoginForm(parent, gestoreFinestre);
        setImpostazioniButtonMostraPassword();
        setImpostazioniLoginButton();
        setImpostazioniResetButton();
        setImpostazioniVarie();


        Listeners();
    }

    private void setImpostazioniLoginForm(JFrame parent, GestoreFinestre gestoreFinestre){
        setIconImage(imageIcon.getImage());
        setLayout(null);
        setResizable(false);
        this.gestoreFinestre = gestoreFinestre;
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(540, 320));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void setImpostazioniButtonMostraPassword(){
        ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/hide.png"));
        ButtonMostraPassword.setMargin(new Insets(0, 0, 0, 0));
        ButtonMostraPassword.setOpaque(false);
        ButtonMostraPassword.setBorderPainted(false);
        ButtonMostraPassword.setBorder(null);
        ButtonMostraPassword.setContentAreaFilled(false);
    }
    
    private void setImpostazioniLoginButton(){
        loginButton.setMargin(new Insets(0, 0, 0, 0));
        loginButton.setOpaque(false);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusable(false);
    }
    
    private void setImpostazioniResetButton(){
        resetButton.setMargin(new Insets(0, 0, 0, 0));
        resetButton.setOpaque(false);
        resetButton.setBorderPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.setFocusable(false);
    }
    
    private void setImpostazioniVarie(){
        imageLogo.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/LogoConScritta.png"));
        imageUnina.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoFedericoII.png"));
        TextLoginMatricola.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
        TextLoginPassword.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
    }

    
    public void Listeners(){
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setForeground(new Color(78, 215, 93));
            }
            public void mouseExited(MouseEvent e) {
                loginButton.setForeground(new Color(251, 255, 248));
            }
        });
        
        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                resetButton.setForeground(new Color(203, 19, 24));
            }
            public void mouseExited(MouseEvent e) {
                resetButton.setForeground(new Color(251, 255, 248));
            }
        });
        
        ButtonMostraPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPasswordVisibile) {
                    isPasswordVisibile = true;
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/visible.png"));
                    TextLoginPassword.setEchoChar((char)0);
                } else {
                    isPasswordVisibile = false;
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/hide.png"));
                    TextLoginPassword.setEchoChar('•');
                }
            }
        });
        
        ButtonMostraPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isPasswordVisibile){
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/visible.png"));
                } else {
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/hide.png"));
                }
            }
        });
        
        ButtonMostraPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isPasswordVisibile){
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/hide.png"));
                } else {
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/visible.png"));
                }
            }
        });
        
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextLoginMatricola.setText("");
                TextLoginPassword.setText("");
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ControllaLogin();
                } catch (CampiVuotiException ex) {
                    System.out.println("campi vuoti: " + ex);
                }
            }
        });
        
        TextLoginMatricola.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char lettera = e.getKeyChar();
                if(!Character.isDigit(lettera)) {
                    e.consume();
                }
            }
        });
        TextLoginPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        ControllaLogin();
                    } catch (CampiVuotiException ex) {
                        System.out.println("campi vuoti: " + ex);
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    TextLoginMatricola.grabFocus();
                }
            }
        });
        
        TextLoginMatricola.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        ControllaLogin();
                    } catch (CampiVuotiException ex) {
                        System.out.println("campi vuoti: " + ex);
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    TextLoginPassword.grabFocus();
                }
            }
        });
        
        ButtonMostraPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        ControllaLogin();
                    } catch (CampiVuotiException ex) {
                        System.out.println("campi vuoti: " + ex);
                    }
                }
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    gestoreFinestre.chiudiConnessioneDB();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    
    public void ControllaLogin() throws CampiVuotiException {
        String Matricola = TextLoginMatricola.getText();
        String Password = new String(TextLoginPassword.getPassword());
        
        
        if (Matricola.isEmpty() || Password.isEmpty())
            throw new CampiVuotiException(this, gestoreFinestre);
        
        gestoreFinestre.EffettuaLogin(Matricola, Password, LoginForm.this);
        
    }
}
