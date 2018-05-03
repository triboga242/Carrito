package modelos;

import utilesbbdd.Container;

/**
 * Created by Triboga on 22/4/18.
 */

public class Tienda {

    private String emailDuenio;
    private String nombre;
    private String localizacion;
    private String horario;
    private String emailPedidos;
    private String telefono;
    private String direccion;

    public Tienda() {

        this.emailDuenio = Container.personaLogueada.getEmail();
        this.nombre = "";
        this.localizacion = "";
        this.horario = "";
        this.emailPedidos = "";
        this.telefono = "";
        this.direccion = "";
    }


    public Tienda(String emailDuenio, String nombre, String localizacion, String horario, String emailPedidos, String telefono, String direccion) {
        this.emailDuenio = emailDuenio;
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.horario = horario;
        this.emailPedidos = emailPedidos;
        this.telefono = telefono;
        this.direccion = direccion;
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

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
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
