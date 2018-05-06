package modelos;

import utilesbbdd.Container;

/**
 * Created by Triboga on 22/4/18.
 * Clase tienda para gestionar los negocios registrados
 */

public class Tienda {

    private String emailDuenio;
    private String nombre;
    private String latitud;
    private String longitud;
    private String horario;
    private String emailPedidos;
    private String telefono;
    private String direccion;

    public Tienda() {

        this.emailDuenio = Container.personaLogueada.getEmail();
        this.nombre = "";
        this.horario = " -- ";
        this.emailPedidos = "";
        this.telefono = "";
        this.direccion = "";
        this.latitud = "";
        this.longitud = "";
    }


    public Tienda(String emailDuenio, String nombre, String localizacion, String latitud, String longitud, String horario, String emailPedidos, String telefono, String direccion) {
        this.emailDuenio = emailDuenio;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.horario = horario;
        this.emailPedidos = emailPedidos;
        this.telefono = telefono;
        this.direccion = direccion;
    }


    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getEmailDuenio() {
        return emailDuenio;
    }

    public void setEmailDuenio(String emailDuenio) {
        this.emailDuenio = emailDuenio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEmailPedidos() {
        return emailPedidos;
    }

    public void setEmailPedidos(String emailPedidos) {
        this.emailPedidos = emailPedidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Tienda{" +
                "emailDuenio=" + emailDuenio +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", horario='" + horario + '\'' +
                ", emailPedidos='" + emailPedidos + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
