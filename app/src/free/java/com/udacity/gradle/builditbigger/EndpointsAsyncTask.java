package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.MyJokes;
import com.example.goodbox.myapplication.backend.myApi.MyApi;
import com.example.goodbox.myapplication.backend.myApi.model.MyBean;
import com.example.jokeactivity.JokeActivity;
import com.example.jokeactivity.JokeActivityFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Goodbox on 23-12-2016.
 */

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private InterstitialAd interstitialAd;
    private String mResult;
    private ProgressBar progressBar;

    public EndpointsAsyncTask(Context context, ProgressBar progressBar) {

        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(context.getString(R.string.root_url_api));

            // end options for devappserver
            myApiService = builder.build();
        }

        try {
            return myApiService.putJoke(new MyBean()).execute().getJokes();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mResult = result;
        // Setting the interstitial ad
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(context.getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                interstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                startJokeActivity();
            }

            @Override
            public void onAdClosed() {
                startJokeActivity();
            }
        });

        AdRequest ad = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(context.getString(R.string.device_id))
                .build();
        interstitialAd.loadAd(ad);
    }

    private void startJokeActivity() {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivityFragment.ARG_JOKE, mResult);
        context.startActivity(intent);
    }
}
