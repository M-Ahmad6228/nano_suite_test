package com.test.nano_suite.activities.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.nano_suite.dependency_injection.base.BaseViewModel
import com.test.nano_suite.dependency_injection.data.DataManager
import com.test.nano_suite.dependency_injection.rx.ResourceProvider
import com.test.nano_suite.dependency_injection.rx.SchedulerProvider
import com.test.nano_suite.models.login.LoginBody
import com.test.nano_suite.models.login.LoginResponse
import com.test.nano_suite.retrofit.ApiInterface
import com.test.nano_suite.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    resourceProvider: ResourceProvider
) : BaseViewModel<LoginNavigator>(dataManager, schedulerProvider, resourceProvider) {

    val result = MutableLiveData<LoginResponse>()

    fun performLogin(body: LoginBody) {
        getNavigator().showProgressLoader()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val obj = RetrofitClient.retrofitInstance.create(ApiInterface::class.java)
                val call = obj.login(body)

                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>, response: Response<LoginResponse>
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

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
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