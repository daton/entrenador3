package org.unitec.entrenador3;

/**
 * Created by campitos on 10/03/17.
 */
public class AlumnoSesion {
String sessionId;
String nombre;
    String paterno;
String cuenta;

    public AlumnoSesion(String sessionId, String nombre, String paterno, String cuenta) {
        this.sessionId = sessionId;
        this.nombre = nombre;
        this.paterno = paterno;
        this.cuenta = cuenta;
    }

    public AlumnoSesion() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
}
