package com.example.vocalcoach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TaskCategories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_categories);
    }
    public void toVoiceTasks(View view){
        Intent intent = new Intent(this, VoiceTasksActivity.class);
        startActivity(intent);
    }
    public void toPhysTasks(View view){
        Intent intent = new Intent(this, PhysTasksActivity.class);
        startActivity(intent);
    }
}