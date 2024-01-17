package org.UninaDelivery;

import org.UninaDelivery.Controllori.ControlloreDAO;
import org.UninaDelivery.Controllori.ControlloreFinestre;
import org.UninaDelivery.Exception.CampiVuotiException;
import org.UninaDelivery.Exception.OperatoreNonTrovatoException;
import org.UninaDelivery.Operatore.OperatoreDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginPage extends JFrame {

    private JTextField textLoginMatricola;
    private JPasswordField textLoginPassword;
    private JButton loginButton;
    private JButton resetButton;
    private JPanel loginPanel;
    private JLabel imageUnina;
    private JButton mostraPasswordButton;
    private JLabel imageLogo;
    private Boolean isPasswordVisibile = false;
    private ControlloreFinestre controlloreFinestre;
    private ControlloreDAO controlloreDAO;
    private ImageIcon imageIcon = new ImageIcon("src/main/java/org/UninaDelivery/Icon/logoSenzaScritte.png");
    
    public LoginPage(JFrame parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO) {
        setImpostazioniLoginPage(parent, controlloreFinestre, controlloreDAO);
        setImpostazioniButtonMostraPassword();
        setImpostazioniLoginButton();
        setImpostazioniResetButton();
        setImpostazioniVarie();


        listeners();
    }

    private void setImpostazioniLoginPage(JFrame parent, ControlloreFinestre controlloreFinestre, ControlloreDAO controlloreDAO){
        setIconImage(imageIcon.getImage());
        setLayout(null);
        setResizable(false);
        this.controlloreFinestre = controlloreFinestre;
        this.controlloreDAO = controlloreDAO;
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(540, 320));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void setImpostazioniButtonMostraPassword(){
        mostraPasswordButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/hide.png"));
        mostraPasswordButton.setMargin(new Insets(0, 0, 0, 0));
        mostraPasswordButton.setOpaque(false);
        mostraPasswordButton.setBorderPainted(false);
        mostraPasswordButton.setBorder(null);
        mostraPasswordButton.setContentAreaFilled(false);
        mostraPasswordButton.setFocusable(false);
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
        textLoginMatricola.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
        textLoginPassword.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
    }

    
    private void listeners(){
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
        
        mostraPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPasswordVisibile) {
                    isPasswordVisibile = true;
                    mostraPasswordButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/visible.png"));
                    textLoginPassword.setEchoChar((char)0);
                } else {
                    isPasswordVisibile = false;
                    mostraPasswordButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/hide.png"));
                    textLoginPassword.setEchoChar('•');
                }
            }
        });
        
        mostraPasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isPasswordVisibile){
                    mostraPasswordButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/visible.png"));
                } else {
                    mostraPasswordButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/hide.png"));
                }
            }
        });
        
        mostraPasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isPasswordVisibile){
                    mostraPasswordButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/hide.png"));
                } else {
                    mostraPasswordButton.setIcon(new ImageIcon("src/main/java/org/UninaDelivery/Icon/visible.png"));
                }
            }
        });
        
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textLoginMatricola.setText("");
                textLoginPassword.setText("");
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eseguiLogin();
                } catch (CampiVuotiException ex) {
                    System.out.println("campi vuoti: " + ex);
                }
            }
        });
        
        textLoginMatricola.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char lettera = e.getKeyChar();
                if(!Character.isDigit(lettera)) {
                    e.consume();
                }
            }
        });
        textLoginPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        eseguiLogin();
                    } catch (CampiVuotiException ex) {
                        System.out.println("campi vuoti: " + ex);
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    textLoginMatricola.grabFocus();
                }
            }
        });
        
        textLoginMatricola.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        eseguiLogin();
                    } catch (CampiVuotiException ex) {
                        System.out.println("campi vuoti: " + ex);
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    textLoginPassword.grabFocus();
                }
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    controlloreDAO.chiudiConnessioneDB();
                } catch (SQLException ex) {
                    System.out.println("errore SQL in chiusura del database: " + ex);
                }
            }
        });
    }
    
    private void eseguiLogin() throws CampiVuotiException {
        String matricola = textLoginMatricola.getText();
        String password = new String(textLoginPassword.getPassword());

        if (matricola.isEmpty() || password.isEmpty())
            throw new CampiVuotiException(this, controlloreFinestre);
        
        try{
            OperatoreDTO operatoreEntrante = controlloreDAO.getOperatore(matricola, password);
            controlloreFinestre.apriHome(operatoreEntrante, this);
        } catch (OperatoreNonTrovatoException e){
            System.out.println("Operatore non trovato nel database: " + e);
            controlloreFinestre.mostraMessageDialogErrore(this, "Matricola o Password errati.", "Attenzione");
        }
    }
}