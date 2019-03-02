package org.unitec.entrenador3;

import java.io.Serializable;

/**
 * Created by campitos on 21/08/15.
 */
public class Opcion implements Serializable{

    String titulo;
    boolean acierto;

    public boolean isAcierto() {
        return acierto;
    }

    public void setAcierto(boolean acierto) {
        this.acierto = acierto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Opcion() {
    }

    public Opcion(String titulo, boolean acierto) {
        this.titulo = titulo;
        this.acierto = acierto;
    }
}
