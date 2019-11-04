package telstra.sohan.com.sohantelstracodingtest.presenter

import android.content.Context
import telstra.sohan.com.sohantelstracodingtest.model.FactsDataDto


interface GetDataCallBack {
    interface View {
        fun onGetDataSuccess(listFactsDataDto: List<FactsDataDto>)
        fun onGetDataFailure(message: String)
    }

    interface Presenter {
        fun getCall(context: Context)
    }

    interface Interactor {
        fun initRetrofitCall(context: Context)

    }

   interface onGetDataListener {
        fun onSuccess(listFactsDataDto: List<FactsDataDto>)
        fun onFailure(message: String)
    }
}
