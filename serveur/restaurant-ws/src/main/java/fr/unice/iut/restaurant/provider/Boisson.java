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

	private static String nom;
	private static String description;
	private static float prix;
	
	private Boisson(String nom,String description,float prix){
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}	
		
	public static ArrayList<Boisson> GetAllBoisson() throws SQLException{
        Connection cn = null;
        Statement st = null;
		ArrayList<Boisson> listBoissons = new ArrayList<Boisson>();
        try {
        	cn = BddConnexion.getConnection();
        	st = (Statement) cn.createStatement();
			ResultSet result = st.executeQuery("SELECT nom,description,prix FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='4' and T_idTarif = idtarif;");
			while (result.next()) {
				Boisson boissons = new Boisson(result.getString("nom"),result.getString("description"),result.getFloat("prix"));
				System.out.println(boissons.getNom());
			}
		
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Test
		for(Iterator<Boisson> i = listBoissons.iterator(); i.hasNext(); ) {
			Boisson item = i.next();
			System.out.println(item.getNom());
		}

		return listBoissons;
	}
		
	public static String getNom() {
		return nom;
	}

	public static void setNom(String nom) {
		Boisson.nom = nom;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		Boisson.description = description;
	}

	public static float getPrix() {
		return prix;
	}

	public static void setPrix(float prix) {
		Boisson.prix = prix;
	}

	
}
