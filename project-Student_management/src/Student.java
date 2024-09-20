import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {

    //new student to the base
    public void updateStudent(char operation, Integer id_student, String first_name, String last_name,
                              String sex, String age, String address)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;

        if (operation == 'i'){
            //i for insert
            try {
                ps=con.prepareStatement("INSERT INTO student(id_student,first_name, last_name, sex, age, address) VALUES (?,?,?,?,?,?)");
                ps.setInt(1,id_student);
                ps.setString(2,first_name);
                ps.setString(3,last_name);
                ps.setString(4,sex);
                ps.setString(5,age);
                ps.setString(6,address);

                if (ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"new student added successfully");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //student table filling to the base
    public void fillTable(JTable table,String SearchValue){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps=con.prepareStatement("SELECT * FROM `student` WHERE CONCAT (`first_name`,`last_name`,`address`) LIKE ?");
            ps.setString(1,"%"+SearchValue +"%");

            ResultSet rs=ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            model.setRowCount(0);//to refresh table
            Object[] row;
            while (rs.next()){
                row=new Object[6];
                row[0]=rs.getInt(1);
                row[1]=rs.getString(2);
                row[2]=rs.getString(3);
                row[3]=rs.getString(4);
                row[4]=rs.getString(5);
                row[5]=rs.getString(6);
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //adding student to the base from manageStudent class
    public void addStudent(int id_student,String firstName, String lastName, String age, String sex, String address) {
        JTable table ;
        table = new JTable();
        try {
            Connection conn = MyConnection.getConnection();
            PreparedStatement st =conn.prepareStatement("SELECT * FROM `student` WHERE 1");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO student (id_student,first_name, last_name, sex, age, address) VALUES (?,?, ?, ?, ?, ?)");
            st.getResultSet();

            stmt.setInt(1, id_student);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, sex);
            stmt.setInt(5, Integer.parseInt(age));
            stmt.setString(6, address);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"new student added successfully");
            // Refresh table
            fillTable(table, "");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

            //remove selected student from base
    public void removeStudent(int id_student){
        try {
            Connection conn = MyConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM student WHERE id_student=?");
            stmt.setInt(1, id_student);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
