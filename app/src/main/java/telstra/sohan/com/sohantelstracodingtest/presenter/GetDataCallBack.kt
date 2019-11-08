package telstra.sohan.com.sohantelstracodingtest.presenter

import android.content.Context
import telstra.sohan.com.sohantelstracodingtest.model.FactsDataDto


interface GetDataCallBack {
    interface View {
        fun onGetDataSuccess(listFactsDataDto: List<FactsDataDto>, title: String)
        fun onGetDataFailure(message: String)
    }

    interface Presenter {
        fun getCall(context: Context)
    }

    interface Interact {
        fun initRetrofitCall(context: Context)
    }

    interface OnGetDataListener {
        fun onSuccess(listFactsDataDto: List<FactsDataDto>, title: String)
        fun onFailure(message: String)
    }
}
