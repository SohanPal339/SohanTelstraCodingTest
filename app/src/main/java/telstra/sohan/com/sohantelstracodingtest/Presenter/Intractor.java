package telstra.sohan.com.sohantelstracodingtest.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import telstra.sohan.com.sohantelstracodingtest.Model.ResponseDto;
import telstra.sohan.com.sohantelstracodingtest.Model.RowsDataDto;
import telstra.sohan.com.sohantelstracodingtest.Model.RowsDto;


public class Intractor implements GetDataCallBack.Interactor{
    private GetDataCallBack.onGetDataListener mOnGetDatalistener;
    List<RowsDataDto> rowsDataDto = new ArrayList<>();

    public Intractor(GetDataCallBack.onGetDataListener mOnGetDatalistener){
        this.mOnGetDatalistener = mOnGetDatalistener;
    }
    @Override
    public void initRetrofitCall(Context context, String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ResponseDto request = retrofit.create(ResponseDto.class);
        retrofit2.Call<RowsDto> call = request.getDta();
        call.enqueue(new retrofit2.Callback<RowsDto>() {
            @Override
            public void onResponse(retrofit2.Call<RowsDto> call, retrofit2.Response<RowsDto> response) {
                RowsDto jsonResponse = response.body();
                rowsDataDto = jsonResponse.getListRowsDto();
                String title = jsonResponse.getTitle();
                Log.d("Data", "Refreshed");
                mOnGetDatalistener.onSuccess("Success", rowsDataDto,title);



            }
            @Override
            public void onFailure(retrofit2.Call<RowsDto> call, Throwable t) {
                Log.v("Error",t.getMessage());
                mOnGetDatalistener.onFailure(t.getMessage());
            }
        });
    }
}
