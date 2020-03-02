package com.example.priyanshu1.apiinterface;

import com.example.priyanshu1.apiinterface.responce.loginresponce;
import com.example.priyanshu1.apiinterface.responce.visidetail_responce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    Call<loginresponce> login(

            @Field("logingatekeeper") String logingatekeeper,
            @Field("mobno") String mobno,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("GatekeeperVisiEntry.php")
    Call<CommanResponse> gatekvisientry(

            @Field("gatekvisientry") String gatekvisientry,
            @Field("fullname") String fullname,
            @Field("numofvisi") String numofvisi,
            @Field("vno") String vno,
            @Field("mobno") String mobno,
            @Field("email") String email,
            @Field("houseno") String houseno,
            @Field("purposeofvisi") String purposeofvisi,
            @Field("date") String date,
            @Field("time") String time

    );



    @FormUrlEncoded
    @POST("GatekeeperVisiEntry.php")
    Call<visidetail_responce> visidetail(
            @Field("gatekvisidetail") String gatekvisidetail

    );

    @FormUrlEncoded
    @POST("GatekeeperVisiEntry.php")
    Call<CommanResponse> visiexittime(
            @Field("gatekvisiexittime") String gatekvisiexittime,
            @Field("id") String id,
            @Field("exittime") String exittime

    );

    @FormUrlEncoded
    @POST("forgrtpass.php")
    Call<CommanResponse> changepassgate(
            @Field("changepassgate") String changepassgate,
            @Field("mobno") String mobno,
            @Field("password") String password

    );


    @FormUrlEncoded
    @POST("forgrtpass.php")
    Call<CommanResponse> mobnoexgate(
            @Field("passmobnoexgate") String passmobnoexgate,
            @Field("mobno") String mobno

    );
}
