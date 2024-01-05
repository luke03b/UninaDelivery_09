package org.example;

import org.example.GestoreFinestre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm extends JFrame {

    private JTextField TextLoginMatricola;
    private JPasswordField TextLoginPassword;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel loginPanel;
    private JLabel imageUnina;


    public GestoreFinestre gestorePagine;

    ImageIcon imageIcon = new ImageIcon("src/main/java/org/example/Icon/UninaDelivery.jpg");
    public LoginForm(JFrame parent, GestoreFinestre gp) {

        imageUnina.setIcon(new ImageIcon("src/main/java/org/example/Icon/logoFedericoII.png"));


        setIconImage(imageIcon.getImage());

        loginButton.setOpaque(false);
        cancelButton.setOpaque(false);
        loginButton.setBorderPainted(false);
        cancelButton.setBorderPainted(false);

        loginButton.setFocusable(false);
        cancelButton.setFocusable(false);
        setLayout(null);
        setResizable(false);
        gestorePagine = gp;
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(520, 300));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        // listener del tasto cancel che chiude il programma
        cancelButton.addActionListener(e -> dispose());

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

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelButton.setForeground(new Color(203, 19, 24));
            }
            public void mouseExited(MouseEvent e) {
                cancelButton.setForeground(new Color(251, 255, 248));
            }
        });


    }

}
