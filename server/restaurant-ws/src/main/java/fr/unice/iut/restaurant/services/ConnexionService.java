package fr.unice.iut.restaurant.services;

import javax.ws.rs.POST;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Interface pour le service des connexions
 * @author Ismael
 * @version 1.0
 */
public interface ConnexionService {
	@POST
	@Path("/")
	Response Connexion(@FormParam("phone") String noTel, @FormParam("password") String password);
}