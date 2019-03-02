package org.unitec.entrenador3;

/**
 * Created by campitos on 16/04/17.
 */
public class AreaCeneval {
    String area;
    float porciento;
    int reactivos;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public float getPorciento() {
        return porciento;
    }

    public void setPorciento(float porciento) {
        this.porciento = porciento;
    }

    public int getReactivos() {
        return reactivos;
    }

    public void setReactivos(int reactivos) {
        this.reactivos = reactivos;
    }

    public AreaCeneval(String area, float porciento, int reactivos) {
        this.area = area;
        this.porciento = porciento;
        this.reactivos = reactivos;
    }
}
