package fr.unice.iut.restaurant.services;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import fr.unice.iut.restaurant.exception.UserAlreadyExistException;
import fr.unice.iut.restaurant.provider.Inscription;

/**
 * Classe pour traiter les demandes d'inscription
 * @author Ismael
 * @version 1.0
 */
@Path("/inscription")
public class InscriptionImpl implements InscriptionService {
	/**
 	 * Créer un objet Inscription qui contient des informations sur un utilisateur et l'insérer dans une base de données
 	 * @param nom Nom d'un utilisateur
 	 * @param prenom Prénom d'un utilisateur
 	 * @param tel Téléphone d'un utilisateur
 	 * @param mdp Mot de passe d'un utilisateur
 	 * @throws Exception
 	 * @return une réponse 201 en cas de réussite ou une réponse 500 sinon
 	 */
	@Override
    public Response insertClient(String nom, String prenom, String tel, String mdp) {
        try {
            Inscription.InsertUser(new Inscription(nom, prenom, tel, mdp));
            return Response.status(201).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    /**
     * Insérer dans une base de données un numéro de table avec un GUID d'un TAG
     * @param table Numéro d'une table
     * @param guid GUID d'un TAG
     * @throws Exception
     * @return une réponse 201 en cas de réussite ou une réponse 500 sinon
     */
    @Override
    public Response insertTag(String table, String guid) {
        try {
            Inscription.InsertTag(table, guid);
            return Response.status(201).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}