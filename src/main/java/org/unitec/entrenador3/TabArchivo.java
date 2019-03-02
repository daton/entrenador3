package org.unitec.entrenador3;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by campitos on 23/02/17.
 */

@SpringComponent
@UIScope
public class TabArchivo extends VerticalLayout {

   // private final RepositorioBinario repo;

byte[] misbytes;


//Como no es el la principal la UI aqui vamos primero a inyectar com autowired via constructor el repositorio de interes
 //usando el Autowired SOBRE EL CONSTRUCTOR poniendo el constructor directamente con aegumento al o los repositorios
   //ya en la clase MiUI alli es donde inyectamos ahora si, el repositorio con @Autowired RepositorioBinario y se lo pasa
    //mos al constructor a usarse en la pagina a mostrarse.
    @Autowired
    public TabArchivo(RepositorioBinario repo){
      //  this.repo=repo;

        Label label=new Label("hay nooo");

        class ImageUploader implements Upload.Receiver, Upload.SucceededListener {
            public File file;
            FileOutputStream fos;

            public OutputStream receiveUpload(String filename,
                                              String mimeType) {
                // Create and return a file output stream

                 file=new File(filename);
               long tamano=  file.length();
               FileInputStream fis=null;


             fos=null;
                try {

                     fos=new FileOutputStream(file);
                label.setValue("cargando...");
                } catch (Exception e) {
                    label.setValue(e.getMessage());
                }
            return fos;
            }

            public void uploadSucceeded(Upload.SucceededEvent event) {
                // Show the uploaded file in the image viewer
             //   image.setSource(new FileResource(file));
                System.out.println("Termino:"+file.length());

                try {
                    FileInputStream in = new FileInputStream(file);

                 misbytes=   IOUtils.toByteArray(in);
                    System.out.println("sale uno "+misbytes.length);
                       label.setValue("longi "+misbytes.length);
                 Binario1 bi = new Binario1( );
                 bi.setArchivo(misbytes);
                 bi.setNombre("hay");
                    System.out.println("sale doscon nombre "+file.getName()+ " y con tamanin "+misbytes.length);
                  // repo.save(new Binario1(file.getName(),misbytes));
                    System.out.println("sale tres");
                    label.setValue("Se cargo con exito ");
                }catch(Exception e){
                    label.setValue(e.getMessage());
                    System.out.println(e.getMessage());
                }

            }
        };
        ImageUploader receiver = new ImageUploader();

// Create the upload with a caption and set receiver later
        Upload upload = new Upload("Upload Image Here", receiver);
      //  upload.setImmediateMode(false);
       upload.addSucceededListener(receiver);

       Button boton2=new Button("Subir ahora");

boton2.addClickListener(jaja->{
  // repo.save(new Binario1("no mames", null));
    System.out.print("No mames");

});


        this.addComponent(upload);
        this.addComponent(label);
        this.addComponent(boton2);


    }
}


