package fr.unice.iut.restaurant.services;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import fr.unice.iut.restaurant.provider.Commande;

/**
 * Classe pour traiter les commandes
 * @author Ismael
 * @version 1.0
 */
@Path("/commande")
public class CommandeImpl implements CommandeService {
    /**
     * Récupérer un objet Commande qui contient les informations sur les demandes d'un utilisateur et l'insérer dans une base de données
     * @param commande La commande faite par l'utilisateur
     * @thorws JSONException
     * @throws Exception
     * @return une réponse 201 en cas de réussite ou une réponse 400 si une exception est levée au niveau de la récupération des identifiants
     * ou une réponse 404 si une exception est levée au niveau du json ou une réponse 500 sinon
     */
    @Override
    public Response insertCommande(String commande) {
        String U_idUsers, Horodatage, T_idTables, details;
        int idUser, idTable;
        try {
            JSONObject json = new JSONObject(commande);
            U_idUsers = json.getString("U_idUsers");
            Horodatage = json.getString("Horodatage");
            T_idTables = json.getString("T_idTables");
            details = json.getString("details");
			try {
            	idUser = Commande.GetIdUser(U_idUsers);
                idTable = Commande.GetIdTable(T_idTables);
			} catch (Exception e) {
            	e.printStackTrace();
				return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
			}
            Commande.create(new Commande(idUser,Horodatage,idTable,details));
            return Response.status(201).build();
        } catch (JSONException e) {
            e.printStackTrace();
			return Response.status(404).entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    /**
     * Créer un objet Commande et l'insérer dans un JSONArray
     * @thorws JSONException
     * @throws SQLException
     * @return une réponse 200 avec un JSONArray qui contient la commande ou une réponse 500 sinon
     */
    @Override
    public Response getCommande() throws JSONException {
    	try {
            JSONArray arrayJson = new JSONArray();
    		Commande objCommande = new Commande();
    		for (int i=0; i<objCommande.showCommande().size(); i++) {
                JSONObject obj = new JSONObject();
    			objCommande = new Commande(objCommande.showCommande().get(i).getT_idTables(),objCommande.showCommande().get(i).getHorodatage(),objCommande.showCommande().get(i).getDetails(),objCommande.showCommande().get(i).getIdCommande());
    			obj.put("Table",objCommande.getT_idTables());
    			obj.put("Date",objCommande.getHorodatage());
    			obj.put("Commande",objCommande.getDetails());
    			obj.put("Id",objCommande.getIdCommande());
    			arrayJson.put(obj);
    		}
    		return Response.ok(arrayJson).build();
        } catch (SQLException e) {
    		e.printStackTrace();
			return Response.status(500).build();
   		}
 	}
    /**
     * Supprimer un objet Commande en utilisant son identifiant
     * @param idCommande Identifiant de la commande
     * @thorws Exception
     * @throws SQLException
     * @return une réponse 200 en cas de réussite ou une réponse 500 sinon
     */
    @Override
    public Response delCommande(int idCommande) throws Exception {
	    try {
	    	Commande.delete(idCommande);
	    	return Response.ok().build();
	    } catch (SQLException e) {
			e.printStackTrace();
			return Response.status(500).build();
	    }
    }
}