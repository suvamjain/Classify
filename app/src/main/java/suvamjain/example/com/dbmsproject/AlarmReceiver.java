//package suvamjain.example.com.dbmsproject;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.support.annotation.RequiresApi;
//
///**
// * Created by SUVAM JAIN on 27-04-2017.
// */
//@SuppressWarnings("deprecation")
//public class AlarmReceiver extends BroadcastReceiver {
//    Notification myNotication;
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        long id = intent.getLongExtra("id", 0);
//        String msg = intent.getStringExtra("msg");
//
//        /*Notification n = new Notification(R.drawable.logo, msg,
//                System.currentTimeMillis());*/
//        PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(), 0);
//
//        Notification.Builder builder = null;
//
//        builder.setAutoCancel(false);
//        builder.setTicker("this is ticker text");
//        builder.setContentTitle("FFCS Notification");
//        builder.setContentText("You have a new message");
//        builder.setSmallIcon(R.drawable.logo);
//        builder.setContentIntent(pi);
//        builder.setOngoing(true);
//        builder.setNumber(100);
//        builder.build();
//
//        myNotication = builder.getNotification();
//
//
//        /*n.setLatestEventInfo(context, "Remind Me", msg, pi);
//        // TODO check user preferences
//        n.defaults |= Notification.DEFAULT_VIBRATE;
//        n.defaults |= Notification.DEFAULT_SOUND;
//        n.flags |= Notification.FLAG_AUTO_CANCEL;*/
//
//        NotificationManager nm = (NotificationManager)
//                context.getSystemService(Context.NOTIFICATION_SERVICE);
//        nm.notify((int)id, myNotication);
//    }
//}
