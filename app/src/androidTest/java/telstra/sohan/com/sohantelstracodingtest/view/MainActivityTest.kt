package telstra.sohan.com.sohantelstracodingtest.view

import android.support.test.runner.AndroidJUnit4
import com.google.gson.GsonBuilder
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import telstra.sohan.com.sohantelstracodingtest.model.FactsApiResponse
import telstra.sohan.com.sohantelstracodingtest.model.FactsDataDto
import telstra.sohan.com.sohantelstracodingtest.model.FactsResponseDto
import telstra.sohan.com.sohantelstracodingtest.presenter.GetDataCallBack
import telstra.sohan.com.sohantelstracodingtest.utility.Constants
import java.util.*


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Mock
    private val mOnGetDataListener: GetDataCallBack.OnGetDataListener? = null
    lateinit var call: Call<FactsResponseDto>
    private var factsDataDto: List<FactsDataDto>? = ArrayList()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        val request = retrofit.create<FactsApiResponse>(FactsApiResponse::class.java)
        call = request.dta
    }

    @Test
    @Throws(Exception::class)
    fun responseTest() {

        call.enqueue(object : Callback<FactsResponseDto> {
            override fun onResponse(call: Call<FactsResponseDto>, response: Response<FactsResponseDto>) {
                val jsonResponse = response.body()
                factsDataDto = jsonResponse.listFactsDto
                val title = jsonResponse.title
                factsDataDto?.let { title?.let { it1 -> Mockito.verify(mOnGetDataListener, Mockito.only())?.onSuccess(it, it1) } };

            }

            override fun onFailure(call: Call<FactsResponseDto>, t: Throwable) {

                Mockito.verify(mOnGetDataListener, Mockito.only())?.onFailure("error");
            }
        })
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        call.cancel()
    }

}