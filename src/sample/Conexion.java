package sample;

import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;


public class Conexion {
    private final String base = "abarrotes";
    private final String user = "root";
    private final String password = "18550696";
    // private final String url = "jdbc:mysql://localhost:3306/" + base;
    private final String  url = "jdbc:mysql://localhost:3306/abarrotes?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private Connection con = null;

    public Connection getConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);

        }
        catch(SQLException e){
            System.err.println(e);
        }
        catch(ClassNotFoundException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return con;
    }

}
