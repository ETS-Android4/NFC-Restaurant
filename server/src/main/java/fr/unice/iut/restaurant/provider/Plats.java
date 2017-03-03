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
public class Plats {

	private static String nom;
	private static String description;
	private static float prix;
	private static File monimage = new File("chemin");
	// FileOutputStream ostreamImage = new FileOutputStream(monimage);
	
	private Plats(String nom,String description,float prix) throws ClassNotFoundException {
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}
	
	public static ArrayList<Plats> GetAllPlats() throws SQLException{
        Connection cn = null;
        Statement st = null;
        ArrayList<Plats> listPlats = new ArrayList<Plats>();
        try {
        	System.out.println( "Connexion à la base de données..." );
        	cn = BddConnexion.getConnection();
        	st = (Statement) cn.createStatement();
			// La requete pour recuperer le nom , description , prix des plats
			// SELECT nom,description,prix FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='2' and T_idTarif = idtarif;		
			ResultSet result = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT nom,description,prix FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='2' and T_idTarif = idtarif");
			while (result.next()) {
				Plats plats = new Plats(result.getString("nom"),result.getString("description"),result.getFloat("prix"));
				listPlats.add(plats);
				System.out.println(plats.getNom());
				System.out.println(plats.getDescription());
				System.out.println(plats.getPrix());
				System.out.println(listPlats.get(0).getNom());
			}
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return listPlats;
	}
	
	// La requete pour recuperer le nom , description , prix des entrée
	// SELECT nom,description,prix FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='1' and T_idTarif = idtarif;
	
	// La requete pour recuperer le nom , description , prix des desserts
	// SELECT nom,description,prix FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='3' and T_idTarif = idtarif;
	
	// La requete pour recuperer le nom , description , prix des boissons
	// SELECT nom,description,prix FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='4' and T_idTarif = idtarif;
	
	public static String getNom() {
		return nom;
	}

	public static void setNom(String nom) {
		Plats.nom = nom;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		Plats.description = description;
	}

	public static float getPrix() {
		return prix;
	}

	public static void setPrix(float prix) {
		Plats.prix = prix;
	}
	
	public static void main(String[] args){
	try {
		GetAllPlats();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	};
	}

}
