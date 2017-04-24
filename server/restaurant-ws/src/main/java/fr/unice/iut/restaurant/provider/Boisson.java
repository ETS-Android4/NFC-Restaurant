package fr.unice.iut.restaurant.provider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import fr.unice.iut.restaurant.database.BddConnexion;

/**
 * Created by Ismael 28/02/2017.
 */

public class Boisson {

	private String nom;
	private String description;
	private float prix;
	private int id;
	private String url;
	
	public Boisson(String nom, String description, String url, float prix, int id){
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.id = id;
		this.url = url;
	}

	public Boisson(){}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getId() {
		return id;
	}
	
	public   String getUrl() {
		return url;
	}

	public   void setUrl(String url) {
		this.url = url;
	}

	public ArrayList<Boisson> GetAllBoisson() throws SQLException{
        Connection cn = null;
        Statement st = null;
		ArrayList<Boisson> listBoissons = new ArrayList<Boisson>();
		try {
        	cn = BddConnexion.getConnection();
        	st = (Statement) cn.createStatement();
			ResultSet result = st.executeQuery("SELECT nom,description,url,prix,idPlat FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='4' and T_idTarif = idtarif;");
			while (result.next()) {
				Boisson boisson = new Boisson(result.getString("nom"),result.getString("description"),result.getString("url"),result.getFloat("prix"), result.getInt("idPlat"));
				listBoissons.add(boisson);
			} 
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listBoissons;
	}
		
		
	
}