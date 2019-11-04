package telstra.sohan.com.sohantelstracodingtest.model


import com.google.gson.annotations.SerializedName

class FactsDataDto {

    @SerializedName("title")
    var title: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("imageHref")
    var imageHref: String? = null
}

