package com.qton.sophie.mojeelektrina

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var statistics_button: TextView;
    private lateinit var top_text: TextView
    private lateinit var lightbulb_color: ImageView
    private lateinit var settings_btn: ImageView
    private lateinit var info_btn: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
