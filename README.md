# FirebaseNotificationTutorial


<a href="https://imgur.com/0g1EMog"><img src="https://i.imgur.com/0g1EMog.png" title="source: imgur.com" /></a>

I hope you all are happy coding and living curiously to learn something new everyday. And like others me too want to make my work easier by creating some useful content which can help me and other programmers around the globe to easily access the code from anywhere and anytime.

Today we are going to learn how to add Firebase Push Notification Perk to your Android App and it is one of the peaceful implementation ever. Just Kodding.



Step 1: Sign in to your Gmail Account and Create a New Project in Firebase Console : https://console.firebase.google.com/

<a href="https://imgur.com/HqTwsbT"><img src="https://i.imgur.com/HqTwsbT.png" title="source: imgur.com" /></a>


Step 2: Clicking on Add Project will pop this dialog up:

<a href="https://imgur.com/rbKMhzC"><img src="https://i.imgur.com/rbKMhzC.png" title="source: imgur.com" /></a>


Step 3: Now click that green button in the middle for adding firebase to android app:

<a href="https://imgur.com/UCdiN0I"><img src="https://i.imgur.com/UCdiN0I.png" title="source: imgur.com" /></a>


Step 4: Enter Package Name and SHA1 Key for step one:

<a href="https://imgur.com/enIBLWS"><img src="https://i.imgur.com/enIBLWS.png" title="source: imgur.com" /></a>


Step 5: Now Download the googleservices.json file and place in your Android Project.

<a href="https://imgur.com/2KfYwzr"><img src="https://i.imgur.com/2KfYwzr.png" title="source: imgur.com" /></a>


Step 6: Now Add the below two lines in respective build.gradle files:

<a href="https://imgur.com/7CT2XYs"><img src="https://i.imgur.com/7CT2XYs.png" title="source: imgur.com" /></a>



Imp Note: Add two java classes in your Android Project main package with preferably these names:

FireBaseMessagingService.java

 FirebaseMessagingService Code:
 ===============================

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

Another Imp Note: And register both these files in your Projects AndroidManifest.xml file in the <application> tag.
  
  <a href="https://imgur.com/1FtMD04"><img src="https://i.imgur.com/1FtMD04.png" title="source: imgur.com" /></a>
  
  
Step 7: And Now Go back to Firebase Console and Go to Cloud Messaging from Side Menu which will open this screen:

<a href="https://imgur.com/sHmW160"><img src="https://i.imgur.com/sHmW160.png" title="source: imgur.com" /></a>


Step 8: Now just enter Notification Message and select your app package name to send the push.

<a href="https://imgur.com/O2yRor8"><img src="https://i.imgur.com/O2yRor8.png" title="source: imgur.com" /></a>


Step 9: Hopefully if you have performed each step then you will get the push notification in your device.
