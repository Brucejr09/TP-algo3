package Calendario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Calendario {
    private LocalDateTime fechaHoraActual;
    private ArrayList<Tarea> tareas;
    private ArrayList<Evento> eventos;

    public Calendario(LocalDateTime fechaHoraActual) {
        this.fechaHoraActual = fechaHoraActual;
        this.eventos = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }

    public String fechaActual() {
        return fechaHoraActual.toLocalDate().toString();
    }

    public String horaActual() {
        return fechaHoraActual.toLocalTime().toString();
    }

    public void avanzarTiempo() {
        this.fechaHoraActual = fechaHoraActual.plusMinutes(15);
        for (Tarea tarea:tareas) {
            tarea.ejecutar(fechaHoraActual);
        }
    }

    public Tarea crearTarea(String nombre, String descripcion,LocalDate fecha) {
        Tarea nuevaTarea = new Tarea(nombre,descripcion);
        nuevaTarea.asignarDiaCompleto(fecha);
        this.tareas.add(nuevaTarea);
        return nuevaTarea;
    }

    public void setFechaHoraLocal(LocalDateTime nuevaFechaHoraActual) {
        this.fechaHoraActual = nuevaFechaHoraActual;
    }

    public Tarea crearTarea(String nombre, String descripcion, LocalDateTime fechaLimite) {
        Tarea nuevaTarea = new Tarea(nombre,descripcion);
        nuevaTarea.asignarVencimiento(fechaLimite);
        this.tareas.add(nuevaTarea);
        return nuevaTarea;
    }

    public Alarma asignarAlarma(int minutosAntes, Tarea tarea){
        Alarma alarma = new AlarmaSonora();
        tarea.asignarAlarma(alarma, minutosAntes);
        return alarma;
    }
}
