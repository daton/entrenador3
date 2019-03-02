package org.unitec.entrenador3;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by campitos on 23/02/17.
 */

@UIScope
@SpringUI

public class TabExamen  extends VerticalLayout {
RepositorioPregunta repositorioPregunta;
RepositorioAlumno repositorioAlumno;
ServicioCorreo servicioCorreo;
String op1;
String op2;
String op3;
String op4;
String laCorrecta="";

    int pregunta=0;
    int contador=0;
    Thread t1;

    //calificaciones
    float calif;
    int buenas;
    int numeroPregunta=1;



    float califtotal;
ArrayList<Pregunta> cincoPreguntas=new ArrayList<>();


    RadioButtonGroup<String> radios;
    TextArea textoPregunta;
    Label labelResumen;
    Button botonChecar;

    public  TabExamen(RepositorioPregunta repositorioPregunta, ServicioCorreo servicioCorreo, RepositorioAlumno repositorioAlumno){
       this.repositorioPregunta= repositorioPregunta;
       this.servicioCorreo=servicioCorreo;
       this.repositorioAlumno=repositorioAlumno;


    //  cincoPreguntas=  obtenerReactivos("A1. Diagnóstico del problema y valoración de la factibilidad para el desarrollo de sistemas de información");




        setMargin(true);
        setSpacing(true);
      Label label=new Label("SELECCIONA LA OPCION CORRECTA , LEE BIEN ANTES DE CONTESTAR!");
      Label labelExplicacion=new Label("El examen consta de 8 preguntas del tema seleccionado y se genera aleatoriamente de " +
              "la base de datos de reactivos" + "");
     label.addStyleName(ValoTheme.LABEL_H3);
     label.setWidth("100%");
     labelExplicacion.setWidth("100%");

        textoPregunta=new TextArea();
        textoPregunta.setWidth("100%");
        textoPregunta.setStyleName(ValoTheme.TEXTAREA_HUGE);
        // textoPregunta.setHeight("250px");
        textoPregunta.setEnabled(false);
        textoPregunta.setStyleName(ValoTheme.TEXTAREA_BORDERLESS);

     Button botonExamensito=new Button("Iniciar Examen", FontAwesome.DELICIOUS);
     botonExamensito.addStyleName(ValoTheme.BUTTON_PRIMARY);


      botonExamensito.addClickListener(iniciarExamen->{

if(labelExplicacion.isVisible()){
    removeComponent(labelExplicacion);
}
          cincoPreguntas=  obtenerReactivos( VaadinSession.getCurrent().getAttribute("tema").toString());
          System.out.println("AAAAAAAAAAAAAAAAAAAA"+VaadinSession.getCurrent().getAttribute("tema").toString());

          addComponent(textoPregunta);
          generarPreguntaParaBoton(contador);



          addComponent(botonChecar);
          removeComponent(botonExamensito);



      });



      addComponent(label);
      addComponent(labelExplicacion);
      addComponent(botonExamensito);

        Page.Styles styles = Page.getCurrent().getStyles();
        // inject the new font size as a style. We need .v-app to override Vaadin's default styles here
        /*  El SIGUIENTE SI FUNCIONAAA
        styles.add(".v-label{\n" +
                "     background: white;\n" +
                "     border-color: #F5F5F5;\n" +
                "     border-image: none;\n" +
                "     border-left: 1px solid #F5F5F5;\n" +
                "     border-radius: 3px 3px 3px 3px;\n" +
                "     border-right: 1px solid #F5F5F5;\n" +
                "     border-style: solid;\n" +
                "     border-width: 1px;\n" +
                " }");

                */





        botonChecar=new Button("Checar Respuesta",FontAwesome.CHECK);
        //AQUIO AGREGAMOS LA PREGUNTA BASADA EN EL INDICE



        /***************************************************************************************
        BOTON PARA CHECAR RESPEUSTA
         ****************************************************************************************/
        botonChecar.addClickListener(click->{
            String respuesta="incorrecta";
              if(!radios.getSelectedItem().isPresent()){
                  Notification.show("Debes selecciona una opcion, haz click aqui y elije una opción", Notification.Type.ERROR_MESSAGE);
              }else {
                  if (radios.getSelectedItem().get().equals(laCorrecta)){
                      respuesta = "correcta";
                      buenas++;
                      cincoPreguntas.get(contador).setEstaBuena(true);
                  }
                //  Notification.show("Tu respuesta es " + respuesta);

                  if (contador < 8) {


                      contador++;
                      generarPreguntaParaBoton(contador);
                  }
              }
        });




    }

    ArrayList<Pregunta> obtenerPregunta(){
        ArrayList<Pregunta> preguntas= (ArrayList<Pregunta>) repositorioPregunta.findAll();
        return preguntas;
    }


    public String[] opcionesAleatorias(String opciones[]){
String[] opcionesAleatorios=new String[4];
        //Implementamos el LinkedHasSet para mantener el orden
        Random ran=new Random();
        Set<Integer> generado=new LinkedHashSet<>();
        while(generado.size()<4){
            Integer proximo=ran.nextInt(4);
            generado.add(proximo);
            //Agregamos las opciones


        }
        int indice=0;
        for(Integer entero:generado){

            System.out.println(entero);
            opcionesAleatorios[indice]=opciones[entero];
            indice++;
        }

return opcionesAleatorios;


    }


