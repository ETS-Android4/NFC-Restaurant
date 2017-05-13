package fr.unice.iut.restaurant.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.unice.iut.restaurant.database.BddConnexion;

/**
 * Classe pour gérer les desserts
 * @author Ismael
 * @version 1.0
 */
public class Dessert {
	
	public String nomDessert;
	public String descriptionDessert;
	public String url;
	public float prixDessert;
	public int id;

	/**
     * Constructeur de base d'un objet Dessert
     * @param nomDessert Nom d'un dessert
     * @param descrptionDessert Composants d'un dessert
     * @param url URL de l'image d'un dessert
     * @param prixDessert Prix d'un dessert
     * @param id Identifiant d'un dessert
     */
	public Dessert(String nomDessert, String descrptionDessert, String url, float prixDessert, int id) {
		this.nomDessert = nomDessert;
		this.descriptionDessert = descrptionDessert;
		this.url = url;
		this.prixDessert = prixDessert;
		this.id = id;
	}	
	
	/**
     * Constructeur vide d'un objet Dessert
     */
	public Dessert() { }
	
	/**
     * Récupérer le nom d'un dessert
     * @return un nom sous forme de chaîne de caractère
     */
	public String getNom() {
		return nomDessert;
	}

	/**
     * Récupérer les composants d'un dessert
     * @return des composants sous forme de chaîne de caractère
     */
	public String getDescription() {
		return descriptionDessert;
	}

	/**
     * Récupérer l'URL de l'image d'un dessert
     * @return un URL sous forme de chaîne de caractère
     */
	public String getUrl() {
		return url;
	}

	/**
     * Récupérer le prix d'un dessert
     * @return un prix sous forme de réel
     */
	public float getPrix() {
		return prixDessert;
	}

	/**
     * Récupérer l'identifiant d'un dessert
     * @return un identifiant sous forme d'entier
     */
	public int getId() {
		return id;
	}

	/**
     * Insérer le nom d'un dessert
     * @param nomDessert Nom d'un dessert
     */
	public void setNom(String nomDessert) {
		this.nomDessert = nomDessert;
	}

	/**
     * Insérer les composants d'un dessert
     * @param descriptionDessert Les composants d'un dessert
     */
	public void setDescription(String descriptionDessert) {
		this.descriptionDessert = descriptionDessert;
	}

	/**
     * Insérer l'URL de l'image d'un dessert
     * @param url URL de l'image d'un dessert
     */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
     * Insérer le prix d'un dessert
     * @param prixDessert Prix d'un dessert
     */
	public void setPrix(float prixDessert) {
		this.prixDessert = prixDessert;
	}
	
	/**
     * Récupérer la liste des desserts à partir de la base de données
     * @throws SQLException
     * @throws ClassNotFoundException
     * @return une liste d'objet Dessert
     */
	public ArrayList<Dessert> GetAllDessert() throws SQLException{
        Connection cn = null;
        Statement st = null;
        String sql = "SELECT nom,description,url,prix,idPlat FROM nfc_resto.plats,nfc_resto.tarif WHERE idType_Plat ='3' and T_idTarif = idtarif;";
        ArrayList<Dessert> listDesserts = new ArrayList<Dessert>();
        try {
        	cn = BddConnexion.getConnection();
        	st = (Statement) cn.createStatement();
			ResultSet result = st.executeQuery(sql);
			while (result.next()) {
				Dessert desserts = new Dessert(result.getString("nom"),result.getString("description"),result.getString("url"),result.getFloat("prix"),result.getInt("idPlat"));
				listDesserts.add(desserts);
			}
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return listDesserts;
	}
}