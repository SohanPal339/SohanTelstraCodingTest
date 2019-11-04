package telstra.sohan.com.sohantelstracodingtest.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import telstra.sohan.com.sohantelstracodingtest.R;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Sohan Jangid on 21-10-2019.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    MainActivity activity = null;
    @Before
    public void setUp() throws Exception {
        activity = mActivityRule.getActivity();

    }

    @Test
    public void launch() throws Exception {
        View view = activity.findViewById(R.id.recycler);
        assertNotNull(view);
    }



    @After
    public void tearDown() throws Exception {
        activity = null;
    }

}