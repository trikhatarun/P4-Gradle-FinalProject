package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {
    @Test
    public void testDoInBackground() throws Exception {
        try {
            GetJokeAsyncTask endpointsAsyncTask = new GetJokeAsyncTask();
            endpointsAsyncTask.execute();
            String result = endpointsAsyncTask.get();

            assertNotNull(result);
            assertTrue(result.length() > 0);
        } catch (Exception e) {
            Log.e("EndpointsAsyncTaskTest", "testDoInBackground: Timed out");
        }
    }
}
