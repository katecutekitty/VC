package com.example.vocalcoach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocalcoach.PhysTasksActivity;
import com.example.vocalcoach.R;
import com.example.vocalcoach.Task;
import com.example.vocalcoach.VoiceTasksActivity;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    Context context;
    private ArrayList<Task> taskList;

    public TaskAdapter(VoiceTasksActivity context, ArrayList<Task> taskList){
        this.taskList = taskList;
        this.context = context;
    }

    public TaskAdapter(PhysTasksActivity context, ArrayList<Task> taskList){
        this.taskList = taskList;
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        ImageView taskIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.task_name);
            taskIcon = itemView.findViewById(R.id.task_icon);
        }
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskName.setText(""+task.getName().substring(3));
        String name = task.getName();
        String type = name.substring(2, 3);
        if (type.equals("1")){
                holder.taskIcon.setImageResource(R.drawable.icon);
        }
        if (type.equals("2")) {
            holder.taskIcon.setImageResource(R.drawable.aircontrol);
        }
        if (type.equals("3")){
                holder.taskIcon.setImageResource(R.drawable.articulation);
        }
    }

    @Override
    public int getItemCount(){
        return taskList.size();
    }

    @Override
    public long getItemId(int position){
        return taskList.get(position).getId();
    }

    public void setItems(ArrayList<Task> items){
        this.taskList = items;
        notifyDataSetChanged();
    }
}


