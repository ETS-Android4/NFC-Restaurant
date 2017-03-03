package fr.unice.iut.restaurant.provider;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.unice.iut.restaurant.database.BddConnexion;
/**
 * Created by Ismael 28/02/2017.
 */

public class Entree {

	private static String nom;
	private static String description;
	private static float prix;
	private static File monimage = new File("chemin");
	// FileOutputStream ostreamImage = new FileOutputStream(monimage);
	
	private Entree(String nom,String description,float prix){
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}	
	
	public static ArrayList<Entree> GetAllPlats() throws SQLException{
        Connection cn = null;
        Statement st = null;
        ArrayList<Entree> listEntree = new ArrayList<Entree>();
        try {
        	System.out.println( "Connexion à la base de données..." );
        	cn = BddConnexion.getConnection();
        	st = (Statement) cn.createStatement();
			// La requete pour recuperer le nom , description , prix des plats
			// SELECT nom,description,prix FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='2' and T_idTarif = idtarif;		
			ResultSet result = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT nom,description,prix FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='2' and T_idTarif = idtarif");
			while (result.next()) {
				Entree entree = new Entree(result.getString("nom"),result.getString("description"),result.getFloat("prix"));
				listEntree.add(entree);
				System.out.println(entree.getNom());
				System.out.println(entree.getDescription());
				System.out.println(entree.getPrix());
				System.out.println(listEntree.get(0).getNom());
			}
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return listEntree;
	}
	
	public static String getNom() {
		return nom;
	}

	public static void setNom(String nom) {
		Entree.nom = nom;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		Entree.description = description;
	}

	public static float getPrix() {
		return prix;
	}

	public static void setPrix(float prix) {
		Entree.prix = prix;
	}

	
}
