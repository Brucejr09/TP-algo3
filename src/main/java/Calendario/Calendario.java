package Calendario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Calendario {
    private LocalDateTime fechaHoraActual;
    private ArrayList<Actividad> actividades;

    public Calendario(LocalDateTime fechaHoraActual) {
        this.fechaHoraActual = fechaHoraActual;
        this.actividades = new ArrayList<>();
    }

    public String fechaActual() {
        return fechaHoraActual.toLocalDate().toString();
    }

    public String horaActual() {
        return fechaHoraActual.toLocalTime().toString();
    }

    public void avanzarTiempo() {
        this.fechaHoraActual = fechaHoraActual.plusMinutes(15);
        for (Actividad actividad:actividades) {
            actividad.ejecutar(fechaHoraActual);
        }
    }

    public Tarea crearTarea(String nombre, String descripcion,LocalDate fecha) {
        Tarea nuevaTarea = new Tarea(nombre,descripcion);
        nuevaTarea.actividadDiaria(fecha);
        this.actividades.add(nuevaTarea);
        return nuevaTarea;
    }

    public void setFechaHoraLocal(LocalDateTime nuevaFechaHoraActual) {
        this.fechaHoraActual = nuevaFechaHoraActual;
    }

    public Tarea crearTarea(String nombre, String descripcion, LocalDateTime fechaLimite) {
        Tarea nuevaTarea = new Tarea(nombre,descripcion);
        nuevaTarea.comienzaActividad(fechaHoraActual);
        nuevaTarea.finalizaActividad(fechaLimite);
        this.actividades.add(nuevaTarea);
        return nuevaTarea;
    }
}
