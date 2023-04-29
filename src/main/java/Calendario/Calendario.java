package Calendario;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.Tarea;
import Calendario.Alarma.Alarma;
import Calendario.Actividad.Evento;
import Calendario.Repeticion.Repeticion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class Calendario {
    private ArrayList<Actividad> actividades;
    private LocalDateTime fechaHoraActual;
    private Hashtable<Integer, Tarea> tareas;
    private Hashtable<Integer, Evento> eventos;
    private int proxId;

    public Calendario(LocalDateTime fechaHoraActual) {
        this.fechaHoraActual = fechaHoraActual;
        this.eventos = new Hashtable<>();
        this.tareas = new Hashtable<>();
        this.actividades = new ArrayList<>();
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
        for (Actividad actividad : actividades) {
            actividad.controlar(fechaHoraActual);
        }
    }

    public Tarea crearTarea(String nombre, String descripcion,LocalDate fecha) {
        Tarea nuevaTarea = new Tarea(proxId, nombre, descripcion);
        nuevaTarea.asignarDiaCompleto(fecha);
        tareas.put(proxId,nuevaTarea);
        actividades.add(nuevaTarea);
        proxId++;
        return nuevaTarea;
    }

    public void setFechaHoraLocal(LocalDateTime nuevaFechaHoraActual) {
        this.fechaHoraActual = nuevaFechaHoraActual;
    }

    public Tarea crearTarea(String nombre, String descripcion, LocalDateTime fechaLimite) {
        Tarea nuevaTarea = new Tarea(proxId, nombre, descripcion);
        nuevaTarea.asignarVencimiento(fechaLimite);
        tareas.put(proxId,nuevaTarea);
        actividades.add(nuevaTarea);
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

    public Evento crearEvento(String titulo, String descripcion, Repeticion tipoRepeticion, Intervalo intervalo) {
        Evento nuevoEvento = new Evento(proxId, titulo, descripcion, tipoRepeticion, intervalo);
        eventos.put(proxId, nuevoEvento);
        actividades.add(nuevoEvento);
        proxId++;
        return nuevoEvento;
    }
}
