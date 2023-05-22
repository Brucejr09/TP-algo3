package Calendario.Actividad;

import Calendario.Alarma.Alarma;
import Calendario.DiaCompleto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Actividad implements DiaCompleto, Serializable {
    protected int id;
    protected String titulo;
    protected String descripcion;
    protected ArrayList<Alarma> alarmas;

    protected Actividad(int id, String titulo, String descripcion){
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.alarmas = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return this.id + this.titulo.length() + this.descripcion.length();
    }

    public void asignarAlarma(Alarma alarma) {
        alarmas.add(alarma);
    }

    public int obtenerId() {
        return id;
    }

    public abstract void controlar(LocalDateTime fechaHoraActual);
}
