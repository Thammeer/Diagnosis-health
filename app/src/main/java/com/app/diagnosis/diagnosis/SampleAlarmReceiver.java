package com.app.diagnosis.diagnosis;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.NotificationCompat;

import java.util.Calendar;


public class SampleAlarmReceiver extends WakefulBroadcastReceiver {
     AlarmManager alarmMgr;
     PendingIntent alarmIntent;
    @Override
    public void onReceive(Context context, Intent intent) {

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Bundle content = intent.getExtras();

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context);
        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(content.getString("note"));
        bigText.setBigContentTitle(content.getString("name"));
        builder.setStyle(bigText);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setContentTitle("Diagnosis App");
        builder.setContentText(intent.getExtras().getString("name"));
        builder.setSound(soundUri);


        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.putExtra("id", content.getInt("id"));
        resultIntent.setAction(Intent.ACTION_MAIN);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, content.getInt("id"),resultIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, builder.build());


        if(content.getString("choice").matches("Daily")){
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, SampleAlarmReceiver.class);
            intent.putExtra("name", content.getString("name"));
        i.putExtra("id", content.getInt("id"));
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c.set(Calendar.MONTH, c.get(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, content.getInt("hour"));
        c.set(Calendar.MINUTE, content.getInt("minute"));
        long time = c.getTimeInMillis();
        time = time + 1000*60*60*24;
        c.setTimeInMillis(time);
        intent.putExtra("year", c.get(Calendar.YEAR));
        intent.putExtra("month", c.get(Calendar.MONTH));
        intent.putExtra("day", c.get(Calendar.DAY_OF_MONTH));
        intent.putExtra("hour", c.get(Calendar.HOUR_OF_DAY));
        intent.putExtra("minute", c.get(Calendar.MINUTE));
        intent.putExtra("note", content.getString("note"));
        alarmIntent = PendingIntent.getBroadcast(context, content.getInt("id"), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), alarmIntent);

    }



    }

    public void setAlarm(Context context, Intent i) {

        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, SampleAlarmReceiver.class);
        Bundle content = i.getExtras();

        intent.putExtra("name", content.getString("name"));
        intent.putExtra("id", content.getInt("id"));
        intent.putExtra("year", content.getInt("year"));
        intent.putExtra("month", content.getInt("month"));
        intent.putExtra("day", content.getInt("day"));
        intent.putExtra("hour", content.getInt("hour"));
        intent.putExtra("minute", content.getInt("minute"));
        intent.putExtra("choice", content.getString("choice"));
        intent.putExtra("note", content.getString("note"));

        alarmIntent = PendingIntent.getBroadcast(context, content.getInt("id"), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.YEAR, content.getInt("year"));
        calendar.set(Calendar.MONTH, content.getInt("month"));
        calendar.set(Calendar.DAY_OF_MONTH, content.getInt("day"));
        calendar.set(Calendar.HOUR_OF_DAY, content.getInt("hour"));
        calendar.set(Calendar.MINUTE, content.getInt("minute"));
        calendar.set(Calendar.SECOND, 0);

            alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);





    }
}
