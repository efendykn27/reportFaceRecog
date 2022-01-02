package com.bigprojek.reportfacerecognition

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

    val myC = Calendar.getInstance()
    val dpd = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        myC.set(Calendar.YEAR, year)
        myC.set(Calendar.MONTH, month)
        myC.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateLable(myC)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        back1.setOnClickListener {
            finish()
        }

        tanggal.setOnClickListener {
            DatePickerDialog(this, dpd, myC.get(Calendar.YEAR), myC.get(Calendar.MONTH),
                myC.get(Calendar.DAY_OF_MONTH)).show()

        }





        setupList()
        refreshApp()

    }

    private fun refreshApp() {
        refresh.setOnRefreshListener {
            Toast.makeText(this, "Page Refreshed ", Toast.LENGTH_SHORT).show()
            getTamu()
            refresh.isRefreshing = false
        }
    }
    fun updateLable(myC: Calendar) : String {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat)
        var tgljadi = sdf.format(myC.time)
        tgl.setText(tgljadi)
        Toast.makeText(this, "Swipe Down to Refresh ", Toast.LENGTH_SHORT).show()

        return  tgljadi

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
        var jaditgl = updateLable(myC)

        api.data(jaditgl).enqueue(object : Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (response.isSuccessful){
                    val listData = response.body()!!.tamu
                    postAdapter.setData(listData)

                }else{
                    Toast.makeText(this@reportActivity,
                        "gagal get data",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.e("reportActivity", t.toString())
            }

        })
    }
}