package com.media.gallery

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

object Utils {

    fun hideSoftKeyBoard(context: Activity) {
        try {
            context.window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            )

            val inputManager = context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputManager.hideSoftInputFromWindow(
                context
                    .currentFocus!!.windowToken, 0
            )

            context.window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            )
        } catch (e: Exception) {
        }

    }

    fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            //        LogUtil.d("Network Change", "isConnected : " + isConnected);
            netInfo != null && netInfo.isConnected
        } catch (e: Exception) {
            true
        }

    }


}