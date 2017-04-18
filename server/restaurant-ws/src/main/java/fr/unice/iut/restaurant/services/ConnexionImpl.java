// http://localhost:8080/restaurant-ws/rest/menu/plats
package fr.unice.iut.restaurant.services;

import javax.ws.rs.Path;
import java.util.ArrayList;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import fr.unice.iut.restaurant.provider.*;
/**
 * Created by Ismael 28/02/2017.
 */

@Path("/connexion")
public class ConnexionImpl implements ConnexionService{
	@Override
	public Response Connexion(String password, String noTel)throws SQLException{
		try{
			return Response.status(201).entity("connexion success").build();
		}catch (Exception e){
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
	}
}
