import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu extends JFrame implements ActionListener{

    JMenuBar mb;
    JMenu  student;
    JMenuItem add ,manage;
    JPanel p1;
    JLabel l1;

    Menu(){


        try {
            // Set Nimbus Look and Feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        add=new JMenuItem("add");add.addActionListener(this);
        manage=new JMenuItem("manage");manage.addActionListener(this);
        mb=new JMenuBar();

        student=new JMenu("student");

        student.add(add);
        student.add(manage);

        mb.add(student);



        l1=new JLabel("Welcome ", SwingConstants.CENTER);
        l1.setFont(new Font("Serif", Font.PLAIN, 30));
        l1.setText("Welcome");


        p1= new JPanel();
        p1.setLayout(new GridLayout(1,1));
        p1.add(l1);


        add(p1);
        this.setJMenuBar(mb);
        this.setSize(300,300);
        this.setVisible(true);
        this.setResizable(false);
    }
    public static void main(String[] args) { new Menu();  }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == add)
        {
            new NewStudent();
            new Student();

        }

        if(e.getSource() ==manage)
        {
            new ManageStudents();

        }


    }
}
