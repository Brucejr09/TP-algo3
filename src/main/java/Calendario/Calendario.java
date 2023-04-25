package Calendario;

import Calendario.Alarma.Alarma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class Calendario {
    private LocalDateTime fechaHoraActual;
    private Hashtable<Integer, Tarea> tareas;
    private Hashtable<Integer, Evento> eventos;
    private int proxId;

    public Calendario(LocalDateTime fechaHoraActual) {
        this.fechaHoraActual = fechaHoraActual;
        this.eventos = new Hashtable<>();
        this.tareas = new Hashtable<>();
        this.proxId = 0;

    }

    public String fechaActual() {
        return fechaHoraActual.toLocalDate().toString();
    }

    public String horaActual() {
        return fechaHoraActual.toLocalTime().toString();
    }

    public void avanzarTiempo() {
        this.fechaHoraActual = fechaHoraActual.plusMinutes(15);
        for (Tarea tarea:tareas.values()) {
            tarea.ejecutar(fechaHoraActual);
        }
        for (Evento evento: eventos.values()) {
            evento.ejecutar(fechaHoraActual);
        }
    }

    public Tarea crearTarea(String nombre, String descripcion,LocalDate fecha) {
        Tarea nuevaTarea = new Tarea(nombre,descripcion,proxId);
        nuevaTarea.asignarDiaCompleto(fecha);
        this.tareas.put(proxId,nuevaTarea);
        proxId++;
        return nuevaTarea;
    }

    public void setFechaHoraLocal(LocalDateTime nuevaFechaHoraActual) {
        this.fechaHoraActual = nuevaFechaHoraActual;
    }

    public Tarea crearTarea(String nombre, String descripcion, LocalDateTime fechaLimite) {
        Tarea nuevaTarea = new Tarea(nombre,descripcion,proxId);
        nuevaTarea.asignarVencimiento(fechaLimite);
        this.tareas.put(proxId,nuevaTarea);
        proxId++;
        return nuevaTarea;
    }

    public Alarma asignarAlarma(Alarma alarma, int id){
        if (tareas.containsKey(id)){
            tareas.get(id).asignarAlarma(alarma);
        }else{
            eventos.get(id).asignarAlarma(alarma);
        }

        return alarma;
    }

    public Evento crearEvento(String titulo, String descripcion, LocalDate fecha) {
        Evento nuevoEvento = new EventoUnico(titulo,descripcion, proxId);
        nuevoEvento.asignarDiaCompleto(fecha);
        eventos.put(proxId, nuevoEvento);
        return nuevoEvento;
    }
}
