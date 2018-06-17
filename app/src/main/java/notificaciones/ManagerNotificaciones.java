package notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import co.com.hgr.cestadelacompra.MainActivity;
import co.com.hgr.cestadelacompra.R;

/**
 * Created by Triboga on 13/5/18.
 */

public class ManagerNotificaciones {

    private Context context;
    private static ManagerNotificaciones instanciaManager;

    public ManagerNotificaciones(Context context) {
        this.context = context;
    }
    public static synchronized ManagerNotificaciones getInstanciaManager(Context context){
        if (instanciaManager==null){
            instanciaManager = new ManagerNotificaciones(context);
        }
        return instanciaManager;
    }

    public void displayNotificacion(String title, String body){

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent =PendingIntent.getActivity(context,0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle(title)
                .setContentText(body).setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager!=null) {
            notificationManager.notify(0, notificationBuilder.build());
        }


    }
}
