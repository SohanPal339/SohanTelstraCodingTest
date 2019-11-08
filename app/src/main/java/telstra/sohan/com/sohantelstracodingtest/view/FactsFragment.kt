package telstra.sohan.com.sohantelstracodingtest.view


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import telstra.sohan.com.sohantelstracodingtest.R
import telstra.sohan.com.sohantelstracodingtest.adapter.UserContentFactsAdapter
import telstra.sohan.com.sohantelstracodingtest.model.FactsDataDto
import telstra.sohan.com.sohantelstracodingtest.presenter.GetDataCallBack
import telstra.sohan.com.sohantelstracodingtest.presenter.Presenter

class FactsFragment : Fragment(), GetDataCallBack.View, SwipeRefreshLayout.OnRefreshListener {

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val notConnected = intent.getBooleanExtra(ConnectivityManager
                    .EXTRA_NO_CONNECTIVITY, false)
            if (notConnected) {
                disconnected()
            } else {
                connected()
            }
        }
    }

    private val listStateKey = "recycler_list_state"
    private var listState: Parcelable? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var userContentFactsAdapter: UserContentFactsAdapter
    private var mPresenter: Presenter? = null
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //Inflate the layout for this fragment.
        val view = inflater?.inflate(R.layout.facts_fragment, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler) as RecyclerView
        linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager

        mPresenter = Presenter(this)

        // SwipeRefreshLayout
        mSwipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container) as SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener(this)
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark)

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post {
            mSwipeRefreshLayout.isRefreshing = true

            // Fetching data from server
            loadRecyclerViewData()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        context?.registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(broadcastReceiver)
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        // Save list state
        listState = linearLayoutManager.onSaveInstanceState()
        state.putParcelable(listStateKey, listState)
    }

    override fun onViewStateRestored(state: Bundle?) {
        super.onViewStateRestored(state)
        // Retrieve list state and list/item positions
        state?.let {
            listState = state.getParcelable(listStateKey)
        }
    }

    override fun onResume() {
        super.onResume()
        listState?.let {
            linearLayoutManager.onRestoreInstanceState(listState)
        }
    }

    private fun loadRecyclerViewData() {
        // Showing refresh animation before making http call
        mSwipeRefreshLayout.isRefreshing = true
        activity?.let { mPresenter!!.getCall(it) }

    }

    override fun onGetDataSuccess(listFactsDataDto: List<FactsDataDto>, title: String) {
        // set title
        (activity as MainActivity).supportActionBar?.title = title
        // Stopping swipe refresh
        mSwipeRefreshLayout.isRefreshing = false
        userContentFactsAdapter = context?.let { UserContentFactsAdapter(it, listFactsDataDto) }!!
        recyclerView.adapter = userContentFactsAdapter
    }

    override fun onGetDataFailure(message: String) {
        // Stopping swipe refresh
        mSwipeRefreshLayout.isRefreshing = false
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        loadRecyclerViewData()
    }

    private fun disconnected() {
        recyclerView.visibility = View.INVISIBLE
        Toast.makeText(activity, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
    }

    private fun connected() {
        recyclerView.visibility = View.VISIBLE
        loadRecyclerViewData()
    }
}
