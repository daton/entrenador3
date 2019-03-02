package org.unitec.entrenador3;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.FooterCell;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by campitos on 22/02/17.
 */


@SpringUI
@Theme("valo")
public class MiUI extends UI {

    VerticalLayout tabInicio;
    HorizontalLayout pantallaSuperior;
    VerticalLayout pantallaInferior;
   Button botonExamen;
   Button botonExamenFinal;
   Button botonAdministracion;

    public static Alumno ALUMNO_INGRESADO;
    //El siguiente se inyectara vía constructor de las clases heredadas UI

@Autowired
RepositorioPregunta repositorioPregunta;
@Autowired
ServicioCorreo servicioCorreo;
@Autowired
RepositorioAlumno repositorioAlumno;
public  static ArrayList<Alumno> alumnos;


    @Override
    protected void init(VaadinRequest request) {

      malo();

    }

public void malo(){
    TabSheet tabito=new TabSheet();
    tabInicio=new VerticalLayout();
    pantallaSuperior=new HorizontalLayout();
    pantallaInferior=new VerticalLayout();
    //Botones para examen y para preguntas
    botonExamen=new Button("Examenes por Área",FontAwesome.BOOK);
    botonExamenFinal=new Button("Emulador EGEL", FontAwesome.CHECK);

    botonAdministracion=new Button("Administración", FontAwesome.CALENDAR);

    //Tabs externos
    TabExamen tabExamen=new TabExamen(repositorioPregunta, servicioCorreo, repositorioAlumno);
    TabExamenFinal tabExamenFinal=new TabExamenFinal(repositorioPregunta,servicioCorreo,repositorioAlumno);
    TabPreguntas tabPreguntas=new TabPreguntas(repositorioPregunta);
    TabVerificador tabVerificador=new TabVerificador(repositorioPregunta);


// AGREGAMOS LA INTERFAZ INICIAL del proyecto hay estoy preocupado

    pantallaSuperior.addComponent(botonExamen);
    pantallaSuperior.addComponent(botonExamenFinal);
    pantallaSuperior.addComponent(botonAdministracion);

    Label labelPrincipal=new Label("Bienvenidos al emulador EGEL,  al iniciar un examen se te pedirá un área a evaluar," +
            " a continuación se muestran las áreas del EGEL");
    labelPrincipal.setStyleName(ValoTheme.LABEL_H3);
    labelPrincipal.setWidth("100%");
    pantallaInferior.addComponent(labelPrincipal);

    //En la pantalla inferior ponemos la tabla
    /*
    Grid<AreaCeneval> grid = new Grid<>(AreaCeneval.class);
    grid.setItems(GeneracionTemas.generaTemas());
    grid.setWidth("100%");
    grid.setColumnResizeMode(ColumnResizeMode.ANIMATED);


    grid.getColumns().get(2).setCaption("Porcentaje");
    grid.getDefaultHeaderRow().getCell("dos").setHtml("The <u>amount</u>");



    Grid grid = new Grid();
    grid.setColumnOrder("name", "amount", "count");

    grid.getDefaultHeaderRow().getCell("amount") .setHtml("The <u>amount</u>");
    grid.getDefaultHeaderRow().getCell("count").setComponent(new Button("Button caption"));

    grid.getColumn("name").setCaption("gato");

    HeaderRow extraHeader = grid.prependHeaderRow();
    HeaderCell joinedCell = extraHeader.join("amount", "count");
    joinedCell.setText("Joined cell");

    FooterCell footer = grid.appendFooterRow().join("name", "amount", "count");
    footer.setText("Right aligned footer");

    getPage().getStyles().add(".footer-right { text-align: right }");
    footer.setStyleName("footer-right");

*/
    Grid<AreaCeneval> grid=new Grid<>(AreaCeneval.class);


  grid.getColumn("area");
  grid.setWidth("100%");
  grid.getColumn("area").setCaption("Areas a evaluar");
  grid.setHeight(730,Unit.PIXELS);



  grid.setItems(GeneracionTemas.generaTemas());
    FooterCell footer = grid.appendFooterRow().join("area", "porciento", "reactivos");
    footer.setText("Total de reactivos: 181");
    getPage().getStyles().add(".footer-right { text-align: left;font-weight: bold; }");

    //    getPage().getStyles().add(".v-grid-cell{height: 140px;}");
    //labelPrincipal.addStyleName("malo");
getPage().getStyles().add(".v-grid-row {color: blue;height:300px;}");
//getPage().getStyles().add(".v-grid-cell{white-space: normal;overflow: hidden;}");
    footer.setStyleName("footer-right");






  pantallaInferior.addComponent(grid);
    tabInicio.addComponent(pantallaSuperior);
    tabInicio.addComponent(pantallaInferior);


    //Se pasa el autowired como argumento como lo dicta la clase.
    // TabArchivo tabArchivo=new TabArchivo(repo);


    botonExamen.addStyleName(ValoTheme.BUTTON_FRIENDLY);
    botonExamenFinal.addStyleName(ValoTheme.BUTTON_FRIENDLY);
    botonAdministracion.addStyleName(ValoTheme.BUTTON_PRIMARY);




    /***************************************************************************************
     *
     PRIMERA VENTANA DE AUTENTICACION PARA ALUMNOS

     ****************************************************************************************/
    Window subWindowExamen = new Window("Examen");
    subWindowExamen.setClosable(false);
    VerticalLayout subContentExamen = new VerticalLayout();
    subWindowExamen.setContent(subContentExamen);

    botonExamen.addClickListener(xxx->{
        subWindowExamen.center();
        subWindowExamen.setWidth("350px");
        addWindow(subWindowExamen);
        alumnos= (ArrayList<Alumno>) repositorioAlumno.findAll();
        tabito.removeComponent(tabInicio);

        ComboBox<Alumno> select =
                new ComboBox<>();
        select.setPlaceholder("Escribe tu nombre aquí");
        select.setItems(alumnos);
        select.setWidth("100%");
        select.setItemCaptionGenerator(Alumno::getMiAlumno);





        ComboBox<Tema> selectTema =
                new ComboBox<>();
        selectTema.setPlaceholder("Selecciona un Tema");
        List<Tema> temitas= new ArrayList<>();
        temitas.add(new Tema("A1. Diagnóstico del problema y valoración de la factibilidad para el desarrollo de sistemas de información"));
        temitas.add(new Tema("A2. Modelado de los requerimientos de un sistema de información"));
        temitas.add(new Tema("B1. Diseño de la solución del problema de tecnología de información"));
        temitas.add(new Tema("B2. Desarrollo de sistema "));
        temitas.add(new Tema("B3. Implantación de sistemas"));
        temitas.add(new Tema("CI1. Gestión de la función informática "));
        temitas.add(new Tema("C1. Administración de proyectos de tecnologías de información"));
        temitas.add(new Tema("C2. Control de calidad de proyectos de tecnologías de información"));
        selectTema.setItems(temitas);
        selectTema.setItemCaptionGenerator(Tema::getNombre);
        selectTema.setWidth("100%");


        //ComboTemas


        final  Label labelExamen=new Label("Bienvenido!!");
        subContentExamen.addComponent(labelExamen);

        final   PasswordField textoPasswordExamen=new PasswordField();
        textoPasswordExamen.setPlaceholder("Introduce tu cuenta");

        subContentExamen.addComponent(select);
        subContentExamen.addComponent(selectTema);
        subContentExamen.addComponent(textoPasswordExamen);

        Button botonAutenticarExamen=new Button("Ingresar", FontAwesome.LOCK);
        botonAutenticarExamen.addStyleName(ValoTheme.BUTTON_PRIMARY);
        subContentExamen.addComponent(botonAutenticarExamen);
        botonAutenticarExamen.addClickListener(ingresar->{

            ALUMNO_INGRESADO=new Alumno();
            ALUMNO_INGRESADO=  select.getValue();
            System.out.println(ALUMNO_INGRESADO);
            if(repositorioAlumno.findByNombre(ALUMNO_INGRESADO.getNombre()).getCuenta().equals(textoPasswordExamen.getValue().toLowerCase())&&selectTema.getSelectedItem().isPresent()){

                String sesionId=     VaadinSession.getCurrent().getSession().getId();
                VaadinSession.getCurrent().setAttribute("nombre",ALUMNO_INGRESADO.getNombre());
                VaadinSession.getCurrent().setAttribute("paterno",ALUMNO_INGRESADO.getPaterno());
                VaadinSession.getCurrent().setAttribute("cuenta",ALUMNO_INGRESADO.getCuenta());
                VaadinSession.getCurrent().setAttribute("tema",selectTema.getValue().getNombre());
                VaadinSession.getCurrent().setAttribute("preguntas",repositorioPregunta.findByTema(selectTema.getValue().getNombre()));


                subWindowExamen.close();

                //  tabito.addTab(tabInicio,"Inicio",FontAwesome.HOME);
                // Create a sub-window and set the content
                //  tabito.removeComponent(v1);
                tabito.addTab(tabExamen,"EXAMEN",FontAwesome.BOOK);
            }else{
                labelExamen.addStyleName(ValoTheme.LABEL_FAILURE);
                labelExamen.setValue("DATOS INCORRECTOS");
            }

        });



    });



    botonExamenFinal.addClickListener(xxx->{
        subWindowExamen.center();
        subWindowExamen.setWidth("350px");
        addWindow(subWindowExamen);
        alumnos= (ArrayList<Alumno>) repositorioAlumno.findAll();
        tabito.removeComponent(tabInicio);

        ComboBox<Alumno> select =
                new ComboBox<>();
        select.setPlaceholder("Escribe tu nombre aquí");
        select.setItems(alumnos);
        select.setWidth("100%");
        select.setItemCaptionGenerator(Alumno::getMiAlumno);








        //ComboTemas



        final  Label labelExamen=new Label("Bienvenido!!");
        subContentExamen.addComponent(labelExamen);

        final   PasswordField textoPasswordExamen=new PasswordField();
        textoPasswordExamen.setPlaceholder("Introduce tu cuenta");


        subContentExamen.addComponent(select);

        subContentExamen.addComponent(textoPasswordExamen);

        Button botonAutenticarExamen=new Button("Ingresar", FontAwesome.LOCK);
        botonAutenticarExamen.addStyleName(ValoTheme.BUTTON_PRIMARY);
        subContentExamen.addComponent(botonAutenticarExamen);
        botonAutenticarExamen.addClickListener(ingresar->{

            ALUMNO_INGRESADO=new Alumno();
            ALUMNO_INGRESADO=  select.getValue();
            System.out.println(ALUMNO_INGRESADO);
            if(repositorioAlumno.findByNombre(ALUMNO_INGRESADO.getNombre()).getCuenta().equals(textoPasswordExamen.getValue().toLowerCase())){

                String sesionId=     VaadinSession.getCurrent().getSession().getId();
                VaadinSession.getCurrent().setAttribute("nombre",ALUMNO_INGRESADO.getNombre());
                VaadinSession.getCurrent().setAttribute("paterno",ALUMNO_INGRESADO.getPaterno());
                VaadinSession.getCurrent().setAttribute("cuenta",ALUMNO_INGRESADO.getCuenta());


                subWindowExamen.close();

                //  tabito.addTab(tabInicio,"Inicio",FontAwesome.HOME);
                // Create a sub-window and set the content
                //  tabito.removeComponent(v1);
                tabito.addTab(tabExamenFinal,"EXAMEN",FontAwesome.BOOK);
            }else{
                labelExamen.addStyleName(ValoTheme.LABEL_FAILURE);
                labelExamen.setValue("DATOS INCORRECTOS");
            }

        });



    });






    /*************************************************************************************
     SEGUNDA VENTANA AUTENTICACION PREGUNTAS
     ************************************************************************************/

    Window subWindowAdministracion =new Window("Administración");
    VerticalLayout subContentAdministracion=new VerticalLayout();
    subWindowAdministracion.setContent(subContentAdministracion);

    botonAdministracion.addClickListener(ingresar->{
        subWindowAdministracion.center();
        addWindow(subWindowAdministracion);
        tabito.removeComponent(tabInicio);
    });

    Label labelAdmin=new Label("Bienvenido. Introduce tus datos");
    subContentAdministracion.addComponent(labelAdmin);
    final  TextField textoLoginAdministracion=new TextField();
    textoLoginAdministracion.setPlaceholder("Login");
    final   PasswordField textoPasswordAdministracion=new PasswordField();
    textoPasswordAdministracion.setPlaceholder("password");

    Button botonAutenticarAdministracion=new Button("Ingresar", FontAwesome.LOCK);
    botonAutenticarAdministracion.addStyleName(ValoTheme.BUTTON_PRIMARY);
    botonAutenticarAdministracion.addClickListener(ingresar->{
        if(textoLoginAdministracion.getValue().equals("campitos")&&textoPasswordAdministracion.getValue().equals("comoquesito")) {
            subWindowAdministracion.close();
            tabito.addTab(tabPreguntas, "Administración", FontAwesome.FACEBOOK);
            tabito.addTab(tabVerificador, "Verificar reactivos",FontAwesome.CHECK_SQUARE_O);

        }else {
            labelAdmin.setValue("DATOS INCORRECTOS");
        }
    });


    subContentAdministracion.addComponent(textoLoginAdministracion);
    subContentAdministracion.addComponent(textoPasswordAdministracion);
    subContentAdministracion.addComponent(botonAutenticarAdministracion);





    tabito.addTab(tabInicio,"Inicio",FontAwesome.HOME);

    setContent(tabito);
}

}


