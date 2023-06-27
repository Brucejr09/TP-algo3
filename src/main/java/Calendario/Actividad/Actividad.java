package Calendario.Actividad;

import Calendario.Alarma.Alarma;
import Calendario.DiaCompleto;
import Calendario.Intervalo;

import java.io.Serializable;
import java.time.LocalDate;
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

    public abstract String getTitulo();

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Alarma> getAlarmas() {
        return this.alarmas;
    }

    public abstract Intervalo obtenerIntervalo (LocalDate fecha);

    public void asignarAlarma(Alarma alarma) {
        alarmas.add(alarma);
    }

    public int obtenerId() {
        return id;
    }

    public abstract void controlar(LocalDateTime fechaHoraActual);

    public abstract boolean soyDelDia (LocalDate dia);

    public abstract String getIntervalo(LocalDate fecha);
}
