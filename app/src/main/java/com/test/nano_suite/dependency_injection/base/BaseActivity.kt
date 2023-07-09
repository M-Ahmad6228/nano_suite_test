package com.test.nano_suite.dependency_injection.base

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.test.nano_suite.dependency_injection.di.module.ActivityModule
import com.test.nano_suite.dependency_injection.di.component.ActivityComponent
import com.test.nano_suite.dependency_injection.di.component.DaggerActivityComponent
import com.test.nano_suite.NanoSuiteApplication
import com.test.nano_suite.utils.Utils
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(),
    BaseFragment.Callback {

    private var mViewDataBinding: T? = null

    @set:Inject
    var viewModel: V? = null

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int


    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        performDependencyInjection(getBuildComponent())
        performDataBinding()
        super.onCreate(savedInstanceState)
    }

    open fun getViewDataBinding(): T {
        return mViewDataBinding!!
    }

    open fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    open fun isNetworkConnected(): Boolean {
        return Utils.checkInternet(applicationContext)
    }

    open fun getBuildComponent(): ActivityComponent? {
        return DaggerActivityComponent.builder()
            .appComponent((application as NanoSuiteApplication).appComponent)
            .activityModule(ActivityModule(this))
            .build()
    }

    abstract fun performDependencyInjection(buildComponent: ActivityComponent?)

    open fun addVariableToBinding(id: Int, value: Any?) {
        mViewDataBinding!!.setVariable(id, value)
    }

    open fun performDataBinding() {
        mViewDataBinding!!.setVariable(getBindingVariable(), viewModel)
        mViewDataBinding!!.executePendingBindings()
    }

}