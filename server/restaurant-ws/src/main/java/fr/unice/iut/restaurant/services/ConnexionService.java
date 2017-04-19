package fr.unice.iut.restaurant.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import fr.unice.iut.restaurant.provider.*;
import org.codehaus.jettison.json.JSONException;

import java.sql.SQLException;

/**
 * Created by Ismael 28/02/2017.
 */

public interface ConnexionService {
	@POST
	@Path("/")
	Response Connexion(@FormParam("phone") String noTel, @FormParam("password") String password) throws SQLException;
	
}