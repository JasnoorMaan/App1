package com.example.animsplashkotlin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    val SPLASH_SCREEN = 3000

    private lateinit var  topAnimation : Animation
    private lateinit var  bottomAnimation: Animation

    private lateinit var imageView: ImageView
    private lateinit var title_txt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.hide()

        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation )
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        imageView = findViewById(R.id.filename)
        title_txt = findViewById(R.id.title_text)

        imageView.animation = topAnimation
        title_txt.animation = bottomAnimation

        Handler().postDelayed(
            {
                val intent = Intent (this , registerpage::class.java)
                startActivity(intent)
                finish()
            },SPLASH_SCREEN.toLong())
    }


    }
