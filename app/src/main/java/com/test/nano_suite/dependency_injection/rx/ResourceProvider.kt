package com.test.nano_suite.dependency_injection.rx

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat


class ResourceProvider(private val mContext: Context) {
    fun getString(resId: Int): String {
        return mContext.getString(resId)
    }

    fun getString(resId: Int, value: String?): String {
        return mContext.getString(resId, value)
    }

    fun getDrawable(resId: Int): Drawable? {
        return ContextCompat.getDrawable(mContext, resId)
    }

    fun getColor(resId: Int): Int {
        return ContextCompat.getColor(mContext, resId)
    }

    fun getColorState(resId: Int): ColorStateList {
        return ContextCompat.getColorStateList(mContext, resId)!!
    }
}