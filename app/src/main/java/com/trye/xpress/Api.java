package com.trye.xpress;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @GET("/users.json")
    Call<HashMap<String,User>> getData();
    @GET("/nota.json")
    Call<HashMap<String,Resi>> getResi();
    @PUT("/users/{id}.json")
    Call<User> putUser(@Path("id") String id,@Body User user);
    @PUT("/nota/{id}.json")
    Call<Resi> putResi(@Path("id") String id, @Body Resi resi);


}
