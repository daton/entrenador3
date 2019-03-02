package org.unitec.entrenador3;

import java.util.ArrayList;

/**
 * Created by campitos on 2/09/15.
 */
public class Profesor extends Usuario {


    ArrayList<Pregunta> reactivos;

    public ArrayList<Pregunta> getReactivos() {
        return reactivos;
    }


    public void setReactivos(ArrayList<Pregunta> reactivos) {
        this.reactivos = reactivos;
    }

    public Profesor() {
    }
}
