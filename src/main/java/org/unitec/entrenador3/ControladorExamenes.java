package org.unitec.entrenador3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by campitos on 4/04/17.
 */
@RestController
@CrossOrigin
public class ControladorExamenes {
    @Autowired
    RepositorioPregunta repositorioPregunta;
    @RequestMapping("/examen/{area}")
    public ArrayList<Pregunta> buscarPorArea(@PathVariable String area)throws Exception {
        ArrayList<Pregunta> preguntasAleatorias = new ArrayList<>();

        ArrayList<Pregunta> todos = (ArrayList<Pregunta>) repositorioPregunta.findByArea(area);


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

        // Vamos a generar ahora las opciones aleatorios

     int i=0;
        ArrayList<Pregunta> preguntasOpcionesAleatorias=new ArrayList<>();
        for(Pregunta p : preguntasAleatorias){
            ArrayList<Opcion> opciones=opcionesAleatorias(p.getOpciones());

            p.setOpciones(opciones);
            preguntasOpcionesAleatorias.add(p);
          p.setOpciones(opciones);


        }




        //REgresamos los 5 aleatorios



        return preguntasOpcionesAleatorias;

    }

    public ArrayList<Opcion> opcionesAleatorias(ArrayList<Opcion> opciones){
        ArrayList<Opcion> opcionesAleatorios=new ArrayList<>();
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

        opcionesAleatorios.add(opciones.get(entero));
            indice++;
        }

        for(Opcion o: opcionesAleatorios){
            System.out.println(o.getTitulo()+" "+o.isAcierto());
        }

        return opcionesAleatorios;


    }

}
