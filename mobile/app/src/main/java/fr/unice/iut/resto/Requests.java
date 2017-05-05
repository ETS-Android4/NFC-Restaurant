package fr.unice.iut.resto;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface Requests {

    String URL = "http://????:8080/restaurant-ws/rest/";

    /* LoginActivity */
    @FormUrlEncoded
    @POST("connexion")
    Call<Void> sendUser(@Field("phone") String phone,
                        @Field("password") String password);

    /* SignActivity */
    @FormUrlEncoded
    @POST("inscription")
    Call<Void> sendData(@Field("firstName") String firstName,
                        @Field("lastName") String lastName,
                        @Field("phone") String phone,
                        @Field("password") String password);

    /* SelectActivity */
    @GET("menu/{target}")
    Call<JsonArray> getMenu(@Path("target") String target);

    /* NfcActivity */
    @FormUrlEncoded
    @POST("commande")
    Call<Void> sendCommand(@Field("order") String order);
}
