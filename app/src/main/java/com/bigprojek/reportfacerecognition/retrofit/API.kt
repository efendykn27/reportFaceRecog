package com.bigprojek.reportfacerecognition.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("data.php")
    fun data(): Call<PostResponse>
}