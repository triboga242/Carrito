package modelos;

/**
 * Created by Triboga on 29/4/18.
 */

public class LocationData {

    String nombreTienda;
    String emailDueño;
    double longitud;
    double latitud;

    public LocationData(double latitud, double longitud, String nombreTienda, String emailDueño) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.nombreTienda=nombreTienda;
        this.emailDueño=emailDueño;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }
}
