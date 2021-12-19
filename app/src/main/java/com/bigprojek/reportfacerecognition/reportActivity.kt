package com.bigprojek.reportfacerecognition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigprojek.reportfacerecognition.Adapter.PostAdapter
import com.bigprojek.reportfacerecognition.retrofit.PostResponse
import com.bigprojek.reportfacerecognition.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_report.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class reportActivity : AppCompatActivity() {

    private val api by lazy { RetrofitClient().endpoint}
    private lateinit var postAdapter: PostAdapter
    private lateinit var listTamu : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val sdf = SimpleDateFormat("EEE, dd MMMM yyyy")
        val currentDate = sdf.format(Date())

        tgl.setText(currentDate)

        back1.setOnClickListener {
            finish()
        }

        setupList()

    }

    private fun setupList() {
        listTamu = findViewById(R.id.rvPost)
        postAdapter = PostAdapter(arrayListOf())
        listTamu.adapter = postAdapter
    }

    override fun onStart(){
        super.onStart()
        getTamu()
    }
    private fun getTamu(){
        api.data().enqueue(object : Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (response.isSuccessful){
                    val listData = response.body()!!.tamu
                    postAdapter.setData(listData)
//                    listData.forEach{
//                        Log.e("reportActivity", response.message())
//                    }

                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.e("reportActivity", t.toString())
            }

        })
    }
}