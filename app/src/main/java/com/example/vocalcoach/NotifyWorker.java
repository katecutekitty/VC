package com.example.vocalcoach;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotifyWorker extends Worker {
    private static final String WORK_RESULT = "work_result";

    public NotifyWorker(@NonNull Context context, @NonNull WorkerParameters parameters){
        super(context, parameters);
    }

    @NonNull
    @Override
    public Result doWork(){
        Data taskData = getInputData();
        String taskDataString = taskData.getString(NotificationActivity.MESSAGE_STATUS);
        triggerNotification("Напоминание", taskDataString != null? taskDataString: "Время петь!");
        Data output = new Data.Builder().putString(WORK_RESULT, "Jobs Finished").build();
        return Result.success(output);
    }
    private void triggerNotification(String task, String desc){
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "task_channel";
        String channelName = "task_name";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId).setContentTitle(task).setContentText(desc).setSmallIcon(R.mipmap.ic_launcher);

        manager.notify(1, builder.build());

    }
}
