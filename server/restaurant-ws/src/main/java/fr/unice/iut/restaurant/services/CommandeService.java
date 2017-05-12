package fr.unice.iut.restaurant.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

/**
 * Created by Utilisateur on 13/04/2017.
 */
public interface CommandeService {
    @POST
    @Path("/")
    Response insertCommande(@FormParam("order") String commande);
    
    @GET
    @Path("/recup")
    @Produces(MediaType.APPLICATION_JSON)
    Response getCommande() throws JSONException;
    
    @DELETE
    @Path("/{idCommande}")
    Response delCommande(@FormParam("idCommande") int idCommande) throws JSONException, Exception;
    
    
}
