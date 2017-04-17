package fr.unice.iut.restaurant.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by Utilisateur on 17/04/2017.
 */
public interface InscriptionService {
@POST
@Path("/inscription")
    Response insertClient(@FormParam("nom") String nom, @FormParam("prenom") String prenom,
                          @FormParam("tel") String tel, @FormParam("mdp") String mdp);
}
