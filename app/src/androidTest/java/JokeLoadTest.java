import android.support.test.runner.AndroidJUnit4;

import android.util.Log;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Goodbox on 24-12-2016.
 */
@RunWith(AndroidJUnit4.class)
public class JokeLoadTest {

    private static final String LOG_TAG = "JokeLoadTest";
    String joke = null;

    @Test
    public void test() {
        Log.v("JokeLoadTest", "Running JokeLoadTest");
        EndpointsAsyncTask mEndpointsAsyncTask = new EndpointsAsyncTask(getContext(),null);
        mEndpointsAsyncTask.execute();
        try {
            joke = mEndpointsAsyncTask.get();
            Log.d(LOG_TAG, "Retrieved a joke successfully: " + joke);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(joke);
    }
}
