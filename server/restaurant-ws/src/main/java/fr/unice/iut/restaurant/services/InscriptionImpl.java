package fr.unice.iut.restaurant.services;

import fr.unice.iut.restaurant.exception.UserAlreadyExistException;
import fr.unice.iut.restaurant.provider.Inscription;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Utilisateur on 17/04/2017.
 */

@Path("/inscription")
public class InscriptionImpl implements InscriptionService{
    public Response insertClient(String nom, String prenom, String tel, String mdp){
        try{
            Inscription.InsertUser(new Inscription(nom, prenom, tel, mdp));
            return Response.status(201).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }

}