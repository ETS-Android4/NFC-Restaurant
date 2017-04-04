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

	private   String nom;
	private   String description;
	private   float prix;
	private   int id;
	private   File monimage = new File("chemin");
	// FileOutputStream ostreamImage = new FileOutputStream(monimage);
	
	public Entree(String nom,String description,float prix,int id){
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.id = id;
	}	
	
	public Entree(){}
	
	public ArrayList<Entree> GetAllEntree() throws SQLException{
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
	                ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT nom,description,prix,idPlat FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat='1' and T_idTarif = idtarif");
			while (result.next()) {
				Entree entree = new Entree(result.getString("nom"),result.getString("description"),result.getFloat("prix"),result.getInt("idPlat"));
				listEntree.add(entree);
				System.out.println(entree.getNom());
				System.out.println(entree.getDescription());
				System.out.println(entree.getPrix());
			}
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println(listEntree.size());
	return listEntree;
	}
	
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

	public int getId() {
		return id;
	}

	public   void setPrix(float prix) {
		this.prix = prix;
	}

	
}
