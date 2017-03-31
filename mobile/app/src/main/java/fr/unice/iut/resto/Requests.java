package fr.unice.iut.resto;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface Requests {

    String URL = "";

    /* MainActivity */
    @GET("")
    Call<String> sendUser(@Query("login") String login, @Query("password") String password);

    /* SelectActivity */
    @GET("")
    Call<JsonArray> getMenu(@Path("") String target);
}
