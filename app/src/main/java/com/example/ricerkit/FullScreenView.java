package com.example.ricerkit;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.squareup.picasso.Picasso;


public class FullScreenView extends AppCompatActivity {


    com.github.chrisbanes.photoview.PhotoView fullscreenphoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_view);

        fullscreenphoto = findViewById(R.id.fullscreenimage);

        String imgurl = getIntent().getStringExtra("ImageUrl");
        Picasso.get().load(imgurl).into(fullscreenphoto);


    }
}RicerKit