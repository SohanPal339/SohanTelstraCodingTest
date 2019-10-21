package telstra.sohan.com.sohantelstracodingtest.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ResponseDto {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<RowsDto> getDta();
}
