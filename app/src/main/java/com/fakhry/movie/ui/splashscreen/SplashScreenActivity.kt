package com.fakhry.movie.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.fakhry.movie.R
import com.fakhry.movie.ui.dashboard.DashboardActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            val intent = Intent(
                this@SplashScreenActivity,
                DashboardActivity::class.java
            )
            startActivity(intent)
            finishAffinity()
        }, 2000)
    }
}
