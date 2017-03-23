package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.jokedisplaylibrary.JokeDisplayActivity;
import com.example.trikh.myapplication.backend.myApi.MyApi;
import com.example.trikh.myapplication.backend.myApi.model.MyBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        Log.v("position: ", "on click executed");
        new GetJokeAsyncTask().execute(this);
    }

    private class GetJokeAsyncTask extends AsyncTask<Context, Void, MyBean> {
        private Context mContext;
        private MyApi mApiService = null;

        @Override
        protected MyBean doInBackground(Context... params) {
            if (mApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://192.168.1.6:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                mApiService = builder.build();
            }
            mContext = params[0];
            try {
                return mApiService.tellJokeGCE().execute();
            } catch (IOException e) {
                Log.v("Error", e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(MyBean myBean) {
            Intent displayJokeIntent = new Intent(mContext, JokeDisplayActivity.class);
            displayJokeIntent.putExtra("joke", myBean.getData());
            startActivity(displayJokeIntent);
        }
    }
}
