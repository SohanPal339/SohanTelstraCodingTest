package telstra.sohan.com.sohantelstracodingtest.view

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import telstra.sohan.com.sohantelstracodingtest.R
import telstra.sohan.com.sohantelstracodingtest.adapter.UserContentFactsAdapter
import telstra.sohan.com.sohantelstracodingtest.model.FactsDataDto
import telstra.sohan.com.sohantelstracodingtest.presenter.GetDataCallBack
import telstra.sohan.com.sohantelstracodingtest.presenter.Presenter

class MainActivity : AppCompatActivity(), GetDataCallBack.View, SwipeRefreshLayout.OnRefreshListener {

    val LIST_STATE_KEY = "recycler_list_state"
    private var listState: Parcelable? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var userContentFactsAdapter: UserContentFactsAdapter
    private var mPresenter: Presenter? = null
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recycler) as RecyclerView
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager

        mPresenter = Presenter(this)

        // SwipeRefreshLayout
        mSwipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_container) as SwipeRefreshLayout
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

    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        // Save list state
        listState = linearLayoutManager.onSaveInstanceState()
        state.putParcelable(LIST_STATE_KEY, listState)
    }

    override fun onRestoreInstanceState(state: Bundle?) {
        super.onRestoreInstanceState(state)
        // Retrieve list state and list/item positions
        if (state != null)
            listState = state.getParcelable(LIST_STATE_KEY)
    }

    override fun onResume() {
        super.onResume()
        if (listState != null) {
            linearLayoutManager.onRestoreInstanceState(listState)
        }
    }

    private fun loadRecyclerViewData() {
        // Showing refresh animation before making http call
        mSwipeRefreshLayout.isRefreshing = true
        mPresenter!!.getCall(applicationContext)

    }

    override fun onGetDataSuccess(listFactsDataDto: List<FactsDataDto>) {

        // Stopping swipe refresh
        mSwipeRefreshLayout.isRefreshing = false
        userContentFactsAdapter = UserContentFactsAdapter(applicationContext, listFactsDataDto)
        recyclerView.adapter = userContentFactsAdapter
    }

    override fun onGetDataFailure(message: String) {
        // Stopping swipe refresh
        mSwipeRefreshLayout.isRefreshing = false
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        loadRecyclerViewData()
    }
}
