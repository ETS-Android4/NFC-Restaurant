package fr.unice.iut.restaurant.provider;

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

}
