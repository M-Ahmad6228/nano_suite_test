package com.test.nano_suite.models.products

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rating {
    @SerializedName("rate")
    @Expose
    var rate: Double? = null

    @SerializedName("count")
    @Expose
    var count: Int? = null
}