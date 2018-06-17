package notificaciones;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashMap;
import java.util.Map;

import utilesbbdd.Container;

/**
 * Created by Triboga on 12/5/18.
 */

public class AyudanteNotificaciones extends FirebaseInstanceIdService {

public static final String TAG="IDservice";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Container.currentTocken=refreshedToken;

        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(refreshedToken);
    }

    public static void sendNotificationToUser(String user, final String message) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference notifications = ref.child("notificationRequests");



        Map notification = new HashMap<>();
        notification.put("username", user);
        notification.put("message", message);

        notifications.push().setValue(notification);
    }


}
