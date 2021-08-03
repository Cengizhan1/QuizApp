package com.cengizhan.doruyanlsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username = intent.getStringExtra(Constants.USER_NAME)
        tv_name.text = username

        val total = intent.getIntExtra(Constants.TOTAL_QUESTİONS,0)
        val correct = intent.getIntExtra(Constants.CORRCT_ANSWER,0)
        tv_score.text = "Your scora is $correct out of $total"

        btnfinish.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}