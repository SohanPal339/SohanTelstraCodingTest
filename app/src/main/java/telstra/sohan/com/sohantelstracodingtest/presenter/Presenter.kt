package telstra.sohan.com.sohantelstracodingtest.presenter

import android.content.Context
import telstra.sohan.com.sohantelstracodingtest.model.FactsDataDto
import telstra.sohan.com.sohantelstracodingtest.model.Interact


class Presenter(private val mGetDataView: GetDataCallBack.View) : GetDataCallBack.Presenter, GetDataCallBack.OnGetDataListener {
    private val mInteract: Interact = Interact(this)

    override fun getCall(context: Context) {
        mInteract.initRetrofitCall(context)
    }

    override fun onSuccess(listFactsDataDto: List<FactsDataDto>, title: String) {
        mGetDataView.onGetDataSuccess(listFactsDataDto, title)
    }

    override fun onFailure(message: String) {
        mGetDataView.onGetDataFailure(message)
    }
}
