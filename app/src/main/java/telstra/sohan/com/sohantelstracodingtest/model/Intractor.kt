package telstra.sohan.com.sohantelstracodingtest.model

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import telstra.sohan.com.sohantelstracodingtest.presenter.GetDataCallBack
import telstra.sohan.com.sohantelstracodingtest.utility.Constants
import java.util.*


class Intractor(private val mOnGetDatalistener: GetDataCallBack.onGetDataListener) : GetDataCallBack.Interactor {
    internal var factsDataDto: List<FactsDataDto>? = ArrayList()
    override fun initRetrofitCall(context: Context) {

            val gson = GsonBuilder()
                    .setLenient()
                    .create()
            val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            val request = retrofit.create<FactsApiResponse>(FactsApiResponse::class.java)
            val call = request.dta
            call.enqueue(object : Callback<FactsResponseDto> {
                override fun onResponse(call: Call<FactsResponseDto>, response: Response<FactsResponseDto>) {
                    val jsonResponse = response.body()
                    factsDataDto = jsonResponse.listFactsDto
                    mOnGetDatalistener.onSuccess(factsDataDto!!)
                }

                override fun onFailure(call: Call<FactsResponseDto>, t: Throwable) {
                    Log.v("Error", t.message)
                    mOnGetDatalistener.onFailure(t.message!!)
                }
            })

    }
}
