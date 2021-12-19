package com.bigprojek.reportfacerecognition.retrofit

import com.google.gson.annotations.SerializedName

data class PostResponse (
    val tamu: List<Data>
    ){
    data class Data(
        val id : String?,
        val nama : String?,
        val nohp : String?,
        val keperluan:String?,
        val tanggal: String?,
        val waktu : String?
    )

}
