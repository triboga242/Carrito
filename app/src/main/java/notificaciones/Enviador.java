package notificaciones;


/**
 * Created by Triboga on 13/5/18.
 */

public class Enviador {
    public Notificacion notification;
    public String to;

    public Enviador() {
    }

    public Enviador(Notificacion notification, String to) {
        this.notification = notification;
        this.to = to;
    }

    public Notificacion getNotification() {
        return notification;
    }

    public void setNotification(Notificacion notification) {
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
