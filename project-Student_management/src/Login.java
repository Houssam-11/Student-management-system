import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Login extends JFrame implements ActionListener {
    private JPanel p0,p1,p2,p3;
    private JLabel l0,l1,l2;
    private JTextField tf;
    private JPasswordField pf;
    private JButton b1,b2,b3;



    Login(){

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        p1=new JPanel();p1.setLayout(new BorderLayout());p1.setBorder(new EmptyBorder(10, 10, 10, 10));
        p2=new JPanel();p2.setLayout(new GridLayout(3,3));p2.setLayout(new GridLayout(3, 10, 10, 10));
        p3=new JPanel();p3.setLayout(new GridLayout(1,3));



        l0=new JLabel("Login Form", SwingConstants.CENTER);
        l0.setFont(new Font("Serif", Font.PLAIN, 24));
        l1=new JLabel("User name:");
        l1.setFont(new Font("Arial", Font.PLAIN, 15));
        l2=new JLabel("Password :");
        l2.setFont(new Font("Arial", Font.PLAIN, 15));

        tf=new JTextField();
        tf.setPreferredSize(new Dimension(150, 500));

        pf=new JPasswordField();
        pf.setPreferredSize(new Dimension(150, 50));



        b1=new JButton("Login");b1.addActionListener(this);
        b2=new JButton("Cancel");b2.addActionListener(this);
        b3=new JButton("Sign in");b3.addActionListener(this);




        p2.add(l1);p2.add(tf);
        p2.add(l2);p2.add(pf);

        p3.add(b1);
        p3.add(b2);
        p3.add(b3);




        p1.add(l0,BorderLayout.NORTH);
        p1.add(p2,BorderLayout.CENTER);
        p1.add(p3,BorderLayout.SOUTH);

        this.add(p1);
        this.setVisible(true);
        this.setTitle("Login");
        this.setSize(400,210);
        this.setResizable(false);
        p1.setBackground(Color.LIGHT_GRAY);
        p2.setBackground(Color.LIGHT_GRAY);
        p3.setBackground(Color.LIGHT_GRAY);

    }
    public static void main(String[]args){
        new Login();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==b1)
        {

            if(tf.getText().isEmpty() || String.valueOf(pf.getPassword()).isEmpty() )
            {
                JOptionPane.showMessageDialog(null,"username and password are empty.","",JOptionPane.ERROR_MESSAGE);

            }
            else {{JOptionPane.showMessageDialog(null,"if you don't have an account ,Sign in ","",JOptionPane.ERROR_MESSAGE);}}

        }

        if(e.getSource()==b2)
        {
            dispose();
        }
        if(e.getSource()==b3)
        {
            new SignIn();
        }


        else
        {
            Connection conn= MyConnection.getConnection();

            PreparedStatement ps;
            String rq ="SELECT * FROM  user WHERE username = ? AND password = ?";
            try {
                ps =conn.prepareStatement(rq);
                ps.setString(1, tf.getText());
                ps.setString(2, String.valueOf(pf.getPassword()));

                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    System.out.println("YES");
                    Menu n =new Menu();
                    n.l1.setText("Welcome"+" "+"*"+tf.getText()+"*");


                }

                else
                {
                    System.out.println("NO");

                }



            }

            catch (SQLException e1) {

                e1.printStackTrace();
            }



        }
    }
}
