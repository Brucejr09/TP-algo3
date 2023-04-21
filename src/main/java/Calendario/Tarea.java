package Calendario;

import java.time.LocalDateTime;

public class Tarea extends Actividad {
    private boolean completada;

    public Tarea (String nombre, String descripcion) {
        super(nombre, descripcion);
        this.completada = false;
    }

    @Override
    public void ejecutar(LocalDateTime fechaHoraActual) {
        if(fechaHoraActual.isAfter(this.finaliza) || fechaHoraActual.isEqual(this.finaliza)){ this.tareaCompletada(); }
    }

    public void tareaCompletada () {
        this.completada = true;
    }

    public boolean estaCompletada() {
        return completada;
    }
}