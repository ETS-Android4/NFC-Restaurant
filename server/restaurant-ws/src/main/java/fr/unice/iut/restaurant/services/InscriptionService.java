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
@Path("/")
    Response insertClient(@FormParam("firstName") String nom, @FormParam("lastName") String prenom,
                          @FormParam("phone") String tel, @FormParam("password") String mdp);
}
