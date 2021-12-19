package com.bigprojek.reportfacerecognition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*

class menuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        report.setOnClickListener(){
            intent = Intent(this, reportActivity::class.java)
            startActivity(intent)
        }
        exit.setOnClickListener(){
            finish()
        }
        back.setOnClickListener(){
            finish()
        }
    }
}