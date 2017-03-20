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
		// Boucle 
		String json="";
		Plats objPlat = new Plats();
		for(int i = 0; i < objPlat.GetAllPlats().size(); i++){
			if(i == objPlat.GetAllPlats().size()-1){
				json += "{\"Nom\":\"" + objPlat.GetAllPlats().get(i).getNom() + "\",\"Description\":\""+objPlat.GetAllPlats().get(i).getDescription()+"\", \"Prix\":\""+objPlat.GetAllPlats().get(i).getPrix()+"\"}";	
				break;
			}
			json +="{\"Nom\":\"" + objPlat.GetAllPlats().get(i).getNom() + "\",\"Description\":\""+objPlat.GetAllPlats().get(i).getDescription()+"\", \"Prix\":\""+objPlat.GetAllPlats().get(i).getPrix()+"\"},";			
		}
		// Plat.GetAllPlats().size()<
		return Response.ok("{\"Plat\":["+json+"]}").build();
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
		String json="";
		Entree objEntree = new Entree();
		for(int i = 0; i < objEntree.GetAllEntree().size(); i++){
			if(i == objEntree.GetAllEntree().size()-1){
				json += "{\"Nom\":\"" + objEntree.GetAllEntree().get(i).getNom() + "\",\"Description\":\""+objEntree.GetAllEntree().get(i).getDescription()+"\", \"Prix\":\""+objEntree.GetAllEntree().get(i).getPrix()+"\"}";	
				break;
			}
			json +="{\"Nom\":\"" + objEntree.GetAllEntree().get(i).getNom() + "\",\"Description\":\""+objEntree.GetAllEntree().get(i).getDescription()+"\", \"Prix\":\""+objEntree.GetAllEntree().get(i).getPrix()+"\"},";			
		}
		// Entree.GetAllEntree().size()<
		return Response.ok("{\"Entree\":["+json+"]}").build();
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
		String json="";
		Dessert objDessert = new Dessert();
		for(int i = 0; i < objDessert.GetAllDessert().size(); i++){
			if(i == objDessert.GetAllDessert().size()-1){
				json += "{\"Nom\":\"" + objDessert.GetAllDessert().get(i).getNom() + "\",\"Description\":\""+objDessert.GetAllDessert().get(i).getDescription()+"\", \"Prix\":\""+objDessert.GetAllDessert().get(i).getPrix()+"\"}";	
				break;
			}
			json +="{\"Nom\":\"" + objDessert.GetAllDessert().get(i).getNom() + "\",\"Description\":\""+objDessert.GetAllDessert().get(i).getDescription()+"\", \"Prix\":\""+objDessert.GetAllDessert().get(i).getPrix()+"\"},";			
		}
		// Dessert.GetAllDessert().size()<
		return Response.ok("{\"Dessert\":["+json+"]}").build();
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
		String json="";
		Boisson objBoisson = new Boisson();
		// Boisson.GetAllBoisson().size();
		// JSONObject jo = new JSONObject();
		for(int i = 0; i < objBoisson.GetAllBoisson().size(); i++){
			if(i == objBoisson.GetAllBoisson().size()-1){
				json += "{\"Nom\":\"" + objBoisson.GetAllBoisson().get(i).getNom() + "\",\"Description\":\""+objBoisson.GetAllBoisson().get(i).getDescription()+"\", \"Prix\":\""+objBoisson.GetAllBoisson().get(i).getPrix()+"\"}";	
				break;
			}
			json +="{\"Nom\":\"" + objBoisson.GetAllBoisson().get(i).getNom() + "\",\"Description\":\""+objBoisson.GetAllBoisson().get(i).getDescription()+"\", \"Prix\":\""+objBoisson.GetAllBoisson().get(i).getPrix()+"\"},";
			
		}
		// Boisson.GetAllBoisson().size()<
		return Response.ok("{\"Boisson\":["+json+"]}").build();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
		}
		
	}

}
