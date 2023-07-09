package com.test.nano_suite.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.test.nano_suite.R
import com.test.nano_suite.activities.login.Login

class Splash : AppCompatActivity() {

    companion object {
        init {
            System.loadLibrary("native-lib")
        }

        external fun baseAPIURL(): String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this@Splash, Login::class.java))
            finish()
        }, 3500);
    }
}