package fr.unice.iut.restaurant.provider;

import fr.unice.iut.restaurant.database.BddConnexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Utilisateur on 17/04/2017.
 */
public class Inscription {
    private String nom,prenom,password, noTel;

    public Inscription(String nom,String prenom, String noTel, String password){
        this.nom = nom;
        this.prenom = prenom;
        this.noTel = noTel;
        this.password = password;
    }

    public Inscription(String noTel, String password){
        this.noTel = noTel;
        this.password = password;
    }

    public static void InsertUser(Inscription obj){
        Connection cn = null;
        Statement st = null;
        int result;
        try {
            cn = BddConnexion.getConnection();
            String sql = "INSERT INTO users (nom, prenom, noTel, password) VALUES ('"+obj.getNom()+"','"+obj.getPrenom()+"', '"
                    +obj.getNoTel()+"', '"+obj.getPassword()+"');";
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            result = st.executeUpdate(sql);
            //deconnexion avec la db
            //liberer la memoire
            /*
            st.close();
            cn.close();
            */
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int checkConnexion(Inscription obj){
        Connection cn = null;
        Statement st = null;
        ResultSet result;
        try {
            cn = BddConnexion.getConnection();
            String sql = "SELECT COUNT(idUsers) FROM users WHERE noTel ='"+obj.getNoTel()+"' AND password='"+obj.getPassword()+"';";
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            result = st.executeQuery(sql);
            result.next();
            if(result.getInt(1)>0) return 1;
            /*
            st.close();
            cn.close();
            */
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }


}