    /************************************************************************************************************************
     *
     * @param tema
     * @return
     ****************************************************************************************************/
    public ArrayList<Pregunta> obtenerReactivos(String tema) {


        ArrayList<Pregunta> preguntasAleatorias = new ArrayList<>();

        ArrayList<Pregunta> todos = (ArrayList<Pregunta>) repositorioPregunta.findByTema(tema);


/**********************************************************************************************************
 Checamos si las pregunats son más de 10 para hacer el acomodado al azar
 **********************************************************************************************************/
        if (todos.size() > 8) {
            Random rng = new Random(); //
// El LinkedHashSet es para mantener el orden
            Set<Integer> generated = new LinkedHashSet<Integer>();
            //Aqui va el numero de reactivos a mostrarse o sea 10 preguntas
            while (generated.size() < 8) {
                // Aqui va el numero total de reactivos en los que debe hacerse la asignacion aleatorio
                Integer next = rng.nextInt(todos.size()) + 1;
                // Agregamos
                generated.add(next);
            }
            //Acomodamos los reactivos de acuerdo a los indices generados
            preguntasAleatorias = new ArrayList<>();
            for (int i : generated) {
                System.out.println("Valor:" + i);
                preguntasAleatorias.add(todos.get(i - 1));
            }


        }
        //REgresamos los 5 aleatorios
        return preguntasAleatorias;

    }


    public void generarPreguntaParaBoton(int contador) {
        if (contador < 8) {
            if (radios != null) {
                removeComponent(radios);
            }
            if (botonChecar != null) {
                removeComponent(botonChecar);
            }
            // removeComponent(botonChecar);
            textoPregunta.setValue(numeroPregunta+". "+cincoPreguntas.get(contador).getPregunta());
            addComponent(textoPregunta);

            radios =
                    new RadioButtonGroup<>("Selecciona la opcion correcta");
            op1 = cincoPreguntas.get(contador).getOpciones().get(0).getTitulo();
            op2 = cincoPreguntas.get(contador).getOpciones().get(1).getTitulo();
            op3 = cincoPreguntas.get(contador).getOpciones().get(2).getTitulo();
            op4 = cincoPreguntas.get(contador).getOpciones().get(3).getTitulo();
            String opcionesillas[] = {op1, op2, op3, op4};

            //*************************************************************************************************************
            //para modo profesor:
            int indiceCorrecta=0;
            String correcta="ninguna";
            for(int i=0;i<4;i++){
                if(cincoPreguntas.get(contador).getOpciones().get(i).isAcierto()){
                    indiceCorrecta=i;
                    correcta=   cincoPreguntas.get(contador).getOpciones().get(i).getTitulo();
                    break;
                }
            }


            radios.setItems(opcionesAleatorias(opcionesillas));
            addComponent(radios);

            /************************************************************************************************
             * MODO PROFESOR

             *************************************************************************************************/
            if(MiUI.ALUMNO_INGRESADO.getCuenta().equals("campitos")) {
                System.out.println("eres profesor");
                radios.setSelectedItem(correcta);
            }
            //termina para modo profesor


            botonChecar.addStyleName(ValoTheme.BUTTON_PRIMARY);
            addComponent(botonChecar);


            for (Opcion op : cincoPreguntas.get(contador).getOpciones()) {
                if (op.isAcierto()) laCorrecta = op.getTitulo();
            }


        }else{
            removeAllComponents();

            Button botonSalir=new Button("regresar",FontAwesome.HOME);
            botonSalir.addStyleName(ValoTheme.BUTTON_PRIMARY);
            addComponent(botonSalir);
            botonSalir.addClickListener(salir->{
                Page.getCurrent().reload();
            });
            calif=(buenas/8.0f)*10;
            labelResumen = new Label(
                    "<center><h2>Resultado de 8 preguntas aleatorias</h2> \n" +
                            "<div>"+
                            "  <h3>Aciertos:"+buenas+"</h3>"+
                            "  <h3>Calificación correspondiente:"+calif+"</h3>"+
                            "</div> "+
                            "<p>Preguntas o sugerencias, envía un mensaje al Lic. Jose Ángel Núñez</p> </center>",
                    ContentMode.HTML);

               String enviarlo="Aciertos:"+buenas+"\n Calificacion:"+calif+"\n Area:A1";
               try {
                   String mensaje = servicioCorreo.enviar(VaadinSession.getCurrent().getAttribute("nombre")+" "+ VaadinSession.getCurrent().getAttribute("paterno"), labelResumen.getValue(), cincoPreguntas,"Tema EGEL: "+VaadinSession.getCurrent().getAttribute("tema"));
               }catch(Exception e){

               }
            addComponent(labelResumen);
            TextArea textArea=new TextArea();
            textArea.setPlaceholder("Escribe tu comentario o duda");
            textArea.setRows(5);
            textArea.setWidth("480px");
        //    addComponent(textArea);
            Button botonMensaje=new Button("Enviar mensaje", FontAwesome.MAIL_FORWARD);
            botonMensaje.addStyleName(ValoTheme.BUTTON_PRIMARY);
           // addComponent(botonMensaje);
            botonMensaje.addClickListener(enviar->{

try{

                String  mensajito=  servicioCorreo.enviar(VaadinSession.getCurrent().getAttribute("nombre")+" "+VaadinSession.getCurrent().getAttribute("paterno"), textArea.getValue().toString(), cincoPreguntas,"Tema EGEL: "+VaadinSession.getCurrent().getAttribute("tema"));
                   textArea.setValue("");
                Notification.show(mensajito);
                }catch(Exception e){
    System.out.println("HUBO ESTE ERROR AL ENVIAR EL ARCHIVOOOO"+e.getMessage());
                    Notification.show(e.getMessage());
                }

                // Update the UI thread-safely

            //    ProgressBar progress = new ProgressBar();
            //  addComponent(progress);
            //  progress.setIndeterminate(true);


            });
        }

        numeroPregunta++;
    }
}
