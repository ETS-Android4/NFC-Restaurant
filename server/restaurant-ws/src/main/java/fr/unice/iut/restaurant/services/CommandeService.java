package fr.unice.iut.restaurant.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Utilisateur on 13/04/2017.
 */
public interface CommandeService {
    @POST
    @Path("/")
    Response insertCommande(@FormParam("order") String commande);

}
