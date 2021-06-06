package com.example.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bplays = findViewById<Button>(R.id.btnplaysingle)
        val bsa = findViewById<Button>(R.id.btnstandalone)
        bplays.setOnClickListener(this)
        bsa.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        val intent = when (view.id) {
            R.id.btnplaysingle ->Intent(this,YoutubeActivity::class.java)
            R.id.btnstandalone->Intent(this,StandaloneActivity::class.java)
             else -> throw IllegalArgumentException("Undefined button clicked")
        }
        startActivity(intent)
    }
}
