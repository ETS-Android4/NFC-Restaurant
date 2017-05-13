package fr.unice.iut.restaurant.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.unice.iut.restaurant.database.BddConnexion;

/**
 * Classe pour gérer les plats
 * @author Ismael
 * @version 1.0
 */
public class Plats {

	private String nom;
	private String description;
	private float prix;
	private int id;
	private String url;
	
	/**
     * Constructeur de base d'un objet Plats
     * @param nom Nom d'un plat
     * @param description Composants d'un plat
     * @param url URL de l'image d'un plat
     * @param prix Prix d'un plat
     * @param id Identifiant d'un plat
     */
	public Plats(String nom, String description, String url, float prix, int id) {
		this.nom = nom;
		this.description = description;
		this.url = url;
		this.prix = prix;
		this.id = id;
	}
	
	/**
     * Constructeur vide d'un objet Plats
     */
	public Plats() { }

	/**
     * Récupérer le nom d'un plat
     * @return un nom sous forme de chaîne de caractère
     */
	public String getNom() {
		return nom;
	}

	/**
     * Récupérer les composants d'un plat
     * @return des composants sous forme de chaîne de caractère
     */
	public String getDescription() {
		return description;
	}

	/**
     * Récupérer l'URL de l'image d'un plat
     * @return un URL sous forme de chaîne de caractère
     */
	public String getUrl() {
		return url;
	}

	/**
     * Récupérer le prix d'un plat
     * @return un prix sous forme de réel
     */
	public float getPrix() {
		return prix;
	}

	/**
     * Récupérer l'identifiant d'un plat
     * @return un identifiant sous forme d'entier
     */
	public int getId() {
		return id;
	}

	/**
     * Insérer le nom d'un plat
     * @param nom Nom d'un plat
     */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
     * Insérer les composants d'un plat
     * @param description Les composants d'un plat
     */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
     * Insérer l'URL de l'image d'un plat
     * @param url URL de l'image d'un plat
     */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
     * Insérer le prix d'un plat
     * @param prix Prix d'un plat
     */
	public void setPrix(float prix) {
		this.prix = prix;
	}

	/**
     * Récupérer la liste des plats à partir de la base de données
     * @throws SQLException
     * @throws ClassNotFoundException
     * @return une liste d'objet Plats
     */
	public ArrayList<Plats> GetAllPlats() throws SQLException {
        Connection cn = null;
        Statement st = null;
        String sql = "SELECT nom,description,url,prix,idPlat FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='2' and T_idTarif = idtarif";
        ArrayList<Plats> listPlats = new ArrayList<Plats>();
        try {
        	cn = BddConnexion.getConnection();
        	st = (Statement) cn.createStatement();	
			ResultSet result = st.executeQuery(sql);
			while (result.next()) {
				Plats plats = new Plats(result.getString("nom"),result.getString("description"),result.getString("url"),result.getFloat("prix"),result.getInt("idPlat"));
				listPlats.add(plats);
			}
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listPlats;
	}
}