package com.test.nano_suite.activities.product_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.nano_suite.dependency_injection.base.BaseViewModel
import com.test.nano_suite.dependency_injection.data.DataManager
import com.test.nano_suite.dependency_injection.rx.ResourceProvider
import com.test.nano_suite.dependency_injection.rx.SchedulerProvider
import com.test.nano_suite.models.products.ProductResponse
import com.test.nano_suite.retrofit.ApiInterface
import com.test.nano_suite.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    resourceProvider: ResourceProvider
) : BaseViewModel<ProductDetailNavigator>(dataManager, schedulerProvider, resourceProvider) {

    val result = MutableLiveData<ProductResponse>()

    fun getProductDetail(id: Int) {
        getNavigator().showProgressLoader()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val obj = RetrofitClient.retrofitInstance.create(ApiInterface::class.java)
                val call = obj.getProductDetail(getDataManager().getString("token")!!, id)

                call.enqueue(object : Callback<ProductResponse> {
                    override fun onResponse(
                        call: Call<ProductResponse>, response: Response<ProductResponse>
                    ) {
                        if (response.isSuccessful) {
                            result.postValue(
                                response.body()
                            )
                        } else {
                            try {
                                val jError = JSONObject(response.errorBody()!!.string())
                                getNavigator().toast(jError.getString("message"))
                            } catch (e: Exception) {
                                viewModelScope.launch(Dispatchers.Main) {
                                    getNavigator().toast(e.message!!)
                                }
                            }
                            getNavigator().hideProgressLoader()
                        }
                    }

                    override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                        viewModelScope.launch(Dispatchers.Main) {
                            getNavigator().toast(t.message!!)
                        }
                        getNavigator().hideProgressLoader()
                    }
                })
            } catch (E: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    getNavigator().toast(E.message.toString())
                }
                getNavigator().hideProgressLoader()
            }
        }
    }

}