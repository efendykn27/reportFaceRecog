package com.bigprojek.reportfacerecognition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.bigprojek.reportfacerecognition.databinding.ActivityMainBinding
import com.bigprojek.reportfacerecognition.retrofit.ResponseLogin
import com.bigprojek.reportfacerecognition.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private var user : String = ""
    private var pass : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        binding!!.login.setOnClickListener(){
            user = binding!!.etusername.text.toString()
            pass = binding!!.etpaswd.text.toString()

            when{
                user == ""->{
                    binding!!.etusername.error = "Username Tidak Boleh Kosong"
                }
                pass == ""->{
                    binding!!.etpaswd.error = "Password Tidak Boleh Kosong"
                }
                else -> {
                    binding!!.loading.visibility = View.VISIBLE
                    getData()
                }
            }
        }

    }

    private fun getData(){
        val api = RetrofitClient().endpoint
        api.login(user, pass).enqueue(object : Callback<ResponseLogin>{
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful){
                    if(response.body()?.response == true){
                        binding!!.loading.visibility = View.GONE
                        startActivity(Intent(this@MainActivity, menuActivity:: class.java))
                        finish()
                    }else{
                        binding!!.loading.visibility=View.GONE
                        Toast.makeText(this@MainActivity,
                            "Login gagal, Periksa Kembali Username dan Password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }else{
                    Toast.makeText(this@MainActivity,
                        "Login gagal, Terjadi Kesalahan",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("Pesan error","${t.message}")
            }

        })
    }
}