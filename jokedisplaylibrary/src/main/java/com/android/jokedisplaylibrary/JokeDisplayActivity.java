package com.android.jokedisplaylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class JokeDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        TextView jokeTextView = (TextView) findViewById(R.id.joke_text_view);

        Intent receivedData = getIntent();
        String joke = receivedData.getStringExtra("joke");
        jokeTextView.setText(joke);

    }
}
