package com.example.vocalcoach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.vocalcoach.adapter.TaskAdapter;

import java.util.ArrayList;

public class VoiceTasksActivity extends AppCompatActivity {

    private ArrayList<Task> taskArrayList;
    private TaskAdapter adapter;
    private RecyclerView recyclerView;
    public MyDatabase db = new MyDatabase(VoiceTasksActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        initData();

        taskArrayList = new ArrayList<>();

        taskArrayList = db.readTasks();

        adapter = new TaskAdapter(VoiceTasksActivity.this, taskArrayList);
        recyclerView = findViewById(R.id.recyclerView);
        adapter.setItems(taskArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(VoiceTasksActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                 String task_name = taskArrayList.get(position).getName().substring(3);
                 String task_type = "" + taskArrayList.get(position).getName().charAt(0);
                 String task_video = "" + taskArrayList.get(position).getName().charAt(1);
                Bundle extras = new Bundle();
                extras.putString("TASK_NAME", task_name);
                extras.putString("TASK_TYPE", task_video);
                 if (task_type.equals("1")){
                        Intent intent = new Intent(VoiceTasksActivity.this, AudioPlayerActivity.class);
                        intent.putExtras(extras);
                        startActivity(intent);
                 }
                 else{
                         Intent intent = new Intent(VoiceTasksActivity.this, VideoPlayerActivity.class);
                         intent.putExtras(extras);
                         startActivity(intent);
                 }
                //Toast.makeText(VoiceTasksActivity.this, task_type, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    @Override
    protected void onStop() {
        super.onStop();
        clearData();
    }

    public void initData(){
        db.putTask(new Task("112СОГЛАСНЫЕ СЛОГИ", 2, "https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/kitten.mp4?alt=media&token=18a69d77-f78f-4f02-aae2-3d6e27d51644", 1, R.drawable.articulation));
        db.putTask(new Task("141РЯДЫ ГЛАСНЫХ", 2, "https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/kitten.mp4?alt=media&token=18a69d77-f78f-4f02-aae2-3d6e27d51644", 2, R.drawable.articulation));
        db.putTask(new Task("153ГРУППЫ СОГЛАСНЫХ", 1, "https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/kitten.mp4?alt=media&token=18a69d77-f78f-4f02-aae2-3d6e27d51644", 3, R.drawable.articulation));
        db.putTask(new Task("213СТИХОТВОРЕНИЕ (ВАРИАНТ 1)", 1, "https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/kitten.mp4?alt=media&token=18a69d77-f78f-4f02-aae2-3d6e27d51644", 4, R.drawable.articulation));
        db.putTask(new Task("223СТИХОТВОРЕНИЕ (ВАРИАНТ 2)", 1, "https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/kitten.mp4?alt=media&token=18a69d77-f78f-4f02-aae2-3d6e27d51644", 5, R.drawable.articulation));
        //db.putTask(new Task("11ГАММЫ", 1, "https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/kitten.mp4?alt=media&token=18a69d77-f78f-4f02-aae2-3d6e27d51644", 6, R.drawable.articulation));
        //db.putTask(new Task("kitten test", 1, "https://drive.google.com/file/d/1RjoY6GMJVyk_6alItJpkWv1ZBNFJjpd5/view", 3, R.drawable.aircontrol));
    }

    public void clearData(){
        db.clear();
    }

}