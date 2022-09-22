package com.example.fitnessapp.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.fitnessapp.MainActivity;
import com.example.fitnessapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    Timer timer = new Timer ();
    public static final String CHANNEL_1_ID = "channel1";
    private NotificationManagerCompat notificationManager;
    CountDownTimer countDownTimer= null;
    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");



            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }



// schedule the task to run starting now and then every hour...



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {




        Intent pending = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, pending, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_gain)
                .setContentTitle("Water reminder")
                .setContentText("Time to drink water !!!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE).setContentIntent(pendingIntent)
                .build();

        notificationManager = NotificationManagerCompat.from(this);
        TimerTask hourlyTask = new TimerTask () {
            @Override
            public void run () {
                createNotificationChannels();
                notificationManager.notify(1, notification);
            }
        };

        timer.schedule (hourlyTask, 0l, 1000*60*60);


        return START_STICKY;
    }

    @Override
    public void onDestroy() {

timer.cancel();
    }
}
