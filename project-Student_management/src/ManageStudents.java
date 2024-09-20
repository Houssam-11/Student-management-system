import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ManageStudents extends JFrame implements ActionListener {
    private JPanel p,pb;
    public static JTable t;
    private DefaultTableModel model;
    private JButton b0,b1,b2,b3;
    /*b1 TO exit _ b2 TO remove _ b3 TO add _ b0 TO show*/

    public ManageStudents() {
        // Style
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Panel
        p = new JPanel(new BorderLayout());
        p.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Table
        model = new DefaultTableModel();
        model.addColumn("id_student");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Sex");
        model.addColumn("Age");
        model.addColumn("Address");
        t = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(t);
        p.add(scrollPane, BorderLayout.CENTER);
        //layout for buttons
        pb = new JPanel();
        pb.setLayout(new GridLayout(1, 4));

        // exit Button
        b1 = new JButton("Exit");
        b1.setFont(new Font("", Font.BOLD, 20));
        b1.addActionListener(this);
        pb.add(b1);
        //remove button
        b2 = new JButton("Remove");
        b2.setFont(new Font("", Font.BOLD, 20));
        b2.addActionListener(this);
        pb.add(b2);
        //add button
        b3 = new JButton("Add");
        b3.setFont(new Font("", Font.BOLD, 20));
        b3.addActionListener(this);
        pb.add(b3);
        //show button
        b0 = new JButton("Show");
        b0.setFont(new Font("", Font.ITALIC, 20));
        b0.addActionListener(this);
        pb.add(b0);
        //buttons to panel
        p.add(pb, BorderLayout.SOUTH);
        // Styling
        p.setBackground(Color.LIGHT_GRAY);

        // all to the Frame
        add(p);
        setTitle("Manage Students");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//buttons actions
        Student std = new Student();

                    //exit interface
        if (e.getSource()==b1){
            this.dispose();
        }
                    //show content dyal database
        else if (e.getSource()==b0) {
            std.fillTable(t, "");
        }

                    //add new student
        else if (e.getSource() == b3) {
            // add user input
            int idStudent = Integer.parseInt(JOptionPane.showInputDialog("Enter id student:"));
            String firstName = JOptionPane.showInputDialog("Enter First Name:");
            String lastName = JOptionPane.showInputDialog("Enter Last Name:");
            String sex = JOptionPane.showInputDialog("Enter Sex (Male/Female):");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:"));
            String address = JOptionPane.showInputDialog("Enter Address:");

            Object[] inputRow = {idStudent, firstName, lastName, sex, age, address};
            model.addRow(inputRow);
            // Add student to the table
            std.fillTable(t, "");
            //add student to database
            std.addStudent(idStudent,firstName, lastName, String.valueOf(age), sex, address);
        }
                    //remove a selected row
        else if (e.getSource()==b2) {
            int selectedRow = t.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) model.getValueAt(selectedRow, 0);

                // Remove the row from the table
                model.removeRow(selectedRow);
                std.removeStudent(id);
                JOptionPane.showMessageDialog(null,"student removed successfuly");

            } else {
                JOptionPane.showMessageDialog(null, "Please select a student to remove.","",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new ManageStudents();
    }
}
