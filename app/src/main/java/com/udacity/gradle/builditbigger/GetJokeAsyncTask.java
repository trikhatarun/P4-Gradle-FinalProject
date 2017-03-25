package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.example.trikh.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by trikh on 25-03-2017.
 */

public class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {
    private MyApi mApiService = null;

    @Override
    protected String doInBackground(Void... params) {
        if (mApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            mApiService = builder.build();
        }
        try {
            return mApiService.tellJokeGCE().execute().getData();
        } catch (IOException e) {
            Log.v("Error", e.toString());
            return null;
        }
    }
}
