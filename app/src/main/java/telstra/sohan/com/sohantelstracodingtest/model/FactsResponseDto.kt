package telstra.sohan.com.sohantelstracodingtest.model

import com.google.gson.annotations.SerializedName


class FactsResponseDto {

    @SerializedName("rows")
    var listFactsDto: List<FactsDataDto>? = null

    @SerializedName("title")
    var title: String? = null
}

