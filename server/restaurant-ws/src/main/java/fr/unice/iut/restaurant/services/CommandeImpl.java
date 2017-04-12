package fr.unice.iut.restaurant.services;

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
public class CommandeImpl {
// Json de la commande -> "commande":[{"U_idUsers":"","T_idTables":,"Horodatage":"","details":["Entree":"","Plats":"","Dessert":"","Boisson":""]}]
/*
    @Override
    public Response insertCommande(String U_idUsers, int T_idTables, String Horodatage, String details){
        try {
            JSONArray arr = new JSONArray(details);
            ArrayList<String> detailsCommande = new ArrayList<String>();
            // ArrayList<Reponse> reponses = new ArrayList<Reponse>();
            for (int i = 0 ; i < arr.length(); i++){
                JSONObject row = arr.getJSONObject(i);
                detailsCommande.add(row.getString("commande"));
            }

            for (int id:ids) {
                reponses.add(ReponsesProvider.find(id));
            }

            if (ScoresProvider.create(new Score(UtilisateursProvider.find(idUtilisateur), reponses),idQuestion))
                return Response.status(Response.Status.CREATED).build();
            else
              return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (JSONException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
*/
}
