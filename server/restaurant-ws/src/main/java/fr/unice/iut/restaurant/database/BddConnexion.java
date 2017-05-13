package fr.unice.iut.restaurant.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe pour gérer la connexion à une base de données
 * @author Ismael
 * @version 1.0
 */
public class BddConnexion {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/nfc_resto";
    private static String user = "root";
    private static String pwd = "root";
    private static Connection con;
    
    /**
     * Se connecter à une base de données
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public BddConnexion() throws ClassNotFoundException {
    	try {
    		Class.forName(driver);
    		con = DriverManager.getConnection(url, user, pwd);
    	} catch (SQLException e) {
    		e.printStackTrace();
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	
    }
    
    /**
     * Relancer la connexion à une base de données
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws ClassNotFoundException  {
        if (con == null) {
            new BddConnexion();
        }
        return con;
    }
}