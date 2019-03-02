package org.unitec.entrenador3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by campitos on 1/03/17.
 */
@RestController
@RequestMapping("/")
public class ControladorAlumno {
    @Autowired
    RepositorioAlumno  repositorioAlumno;
    @RequestMapping(value="/alumno/{nombre}/{paterno}/{materno}/{cuenta}", method = RequestMethod.GET,
    headers = {"Accept=text/html"})
    public String guardar(@PathVariable String nombre, @PathVariable String paterno, @PathVariable String materno,
                          @PathVariable String cuenta){
        repositorioAlumno.save(new Alumno(nombre,paterno,materno,cuenta));
        return "Alumno guardado con exito";
    }


    @RequestMapping(value="/alumno", method = RequestMethod.POST,
            headers = {"Accept=text/html"})
    public String guardarJson(@RequestBody String json)throws Exception{

        String resultado="nada";
        ObjectMapper mapper=new ObjectMapper();
        //convertimos jsonsito a un RegistroMensajeria, que asi se supone vienen :)
        Alumno registro = mapper.readValue(json,Alumno.class);
      //  repositorioAlumno.save(registro);
        System.out.println("Este es el registro:"+registro.getNombre()+"paterno:"+registro.getPaterno());


        Map map = new HashMap();
        map.put("success", true);
        ObjectMapper maper2=new ObjectMapper();
        return maper2.writeValueAsString(map);

    }
}
