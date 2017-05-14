package fr.unice.iut.restaurant.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.unice.iut.restaurant.database.BddConnexion;

/**
 * Classe pour gérer les inscriptions
 * @author Ismael
 * @version 1.0
 */
public class Inscription {

    private String nom;
    private String prenom;
    private String noTel;
    private String password;

    /**
     * Constructeur d'un objet Inscription
     * @param nom Nom de l'utilisateur
     * @param prenom Prénom de l'utilisateur
     * @param noTel Numéro de téléphone de l'utilisateur
     * @param password Mot de passe de l'utilisateur
     */
    public Inscription(String nom, String prenom, String noTel, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.noTel = noTel;
        this.password = password;
    }

    /**
     * Constructeur d'un objet Inscription
     * @param noTel Numéro de téléphone de l'utilisateur
     * @param password Mot de passe de l'utilisateur
     */
    public Inscription(String noTel, String password) {
        this.noTel = noTel;
        this.password = password;
    }

    /**
     * Récupérer le nom d'un utilisateur
     * @return un nom sous forme de chaîne de caractère
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupérer le prénom d'un utilisateur
     * @return un prénom sous forme de chaîne de caractère
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Récupérer le téléphone d'un utilisateur
     * @return un téléphone sous forme de chaîne de caractère
     */
    public String getNoTel() {
        return noTel;
    }

    /**
     * Récupérer le mot de passe d'un utilisateur
     * @return un mot de passe sous forme de chaîne de caractère
     */
    public String getPassword() {
        return password;
    }

    /**
     * Insérer le nom d'un utilisateur
     * @param nom Nom d'un utilisateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Insérer le prénom d'un utilisateur
     * @param prenom Prénom d'un utilisateur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Insérer le téléphone d'un utilisateur
     * @param noTel Téléphone d'un utilisateur
     */
    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    /**
     * Insérer le mot de passe d'un utilisateur
     * @param password Mot de passe d'un utilisateur
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Insérer un utilisateur dans la base de données
     * @param obj Contient toutes les informations dont on a besoin sur un utilisateur
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void InsertUser(Inscription obj) {
        Connection cn = null;
        Statement st = null;
        String sql = "INSERT INTO users (nom, prenom, noTel, password) VALUES ('"+obj.getNom()+"','"+obj.getPrenom()+"', '"
                    +obj.getNoTel()+"', '"+obj.getPassword()+"');";
        try {
            cn = BddConnexion.getConnection();
            st = cn.createStatement();
            int result = st.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vérifier l'existance d'un utilisateur dans la base de données
     * @param obj Contient toutes les informations dont on a besoin sur un utilisateur
     * @throws SQLException
     * @throws ClassNotFoundException
     * @return 1 si un utilisateur existe dans la base de données ou 0 sinon
     */
    public static int checkConnexion(Inscription obj){
        Connection cn = null;
        Statement st = null;
        String sql = "SELECT COUNT(idUsers) FROM users WHERE noTel ='"+obj.getNoTel()+"' AND password='"+obj.getPassword()+"';";
        try {
            cn = BddConnexion.getConnection();
            st = cn.createStatement();
            ResultSet result = st.executeQuery(sql);
            result.next();
            if(result.getInt(1) > 0) return 1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Insérer un numréro de table avec un GUID d'un TAG dans la base données
     * @param table Numéro d'une table
     * @param guid GUID d'un TAG
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void InsertTag(String table, String guid) {
        Connection cn = null;
        Statement st = null;
        String sql = "INSERT INTO tables (idTables, guid) VALUES ('"+table+"','"+guid+"');";
        try {
            cn = BddConnexion.getConnection();
            st = cn.createStatement();
            int result = st.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}