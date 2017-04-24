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

@Path("/menu")
public class MenuImpl implements MenuService{
	@Override
	public Response getChoice(String target){
		String choix = target;
		try{
			if (choix.equals("entree")) {
				// sendEntree();
			} else if (choix.equals("plat")) {
				sendPlat();
			} else if (choix.equals("dessert")) {
				sendDessert();
			} else if (choix.equals("boisson")) {
				sendBoisson();
			} else {
				System.out.println("Erreur envoie");
				return Response.status(500).build();
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return Response.status(200).build();
	}

	@Override
 	public Response sendPlat() throws SQLException{
		try {
		System.out.println("à table");
		// Boucle 
		String json="";
		Plats objPlat = new Plats();
		JSONArray objJsonArray = new JSONArray();
		for(int i = 0; i < objPlat.GetAllPlats().size(); i++){
			JSONObject objJson = new JSONObject();
			objJson.put("Id",objPlat.GetAllPlats().get(i).getId());
			objJson.put("Nom",objPlat.GetAllPlats().get(i).getNom());
			objJson.put("Description",objPlat.GetAllPlats().get(i).getDescription());
			objJson.put("Prix",objPlat.GetAllPlats().get(i).getPrix());
			objJson.put("Url",objPlat.GetAllPlats().get(i).getUrl());
			objJsonArray.put(objJson);
		}
		// Plat.GetAllPlats().size()<
		return Response.ok(objJsonArray).build();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}
	
	@Override
 	public Response sendEntree() throws SQLException, JSONException{
		try {
		System.out.println("à table");
		Entree objEntree = new Entree();
		String json ="";
		//  JSONObject obj = new JSONObject();
		JSONArray arrayJson = new JSONArray();
		for(int i = 0; i < objEntree.GetAllEntree().size(); i++){
			JSONObject obj = new JSONObject();
			obj.put("Id",objEntree.GetAllEntree().get(i).getId());
			obj.put("Nom",objEntree.GetAllEntree().get(i).getNom());
			obj.put("Description",objEntree.GetAllEntree().get(i).getDescription());
			obj.put("Prix",objEntree.GetAllEntree().get(i).getPrix());
			obj.put("Url",objEntree.GetAllEntree().get(i).getUrl());
			arrayJson.put(obj);
			// json +="{\"Id\":\""+objEntree.GetAllEntree().get(i).getId()+"\",\"Nom\":\"" + objEntree.GetAllEntree().get(i).getNom() + "\",\"Description\":\""+objEntree.GetAllEntree().get(i).getDescription()+"\", \"Prix\":\""+objEntree.GetAllEntree().get(i).getPrix()+"\"},";
		}
		return Response.ok(arrayJson).build();
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

			JSONArray objJsonArray = new JSONArray();
			for(int i = 0; i < objDessert.GetAllDessert().size(); i++){
				JSONObject objJson = new JSONObject();
				objJson.put("Id",objDessert.GetAllDessert().get(i).getId());
				objJson.put("Nom",objDessert.GetAllDessert().get(i).getNom());
				objJson.put("Description",objDessert.GetAllDessert().get(i).getDescription());
				objJson.put("Prix",objDessert.GetAllDessert().get(i).getPrix());
				objJson.put("Url",objDessert.GetAllDessert().get(i).getUrl());
				objJsonArray.put(objJson);
			}
			// Plat.GetAllPlats().size()<
			return Response.ok(objJsonArray).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
		} catch (JSONException e) {
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
			JSONArray objJsonArray = new JSONArray();
			for(int i = 0; i < objBoisson.GetAllBoisson().size(); i++){
				JSONObject objJson = new JSONObject();
				objJson.put("Id",objBoisson.GetAllBoisson().get(i).getId());
				objJson.put("Nom",objBoisson.GetAllBoisson().get(i).getNom());
				objJson.put("Description",objBoisson.GetAllBoisson().get(i).getDescription());
				objJson.put("Prix",objBoisson.GetAllBoisson().get(i).getPrix());
				objJson.put("Url",objBoisson.GetAllBoisson().get(i).getUrl());
				objJsonArray.put(objJson);
			}
			// Plat.GetAllPlats().size()<
			return Response.ok(objJsonArray).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
		
	}
}
