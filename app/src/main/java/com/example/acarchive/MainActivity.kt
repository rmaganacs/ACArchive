package com.example.acarchive

import WebViewIntegration
import android.app.Dialog
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    private var isAudioPlaying = true
    private lateinit var mediaPlayer: MediaPlayer
    private var viewPager2: ViewPager2? = null
    private lateinit var webView: WebView
    private lateinit var audioImage: ImageView
    private lateinit var wikiButton: Button
    private lateinit var webViewIntegration: WebViewIntegration
    private var lastSelectedPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Make Transparent Status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setupViewPager()

        // Audio Setup
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    private fun setupViewPager() {
        viewPager2 = findViewById(R.id.viewPagerImageSlider)

        //Loading images from drawable
        val sliderItems: MutableList<SliderItem> = ArrayList()
        sliderItems.add(SliderItem(R.drawable.alexios1))
        sliderItems.add(SliderItem(R.drawable.kassandra2))
        sliderItems.add(SliderItem(R.drawable.bayeck3))
        sliderItems.add(SliderItem(R.drawable.eivor9))
        sliderItems.add(SliderItem(R.drawable.altair4))
        sliderItems.add(SliderItem(R.drawable.ezio5))
        sliderItems.add(SliderItem(R.drawable.shao))
        sliderItems.add(SliderItem(R.drawable.edward6))
        sliderItems.add(SliderItem(R.drawable.shaycormac7))
        sliderItems.add(SliderItem(R.drawable.connor8))
        sliderItems.add(SliderItem(R.drawable.aveline))
        sliderItems.add(SliderItem(R.drawable.arno9))
        sliderItems.add(SliderItem(R.drawable.tenevie))
        sliderItems.add(SliderItem(R.drawable.elevenjacob))
        sliderItems.add(SliderItem(R.drawable.desmond))

        viewPager2?.adapter = SliderAdapter(sliderItems, viewPager2!!, this)
        viewPager2?.clipToPadding = false
        viewPager2?.clipChildren = false
        viewPager2?.offscreenPageLimit = 3
        (viewPager2?.getChildAt(0) as? RecyclerView)?.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPager2?.setPageTransformer(compositePageTransformer)
        viewPager2?.setCurrentItem(lastSelectedPosition, false)

        audioImage = findViewById(R.id.audio)
        if(isAudioPlaying) {
            audioImage.setImageResource(R.drawable.audio_on)
        } else {
            audioImage.setImageResource(R.drawable.audio_off)
        }
        audioImage.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isAudioPlaying = false
                audioImage.setImageResource(R.drawable.audio_off)
            } else {
                mediaPlayer.start()
                isAudioPlaying = true
                audioImage.setImageResource(R.drawable.audio_on)
            }
        }
    }

    private fun showOptionDialog(url: String, position: Int) {
        val dialogView = layoutInflater.inflate(R.layout.modal_option, null)
        val dialogBuilder = AlertDialog.Builder(this).setView(dialogView)
        val alertDialog = dialogBuilder.create()

        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.show()
        wikiButton = dialogView.findViewById(R.id.wikiButton)
        wikiButton.setOnClickListener {
            alertDialog.dismiss()
            showWebView(url, position)
        }
    }


    private fun showWebView(url: String, position: Int) {
        lastSelectedPosition = position
        setContentView(R.layout.activity_webview)
        webView = findViewById(R.id.webView)
        webViewIntegration = WebViewIntegration(this, webView)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        webView.startAnimation(fadeIn)

        // Load the URL after the fade-in animation
        webViewIntegration.loadUrl(url)

        val closeButton: ImageView = findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    setContentView(R.layout.activity_main)
                    setupViewPager()
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
            webView.startAnimation(fadeOut)
        }
    }



    fun onSliderItemClick(position: Int) {
        when (position) {
            0 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Alexios", 0)
            1 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Kassandra", 1)
            2 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Bayek", 2)
            3 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Eivor_Varinsdottir", 3)
            4 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Alta%C3%AFr_Ibn-La%27Ahad", 4)
            5 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Ezio_Auditore_da_Firenze", 5)
            6 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Shao_Jun", 6)
            7 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Edward_Kenway", 7)
            8 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Shay_Cormac", 8)
            9 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Ratonhnhak%C3%A9:ton", 9)
            10 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Aveline_de_Grandpr%C3%A9", 10)
            11 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Arno_Dorian", 11)
            12 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Evie_Frye", 12)
            13 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Jacob_Frye", 13)
            14 -> showOptionDialog("https://assassinscreed.fandom.com/wiki/Desmond_Miles", 14)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    override fun onPause() {
        super.onPause()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

}