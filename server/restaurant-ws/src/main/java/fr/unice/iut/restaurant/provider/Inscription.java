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
    private String nom,prenom,password;
    private int noTel;

    public Inscription(String nom,String prenom, String password, int noTel){
        this.nom = nom;
        this.prenom = prenom;
        this.noTel = noTel;
        this.password = password;
    }

    public static int InsertUser(Inscription obj){
        Connection cn = null;
        Statement st = null;
        ResultSet resultSet = null;
        int ret=0;
        try {
            cn = BddConnexion.getConnection();
            String sql = "INSERT INTO users (nom, prenom, password, noTel) VALUES ('"+obj.getNom()+"','"+obj.getPrenom()+"', '"
                    +obj.getPassword()+"', '"+obj.getNoTel()+"') returning idUsers;";
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultSet = st.executeQuery(sql);

            if (resultSet.first()){
                ret = resultSet.getInt(1);
            }
            //deconnexion avec la db
            //liberer la memoire
            cn.close();
            st.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return ret;
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

    public int getNoTel() {
        return noTel;
    }

    public void setNoTel(int noTel) {
        this.noTel = noTel;
    }


}
