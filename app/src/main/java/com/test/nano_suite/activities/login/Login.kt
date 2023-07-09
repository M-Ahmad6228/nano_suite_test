package com.test.nano_suite.activities.login

import android.os.Bundle
import android.widget.Toast
import com.test.nano_suite.BR
import com.test.nano_suite.R
import com.test.nano_suite.databinding.ActivityLoginBinding
import com.test.nano_suite.dependency_injection.base.BaseActivity
import com.test.nano_suite.dependency_injection.di.component.ActivityComponent
import com.test.nano_suite.utils.Utils

class Login : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel?.setNavigator(this)
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
}