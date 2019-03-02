package org.unitec.entrenador3;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class Pregunta{
    @Id
    String id;

    boolean validado;
    String area;
    String tema;
    int claveProfesor;
    String pregunta;
    boolean estaBuena;

    public boolean isEstaBuena() {
        return estaBuena;
    }

    public void setEstaBuena(boolean estaBuena) {
        this.estaBuena = estaBuena;
    }

    ArrayList<Opcion> opciones;

    public Pregunta(boolean validado, String area, String tema, int claveProfesor, String pregunta, ArrayList<Opcion> opciones) {
        this.validado = validado;
        this.area = area;
        this.tema = tema;
        this.claveProfesor = claveProfesor;
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getClaveProfesor() {
        return claveProfesor;
    }

    public void setClaveProfesor(int claveProfesor) {
        this.claveProfesor = claveProfesor;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList<Opcion> opciones) {
        this.opciones = opciones;
    }

    public Pregunta() {
    }
}