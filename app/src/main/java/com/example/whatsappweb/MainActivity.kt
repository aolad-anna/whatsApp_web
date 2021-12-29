package com.example.whatsappweb

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView =
            findViewById<View>(R.id.splash) as LinearLayout // Declare an imageView to show the animation.

        val anim = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.fade_in
        ) // Create the animation.

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                if (InternetConnection.checkConnection(this@MainActivity)) {
                    val i = Intent(baseContext, Home::class.java)
                    startActivity(i)
                } else {
                    val rootView =findViewById<View>(R.id.coordinatorLayout)
                    val snackbar = Snackbar

                        .make(rootView,
                            "No Internet or Data Connection!",
                            Snackbar.LENGTH_LONG
                        )
                        .setDuration(80000)
                        .setActionTextColor(Color.RED)
                        .setAction("Retry") {
                            val i = Intent(baseContext, MainActivity::class.java)
                            startActivity(i)
                        }


                    val sbView = snackbar.view
                    val textView = sbView.findViewById<View>(R.id.snackbar_text) as TextView
                    sbView.setBackgroundColor(Color.WHITE)
                    textView.setTextColor(Color.BLACK)

                    snackbar.show()

                }
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        textView.startAnimation(anim)
    }
}