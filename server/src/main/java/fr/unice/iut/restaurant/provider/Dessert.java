package fr.unice.iut.restaurant.provider;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Ismael 28/02/2017.
 */
public class Dessert {
	
	public static String nomDessert;
	public static String descriptionDessert;
	public static float prixDessert;
	File monimage = new File("chemin");
	// FileOutputStream ostreamImage = new FileOutputStream(monimage);
	
	private Dessert(String nomDessert,String descrptionDessert, float prixDessert){
		this.nomDessert = nomDessert;
		this.prixDessert = prixDessert;
		this.descriptionDessert = descrptionDessert;
	}	
	
	public static String getnomDessert() {
		return nomDessert;
	}

	public static void setnomDessert(String nomDessert) {
		Dessert.nomDessert = nomDessert;
	}

	public static String getdescriptionDessert() {
		return descriptionDessert;
	}

	public static void setdescriptionDessert(String descriptionDessert) {
		Dessert.descriptionDessert = descriptionDessert;
	}

	public static float getprixDessert() {
		return prixDessert;
	}

	public static void setprixDessert(float prixDessert) {
		Dessert.prixDessert = prixDessert;
	}

}
