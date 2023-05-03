package Calendario.Actividad;

import Calendario.Alarma.Alarma;
import Calendario.DiaCompleto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Actividad implements DiaCompleto {
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

    public void asignarAlarma(Alarma alarma) {
        alarmas.add(alarma);
    }

    public int obtenerId() {
        return id;
    }

    public abstract void controlar(LocalDateTime fechaHoraActual);
}
