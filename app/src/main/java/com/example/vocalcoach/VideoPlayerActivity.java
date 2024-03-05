package com.example.vocalcoach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VideoPlayerActivity extends AppCompatActivity {

    TextView taskName;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    VideoView videoView;
    Uri videoUri;
    TextView desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        taskName = (TextView) findViewById(R.id.textView);
        desc = findViewById(R.id.description);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String name = extras.getString("TASK_NAME");
        String type = extras.getString("TASK_TYPE");
        taskName.setText(name);
        videoView = (VideoView) findViewById(R.id.videoView);
        if (type.equals("1")) {
            videoUri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/%D1%81%D1%82%D0%B8%D1%85%D0%BE%D1%82%D0%B2%D0%BE%D1%80%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%D0%B0%D1%80%D0%B8%D0%B0%D0%BD%D1%82%201.mp4?alt=media&token=595dc307-381c-4a43-b51d-f19eed104315");
            //СТИХОТВОРЕНИЕ ВАРИАНТ 1
        }
        if (type.equals("2")){
            videoUri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/%D1%81%D1%82%D0%B8%D1%85%D0%BE%D1%82%D0%B2%D0%BE%D1%80%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B2%D0%B0%D1%80%D0%B8%D0%B0%D0%BD%D1%82%202.mp4?alt=media&token=2de247fa-9def-44a7-a072-7570356ee202");
            //СТИХОТВОРЕНИЕ ВАРИАНТ 2
        }
        if (type.equals("3")){
            videoUri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/%D0%B2%D0%B2%D0%B5%D1%80%D1%85-%D0%B2%D0%BD%D0%B8%D0%B7.mp4?alt=media&token=95ffdbc9-3e89-4cde-9ec9-10eb70b77879");
            //ВВЕРХ-ВНИЗ
        }
        /*if (type.equals("4")){
            videoUri = Uri.parse("");
            //ВВЕРХ-ВНИЗ
        }
        if (type.equals("5")){
            videoUri = Uri.parse("");
            //ВВЕРХ-ВНИЗ
        }*/
        videoView.setVideoURI(videoUri);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.start();
    }
}