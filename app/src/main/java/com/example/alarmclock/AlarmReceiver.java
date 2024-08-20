package com.example.alarmclock;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Create an explicit intent for an Activity in your app
        Intent nextActivity = new Intent(context, NotificationActivity.class);
        nextActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Create a PendingIntent for the notification
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                nextActivity,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE // Use correct flags
        );

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "androidknowledge")
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Reminder")
                .setContentText("It's time to wake up")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        // Check if the permission to post notifications is granted
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Notify the user with the notification
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(123, builder.build());
        } else {
            // Handle the case where notification permission is not granted
            // This might involve requesting permission if this were an Activity or informing the user in some way
        }
    }
}
