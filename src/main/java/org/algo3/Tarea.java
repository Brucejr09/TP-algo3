package org.algo3;

import java.time.LocalDateTime;

public class Tarea extends Actividad {
    private boolean completada;

    public Tarea (String nombre, String descripcion) {
        super(nombre, descripcion);
        this.completada = false;
    }

    public void comienzaTarea () {
        this.comienza = LocalDateTime.now();
    }
    public void tareaCompletada () {
        this.completada = true;
    }

}