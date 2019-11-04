package telstra.sohan.com.sohantelstracodingtest.model

import retrofit2.Call
import retrofit2.http.GET
import telstra.sohan.com.sohantelstracodingtest.utility.Constants

interface FactsApiResponse {
    @get:GET(Constants.FACTS_API)
    val dta: Call<FactsResponseDto>
}
