package com.example.mainactivity.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mainactivity.R;
import com.example.mainactivity.model.SerialTV;

public class SerialTVDetailActivity extends AppCompatActivity {

    private TextView tvNameSerial,tvTglSerial,tvDescSerial,tvCrewSerial,tvCrewDetailSerial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_tv_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent moveIntent = getIntent();
        SerialTV dataSerialTV= moveIntent.getParcelableExtra("GET_DATA_SERIAL_TV");
        String movieNameSerial= dataSerialTV.getNamaSerial();
        String movieTglSerial= dataSerialTV.getTglSerial();
        String movieCrewSerial= dataSerialTV.getCrewSerial();
        String moviePositionSerial= dataSerialTV.getCrewDetailSerial();
        String movieDescSerial= dataSerialTV.getDescSerial();

        getSupportActionBar().setTitle(movieNameSerial);

        ImageView imageViewBDSerial = findViewById(R.id.backdrop_detail_serial);
        Glide.with(this)
                .load(dataSerialTV.getPicture())
                .into(imageViewBDSerial);

        ImageView imageViewSerial = findViewById(R.id.image_detail_serial);
        Glide.with(this)
                .load(dataSerialTV.getPicture())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageViewSerial);

        tvNameSerial= findViewById(R.id.title_detail_serial);
        tvNameSerial.setText(movieNameSerial);
        tvTglSerial = findViewById(R.id.tgl_detail_serial);
        tvTglSerial.setText(movieTglSerial);
        tvDescSerial= findViewById(R.id.detail_description_serial);
        tvDescSerial.setText(movieDescSerial);
        tvCrewSerial =findViewById(R.id.crew_detail_serial);
        tvCrewSerial.setText(movieCrewSerial);
        tvCrewDetailSerial =findViewById(R.id.position_detail_serial);
        tvCrewDetailSerial.setText(moviePositionSerial);


    }
}
