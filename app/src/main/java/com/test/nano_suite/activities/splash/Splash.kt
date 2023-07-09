package com.test.nano_suite.activities.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import com.test.nano_suite.BR
import com.test.nano_suite.R
import com.test.nano_suite.activities.login.Login
import com.test.nano_suite.activities.main.MainActivity
import com.test.nano_suite.databinding.ActivitySplashBinding
import com.test.nano_suite.dependency_injection.base.BaseActivity
import com.test.nano_suite.dependency_injection.di.component.ActivityComponent


class Splash : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    companion object {
        init {
            System.loadLibrary("native-lib")
        }

        external fun baseAPIURL(): String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        viewModel?.setNavigator(this)

        if (viewModel!!.getDataManager().getBoolean("isLoggedIn")) {
            startActivity(Intent(this@Splash, MainActivity::class.java))
            finish()
        } else Handler(Looper.myLooper()!!).postDelayed(Runnable {
            startActivity(Intent(this@Splash, Login::class.java))
            finish()
        }, 3500);

        Handler(Looper.getMainLooper()).postDelayed(Runnable {

        }, 3500);
    }

    override fun getBindingVariable(): Int {
        return BR.splashVar
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun performDependencyInjection(buildComponent: ActivityComponent?) {
        buildComponent?.inject(this)
    }

    override fun showProgressLoader() {
        TODO("Not yet implemented")
    }

    override fun hideProgressLoader() {
        TODO("Not yet implemented")
    }

    override fun toast(text: String) {
        TODO("Not yet implemented")
    }
}