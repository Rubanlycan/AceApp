package com.ionicframework.itech719214;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClass {

    String LATEST_BASE_URL = "https://www.aceupdate.com/ACE_App/";
    String Previous_BASE_URL = "https://www.aceupdate.com/ACE_App2/";


    @GET("getdata.php")
    Call<List<Data>> getProducts_Latest();

    @GET("getdata.php")
    Call<List<Data>> getProducts_Previous();

    @GET("sb_getdata.php")
    Call<List<Data>> getProducts_ADds();


}
