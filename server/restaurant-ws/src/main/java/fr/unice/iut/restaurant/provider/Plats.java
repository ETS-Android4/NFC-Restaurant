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

	private   String nom;
	private   String description;
	private   float prix;
	private   int id;
	private   File monimage = new File("chemin");
	// FileOutputStream ostreamImage = new FileOutputStream(monimage);
	
	public Plats(String nom,String description,float prix,int id) throws ClassNotFoundException {
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.id = id;
	}
	
	public Plats(){}
	
	public  ArrayList<Plats> GetAllPlats() throws SQLException{
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
	                ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT nom,description,prix,idPlat FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='2' and T_idTarif = idtarif");
			while (result.next()) {
				Plats plats = new Plats(result.getString("nom"),result.getString("description"),result.getFloat("prix"), result.getInt("idPlat"));
				listPlats.add(plats);
			}
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println(listPlats.size());
	return listPlats;
	}
	
	// La requete pour recuperer le nom , description , prix des entrée
	// SELECT nom,description,prix FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='1' and T_idTarif = idtarif;
	
	// La requete pour recuperer le nom , description , prix des desserts
	// SELECT nom,description,prix FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='3' and T_idTarif = idtarif;
	
	// La requete pour recuperer le nom , description , prix des boissons
	// SELECT nom,description,prix FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='4' and T_idTarif = idtarif;
	
	public   String getNom() {
		return nom;
	}

	public   void setNom(String nom) {
		this.nom = nom;
	}

	public   String getDescription() {
		return description;
	}

	public   void setDescription(String description) {
		this.description = description;
	}

	public   float getPrix() {
		return prix;
	}

	public   void setPrix(float prix) {
		this.prix = prix;
	}

	public int getId() {
		return id;
	}

	public   void main(String[] args){
	try {
		GetAllPlats();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	};
	}

}
