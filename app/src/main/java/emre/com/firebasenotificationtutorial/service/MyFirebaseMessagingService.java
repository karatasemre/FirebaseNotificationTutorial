package emre.com.firebasenotificationtutorial.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import emre.com.firebasenotificationtutorial.R;
import emre.com.firebasenotificationtutorial.activities.MainActivity;
import emre.com.firebasenotificationtutorial.utility.LogManager;

/**
 * Created by Emre.Karatas on 16.10.2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        LogManager.writeLog(remoteMessage.getFrom());

        String whichActivity = null;

        if (remoteMessage.getData().size() > 0) {
            LogManager.writeLog(remoteMessage.getData().toString());

            whichActivity = remoteMessage.getData().get("act");
        }

        if (remoteMessage.getNotification() != null) {
            LogManager.writeLog(remoteMessage.getNotification().getBody());
        }

        sendNotification(remoteMessage.getNotification(), whichActivity);
    }

    private void sendNotification(RemoteMessage.Notification message, String whichActivity) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("EXTRAS_ACTIVITY", whichActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "M_CH_ID")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText(message.getBody())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setVibrate(new long[]{1000, 1000})
                .setContentIntent(pendingIntent);

        if (message.getTitle() != null) {
            notificationBuilder.setContentTitle(message.getTitle());
        } else {
            notificationBuilder.setContentText("Boş geldi");
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
