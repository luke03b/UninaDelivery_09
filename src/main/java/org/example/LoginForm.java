package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    
    public GestoreFinestre gestorePagine;

    ImageIcon imageIcon = new ImageIcon("src/main/java/org/example/Icon/UninaDelivery.jpg");
    public LoginForm(JFrame parent, GestoreFinestre gp) {
        
        setIconImage(imageIcon.getImage());
        imageUnina.setIcon(new ImageIcon("src/main/java/org/example/Icon/logoFedericoII.png"));
        imageLogo.setIcon(new ImageIcon("src/main/java/org/example/Icon/LogoConScritta.png"));
        
        
        ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/example/Icon/hide.png"));
        ButtonMostraPassword.setOpaque(false);
        ButtonMostraPassword.setBorderPainted(false);
        
        // to remote the spacing between the image and button's borders
        ButtonMostraPassword.setMargin(new Insets(0, 0, 0, 0));
        // to remove the border
        ButtonMostraPassword.setBorder(null);
        ButtonMostraPassword.setContentAreaFilled(false);
        
        TextLoginMatricola.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
        TextLoginPassword.setBorder(BorderFactory.createMatteBorder(0,2,0,0,Color.WHITE));
        
        resetButton.setMargin(new Insets(0, 0, 0, 0));
        loginButton.setMargin(new Insets(0, 0, 0, 0));
        
        loginButton.setOpaque(false);
        resetButton.setOpaque(false);
        loginButton.setBorderPainted(false);
        resetButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        resetButton.setContentAreaFilled(false);
        loginButton.setFocusable(false);
        resetButton.setFocusable(false);
        
        
        setLayout(null);
        setResizable(false);
        gestorePagine = gp;
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(540, 320));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        

        // azione del bottone register quando si passa sul bottone con il mouse
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
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/example/Icon/visible.png"));
                    TextLoginPassword.setEchoChar((char)0);
                } else {
                    isPasswordVisibile = false;
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/example/Icon/hide.png"));
                    TextLoginPassword.setEchoChar('•');
                }
            }
        });
        ButtonMostraPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isPasswordVisibile){
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/example/Icon/visible.png"));
                } else {
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/example/Icon/hide.png"));
                }
            }
        });
        ButtonMostraPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isPasswordVisibile){
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/example/Icon/hide.png"));
                } else {
                    ButtonMostraPassword.setIcon(new ImageIcon("src/main/java/org/example/Icon/visible.png"));
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
    }

}
