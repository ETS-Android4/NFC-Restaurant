package fr.unice.iut.restaurant.services;

import fr.unice.iut.restaurant.provider.Commande;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Path;
import javax.xml.ws.Response;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 13/04/2017.
 */

@Path("/commande")
public class CommandeImpl implements CommandeService {
// Json de la commande -> {"U_idUsers":"","T_idTables":,"Horodatage":"","details":["Entree":"","Plats":"","Dessert":"","Boisson":""]}

    @Override
    public Response insertCommande(String commande){
        String U_idUsers, Horodatage;
        int T_idTables;
        String details;
        try {
            JSONObject json = new JSONObject(commande);
            ArrayList<String> detailsCommande = new ArrayList<String>();
            U_idUsers = json.getString("U_idUsers");
            Horodatage = json.getString("Horodatage");
            T_idTables = json.getInt("T_idTables");
            // Je recuperer le tableau avec les details de la commande.
            JSONArray arrJson = new JSONArray();
            arrJson = json.getJSONArray("details");
            details = arrJson.getString(1)+" "+arrJson.getString(2)+" "+arrJson.getString(3)+" "+arrJson.getString(4);
            // if(true){
            Commande.create(new Commande(U_idUsers,Horodatage,T_idTables,details));
            return Response.status(201).entity(idCommande).build();;
        } catch (JSONException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
