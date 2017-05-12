package fr.unice.iut.restaurant.provider;

import fr.unice.iut.restaurant.database.BddConnexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 13/04/2017.
 */
public class Commande {
    String Horodatage, details;
    int T_idTables, U_idUsers, idCommande;

	public Commande(int idUser,String Horodatage, int T_idTables, String details){
        this.U_idUsers = idUser;
        this.Horodatage = Horodatage;
        this.details = details;
        this.T_idTables = T_idTables;
    }
    
    public Commande(int T_idTables,String Horodatage,String details,int idCommande){
        this.idCommande = idCommande;
        this.Horodatage = Horodatage;
        this.details = details;
        this.T_idTables = T_idTables;
    }
    
    public Commande(){}

    public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
    
    public int getU_idUsers() {
        return U_idUsers;
    }

    public void setU_idUsers(int u_idUsers) {
        U_idUsers = u_idUsers;
    }

    public String getHorodatage() {
        return Horodatage;
    }

    public void setHorodatage(String horodatage) {
        Horodatage = horodatage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getT_idTables() {
        return T_idTables;
    }

    public void setT_idTables(int t_idTables) {
        T_idTables = t_idTables;
    }

    public static void create(Commande obj) throws SQLException {
        Connection cn = null;
        Statement st = null;
        int result;
        try {
            cn = BddConnexion.getConnection();
            String sql = "INSERT INTO commandes (Horodatage,T_idTables,U_idUsers,detail) VALUES ('"+obj.getHorodatage()+"'," +
                    "'"+obj.getT_idTables()+"','"+obj.getU_idUsers()+"','"+obj.getDetails()+"');";
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            result = st.executeUpdate(sql);
            //deconnexion avec la db
            //liberer la memoire
            /*
            st.close();
            cn.close();
            */
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    
    public static int delete(int idCommande) throws Exception {
    	Connection cn = null;
        Statement st = null;
        int result = 0;
        try {
	            cn = BddConnexion.getConnection();
	            String sql = "DELETE FROM nfc_resto.commandes WHERE idCommande = "+idCommande;
	            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	            result = st.executeUpdate(sql);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
        }
		return result;
    }
    
    public ArrayList<Commande> showCommande() throws SQLException{
    	ArrayList<Commande> commandes = new ArrayList<Commande>(); 
    	Connection cn = null;
        Statement st = null;
    	try {
        	System.out.println( "Connexion à la base de données..." );
        	cn = BddConnexion.getConnection();
        	st = (Statement) cn.createStatement();
			ResultSet result = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM nfc_resto.commandes;");
			while (result.next()) {
				Commande objCommande = new Commande(result.getInt("T_idTables"),result.getString("Horodatage"),result.getString("detail"),result.getInt("idCommande"));
				 commandes.add(objCommande);
			}
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return commandes;
    } 
    
    public static int GetIdTable(String Guid)throws SQLException{
    	Connection cn = null;
        Statement st = null;
        ResultSet resultSet = null;
    	int idTable=0;
    	try{
    		cn = BddConnexion.getConnection();
    		String sql = "SELECT idTables FROM nfc_resto.tables WHERE guid='"+Guid+"';";
    		resultSet = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
    		System.out.println("resulset :"+resultSet);
			while (resultSet.next()) {
				System.out.println("RESULT SET " + resultSet.getInt("idTables"));
				idTable = resultSet.getInt("idTables");
				System.out.println(idTable);
			}
    	}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return idTable;
    }
    
    public static int GetIdUser(String username)throws SQLException{
    	Connection cn = null;
        Statement st = null;
        ResultSet resultSet = null;
    	int idUser=0;
    	try{
    		cn = BddConnexion.getConnection();
    		String sql = "SELECT idUsers FROM nfc_resto.users WHERE noTel='"+username+"';";
    		resultSet = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
    		System.out.println("resulset :"+resultSet);
			while (resultSet.next()) {
    			System.out.println("RESULT SET " + resultSet.getInt("idUsers"));
				idUser = resultSet.getInt("idUsers");
				System.out.println(idUser);
			}
    	}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return idUser;
    }
    

}
