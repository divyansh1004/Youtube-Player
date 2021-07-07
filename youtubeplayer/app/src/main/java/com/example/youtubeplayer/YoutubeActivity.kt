package com.example.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YVI = "I4AgeDIrHGY"
const val YP = "PLCsuqbR8ZoiCO028dfW1n5XmbHGEmim1g"


class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val TAG = "youtubeActivity"
    private val DIALOG_REQUEST_CODE=1
    val pv by lazy {YouTubePlayerView(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//called when activity is launched
        //setContentView(R.layout.activity_youtube)
        //val layout=findViewById<ConstraintLayout>(R.id.activity_youtube)
        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)


        // val b1=Button(this)
        // b1.layoutParams=ConstraintLayout.LayoutParams(600,180)
        //b1.text="Button added"
        //layout.addView(b1)

    //this is used for using google api and makes the youtube player
        pv.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(pv)

        pv.initialize(getString(R.string.GOOGLE_API_KEY), this)

    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youTubePlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        Log.d(TAG, "OnInitialiseSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "OnInitialiseSuccess: youTubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this, "Initialized Youtube Player Successfully", Toast.LENGTH_SHORT).show()

        youTubePlayer?.setPlayerStateChangeListener(pscl)
        youTubePlayer?.setPlaybackEventListener(pbel)
        if (!wasRestored) {
            youTubePlayer?.loadVideo(YVI)
        }
        else{
            youTubePlayer?.play()
        }

    }

    override fun onInitializationFailure(
        provide: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if (youTubeInitializationResult?.isUserRecoverableError == true)//comparing nullable boolean
        {
            youTubeInitializationResult.getErrorDialog(this, DIALOG_REQUEST_CODE).show()
        } else {
            val em =
                "There was an error initializing the YoutubePlayer($youTubeInitializationResult)"
            Toast.makeText(this, em, Toast.LENGTH_LONG).show()
        }

    }

    private val pbel = object : YouTubePlayer.PlaybackEventListener {
        override fun onPlaying() {
            Toast.makeText(
                this@YoutubeActivity, "video is playing", Toast.LENGTH_SHORT
            ).show()

        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "video has paused", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            Toast.makeText(this@YoutubeActivity, "video has Stopped", Toast.LENGTH_SHORT).show()

        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onSeekTo(p0: Int) {
        }

    }
    private val pscl = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onLoading() {

        }

        override fun onLoaded(p0: String?) {

        }

        override fun onAdStarted() {
            Toast.makeText(
                this@YoutubeActivity,
                "click add now and make video creator rich",
                Toast.LENGTH_SHORT
            ).show()

        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "video has started", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "video has ended", Toast.LENGTH_SHORT).show()

        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG,"OnActivityResult called With respone code $resultCode for request $requestCode")

        if(requestCode==DIALOG_REQUEST_CODE)
            {
                Log.d(TAG, intent?.toString().toString())
                Log.d(TAG,intent?.extras.toString())
                pv.initialize(getString(R.string.GOOGLE_API_KEY),this)
            }
    }
}
