package telstra.sohan.com.sohantelstracodingtest.Presenter;

import android.content.Context;

import java.util.List;

import telstra.sohan.com.sohantelstracodingtest.Model.RowsDataDto;


public class Presenter implements GetDataCallBack.Presenter, GetDataCallBack.onGetDataListener {
    private GetDataCallBack.View mGetDataView;
    private Intractor mIntractor;
    public Presenter(GetDataCallBack.View mGetDataView){
        this.mGetDataView = mGetDataView;
        mIntractor = new Intractor(this);
    }
    @Override
    public void getDataFromURL(Context context, String url) {
        mIntractor.initRetrofitCall(context,url);
    }


    @Override
    public void onSuccess(String message, List<RowsDataDto> list, String title) {
        mGetDataView.onGetDataSuccess(message, list, title);
    }

    @Override
    public void onFailure(String message) {
        mGetDataView.onGetDataFailure(message);
    }
}
