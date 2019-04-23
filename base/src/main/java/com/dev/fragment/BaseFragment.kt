package com.dev.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dev.activity.BaseActivity

abstract class BaseFragment(private val layoutId: Int) : Fragment() {

    private val loaderFragment by lazy {
        LoaderFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    abstract fun initViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    fun showLoader() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showLoader()
        } else {
            loaderFragment.show(childFragmentManager, "loader")
        }
    }

    fun hideLoader() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).hideLoader()
        } else {
            loaderFragment.dismissAllowingStateLoss()
        }

    }
}