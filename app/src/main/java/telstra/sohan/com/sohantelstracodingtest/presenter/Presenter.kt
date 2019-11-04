package telstra.sohan.com.sohantelstracodingtest.presenter

import android.content.Context
import telstra.sohan.com.sohantelstracodingtest.model.FactsDataDto
import telstra.sohan.com.sohantelstracodingtest.model.Intractor


class Presenter(private val mGetDataView: GetDataCallBack.View) : GetDataCallBack.Presenter, GetDataCallBack.onGetDataListener {
    private val mIntractor: Intractor

    init {
        mIntractor = Intractor(this)
    }

    override fun getCall(context: Context) {
        mIntractor.initRetrofitCall(context)
    }


    override fun onSuccess(listFactsDataDto: List<FactsDataDto>) {
        mGetDataView.onGetDataSuccess(listFactsDataDto)
    }

    override fun onFailure(message: String) {
        mGetDataView.onGetDataFailure(message)
    }
}
