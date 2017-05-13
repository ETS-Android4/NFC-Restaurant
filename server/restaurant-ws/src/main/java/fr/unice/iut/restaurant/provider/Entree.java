package fr.unice.iut.restaurant.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.unice.iut.restaurant.database.BddConnexion;

/**
 * Classe pour gérer les entrées
 * @author Ismael
 * @version 1.0
 */
public class Entree {

	private String nom;
	private String description;
	private float prix;
	private int id;
	private String url;
	
	/**
     * Constructeur de base d'un objet Entree
     * @param nom Nom d'une entrée
     * @param description Composants d'une entrée
     * @param url URL de l'image d'une entrée
     * @param prix Prix d'une entrée
     * @param id Identifiant d'une entrée
     */
	public Entree(String nom, String description, String url, float prix, int id) {
		this.nom = nom;
		this.description = description;
		this.url = url;
		this.prix = prix;
		this.id = id;
	}	
	
	/**
     * Constructeur vide d'un objet Entree
     */
	public Entree() { }

	/**
     * Récupérer le nom d'une entrée
     * @return un nom sous forme de chaîne de caractère
     */
	public String getNom() {
		return nom;
	}

	/**
     * Récupérer les composants d'une entrée
     * @return des composants sous forme de chaîne de caractère
     */
	public String getDescription() {
		return description;
	}

	/**
     * Récupérer l'URL de l'image d'une entrée
     * @return un URL sous forme de chaîne de caractère
     */
	public String getUrl() {
		return url;
	}

	/**
     * Récupérer le prix d'une entrée
     * @return un prix sous forme de réel
     */
	public float getPrix() {
		return prix;
	}

	/**
     * Récupérer l'identifiant d'une entrée
     * @return un identifiant sous forme d'entier
     */
	public int getId() {
		return id;
	}

	/**
     * Insérer le nom d'une entrée
     * @param nom Nom d'une entrée
     */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
     * Insérer les composants d'une entrée
     * @param description Les composants d'une entrée
     */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
     * Insérer l'URL de l'image d'une entrée
     * @param url URL de l'image d'une entrée
     */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
     * Insérer le prix d'une entrée
     * @param prix Prix d'une entrée
     */
	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	/**
     * Récupérer la liste des entrées à partir de la base de données
     * @throws SQLException
     * @throws ClassNotFoundException
     * @return une liste d'objet Entree
     */
	public ArrayList<Entree> GetAllEntree() throws SQLException{
        Connection cn = null;
        Statement st = null;
        String sql = "SELECT nom,description,url,prix,idPlat FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat='1' and T_idTarif = idtarif";
        ArrayList<Entree> listEntree = new ArrayList<Entree>();
        try {
        	cn = BddConnexion.getConnection();
        	st = (Statement) cn.createStatement();	
			ResultSet result = st.executeQuery(sql);
			while (result.next()) {
				Entree entree = new Entree(result.getString("nom"),result.getString("description"),result.getString("url"),result.getFloat("prix"),result.getInt("idPlat"));
				listEntree.add(entree);
			}
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	return listEntree;
	}
}