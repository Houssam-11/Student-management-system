import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SignIn extends JFrame implements ActionListener {
    private JLabel l0,l1,l2,l3,l4;
    private JTextField tf1,tf2;
    private JPasswordField pf1,pf2;
    private JPanel p1,p2,p3;
    private JButton b;

    SignIn(){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        p1=new JPanel();p1.setLayout(new BorderLayout());p1.setBorder(new EmptyBorder(10, 10, 10, 10));
        p2=new JPanel();p2.setLayout(new GridLayout(4,4));p2.setLayout(new GridLayout(4, 1,1,1));
        p3=new JPanel();p3.setLayout(new GridLayout(1,1));



        l0=new JLabel("Login Form", SwingConstants.CENTER);
        l0.setFont(new Font("Serif", Font.PLAIN, 24));
        l1=new JLabel("User name:");
        l1.setFont(new Font("Arial", Font.PLAIN, 15));
        l2=new JLabel("Confirm your user name:");
        l2.setFont(new Font("Arial", Font.PLAIN, 15));
        l3=new JLabel("Password :");
        l3.setFont(new Font("Arial", Font.PLAIN, 15));
        l4=new JLabel("Confirm your password :");
        l4.setFont(new Font("Arial", Font.PLAIN, 15));

        tf1=new JTextField();
        tf1.setPreferredSize(new Dimension(150, 500));
        tf2=new JTextField();
        tf2.setPreferredSize(new Dimension(150, 500));

        pf1=new JPasswordField();
        pf1.setPreferredSize(new Dimension(150, 50));
        pf2=new JPasswordField();
        pf2.setPreferredSize(new Dimension(150, 50));



        b=new JButton("Sign in");b.addActionListener(this);




        p2.add(l1);p2.add(tf1);
        p2.add(l2);p2.add(tf2);
        p2.add(l3);p2.add(pf1);
        p2.add(l4);p2.add(pf2);

        p3.add(b);





        p1.add(l0,BorderLayout.NORTH);
        p1.add(p2,BorderLayout.CENTER);
        p1.add(p3,BorderLayout.SOUTH);

        this.add(p1);
        this.setVisible(true);
        this.setTitle("Login");
        this.setSize(400,250);
        this.setResizable(false);
        p1.setBackground(Color.LIGHT_GRAY);
        p2.setBackground(Color.LIGHT_GRAY);
        p3.setBackground(Color.LIGHT_GRAY);

    }
    public static void main(String[]args){
        new SignIn();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String url ="jdbc:mysql://localhost/ student_management";

        if(e.getSource()==b) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try {
                    Connection conn = DriverManager.getConnection(url, "root", "");
                    String sql = "INSERT INTO user (username, password) VALUES (?, ?)";

                    PreparedStatement statement = conn.prepareStatement(sql);

                    statement.setString(1, tf1.getText());
                    statement.setString(2, new String(pf1.getPassword()));



                    statement.executeUpdate();

                    statement.close();

                    conn.close();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        if(tf1.getText()!=(tf2.getText()) && pf1.getPassword()!=(pf2.getPassword()))
        {
            JOptionPane.showMessageDialog(null,"User name or passeword isn't confirmed","",JOptionPane.ERROR_MESSAGE);
        } else if (tf1.getText()==(tf2.getText()) && pf1.getPassword()==(pf2.getPassword())) {
            JOptionPane.showMessageDialog(null,"successful registration","",JOptionPane.INFORMATION_MESSAGE);
        }

    }


			    /*String query = "INSERT INTO users ('username', 'password') VALUES ( ?, ?)";
		        String url ="jdbc:mysql://localhost/student_management";
	        	String user="root";
		        String password= "";
		        try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

				try
		        	(Connection conn = DriverManager.getConnection(url, user, password );
		             PreparedStatement pstmt = conn.prepareStatement(query)) {
		            pstmt.setString(1, tf1.getText());
		            pstmt.setString(2, new String(pf1.getPassword()));

		            int rowsInserted = pstmt.executeUpdate();

		            if (rowsInserted > 0) {
		                JOptionPane.showMessageDialog(null, "User successfully registered!", "", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "Error registering user.", "", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException e1) {
		            System.out.println("Error inserting user: " + e1.getMessage());
		        }*/








}



