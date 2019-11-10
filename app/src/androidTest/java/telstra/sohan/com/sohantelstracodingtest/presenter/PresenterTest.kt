package telstra.sohan.com.sohantelstracodingtest.presenter

import android.support.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import telstra.sohan.com.sohantelstracodingtest.model.FactsDataDto
import telstra.sohan.com.sohantelstracodingtest.view.MainActivity

class PresenterTest {

    lateinit var presenter: Presenter
    @Mock
    var mGetDataView: GetDataCallBack.View? = null
    @Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)
    var activity: MainActivity? = null
    @Before
    fun setUp() {
        activity = mActivityRule.activity
        MockitoAnnotations.initMocks(this);
        presenter = mGetDataView?.let { Presenter(it) }!!
    }

    @Test
    fun getCall(){
        val listFactsDataDto = ArrayList<FactsDataDto>()
        activity?.let { presenter!!.getCall(it) }
        Mockito.verify(mGetDataView, Mockito.only())?.onGetDataSuccess(listFactsDataDto,"title");
        Mockito.verify(mGetDataView, Mockito.only())?.onGetDataFailure("error");
    }

    @After
    fun tearDown() {

    }
}