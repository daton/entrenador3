package org.unitec.entrenador3;


/**
 * Created by campitos on 23/02/17.
 */
//@Entity
public class Binario1 {

    Long id;

    String nombre;

    //@Lob
    byte[] archivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public Binario1(String nombre, byte[] archivo) {
        this.nombre = nombre;
        this.archivo = archivo;
    }

    public Binario1() {
    }
}