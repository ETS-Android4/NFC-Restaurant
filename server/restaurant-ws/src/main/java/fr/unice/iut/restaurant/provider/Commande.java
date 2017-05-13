package fr.unice.iut.restaurant.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.unice.iut.restaurant.database.BddConnexion;

/**
 * Classe pour gérer les commandes
 * @author Ismael
 * @version 1.0
 */
public class Commande {

    private int U_idUsers;
    private int T_idTables;
    private String Horodatage;
    private String details;
    private int idCommande;

    /**
     * Constructeur d'un objet Commande
     * @param idUser Identifiant de l'utilisateur qu'a fait la commande
     * @param Horodatage Date et heure de la commande
     * @param T_idTables Numéro de la table de la commande
     * @param details Contenu de la commande
     */
	public Commande(int idUser, String Horodatage, int T_idTables, String details) {
        this.U_idUsers = idUser;
        this.Horodatage = Horodatage;
        this.T_idTables = T_idTables;
        this.details = details;
    }
    
    /**
     * Constructeur d'un objet Commande
     * @param T_idTables Numéro de la table de la commande
     * @param Horodatage Date et heure de la commande
     * @param details Contenu de la commande
     * @param idCommande Identifiant de la commande
     */
    public Commande(int T_idTables, String Horodatage, String details, int idCommande) {
        this.T_idTables = T_idTables;
        this.Horodatage = Horodatage;
        this.details = details;
        this.idCommande = idCommande;
    }
    
    /**
     * Constructeur vide d'un objet Commande
     */
    public Commande() { }

    /**
     * Récupérer l'identifiant d'un utilisateur
     * @return un identifiant sous forme d'entier
     */
    public int getU_idUsers() {
        return U_idUsers;
    }

    /**
     * Récupérer l'identifiant d'une table
     * @return un identifiant sous forme d'entier
     */
    public int getT_idTables() {
        return T_idTables;
    }

    /**
     * Récupérer l'identifiant d'une commande
     * @return un identifiant sous forme d'entier
     */
    public int getIdCommande() {
        return idCommande;
    }

    /**
     * Récupérer la date d'une commande
     * @return une date sous forme de chaîne de caractère
     */
    public String getHorodatage() {
        return Horodatage;
    }

    /**
     * Récupérer le contenu d'une commande
     * @return un contenu sous forme de chaîne de caractère
     */
    public String getDetails() {
        return details;
    }

    /**
     * Insérer l'identifiant d'un utilisateur
     * @param u_idUsers Identifinat d'un utilisateur
     */
    public void setU_idUsers(int u_idUsers) {
        U_idUsers = u_idUsers;
    }

    /**
     * Insérer l'identifiant d'une table
     * @param t_idTables Identifinat d'une table
     */
    public void setT_idTables(int t_idTables) {
        T_idTables = t_idTables;
    }

    /**
     * Insérer l'identifiant d'une commande
     * @param idCommande Identifiant d'une commande
     */
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

    /**
     * Insérer la date d'une commande
     * @param horodatage Date de la commande
     */
    public void setHorodatage(String horodatage) {
        Horodatage = horodatage;
    }

    /**
     * Insérer le contenu d'une commande
     * @param details Contenu d'une commande
     */
    public void setDetails(String details) {
        this.details = details;
    }
    
    /**
     * Insérer une commande dans une base de données
     * @param obj Une commande qui contient toutes les informations dont on a besoin
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void create(Commande obj) throws SQLException {
        Connection cn = null;
        Statement st = null;
        String sql = "INSERT INTO commandes (Horodatage,T_idTables,U_idUsers,detail) VALUES('"+obj.getHorodatage()+"'," + "'"+obj.getT_idTables()
                    +"','"+obj.getU_idUsers()+"','"+obj.getDetails()+"');";
        try {
            cn = BddConnexion.getConnection();
            st = cn.createStatement();
            int result = st.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }   
    }
    
    /**
     * Supprimer une commande d'une base de données
     * @param idCommande Identifiant de la commande à supprimer
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void delete(int idCommande) throws SQLException {
    	Connection cn = null;
        Statement st = null;
        String sql = "DELETE FROM nfc_resto.commandes WHERE idCommande = " + idCommande;
        try {
            cn = BddConnexion.getConnection();
            st = cn.createStatement();
            int result = st.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Récupérer la liste des commandes d'une base de données
     * @throws SQLException
     * @throws ClassNotFoundException
     * @return une liste d'objet Commande
     */
    public ArrayList<Commande> showCommande() throws SQLException {
    	Connection cn = null;
        Statement st = null;
        String sql = "SELECT * FROM nfc_resto.commandes;";
        ArrayList<Commande> commandes = new ArrayList<Commande>(); 
    	try {
        	cn = BddConnexion.getConnection();
        	st = (Statement) cn.createStatement();
			ResultSet result = st.executeQuery(sql);
			while (result.next()) {
				Commande objCommande = new Commande(result.getInt("T_idTables"),result.getString("Horodatage"),result.getString("detail"),result.getInt("idCommande"));
				commandes.add(objCommande);
			}
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	return commandes;
    }
    
    /**
     * Récupérer le numéro de la table qui correspond à son GUID
     * @param Guid Le GUID d'un TAG
     * @throws SQLException
     * @throws Exception
     * @return numéro de la table sous forme d'entier
     */
    public static int GetIdTable(String Guid) throws SQLException {
    	Connection cn = null;
        Statement st = null;
        String sql = "SELECT idTables FROM nfc_resto.tables WHERE guid='"+Guid+"';";
    	int idTable = 0;
    	try {
    		cn = BddConnexion.getConnection();
            st = (Statement) cn.createStatement();
    		ResultSet result = st.executeQuery(sql);
			while (result.next()) {
				idTable = result.getInt("idTables");
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return idTable;
    }
    
    /**
     * Récupérer l'identifiant de l'utilisateur qui correspond à son numéro de téléphone
     * @param username Téléphone d'un utilisateur
     * @throws SQLException
     * @throws Exception
     * @return identifiant de l'utilisateur sous forme d'entier
     */
    public static int GetIdUser(String username) throws SQLException {
    	Connection cn = null;
        Statement st = null;
        String sql = "SELECT idUsers FROM nfc_resto.users WHERE noTel='"+username+"';";
    	int idUser = 0;
    	try {
    		cn = BddConnexion.getConnection();
    		st = (Statement) cn.createStatement();
    		ResultSet result = st.executeQuery(sql);
			while (result.next()) {
				idUser = result.getInt("idUsers");
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return idUser;
    }
}