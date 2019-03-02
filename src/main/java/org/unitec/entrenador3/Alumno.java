package org.unitec.entrenador3;

import java.util.ArrayList;

/**
 * Created by campitos on 1/03/17.
 */
public class Alumno {
    String nombre;
    String paterno;
    String materno;
    String cuenta;

    ArrayList<Evaluacion> evaluaciones;

    public Alumno(String nombre, String paterno, String materno, String cuenta) {
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.cuenta = cuenta;
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

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public Alumno() {

    }

    public String getMiAlumno(){
        return getNombre()+" "+getPaterno();
    }


}
