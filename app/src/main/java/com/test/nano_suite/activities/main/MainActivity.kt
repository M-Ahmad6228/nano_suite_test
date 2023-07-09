package com.test.nano_suite.activities.main

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.test.nano_suite.BR
import com.test.nano_suite.R
import com.test.nano_suite.databinding.ActivityMainBinding
import com.test.nano_suite.dependency_injection.base.BaseActivity
import com.test.nano_suite.dependency_injection.di.component.ActivityComponent
import com.test.nano_suite.fragments.CartFragment
import com.test.nano_suite.fragments.LikeFragment
import com.test.nano_suite.fragments.UserFragment
import com.test.nano_suite.fragments.home.HomeFragment


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    lateinit var binding: ActivityMainBinding
    private val NUM_PAGES = 4
    private var pagerAdapter: FragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary)

        super.onCreate(savedInstanceState)
        viewModel?.setNavigator(this)
        binding = getViewDataBinding()

        supportFragmentManager.addOnBackStackChangedListener(getListener())
        pagerAdapter = object :
            FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            override fun getItem(position: Int): Fragment {
                val mFragment: Fragment = when (position) {
                    0 -> HomeFragment()
                    1 -> CartFragment()
                    2 -> LikeFragment()
                    else -> UserFragment()
                }
                return mFragment
            }

            override fun getCount(): Int {
                return NUM_PAGES
            }
        }

        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.adapter = pagerAdapter

        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeId -> {
                    binding.viewPager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.cartId -> {
                    binding.viewPager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.likeId -> {
                    binding.viewPager.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
                R.id.userId -> {
                    binding.viewPager.currentItem = 3
                    return@OnNavigationItemSelectedListener true
                }
                else -> false
            }
        })

        binding.viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.bottomNav.selectedItemId =
                    if (position == 0) R.id.homeId else if (position == 1) R.id.cartId else if (position == 2) R.id.likeId else R.id.userId
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        binding.viewPager.currentItem = 0
    }

    override fun getBindingVariable(): Int {
        return BR.mainVar
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
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

    override fun onBackPressed() {
        val currentFragment: Fragment? =
            supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]
        if (currentFragment == null) {
            if (binding.viewPager.currentItem == 0) {
                finish()
            } else {
                binding.viewPager.currentItem = 0
            }
        } else {
            binding.viewPager.currentItem = 0
        }
    }

    private fun getListener(): FragmentManager.OnBackStackChangedListener {
        return FragmentManager.OnBackStackChangedListener {
            val manager: FragmentManager = supportFragmentManager
            val currFrag: Fragment? = manager.findFragmentById(R.id.viewPager)
            currFrag?.onResume()
        }
    }
}