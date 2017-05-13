package fr.unice.iut.restaurant.services;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import fr.unice.iut.restaurant.provider.*;

/**
 * Classe pour traiter l'affichage des menus
 * @author Ismael
 * @version 1.0
 */
@Path("/menu")
public class MenuImpl implements MenuService {
	/**
     * Lancer la bonne fonction selon le choix de l'utilisateur (s'il veut visualiser les entrées, les plats, les desserts ou les boissons)
     * @param target Choix de l'utilisateur
     * @thorws SQLException
     * @return une réponse 200 en cas de réussite ou une réponse 400 si le choix ne correspond pas ou une réponse 500 sinon
     */
	@Override
	public Response getChoice(String target) {
		try {
			if (target.equals("entree")) {
				sendEntree();
			} else if (target.equals("plat")) {
				sendPlat();
			} else if (target.equals("dessert")) {
				sendDessert();
			} else if (target.equals("boisson")) {
				sendBoisson();
			} else {
				return Response.status(400).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
		return Response.status(200).build();
	}
	/**
     * Mettre les plats dans un json
     * @thorws JSONException
     * @throws SQLException
     * @return une réponse 200 avec un JSONArray qui contient les plats ou une réponse 500 sinon
     */
	@Override
 	public Response sendPlat() throws SQLException {
		try {
			Plats objPlat = new Plats();
			JSONArray objJsonArray = new JSONArray();
			for (int i = 0; i<objPlat.GetAllPlats().size(); i++) {
				JSONObject objJson = new JSONObject();
				objJson.put("Id",objPlat.GetAllPlats().get(i).getId());
				objJson.put("Nom",objPlat.GetAllPlats().get(i).getNom());
				objJson.put("Description",objPlat.GetAllPlats().get(i).getDescription());
				objJson.put("Prix",objPlat.GetAllPlats().get(i).getPrix());
				objJson.put("Url",objPlat.GetAllPlats().get(i).getUrl());
				objJsonArray.put(objJson);
			}
			return Response.ok(objJsonArray).build();
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}
	/**
     * Mettre les entrées dans un json
     * @thorws JSONException
     * @throws SQLException
     * @return une réponse 200 avec un JSONArray qui contient les entrées ou une réponse 500 sinon
     */
	@Override
 	public Response sendEntree() throws SQLException {
		try {
			Entree objEntree = new Entree();
			JSONArray arrayJson = new JSONArray();
			for (int i=0; i<objEntree.GetAllEntree().size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("Id",objEntree.GetAllEntree().get(i).getId());
				obj.put("Nom",objEntree.GetAllEntree().get(i).getNom());
				obj.put("Description",objEntree.GetAllEntree().get(i).getDescription());
				obj.put("Prix",objEntree.GetAllEntree().get(i).getPrix());
				obj.put("Url",objEntree.GetAllEntree().get(i).getUrl());
				arrayJson.put(obj);
			}
			return Response.ok(arrayJson).build();
        } catch (JSONException e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}
	/**
     * Mettre les desserts dans un json
     * @thorws JSONException
     * @throws SQLException
     * @return une réponse 200 avec un JSONArray qui contient les desserts ou une réponse 500 sinon
     */
	@Override
 	public Response sendDessert() throws SQLException {
		try {
			Dessert objDessert = new Dessert();
			JSONArray objJsonArray = new JSONArray();
			for (int i=0; i<objDessert.GetAllDessert().size(); i++) {
				JSONObject objJson = new JSONObject();
				objJson.put("Id",objDessert.GetAllDessert().get(i).getId());
				objJson.put("Nom",objDessert.GetAllDessert().get(i).getNom());
				objJson.put("Description",objDessert.GetAllDessert().get(i).getDescription());
				objJson.put("Prix",objDessert.GetAllDessert().get(i).getPrix());
				objJson.put("Url",objDessert.GetAllDessert().get(i).getUrl());
				objJsonArray.put(objJson);
			}
			return Response.ok(objJsonArray).build();
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}
	/**
     * Mettre les boissons dans un json
     * @thorws JSONException
     * @throws SQLException
     * @return une réponse 200 avec un JSONArray qui contient les boissons ou une réponse 500 sinon
     */
	@Override
 	public Response sendBoisson() throws SQLException {
		try {
			Boisson objBoisson = new Boisson();
			JSONArray objJsonArray = new JSONArray();
			for (int i=0; i<objBoisson.GetAllBoisson().size(); i++) {
				JSONObject objJson = new JSONObject();
				objJson.put("Id",objBoisson.GetAllBoisson().get(i).getId());
				objJson.put("Nom",objBoisson.GetAllBoisson().get(i).getNom());
				objJson.put("Description",objBoisson.GetAllBoisson().get(i).getDescription());
				objJson.put("Prix",objBoisson.GetAllBoisson().get(i).getPrix());
				objJson.put("Url",objBoisson.GetAllBoisson().get(i).getUrl());
				objJsonArray.put(objJson);
			}
			return Response.ok(objJsonArray).build();
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}
}