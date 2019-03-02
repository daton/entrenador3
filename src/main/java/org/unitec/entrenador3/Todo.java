package org.unitec.entrenador3;

/**
 * Created by campitos on 17/03/17.
 */
public class Todo {
    String task;
    boolean done;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Todo(String task, boolean done) {
        this.task = task;
        this.done = done;
    }
}
