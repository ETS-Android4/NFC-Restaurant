package fr.unice.iut.restaurant.services;

import javax.ws.rs.Path;
import java.util.ArrayList;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.eclipse.jetty.util.ajax.JSON;

import fr.unice.iut.restaurant.provider.*;
/**
 * Created by Ismael 28/02/2017.
 */

@Path("/menu")
public class MenuImpl implements MenuService{
	@Override
 public Response sendMenu() throws SQLException{
		try {
		System.out.println("à table");
		return Response.ok("[{\"Plats\":\"" + Plats.GetAllPlats().get(0).getNom() + "\"}]").build();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
		}
		
	}

}
