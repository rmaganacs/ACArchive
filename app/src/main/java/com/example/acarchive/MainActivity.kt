package com.example.acarchive

import WebViewIntegration
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    private var viewPager2: ViewPager2? = null
    private lateinit var webView: WebView
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
            0 -> showWebView("https://assassinscreed.fandom.com/wiki/Alexios", 0)
            1 -> showWebView("https://assassinscreed.fandom.com/wiki/Kassandra", 1)
            2 -> showWebView("https://assassinscreed.fandom.com/wiki/Bayek", 2)
            3 -> showWebView("https://assassinscreed.fandom.com/wiki/Eivor_Varinsdottir", 3)
            4 -> showWebView("https://assassinscreed.fandom.com/wiki/Alta%C3%AFr_Ibn-La%27Ahad", 4)
            5 -> showWebView("https://assassinscreed.fandom.com/wiki/Ezio_Auditore_da_Firenze", 5)
            6 -> showWebView("https://assassinscreed.fandom.com/wiki/Shao_Jun", 6)
            7 -> showWebView("https://assassinscreed.fandom.com/wiki/Edward_Kenway", 7)
            8 -> showWebView("https://assassinscreed.fandom.com/wiki/Shay_Cormac", 8)
            9 -> showWebView("https://assassinscreed.fandom.com/wiki/Ratonhnhak%C3%A9:ton", 9)
            10 -> showWebView("https://assassinscreed.fandom.com/wiki/Aveline_de_Grandpr%C3%A9", 10)
            11 -> showWebView("https://assassinscreed.fandom.com/wiki/Arno_Dorian", 11)
            12 -> showWebView("https://assassinscreed.fandom.com/wiki/Evie_Frye", 12)
            13 -> showWebView("https://assassinscreed.fandom.com/wiki/Jacob_Frye", 13)
            14 -> showWebView("https://assassinscreed.fandom.com/wiki/Desmond_Miles", 14)
        }
    }

}


