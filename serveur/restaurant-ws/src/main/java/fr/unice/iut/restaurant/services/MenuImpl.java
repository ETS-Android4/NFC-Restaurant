// http://localhost:8080/restaurant-ws/rest/menu/plats
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
 public Response sendPlat() throws SQLException{
		try {
		System.out.println("à table");
		System.out.println(Plats.GetAllPlats().get(0).getNom());
		// Boucle 
		return Response.ok("{\"Plats\":[{\"Nom\":\"" + Plats.GetAllPlats().get(0).getNom() + "\",\"Description\":\""+Plats.GetAllPlats().get(0).getDescription()+"\", \"Prix\":\""+Plats.GetAllPlats().get(0).getPrix()+"\"}]}").build();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
		}
		
	}
	
	@Override
 public Response sendEntree() throws SQLException{
		try {
		System.out.println("à table");
		// Boucle 
		return Response.ok("{\"Entree\":[{\"Nom\":\"" + Entree.GetAllEntree().get(0).getNom() + "\",\"Description\":\""+Entree.GetAllEntree().get(0).getDescription()+"\", \"Prix\":\""+Entree.GetAllEntree().get(0).getPrix()+"\"}]}").build();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
		}
		
	}
	
	@Override
 public Response sendDessert() throws SQLException{
		try {
		System.out.println("à table");
		// Boucle 
		return Response.ok("{\"Dessert\":[{\"Nom\":\"" + Dessert.GetAllDessert().get(0).getNom() + "\",\"Description\":\""+Dessert.GetAllDessert().get(0).getDescription()+"\", \"Prix\":\""+Dessert.GetAllDessert().get(0).getPrix()+"\"}]}").build();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
		}
		
	}
	
	@Override
 public Response sendBoisson() throws SQLException{
		try {
		System.out.println("à table");
		// Boucle 
		return Response.ok("{\"Boisson\":[{\"Nom\":\"" + Boisson.GetAllBoisson().get(0).getNom() + "\",\"Description\":\""+Boisson.GetAllBoisson().get(0).getDescription()+"\", \"Prix\":\""+Boisson.GetAllBoisson().get(0).getPrix()+"\"}]}").build();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
		}
		
	}

}
