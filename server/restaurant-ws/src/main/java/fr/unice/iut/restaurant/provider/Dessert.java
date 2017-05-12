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
public class Dessert {
	
	public   String nomDessert;
	public   String descriptionDessert;
	public   float prixDessert;
	public	 String url;
	
	public int getId() {
		return id;
	}

	public   int id;
	File monimage = new File("chemin");
	// FileOutputStream ostreamImage = new FileOutputStream(monimage);
	
	public Dessert(String nomDessert,String descrptionDessert,String url,float prixDessert, int id){
		this.nomDessert = nomDessert;
		this.prixDessert = prixDessert;
		this.descriptionDessert = descrptionDessert;
		this.id = id;
		this.url = url;
	}	
	
	public Dessert(){}
	
	public   ArrayList<Dessert> GetAllDessert() throws SQLException{
        Connection cn = null;
        Statement st = null;
        ArrayList<Dessert> listDesserts = new ArrayList<Dessert>();
        try {
        	System.out.println( "Connexion à la base de données..." );
        	cn = BddConnexion.getConnection();
        	st = (Statement) cn.createStatement();
			ResultSet result = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT nom,description,url,prix,idPlat FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='3' and T_idTarif = idtarif;");
			while (result.next()) {
				Dessert desserts = new Dessert(result.getString("nom"),result.getString("description"),result.getString("url"),result.getFloat("prix"),result.getInt("idPlat"));
				listDesserts.add(desserts);
				System.out.println(desserts.getNom());
				System.out.println(desserts.getDescription());
				System.out.println(desserts.getPrix());
				System.out.println(listDesserts.get(0).getNom());
			}
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listDesserts.size());
        return listDesserts;
	}
	
	
	public   String getNom() {
		return nomDessert;
	}

	public   void setNom(String nomDessert) {
		this.nomDessert = nomDessert;
	}

	public   String getDescription() {
		return descriptionDessert;
	}

	public   void setDescription(String descriptionDessert) {
		this.descriptionDessert = descriptionDessert;
	}

	public   float getPrix() {
		return prixDessert;
	}

	public   void setPrix(float prixDessert) {
		this.prixDessert = prixDessert;
	}

	public   String getUrl() {
		return url;
	}

	public   void setUrl(String url) {
		this.url = url;
	}
}
