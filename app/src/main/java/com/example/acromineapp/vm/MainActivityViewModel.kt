package com.example.acromineapp.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acromineapp.model.AcronymResponseObject
import com.example.acromineapp.network.AcronymApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val acronymApi: AcronymApi
) : ViewModel() {

    private val _viewItems = MutableLiveData<List<AcronymResponseObject>>()
    val viewItems: LiveData<List<AcronymResponseObject>> = _viewItems


    fun getAcronymDetails(acronymType: String, searchString: String) {
       val call = if (acronymType == "sf") {
            acronymApi.getShortAcronymDetails(searchString)
        } else {
            acronymApi.getLongAcronymDetails(searchString)
        }

       call.enqueue(object : Callback<List<AcronymResponseObject>> {
         override fun onResponse(
             call: Call<List<AcronymResponseObject>>,
             response: Response<List<AcronymResponseObject>>
         ) {
             if (response.isSuccessful){
                 response.body()?.let { items ->
                     if (items.isNotEmpty()) {
                         _viewItems.value = items
                     } else {
                         _viewItems.value = emptyList()
                     }
                 }
             } else {
                 // response is null
                 Log.d("MainActivityViewModel", "Response is null")
             }
         }
         override fun onFailure(call: Call<List<AcronymResponseObject>>, t: Throwable) {
             Log.e("MainActivityViewModel", "Error Response:${t.message}")
         }
     })
   }

}