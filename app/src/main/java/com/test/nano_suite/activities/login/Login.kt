package com.test.nano_suite.activities.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.test.nano_suite.BR
import com.test.nano_suite.R
import com.test.nano_suite.activities.main.MainActivity
import com.test.nano_suite.databinding.ActivityLoginBinding
import com.test.nano_suite.dependency_injection.base.BaseActivity
import com.test.nano_suite.dependency_injection.di.component.ActivityComponent
import com.test.nano_suite.models.login.LoginBody
import com.test.nano_suite.utils.Utils


class Login : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    lateinit var binding: ActivityLoginBinding
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    var isEmail = false
    var isPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel?.setNavigator(this)
        binding = getViewDataBinding()
        binding.emailLayout.isEndIconVisible = false

        observers()
        textChangeListeners()
    }

    private fun observers() {
        viewModel!!.result.observe(this) {
            viewModel!!.getDataManager().saveString("token", it?.token)
            viewModel!!.getDataManager().saveBoolean("isLoggedIn", true)

            navigateToMainScreen()
            hideProgressLoader()
        }
    }

    private fun textChangeListeners() {
        binding.email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
//                isEmail = s.toString().matches(Regex(emailPattern))
                isEmail = s.toString().isNotEmpty()
                binding.emailLayout.isEndIconVisible = isEmail
                binding.emailLayout.endIconDrawable =
                    ContextCompat.getDrawable(this@Login, R.drawable.tick)
                binding.login.isEnabled = isEmail && isPassword
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                isPassword = s.toString().length in 6..15
                binding.login.isEnabled = isEmail && isPassword
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })

        binding.login.setOnClickListener {
            Utils.showLoader(this)
            val body = LoginBody()
            body.username = binding.email.text.toString().trim()
            body.password = binding.password.text.toString().trim()

            viewModel!!.performLogin(body)
        }
    }

    override fun getBindingVariable(): Int {
        return BR.loginVar
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login;
    }

    override fun performDependencyInjection(buildComponent: ActivityComponent?) {
        buildComponent?.inject(this)
    }

    override fun showProgressLoader() {
        Utils.showLoader(this)
    }

    override fun hideProgressLoader() {
        Utils.hideLoader()
    }

    override fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    fun navigateToMainScreen() {
        val intent = Intent(
            this, MainActivity::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}