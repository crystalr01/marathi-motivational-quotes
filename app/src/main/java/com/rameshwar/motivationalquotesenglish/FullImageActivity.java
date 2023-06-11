package com.rameshwar.motivationalquotesenglish;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FullImageActivity extends AppCompatActivity {

    private String url;
    private ImageView fullImage;
    private TextView full_news,full_time,full_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image1);

        ImageView fullImage = findViewById(R.id.full_image);
        url = getIntent().getStringExtra("image");

        Glide.with(this).load(url).into(fullImage);

        full_news = findViewById(R.id.full_text);
        full_time =findViewById(R.id.custom_time3);
        full_date = findViewById(R.id.custom_date3);

        String news_full = getIntent().getStringExtra("newsText");
        String time_full = getIntent().getStringExtra("time");
        String date_full = getIntent().getStringExtra("date");

        full_news.setText(news_full);
        full_time.setText(time_full);
        full_date.setText(date_full);

    }
}