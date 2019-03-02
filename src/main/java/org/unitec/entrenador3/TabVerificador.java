package org.unitec.entrenador3;

import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Arrays;
import java.util.List;

/**
 * Created by campitos on 17/03/17.
 */

@UIScope
@SpringUI
public class TabVerificador extends VerticalLayout {

    public  TabVerificador(RepositorioPregunta repositorio){

        Label label=new Label("Validador de Reactivos");
        label.addStyleName(ValoTheme.LABEL_LARGE);


        List<Todo> items = Arrays.asList(new Todo("Done task", true),
                new Todo("Not done", false));

        Grid<Todo> grid = new Grid<>();

        grid.setItems(items);

        TextArea taskField = new TextArea();
        taskField.setRows(5);
        CheckBox doneField = new CheckBox();

        Binder<Todo> binder = grid.getEditor().getBinder();

        Binder.Binding<Todo, Boolean> doneBinding = binder.bind(
                doneField, Todo::isDone, Todo::setDone);

        Grid.Column<Todo, String> column = grid.addColumn(
                todo -> String.valueOf(todo.isDone()));
        column.setWidth(75);
        column.setEditorBinding(doneBinding);

        grid.addColumn(Todo::getTask).setEditorComponent(
                taskField, Todo::setTask).setExpandRatio(1);
        grid.setSizeFull();

        grid.getEditor().setEnabled(true);

         grid.getEditor().setSaveCaption("Guardar todo");
        grid.getEditor().setCancelCaption("Cancelar");

TextField texto=new TextField();
texto.setPlaceholder("escribe algo");
        //Agregamos cosas
        addComponent(label);
        addComponent(grid);
        addComponent((texto));


    }
}
