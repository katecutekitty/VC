package com.example.vocalcoach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.app.AlarmManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NotificationActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    public static final String MESSAGE_STATUS = "message_status";
    Button delay;
    Button delete;
    TextView existingNotification;
    TextView notification;
    CheckBox check;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notification = (TextView) findViewById(R.id.notification);
        existingNotification = (TextView) findViewById(R.id.next_notification_view);
        delete = (Button) findViewById(R.id.delete);
        delay = (Button) findViewById(R.id.delay_5);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        check = (CheckBox) findViewById(R.id.checkbox);
        Calendar now = Calendar.getInstance();

        timePicker.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(now.get(Calendar.MINUTE));

        final WorkManager manager = WorkManager.getInstance();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime timeAt = LocalDate.now().atTime(13, 0);
        }
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hours = timePicker.getCurrentHour().toString();
                if (hours.length()==1){
                    hours = "0" + hours;
                }
                String minutes = timePicker.getCurrentMinute().toString();
                if (minutes.length()==1){
                    minutes = "0" + minutes;
                }
                notification.setText(hours + ":" + minutes);
                delete.setVisibility(View.VISIBLE);
                existingNotification.setVisibility(View.VISIBLE);
                delete.setClickable(true);
                notification.setVisibility(View.VISIBLE);
                Date date = new Date();
                String strDateFormat = "hh:mm:ss a";
                DateFormat dF = new SimpleDateFormat(strDateFormat);
                String fDate = dF.format(date);
                String curHours = fDate.substring(0,2);
                String curMinutes = fDate.substring(3,5);
                String curSeconds = fDate.substring(6,8);
                /*Toast.makeText(NotificationActivity.this, curHours + " "
                        + curMinutes + " " + curSeconds, Toast.LENGTH_SHORT).show();*/
                int delayTimeHours = Integer.parseInt(hours) - Integer.parseInt(curHours);
                if (Integer.parseInt(hours) - Integer.parseInt(curHours)<2){
                    delayTimeHours = 0;
                }
                int delayTimeMinutes = Integer.parseInt(minutes) - Integer.parseInt(curMinutes);
                if (Integer.parseInt(minutes) - Integer.parseInt(curMinutes)<2){
                    delayTimeMinutes = 0;
                }
                int delayTimeSeconds = 60 - Integer.parseInt(curSeconds);
                int delayTime = delayTimeHours * 60 * 60 + delayTimeMinutes * 60 + delayTimeSeconds;
                if (check.isChecked()==true) {
                    PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(NotifyWorker.class, 15, TimeUnit.SECONDS, 5, TimeUnit.SECONDS).build();
                    manager.enqueue(request);
                }
                else {
                    OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(NotifyWorker.class).setInitialDelay(delayTime, TimeUnit.SECONDS).build();
                    manager.enqueue(request);
                }
            }
        });
    }
    public void onDelete(View view){
        delete.setVisibility(View.INVISIBLE);
        delete.setClickable(false);
        notification.setVisibility(View.INVISIBLE);
        existingNotification.setVisibility(View.INVISIBLE);
    }

}