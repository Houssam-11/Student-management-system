import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class User {
    public void updateDeleteUser( Integer id_student, String username, String password
    )
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;


        try {
            ps=con.prepareStatement("INSERT INTO user( username, password) VALUES (?,?)");
            ps.setString(1,username);
            ps.setString(2,password);


            ResultSet rs = ps.executeQuery();



        }
        catch (SQLException e) {

            e.printStackTrace();

        }

    }
}

