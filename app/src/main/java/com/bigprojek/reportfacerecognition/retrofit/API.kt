package com.bigprojek.reportfacerecognition.retrofit

import retrofit2.Call
import retrofit2.http.*

interface API {
//    @GET("data.php")
//    fun data(): Call<PostResponse>

    @FormUrlEncoded
    @POST("data.php/")
    fun data(
        @Field("post_date") tanggal : String?
    ): Call<PostResponse>


    @FormUrlEncoded
    @POST("login.php/")
    fun login(
        @Field("post_username") username : String?,
        @Field("post_password") password : String?
    ): Call<ResponseLogin>
}