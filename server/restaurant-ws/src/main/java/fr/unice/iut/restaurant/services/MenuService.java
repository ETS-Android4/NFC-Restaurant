package fr.unice.iut.restaurant.services;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;

/**
 * Interface pour le service des menus
 * @author Ismael
 * @version 1.0
 */
public interface MenuService {
	@GET
	@Path("/{target}")
	Response getChoice(@PathParam("target") String target);

	@GET
	@Path("/plat")
	@Produces(MediaType.APPLICATION_JSON)
	Response sendPlat() throws SQLException;
	
	@GET
	@Path("/entree")
	@Produces(MediaType.APPLICATION_JSON)
	Response sendEntree() throws SQLException;
	
	@GET
	@Path("/dessert")
	@Produces(MediaType.APPLICATION_JSON)
	Response sendDessert() throws SQLException;
	
	@GET
	@Path("/boisson")
	@Produces(MediaType.APPLICATION_JSON)
	Response sendBoisson() throws SQLException;
}