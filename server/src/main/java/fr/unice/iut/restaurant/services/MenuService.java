package fr.unice.iut.restaurant.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import fr.unice.iut.restaurant.provider.*;
import java.sql.SQLException;

/**
 * Created by Ismael 28/02/2017.
 */

public interface MenuService {
	@POST
	@Path("/plats")
	@Produces(MediaType.APPLICATION_JSON)
	Response sendMenu() throws SQLException;
	/*
	@POST
	@Path("/")
	Plats.GetAllPlats();
	*/
}