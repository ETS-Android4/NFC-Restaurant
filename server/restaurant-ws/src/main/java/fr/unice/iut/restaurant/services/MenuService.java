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

public interface MenuService {
	@GET
	@Path("/plat")
	@Produces(MediaType.APPLICATION_JSON)
	Response sendPlat() throws SQLException;
	
	@GET
	@Path("/entree")
	@Produces(MediaType.APPLICATION_JSON)
	Response sendEntree() throws SQLException, JSONException;
	
	@GET
	@Path("/dessert")
	@Produces(MediaType.APPLICATION_JSON)
	Response sendDessert() throws SQLException;
	
	@GET
	@Path("/boisson")
	@Produces(MediaType.APPLICATION_JSON)
	Response sendBoisson() throws SQLException;

	@GET
	@Path("/{target}")
	Response getChoice(@PathParam("target") String target) throws SQLException;

	/*
	@POST
	@Path("/")
	Plats.GetAllPlats();
	*/
}