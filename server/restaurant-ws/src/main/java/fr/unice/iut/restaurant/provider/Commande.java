package fr.unice.iut.restaurant.provider;

import fr.unice.iut.restaurant.database.BddConnexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Utilisateur on 13/04/2017.
 */
public class Commande {
    String U_idUsers, Horodatage, details;
    int T_idTables;

    public Commande(String U_idUsers,String Horodatage, int T_idTables, String details){
        this.U_idUsers = U_idUsers;
        this.Horodatage = Horodatage;
        this.details = details;
        this.T_idTables = T_idTables;
    }

    public String getU_idUsers() {
        return U_idUsers;
    }

    public void setU_idUsers(String u_idUsers) {
        U_idUsers = u_idUsers;
    }

    public String getHorodatage() {
        return Horodatage;
    }

    public void setHorodatage(String horodatage) {
        Horodatage = horodatage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getT_idTables() {
        return T_idTables;
    }

    public void setT_idTables(int t_idTables) {
        T_idTables = t_idTables;
    }

    public static int create(Commande obj) throws SQLException {
        Connection cn = null;
        Statement st = null;
        ResultSet resultSet = null;

        try {
            cn = BddConnexion.getConnection();
            String sql = "INSERT INTO `commande` (`Horodatage`,`T_idTables`,`U_idUsers`,`details`) VALUES ('"+obj.getHorodatage()+"'," +
                    "'"+obj.getT_idTables()+"','"+obj.getU_idUsers()+"','"+obj.getDetails()+"') returning idCommande;";
            // st = (Statement) cn.createStatement();
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultSet = st.executeQuery(sql);
            int ret;
            if (resultSet.first()){
                ret = resultSet.getInt(1);
            }
            //deconnexion avec la db
            //liberer la memoire
            cn.close();
            st.close();
            return ret;
        }catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
