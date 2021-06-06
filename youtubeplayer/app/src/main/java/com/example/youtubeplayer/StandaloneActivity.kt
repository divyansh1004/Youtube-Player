package com.example.youtubeplayer

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer

class StandaloneActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        val bplay = findViewById<Button>(R.id.btnplayvideo)
        val bPlaylist = findViewById<Button>(R.id.btnplaylist)
        bplay.setOnClickListener(this)
        bPlaylist.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val intent = when (view.id) {
            R.id.btnplayvideo -> YouTubeStandalonePlayer.createVideoIntent(
                this, getString(R.string.GOOGLE_API_KEY), YVI, 0, true, false
            )
            R.id.btnplaylist -> YouTubeStandalonePlayer.createPlaylistIntent(
                this, getString(R.string.GOOGLE_API_KEY), YP, 0, 0, true, true
            )
            else -> throw IllegalArgumentException("Undefined button clicked")
        }
        startActivity(intent)
    }
}