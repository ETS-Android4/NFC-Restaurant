package fr.unice.iut.restaurant.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.unice.iut.restaurant.database.BddConnexion;

/**
 * Classe pour gérer les boissons
 * @author Ismael
 * @version 1.0
 */
public class Boisson {

	private String nom;
	private String description;
	private String url;
	private float prix;
	private int id;
	
	/**
     * Constructeur de base d'un objet Boisson
     * @param nom Nom d'un boisson
     * @param description Composants d'un boisson
     * @param url URL de l'image d'un boisson
     * @param prix Prix d'un boisson
     * @param id Identifiant d'un boisson
     */
	public Boisson(String nom, String description, String url, float prix, int id) {
		this.nom = nom;
		this.description = description;
		this.url = url;
		this.prix = prix;
		this.id = id;
	}

	/**
     * Constructeur vide d'un objet Boisson
     */
	public Boisson() { }

	/**
     * Récupérer le nom d'un boisson
     * @return un nom sous forme de chaîne de caractère
     */
	public String getNom() {
		return nom;
	}

	/**
     * Récupérer les composants d'un boisson
     * @return des composants sous forme de chaîne de caractère
     */
	public String getDescription() {
		return description;
	}

	/**
     * Récupérer l'URL de l'image d'un boisson
     * @return un URL sous forme de chaîne de caractère
     */
	public String getUrl() {
		return url;
	}

	/**
     * Récupérer le prix d'un boisson
     * @return un prix sous forme de réel
     */
	public float getPrix() {
		return prix;
	}

	/**
     * Récupérer l'identifiant d'un boisson
     * @return un identifiant sous forme d'entier
     */
	public int getId() {
		return id;
	}

	/**
     * Insérer le nom d'un boisson
     * @param nom Nom d'un boisson
     */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
     * Insérer les composants d'un boisson
     * @param description Les composants d'un boisson
     */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
     * Insérer l'URL de l'image d'un boisson
     * @param url URL de l'image d'un boisson
     */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
     * Insérer le prix d'un boisson
     * @param prix Prix d'un boisson
     */
	public void setPrix(float prix) {
		this.prix = prix;
	}

	/**
     * Récupérer la liste des boissons à partir de la base de données
     * @throws SQLException
     * @throws ClassNotFoundException
     * @return une liste d'objet Boisson
     */
	public ArrayList<Boisson> GetAllBoisson() throws SQLException {
        Connection cn = null;
        Statement st = null;
        String sql = "SELECT nom,description,url,prix,idPlat FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='4' and T_idTarif = idtarif;";
		ArrayList<Boisson> listBoissons = new ArrayList<Boisson>();
		try {
        	cn = BddConnexion.getConnection();
        	st = (Statement) cn.createStatement();
			ResultSet result = st.executeQuery(sql);
			while (result.next()) {
				Boisson boisson = new Boisson(result.getString("nom"),result.getString("description"),result.getString("url"),result.getFloat("prix"),result.getInt("idPlat"));
				listBoissons.add(boisson);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listBoissons;
	}
}