package com.test.nano_suite.models.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginBody {
    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null
}