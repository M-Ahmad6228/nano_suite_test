package com.test.nano_suite.fragments.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.nano_suite.BR
import com.test.nano_suite.NanoSuiteApplication.Companion.TAG
import com.test.nano_suite.R
import com.test.nano_suite.activities.login.Login
import com.test.nano_suite.activities.main.MainActivity
import com.test.nano_suite.activities.product_detail.ProductDetail
import com.test.nano_suite.adapters.ProductsAdapter
import com.test.nano_suite.databinding.FragmentHomeBinding
import com.test.nano_suite.dependency_injection.base.BaseFragment
import com.test.nano_suite.dependency_injection.di.component.FragmentComponent
import com.test.nano_suite.interfaces.Clicker
import com.test.nano_suite.utils.Utils

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), HomeNavigator, Clicker {

    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.setNavigator(this)
        binding = getViewDataBinding()

        observers()
        clickListeners()
    }

    private fun clickListeners() {
        binding.logout.setOnClickListener {
            viewModel?.getDataManager()?.saveString("token", null)
            viewModel?.getDataManager()?.saveBoolean("isLoggedIn", false)

            val intent = Intent(
                requireContext(), Login::class.java
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getProducts()
    }

    private fun observers() {
        viewModel!!.result.observe(requireActivity()) {
            Log.d(TAG, "products: $it")
            val adapter = ProductsAdapter(it, this)
            val layoutManager = LinearLayoutManager(requireContext())
            binding.products.layoutManager = layoutManager
            binding.products.adapter = adapter

            hideProgressLoader()
        }
    }

    override fun getBindingVariable(): Int {
        return BR.homeVar
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun performDependencyInjection(buildComponent: FragmentComponent?) {
        buildComponent?.inject(this)
    }

    override fun showProgressLoader() {
        Utils.showLoader(requireContext())
    }

    override fun hideProgressLoader() {
        Utils.hideLoader()
    }

    override fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    override fun onClick(pos: Int, id: Int) {
        startActivity(Intent(requireContext(), ProductDetail::class.java).putExtra("id", id))
    }

}