import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NewStudent extends JFrame implements ActionListener {
    private JPanel p, p1, p11, p2, p4, pb;
    //p1 for labels
    //p11 for what's 9damhom
    //p2 for age
    //p4 for sex
    //pb for buttons
    private JLabel l1, l2, l3, l4, l5, l0,l;
    private JTextField t1, t2,ta,t;
    private JRadioButton r1, r2; //sex male_female
    private JComboBox c1;   //age
    private JButton b1, b2,b3; //exit_add_clear
    private Connection conn;
    private ManageStudents ms; // Reference to ManageStudents class

    Student std=new Student();

    NewStudent() {
        JTable table ;
        table = new JTable();
        std.fillTable(table,"");

        //style
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //initialisation d paneaux
        p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p1 = new JPanel();
        p1.setLayout(new GridLayout(7, 1));
        p1.setLayout(new GridLayout(6, 10, 10, 10));
        p11 = new JPanel();
        p11.setLayout(new GridLayout(7, 1));
        p11.setLayout(new GridLayout(6, 10, 10, 10));
        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 1));
        p4 = new JPanel();
        p4.setLayout(new GridLayout(1, 2));
        pb = new JPanel();
        pb.setLayout(new GridLayout(1, 3));

        //initialisation d grande titre
        l0 = new JLabel("New Student", SwingConstants.CENTER);
        l0.setFont(new Font("Serif", Font.PLAIN, 24));

        //initialisation d label
        l = new JLabel("Id_Student:");
        l.setFont(new Font("arial", Font.BOLD, 20));
        l1 = new JLabel("First Name:");
        l1.setFont(new Font("arial", Font.BOLD, 20));
        l2 = new JLabel("Last Name:");
        l2.setFont(new Font("arial", Font.BOLD, 20));
        l3 = new JLabel("Sex:");
        l3.setFont(new Font("arial", Font.BOLD, 20));
        l4 = new JLabel("Age:");
        l4.setFont(new Font("arial", Font.BOLD, 20));
        l5 = new JLabel("Address:");
        l5.setFont(new Font("arial", Font.BOLD, 20));

        //initialisation d botton and style
        b1 = new JButton("Exit");b1.setFont(new Font("", Font.BOLD, 20));
        b2 = new JButton("Add");b2.setFont(new Font("", Font.BOLD, 20));
        b3=new JButton("Clear");b3.setFont(new Font("", Font.BOLD, 20));
        // init d textfields with style
        t = new JTextField();t.setPreferredSize(new Dimension(100, 50));
        t1 = new JTextField();t1.setPreferredSize(new Dimension(100, 50));
        t2 = new JTextField();t2.setPreferredSize(new Dimension(100, 50));
        ta = new JTextField();ta.setPreferredSize(new Dimension(100, 50));

        //init d list d age
        String[] days = new String[21];
        for (int i = 10; i <= 30; i++) days[i - 10] = "" + i;
        c1 = new JComboBox(days);

        //init d gender selection
        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");
        //groupage d botton d gender
        ButtonGroup grp = new ButtonGroup();
        grp.add(r1);
        grp.add(r2);

    //fillling panels
        //labels filling
        p1.add(l);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        //text filling _ sex-age filling
        p11.add(t);
        p11.add(t1);
        p11.add(t2);
            p4.add(r1);p4.add(r2);
        p11.add(p4);
            p2.add(c1);
        p11.add(p2);
        p11.add(ta);

        pb.add(b1);
        pb.add(b3);
        pb.add(b2);

        p.add(l0, BorderLayout.NORTH);
        p.add(p1, BorderLayout.WEST);
        p.add(p11, BorderLayout.EAST);
        p.add(pb, BorderLayout.SOUTH);

        //styling panel
        p.setBackground(Color.LIGHT_GRAY);
        p1.setBackground(Color.LIGHT_GRAY);
        p11.setBackground(Color.LIGHT_GRAY);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        //all to the big panel
        this.add(p);
        this.setVisible(true);
        this.setTitle("New student");
        setSize(600, 650);
        setVisible(true);
    }

    //codage des buttons
    public void actionPerformed(ActionEvent e){
        //button CLEAR
        if (e.getSource()==b3){
            t.setText("");
            t1.setText("");
            t2.setText("");
            ta.setText("");
            c1.setSelectedIndex(0);
        }
        /* button cancel */
        if (e.getSource() == b1) {
            dispose();
        }
        /* button add: */
        if (e.getSource() == b2) {
            int id_student = Integer.valueOf(t.getText());
            String first_name = t1.getText();
            String last_name = t2.getText();
            String address = ta.getText();
            String sex = "";
            //condition radiobox
            if (r1.isSelected()) {
                sex = "Male";
            } else if (r2.isSelected()) {
                sex = "Female";
            }
            //convertire value of age
            String age = (String) c1.getSelectedItem();
            int value = Integer.parseInt(age);

            //condtion sur the filling of formulaire
            if (t1.getText().isEmpty()||t2.getText().isEmpty()||ta.getText().isEmpty()
                    ||!(r1.isSelected() || r2.isSelected())){

                JOptionPane.showMessageDialog(null, "Error: The form is incomplete. Please fill in all fields.","",JOptionPane.ERROR_MESSAGE);
            }else {
                //saving to the database
                Student std = new Student();
                std.updateStudent('i', id_student, first_name, last_name, sex, age, address);
            }
        }
    }

    public static void main(String[] args) {
        new NewStudent();
    }
}