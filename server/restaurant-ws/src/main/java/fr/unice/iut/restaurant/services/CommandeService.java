package fr.unice.iut.restaurant.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Utilisateur on 13/04/2017.
 */
public interface CommandeService {
    @POST
    @Path("/")
    Response insertCommande(@FormParam("U_idUsers") String U_idUsers, @FormParam("T_idTables") int T_idTables, @FormParam("Horodatage") String Horodatage, @FormParam("details") String details);

}
