package telstra.sohan.com.sohantelstracodingtest.Presenter;

import android.content.Context;

import java.util.List;

import telstra.sohan.com.sohantelstracodingtest.Model.RowsDataDto;


public interface GetDataCallBack {
    interface View{
        void onGetDataSuccess(String message, List<RowsDataDto> list,String title);
        void onGetDataFailure(String message);
    }
    interface Presenter{
        void getDataFromURL(Context context, String url);
    }
    interface Interactor{
        void initRetrofitCall(Context context, String url);

    }
    interface onGetDataListener{
        void onSuccess(String message, List<RowsDataDto> list, String title);
        void onFailure(String message);
    }
}
