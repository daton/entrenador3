package org.unitec.entrenador3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by campitos on 17/03/17.
 */

@RestController
@CrossOrigin
public class ControladorTemas {
    @Autowired
    RepositorioPregunta pregunta;
    @RequestMapping(value = "/tema.json" ,headers = {"Accept=application/json"})
    public ArrayList<Pregunta> buscarTodos()throws Exception {

        return (ArrayList<Pregunta>) pregunta.findAll();

    }
    @RequestMapping("/tema/{area}")
    public ArrayList<Pregunta> buscarPorArea(@PathVariable String area)throws Exception {

        return (ArrayList<Pregunta>) pregunta.findByArea(area);

    }

    @RequestMapping("/tema/modificar/{area}")
    public String modificar(@PathVariable String area)throws Exception {

          for(Pregunta p: pregunta.findAll()){
              if(p.getTema().contains(area)){
                  p.setArea(area);
                  pregunta.save(p);
              }
          }
        return "Agregada la area" +area;

    }


    /**
     *
     * @param response La respuesta es un arreglo de bytes correspondiente al excel en formato xlsx
     * @return  El tipo de retorno es el excel
     * @throws IOException Lanza una excepcion
     */
    @RequestMapping(value="/generar", method= RequestMethod.GET)
    public @ResponseBody
    byte[] culera2(HttpServletResponse response)throws IOException {


        // Write the output to a file
        String nombre = "reactivos.json";

       ArrayList<Pregunta> preguntas= (ArrayList<Pregunta>) pregunta.findAll();


        System.out.println("Recobrando correctamente con este metodo del todo nuevof");
        response.setHeader("Content-Disposition", "attachment; filename=\"rata.json" + "\"");
        // response.setHeader("Content-Disposition", "attachment; filename=deployment-definitions.xlsx");
        //response.setContentLength(bytes.length);
        response.setContentType("application/json");

        ObjectMapper maper=new ObjectMapper();
        String salida=maper.writeValueAsString(preguntas);
        return salida.getBytes();

        /*
         Map map = new HashMap();
        map.put("success", true);
        ObjectMapper maper2=new ObjectMapper();
         */
    }






}
