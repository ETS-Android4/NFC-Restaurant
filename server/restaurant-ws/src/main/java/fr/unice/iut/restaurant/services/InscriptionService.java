package fr.unice.iut.restaurant.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by Utilisateur on 17/04/2017.
 */
public interface InscriptionService {
    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    Response insertClient(String inscription)throws SQLException;
}
