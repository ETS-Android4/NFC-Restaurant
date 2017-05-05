package fr.unice.iut.restaurant.services;

import fr.unice.iut.restaurant.provider.Commande;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 13/04/2017.
 */

@Path("/commande")
public class CommandeImpl implements CommandeService {
// Json de la commande -> {"U_idUsers":"","T_idTables":,"Horodatage":"","details":[e1, e2, e3]}

    @Override
    public javax.ws.rs.core.Response insertCommande(String commande){
        String U_idUsers, Horodatage, T_idTables, details;
        try {
            JSONObject json = new JSONObject(commande);
            ArrayList<String> detailsCommande = new ArrayList<String>();
            U_idUsers = json.getString("U_idUsers");
            Horodatage = json.getString("Horodatage");
            T_idTables = json.getString("T_idTables");
            details = json.getString("details");
            System.out.println("USER " + U_idUsers);
            System.out.println("HORO " + Horodatage);
            System.out.println("GUID " + T_idTables);
            System.out.println("COMMAND " + details);
            // Je recuperer le tableau avec les details de la commande.
            // int idCommande = Commande.create(new Commande(U_idUsers,Horodatage,T_idTables,details));
            return Response.status(201).build();
        } catch (JSONException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
