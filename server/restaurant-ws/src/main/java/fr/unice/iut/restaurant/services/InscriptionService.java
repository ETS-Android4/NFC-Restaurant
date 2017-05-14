package fr.unice.iut.restaurant.services;

import javax.ws.rs.POST;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Interface pour le service des inscriptions
 * @author Ismael
 * @version 1.0
 */
public interface InscriptionService {
	@POST
	@Path("/")
    Response insertClient(@FormParam("firstName") String nom, @FormParam("lastName") String prenom,
                          @FormParam("phone") String tel, @FormParam("password") String mdp);

    @POST
	@Path("/tag")
    Response insertTag(@FormParam("table") String table, @FormParam("guid") String guid);
}