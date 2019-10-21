package telstra.sohan.com.sohantelstracodingtest.View;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import telstra.sohan.com.sohantelstracodingtest.Adapter.MyAdapter;
import telstra.sohan.com.sohantelstracodingtest.Model.RowsDataDto;
import telstra.sohan.com.sohantelstracodingtest.Presenter.GetDataCallBack;
import telstra.sohan.com.sohantelstracodingtest.Presenter.Presenter;
import telstra.sohan.com.sohantelstracodingtest.R;

public class MainActivity extends AppCompatActivity implements GetDataCallBack.View, SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MyAdapter myAdapter;
    private Presenter mPresenter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        mPresenter = new Presenter(this);

        // SwipeRefreshLayout
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(true);

                // Fetching data from server
                loadRecyclerViewData();
            }
        });

    }

    private void loadRecyclerViewData() {
        // Showing refresh animation before making http call
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.getDataFromURL(getApplicationContext(), "");
    }

    @Override
    public void onGetDataSuccess(String message, List<RowsDataDto> list,String title) {
        // set title
        if(title!=null){
            setTitle(title);
        }
        // Stopping swipe refresh
        mSwipeRefreshLayout.setRefreshing(false);
        myAdapter = new MyAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onGetDataFailure(String message) {
        // Stopping swipe refresh
        mSwipeRefreshLayout.setRefreshing(false);
        Log.d("Status", message);
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        loadRecyclerViewData();
    }
}
