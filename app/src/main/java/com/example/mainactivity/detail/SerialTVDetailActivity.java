package com.example.mainactivity.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mainactivity.R;
import com.example.mainactivity.model.SerialTV;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SerialTVDetailActivity extends AppCompatActivity {

    private TextView tvNameSerial,tvTglSerial,tvDescSerial,tvGenresSerial;
    private ImageView posterTV,backdropTV;
    private ProgressBar pb_detail_tv;
    private ScrollView scrollViewTV;
    private String movieNameSerial,movieTglSerial,movieDescSerial,imgSerialTV,backDropSerialTV;
    private RatingBar rateDetailTV;
    private float rateDetailTV_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_tv_detail);

        onBindDeclareTV();
        onGetDataTV();
        onSetDataTV();

    }

    private void onBindDeclareTV() {

        tvNameSerial= findViewById(R.id.title_detail_serial);
        tvTglSerial = findViewById(R.id.tgl_detail_serial);
        tvDescSerial= findViewById(R.id.detail_description_serial);
        posterTV = findViewById(R.id.image_detail_serial);
        backdropTV = findViewById(R.id.backdrop_detail_serial);
        scrollViewTV = findViewById(R.id.scrollViewTV);
        pb_detail_tv = findViewById(R.id.loading_tv_detail);
        rateDetailTV = findViewById(R.id.RATE_TV_detail);
        tvGenresSerial = findViewById(R.id.genre_tv_detail);

    }

    private void onGetDataTV() {
        Intent moveIntent = getIntent();
        SerialTV dataSerialTV= moveIntent.getParcelableExtra("GET_DATA_SERIAL_TV");
        getDetailDataTV(dataSerialTV.getID_TV());

        movieNameSerial= dataSerialTV.getSerialNameTV();
        movieTglSerial= dataSerialTV.getTglSerial();
        movieDescSerial= dataSerialTV.getDescSerial();
        imgSerialTV = dataSerialTV.getPictureTV();
        backDropSerialTV = dataSerialTV.getBackdropPictTV();
        rateDetailTV_2 = (float) dataSerialTV.getRateTV();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(dataSerialTV.getSerialNameTV());
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    private void onSetDataTV() {

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/original/"+ backDropSerialTV)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(backdropTV);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/original/"+ imgSerialTV)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(posterTV);

        tvNameSerial.setText(movieNameSerial);
        tvTglSerial.setText(movieTglSerial);
        tvDescSerial.setText(movieDescSerial);
        rateDetailTV.setRating(rateDetailTV_2 / 2);

    }


    private void getDetailDataTV(int id_tv) {
        String url = "https://api.themoviedb.org/3/movie/" + id_tv + "?api_key=6acbbbb9dc49a42c7a5afa2490cd87b1&language=en-US";

        AsyncHttpClient tvClient = new AsyncHttpClient();

        tvClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pb_detail_tv.setVisibility(View.GONE);
                scrollViewTV.setVisibility(View.VISIBLE);
                try {
                    String responeAPI = new String(responseBody);
                    JSONObject objectAPI = new JSONObject(responeAPI);

                    JSONArray jsonArray = objectAPI.getJSONArray("genres");
                    List<String> genreList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String genreName = jsonObject.getString("name");
                        genreList.add(genreName);
                    }
                    String genres = TextUtils.join(", ", genreList);
                    tvGenresSerial.setText(genres);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("onFailure", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


}
