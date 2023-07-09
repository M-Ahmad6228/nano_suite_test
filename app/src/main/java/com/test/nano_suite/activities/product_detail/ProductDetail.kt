package com.test.nano_suite.activities.product_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.test.nano_suite.BR
import com.test.nano_suite.NanoSuiteApplication
import com.test.nano_suite.R
import com.test.nano_suite.adapters.ProductsAdapter
import com.test.nano_suite.databinding.ActivityProductDetailBinding
import com.test.nano_suite.dependency_injection.base.BaseActivity
import com.test.nano_suite.dependency_injection.di.component.ActivityComponent
import com.test.nano_suite.utils.Utils

class ProductDetail : BaseActivity<ActivityProductDetailBinding, ProductDetailViewModel>(),
    ProductDetailNavigator {

    var id: Int = 0
    lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary)

        super.onCreate(savedInstanceState)
        viewModel?.setNavigator(this)
        binding = getViewDataBinding()

        id = intent.getIntExtra("id", 0)
        observers()
        clickListeners()

        viewModel?.getProductDetail(id)
    }

    private fun clickListeners() {
        binding.back.setOnClickListener { finish() }

        binding.toggleSheet.setOnClickListener {
            if (binding.ratingView.visibility == View.GONE) {
                binding.ratingView.visibility = View.VISIBLE
                binding.toggleSheet.setImageDrawable(
                    ContextCompat.getDrawable(
                        this, R.drawable.arrow_down
                    )
                )
            } else {
                binding.ratingView.visibility = View.GONE
                binding.toggleSheet.setImageDrawable(
                    ContextCompat.getDrawable(
                        this, R.drawable.arrow_up
                    )
                )
            }
        }
    }

    private fun observers() {
        viewModel!!.result.observe(this) {
            Glide.with(this).load(it.image).into(binding.image)
            binding.description.text = it.description
            binding.ratings.rating = it.rating?.rate!!.toFloat()
            binding.totalRatings.text = "Reviews (${it.rating?.count})"
            binding.price.text = "${it.price} AED"
            binding.rating.text = it.rating?.rate!!.toString()

            hideProgressLoader()
        }
    }

    override fun getBindingVariable(): Int {
        return BR.productDetailVar
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_product_detail
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