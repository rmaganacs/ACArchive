package com.example.acarchive

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    private var videoView: VideoView? = null
    @SuppressLint("ClickableViewAccessibility")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        // Code for Starting Video
        videoView = findViewById<View>(R.id.videoView) as VideoView
        val video = Uri.parse("android.resource://" + packageName + "/" + R.raw.splash)
        videoView!!.setVideoURI(video)
        videoView!!.setOnCompletionListener { startNextActivity() }
        videoView!!.start()


        // Touch Listener for Skipping Splash Video
        videoView!!.setOnTouchListener { v, event ->
            startNextActivity()
            false
        }
    }

    // function for skipping to next activity
    private fun startNextActivity() {
        if (isFinishing) return
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}