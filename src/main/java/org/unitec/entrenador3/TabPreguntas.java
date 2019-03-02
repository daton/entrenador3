package org.unitec.entrenador3;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.*;

/**
 * Created by campitos on 23/02/17.
 */

@UIScope
@SpringUI
public class TabPreguntas extends VerticalLayout {
    public TabPreguntas(RepositorioPregunta repositorioPregunta){

        // List of planets
        List<String> temas = new ArrayList<>();
        temas.add("A1. Diagnóstico del problema y valoración de la factibilidad para el desarrollo de sistemas de información");
        temas.add("A2. Modelado de los requerimientos de un sistema de información");
        temas.add("B1. Diseño de la solución del problema de tecnología de información");
        temas.add("B2. Desarrollo de sistema ");
        temas.add("B3. Implantación de sistemas");
        temas.add("B4. Aplicación de modelos matemáticos");
        temas.add("C1. Administración de proyectos de tecnologías de información");
        temas.add("CI1. Gestión de la función informática ");
        temas.add("C2. Control de calidad de proyectos de tecnologías de información");


        ComboBox<String> select =
                new ComboBox<>("Tema EGEL");
        select.setPlaceholder("Selecciona el tema EGEL de tu pregunta");
        select.addStyleName(ValoTheme.COMBOBOX_LARGE);
        select.setItems(temas);
        select.setWidth("70%");
        addComponent(select);



        Label label=new Label("PREGUNTA");
        label.addStyleName(ValoTheme.LABEL_SUCCESS);
        label.addStyleName(ValoTheme.LABEL_H2);
        addComponent(label);

        TextArea textoPregunta=new TextArea();


        textoPregunta.setPlaceholder("Introduce el título de la pregunta");
        textoPregunta.setRows(5);
        textoPregunta.setWidth("70%");
        addComponent(textoPregunta);

Label labelOpcionCorrecta=new Label("Opción correcta");
labelOpcionCorrecta.addStyleName(ValoTheme.LABEL_SUCCESS);
labelOpcionCorrecta.addStyleName(ValoTheme.LABEL_H3);
addComponent(labelOpcionCorrecta);

        TextArea textoOpcionCorrecta=new TextArea();
        textoOpcionCorrecta.setPlaceholder("Opción Correcta");
        textoOpcionCorrecta.setRows(3);
        textoOpcionCorrecta.setWidth("60%");
        addComponent(textoOpcionCorrecta);

        Label labelOpcionesIncorrectas=new Label("Opciones incorrectas");
        labelOpcionesIncorrectas.addStyleName(ValoTheme.LABEL_FAILURE);
        labelOpcionesIncorrectas.addStyleName(ValoTheme.LABEL_H3);
        addComponent(labelOpcionesIncorrectas);

        TextArea textoOpcionMala1=new TextArea();

        textoOpcionMala1.setPlaceholder("Opción Incorrecta 1");
        textoOpcionMala1.setRows(3);
        textoOpcionMala1.setWidth("60%");
        addComponent(textoOpcionMala1);

        TextArea textoOpcionMala2=new TextArea();
        textoOpcionMala2.setPlaceholder("Opción Incorrecta 2");
        textoOpcionMala2.setRows(3);
        textoOpcionMala2.setWidth("60%");
        addComponent(textoOpcionMala2);

        TextArea textoOpcionMala3=new TextArea();
        textoOpcionMala3.setPlaceholder("Opción Incorrecta 3");
        textoOpcionMala3.setRows(3);
        textoOpcionMala3.setWidth("60%");
        addComponent(textoOpcionMala3);

        Button botonGuardarPregunta=new Button("Guardar pregunta",FontAwesome.SAVE);
        botonGuardarPregunta.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addComponent(botonGuardarPregunta);

        /**************************************************************************
        GUARDAR UNA PREGUNTA

         **************************************************************************/
        botonGuardarPregunta.addClickListener(click->{
           String tema=select.getSelectedItem().get();
           String titulo=textoPregunta.getValue().toString();
           String correcta= textoOpcionCorrecta.getValue().toString();
           String opmala1=textoOpcionMala1.getValue().toString();
           String opmala2=textoOpcionMala2.getValue().toString();
            String opmala3=textoOpcionMala3.getValue().toString();
            String opciones[]={correcta,opmala1,opmala2,opmala3};

           ArrayList<Opcion>  opcions=new ArrayList<>();
           Opcion op1=new Opcion(correcta, true);
           Opcion op2=new Opcion(opmala1,false);
           Opcion op3=new Opcion(opmala2,false);
           Opcion op4=new Opcion(opmala3,false);
           opcions.add(op1);
           opcions.add(op2);
           opcions.add(op3);
           opcions.add(op4);

            Pregunta p=new Pregunta();
            p.setClaveProfesor(33868);
            p.setPregunta(titulo);
            p.setTema(tema);
            p.setValidado(false);
            p.setOpciones(opcions);

            //Guardamos la nueva pregunta
            repositorioPregunta.save(p);
          Notification noti=  new Notification("Pregunta guardada",
                    "Esta <i>pregunta</i> se guardó con éxito",
                    Notification.Type.ERROR_MESSAGE, true);
            noti.setPosition(Position.MIDDLE_CENTER);
            noti.setDelayMsec(3000);
            noti.show(Page.getCurrent());

            textoPregunta.setValue("");
            textoOpcionCorrecta.setValue("");
            textoOpcionMala1.setValue("");
            textoOpcionMala2.setValue("");
            textoOpcionMala3.setValue("");


        });


        setResponsive(true);


    }

    public void opcionesAleatorias(String opciones[]){

        //Implementamos el LinkedHasSet para mantener el orden
        Random ran=new Random();
        Set<Integer> generado=new LinkedHashSet<>();
        while(generado.size()<4){
            Integer proximo=ran.nextInt(4);
            generado.add(proximo);
        }
        for(Integer entero:generado){
            System.out.println(entero);
        }




    }
}
