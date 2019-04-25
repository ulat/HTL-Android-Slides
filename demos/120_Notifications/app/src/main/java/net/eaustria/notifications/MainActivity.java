package net.eaustria.notifications;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;
import static android.support.v4.app.NotificationCompat.*;

public class MainActivity extends AppCompatActivity {

    protected static final String CHANNEL_ID = "12345";
    private static final String ACTION_SNOOZE = "SNOOZE";
    protected static final int REQUEST_CODE_HELP = 456;
    protected static final String KEY_INTENT_HELP = "Direct_reply_intent";
    protected static final int NOTIFICATION_ID = 789;
    protected static final String NOTIFICATION_REPLY = "direct_reply_notification";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String GROUP_KEY_EMAILS = "Email_Notification_Group";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
    }

    public void showNotification(View view) {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, NotificationDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Notice that the Notification.Builder constructor requires a channel ID.
        // This is required for compatibility with Android 8.0 (API level 26) and higher,
        // but is ignored by older versions.
        Builder builder  = new Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.star_big_on)
                .setColor(Color.YELLOW)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("This is just a small Notification")
                .setWhen(System.currentTimeMillis())
                .setPriority(PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        int notificationId = 1;
        notificationManager.notify(notificationId, builder.build());
    }



    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public void showSnoozeNotification(View view) {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, NotificationDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent, 0);

        Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);

        Builder builder = new Builder(
                this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.alert_dark_frame)
                .setContentTitle("My notification with snooze button")
                .setContentText("This notification can be snoozed!")
                .setPriority(PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.ic_menu_zoom
                        , getString(R.string.snooze),
                        snoozePendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        int notificationId = 2;
        notificationManager.notify(notificationId, builder.build());
    }

    public void showDirectReplyNotification(View view) {

        //Pending intent for a notification button help
        PendingIntent helpPendingIntent = PendingIntent.getBroadcast(
                MainActivity.this,
                REQUEST_CODE_HELP,
                new Intent(MainActivity.this, MyBroadcastReceiver.class)
                        .putExtra(KEY_INTENT_HELP, REQUEST_CODE_HELP),
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        //We need this object for getting direct input from notification
        RemoteInput remoteInput = new RemoteInput.Builder(NOTIFICATION_REPLY)
                .setLabel("Please enter your question")
                .build();


        //For the remote input we need this action object
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(android.R.drawable.ic_delete,
                        "Reply Now...", helpPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        //Creating the notifiction builder object
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentTitle("Hey, we received your email! How can we help you?")
                .setContentText("How can we help you?")
                .setAutoCancel(true)
                .setContentIntent(helpPendingIntent)
                .addAction(action)
                .addAction(android.R.drawable.ic_menu_directions, "Help", helpPendingIntent);


        //finally displaying the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    public void showProgressBarNotification(View view) {
        final NotificationManager mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(android.R.drawable.ic_dialog_info);

        // Start a lengthy operation in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                int incr;
                // Do the "lengthy" operation 20 times
                for (incr = 0; incr <= 100; incr += 10) {
                    // Sets the progress indicator to a max value, the
                    // current completion percentage, and "determinate"
                    // state
                    mBuilder.setProgress(100, incr, false);
                    // Displays the progress bar for the first time.
                    mNotifyManager.notify(0, mBuilder.build());
                    // Sleeps the thread, simulating an operation
                    // that takes time
                    try {
                        // Sleep for 5 seconds
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        Log.d(TAG, "sleep failure");
                    }
                }
                // When the loop is finished, updates the notification
                mBuilder.setContentText("Download complete")
                        // Removes the progress bar
                        .setProgress(0, 0, false);
                mNotifyManager.notify(999, mBuilder.build());
                }
            // Starts the thread by calling the run() method in its Runnable
        }).start();
    }

    public void showGroupNotification(View view) {
        // Build the notification, setting the group appropriately
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("New mail from Max!")
            .setContentText("Hi out there! This is my mail! Cheers! Max")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setGroup(GROUP_KEY_EMAILS)
            .build();

        // Issue the notification
        NotificationManagerCompat notificationManager =
                                NotificationManagerCompat.from(this);
        notificationManager.notify(111, notification);

        // Sending another notification to the same group
        Notification notification2 = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("New mail from Erik!")
            .setContentText("Hi there! This is Erik!")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setGroup(GROUP_KEY_EMAILS)
            .build();
        notificationManager.notify(222, notification2);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
                android.R.drawable.ic_dialog_info);

        // Create an InboxStyle notification
        Notification summaryNotification = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("2 new messages")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setLargeIcon(largeIcon)
            .setStyle(new NotificationCompat.InboxStyle()
            .addLine("Alex Faaborg   Check this out")
            .addLine("Jeff Chang   Launch Party")
            .setBigContentTitle("2 new messages")
            .setSummaryText("johndoe@gmail.com"))
            .setGroup(GROUP_KEY_EMAILS)
            .setGroupSummary(true)
            .build();

        notificationManager.notify(333, summaryNotification);
    }
}