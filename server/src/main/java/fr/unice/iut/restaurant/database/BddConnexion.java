package fr.unice.iut.restaurant.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by Ismael 28/02/2017.
 */

public class BddConnexion {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/nfc_resto";
    private static String user = "root";
    private static String pwd = "root";
    
    private static Connection con;
    
    public BddConnexion() throws ClassNotFoundException{
    	try{
    		Class.forName(driver);
    		con = DriverManager.getConnection(url, user, pwd);
    	}catch (SQLException e) {
			// TODO: handle exception
    		e.printStackTrace();
		}catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	
    }
    
    public static Connection getConnection() throws ClassNotFoundException  {
        if(con == null){
            new BddConnexion();
        }
        return con;
    }
	
}
