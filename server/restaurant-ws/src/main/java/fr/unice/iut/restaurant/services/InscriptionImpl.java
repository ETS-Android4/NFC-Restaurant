package fr.unice.iut.restaurant.services;

import fr.unice.iut.restaurant.exception.UserAlreadyExistException;
import fr.unice.iut.restaurant.provider.Inscription;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Utilisateur on 17/04/2017.
 */
// Json -> {"nom":"","prenom":"","password":"","noTel":}

@Path("/inscription")
public class InscriptionImpl implements InscriptionService{
    String nom,prenom,password;
    int noTel;
    public Response insertClient(String inscription){
        try{
            JSONObject json = new JSONObject(inscription);
            nom = json.getString("nom");
            prenom = json.getString("prenom");
            password = json.getString("password");
            noTel = json.getInt("noTel");
            int idUsers = Inscription.InsertUser(new Inscription(nom, prenom, password, noTel));
            return Response.status(201).entity(idUsers).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }

}
