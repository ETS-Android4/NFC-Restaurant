package fr.unice.iut.restaurant.provider;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Ismael 28/02/2017.
 */

public class Boisson {

	private static String nom;
	private static String description;
	private static float prix;
	
	private Boisson(){
		
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
