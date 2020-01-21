package com.example.priyanshu1.apiinterface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("RegistrationControllerJson.php")
    Call<CommanResponse> createUser(

            @Field("gatekeeperRegistration") String gatekeeperRegistration,
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("roletype") String roletype,
            @Field("mobno") String mobno,
            @Field("email") String email,
            @Field("address") String address,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("loginapi.php")
    Call<CommanResponse> login(

            @Field("logingatekeeper") String logingatekeeper,
            @Field("mobno") String mobno,
            @Field("password") String password
    );
}
