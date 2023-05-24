package Calendario;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.Tarea;
import Calendario.Alarma.Alarma;
import Calendario.Actividad.Evento;
import Calendario.Repeticion.Repeticion;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Hashtable;

public class Calendario implements Serializable{
    private Hashtable<Integer, Actividad> actividades;
    private int proxId;

    public Calendario() {
        this.actividades = new Hashtable<>();
        this.proxId = 0;
    }

    public int getProxId() {
        return this.proxId;
    }

    public void setActividades(Hashtable<Integer, Actividad> actividades) {
        this.actividades = actividades;
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

    public void serializar (String nombreArchivo) throws IOException {
        ObjectOutputStream objetoAGuardar = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nombreArchivo)));
        objetoAGuardar.writeObject(actividades);
        objetoAGuardar.close();
    }

    public Hashtable<Integer, Actividad> deSerializar (String nombreArchivo) throws IOException, ClassNotFoundException {
        Hashtable<Integer, Actividad> nuevasActividades;

        try {
            FileInputStream archivoPreparado = new FileInputStream(nombreArchivo);
            BufferedInputStream archivoOptimizado = new BufferedInputStream(archivoPreparado);
            ObjectInputStream objetoALeer = new ObjectInputStream(archivoOptimizado);
            nuevasActividades = (Hashtable<Integer, Actividad>) objetoALeer.readObject();
            objetoALeer.close();
        }
        catch (IOException | ClassNotFoundException excepcion){
            nuevasActividades = new Hashtable<>();
        }
        return  nuevasActividades;
    }

    public Actividad buscarActividad(int index) {
        return actividades.get(index);
    }

    public Hashtable<Integer, Actividad> getActividades() {
        return this.actividades;
    }
}
