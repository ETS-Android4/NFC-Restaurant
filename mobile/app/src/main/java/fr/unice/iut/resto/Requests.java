package fr.unice.iut.resto;

import com.google.gson.JsonArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface Requests {

    String URL = "http://????:8080/restaurant-ws/rest/";

    /* LoginActivity */
    @GET("????")
    Call<String> sendUser(@Query("phone") String phone, @Query("password") String password);

    /* SignActivity */
    @GET("????")
    Call<String> sendData(@Query("firstName") String firstName,
                          @Query("lastName") String lastName,
                          @Query("phone") String phone,
                          @Query("password") String password);

    /* SelectActivity */
    @GET("menu/{target}")
    Call<JsonArray> getMenu(@Path("target") String target);

    /* NfcActivity */
    @GET("????")
    Call<Void> sendCommand(@Query("table") String table,
                           @Query("user") String user,
                           @Query("command") ArrayList<Food> command);
}
