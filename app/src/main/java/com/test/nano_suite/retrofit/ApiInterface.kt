package com.test.nano_suite.retrofit

import com.test.nano_suite.dependency_injection.network.ApiEndPoints
import com.test.nano_suite.models.login.LoginBody
import com.test.nano_suite.models.login.LoginResponse
import com.test.nano_suite.models.products.ProductResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST(ApiEndPoints.ENDPOINT_LOGIN)
    fun login(@Body data: LoginBody): Call<LoginResponse>

    @GET(ApiEndPoints.ENDPOINT_GET_PRODUCTS)
    fun getProducts(@Header("Authorization") token: String): Call<List<ProductResponse>>

    @GET("${ApiEndPoints.ENDPOINT_GET_PRODUCT_DETAIL}/{id}")
    fun getProductDetail(
        @Header("Authorization") token: String, @Path("id") id: Int
    ): Call<ProductResponse>

}