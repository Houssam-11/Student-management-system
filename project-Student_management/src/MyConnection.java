import java.sql.Connection;
import java.sql.DriverManager;
public class MyConnection {

    public  static  Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e){
            System.out.println("erreur"+e.getMessage());
        }
        try {
            String url ="jdbc:mysql://localhost/student_management";
            String user="root";
            String passwrd="";
            conn = DriverManager.getConnection(url,user,passwrd);
            System.out.println("connexion etablie");
        }catch (Exception e){
            System.out.println("erreur"+e.getMessage());
        }
        return conn;
    }
    public static void main(String[]args){
        MyConnection b = new MyConnection();
        b.getConnection();
    }
}