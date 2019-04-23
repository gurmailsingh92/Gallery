package com.dev.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dev.fragment.LoaderFragment

abstract class BaseActivity : AppCompatActivity() {

    private val loaderFragment by lazy {
        LoaderFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    abstract fun initViewModel()

    fun showLoader() {
        loaderFragment.show(supportFragmentManager, "loader")
    }

    fun hideLoader() {
        loaderFragment.dismissAllowingStateLoss()
    }
}