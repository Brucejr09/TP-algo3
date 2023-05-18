package Calendario;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.Tarea;
import Calendario.Alarma.Alarma;
import Calendario.Actividad.Evento;
import Calendario.Repeticion.Repeticion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Hashtable;

public class Calendario {
    private Hashtable<Integer, Actividad> actividades;
    private int proxId;

    public Calendario() {
        this.actividades = new Hashtable<>();
        this.proxId = 0;

    }

    public void controlarActividades(LocalDateTime fechaHoraActual) {
        for (Actividad actividad : actividades.values()) {
            actividad.controlar(fechaHoraActual);
        }
    }

    public Tarea crearTarea(String nombre, String descripcion,LocalDate fecha) {
        Tarea nuevaTarea = new Tarea(proxId, nombre, descripcion);
        nuevaTarea.asignarDiaCompleto(fecha);
        actividades.put(proxId,nuevaTarea);
        proxId++;
        return nuevaTarea;
    }

    public Tarea crearTarea(String nombre, String descripcion, LocalDateTime fechaLimite) {
        Tarea nuevaTarea = new Tarea(proxId, nombre, descripcion);
        nuevaTarea.asignarVencimiento(fechaLimite);
        actividades.put(proxId,nuevaTarea);
        proxId++;
        return nuevaTarea;
    }

    public void asignarAlarma(Alarma alarma, int id){
        actividades.get(id).asignarAlarma(alarma);
    }

    public Evento crearEvento(String titulo, String descripcion, Repeticion tipoRepeticion, Intervalo intervalo) {
        Evento nuevoEvento = new Evento(proxId, titulo, descripcion, tipoRepeticion, intervalo);
        actividades.put(proxId,nuevoEvento);
        proxId++;
        return nuevoEvento;
    }
}
