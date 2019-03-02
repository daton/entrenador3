package org.unitec.entrenador3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by campitos on 15/03/17.
 */
public class GeneracionTemas {

    public static   List<AreaCeneval> generaTemas(){
        AreaCeneval a1=new AreaCeneval("A 1. Diagnóstico del problema y valoración de la factibilidad para el desarrollo de sistemas de información",
                7.18f,13);

        AreaCeneval a2=new AreaCeneval("A 2. Modelado de los requerimientos de un sistema de información",
                6.08f,11);

        AreaCeneval b1=new AreaCeneval("B 1. Diseño de la solución del problema de tecnología de información",
                9.39f,17);
        AreaCeneval b2=new AreaCeneval("B 2. Desarrollo de sistemas",
                22.65f,41);
        AreaCeneval b3=new AreaCeneval("B 3. Implantacación de sistemas",
                3.87f,7);
        AreaCeneval b4=new AreaCeneval("B 4. Aplicación de modelos mátematicos",
                4.97f,9);
        AreaCeneval c1=new AreaCeneval("C 1. Administración de proyectos de tecnologías de información",
                5.52f,10);
        AreaCeneval c2=new AreaCeneval("C 2. Control de calidad de proyectos de tecnologías de información ",
                8.84f,16);
        AreaCeneval d1=new AreaCeneval("D 1. Gestión de redes de datos",
                8.84f,16);
        AreaCeneval d2=new AreaCeneval("D 2. Gestión de bases de datos ",
                12.15f,22);
        AreaCeneval d3=new AreaCeneval("D 3. Gestión de sistemas operativos o lenguajes de desarrollo ",
                10.50f,19);
        List<AreaCeneval> areas= new ArrayList<>();
                areas.add(a1);
                areas.add(a2);
                areas.add(b1);
                areas.add(b2);
                areas.add(b3);
                areas.add(c1);
                areas.add(c2);
                areas.add(d1);
                areas.add(d2);
                areas.add(d3);
return areas;

    }
}
