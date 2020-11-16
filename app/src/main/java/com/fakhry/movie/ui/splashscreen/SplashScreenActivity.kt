package com.fakhry.movie.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fakhry.movie.R
import com.fakhry.movie.ui.dashboard.DashboardActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                delay(2000)
                val intent = Intent(
                    this@SplashScreenActivity,
                    DashboardActivity::class.java
                )
                startActivity(intent)
                finishAffinity()
            } catch (e: Exception) {
                Log.d("onBoarding", e.message.toString())
            }
        }
    }
}