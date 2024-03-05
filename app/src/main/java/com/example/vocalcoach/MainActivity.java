package com.example.vocalcoach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTasksClick(View view){
        Intent intent = new Intent(this, TaskCategories.class);
        startActivity(intent);
    }
    public void onAlarmClick(View view){
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }
    public void onExitClick(View view){
        this.finishAndRemoveTask();
    }

}