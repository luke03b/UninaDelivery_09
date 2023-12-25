package org.example;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame{
    private JPanel LoginPanel;
    private JTextField matricolaTextField;
    private JPasswordField passwordPasswordField;
    private JButton accediButton;
    public LoginPage(JFrame parent){
        setTitle("UninaDelivery - Login");
        setContentPane(LoginPanel);
        
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
