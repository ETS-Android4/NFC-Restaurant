package fr.unice.iut.restaurant.services;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import fr.unice.iut.restaurant.provider.Inscription;

/**
 * Classe pour traiter les demandes de connexion
 * @author Ismael
 * @version 1.0
 */
@Path("/connexion")
public class ConnexionImpl implements ConnexionService {
	/**
 	 * Créer un objet Inscription qui contient le téléphone et le mot de passe d'un utilisateur et vérifier qu'il existe dans une base de données
 	 * @param noTel Téléphone d'un utilisateur
 	 * @param password Mot de passe d'un utilisateur
 	 * @throws Exception
 	 * @return une réponse 200 en cas de réussite ou une réponse 404 si l'utilisateur n'existe pas dans la BD ou une réponse 500 sinon
 	 */
	@Override
	public Response Connexion(String noTel, String password) {
		try {
			if (Inscription.checkConnexion(new Inscription(noTel, password)) == 1) return Response.status(200).build();
			return Response.status(404).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}