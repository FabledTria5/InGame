package com.example.ingame.ui.activities.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.ActivitySplashScreenBinding
import com.example.ingame.ui.activities.main.MainActivity
import moxy.MvpAppCompatActivity

class SplashScreenActivity : MvpAppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(this@SplashScreenActivity, MainActivity::class.java)
            )
        }, 1000)
    }
}