package Calendario;

import Calendario.Alarma.Alarma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Evento implements DiaCompleto, FechaLimite {
    protected String titulo;
    protected String descripcion;
    protected LocalDateTime comienza;
    protected LocalDateTime finaliza;
    protected LocalDate duracion;
    protected int frecuencia;
    protected ArrayList<Alarma> alarmas;
    protected int id;

    public Evento (String titulo, String descripcion, int frecuencia, int id) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.frecuencia = frecuencia;
        this.comienza = LocalDateTime.MIN;
        this.finaliza = LocalDateTime.MIN;
        this.id = id;
    }

    public Evento (String titulo, String descripcion, int frecuencia, LocalDate duracion, ArrayList<Alarma> alarmas, int id) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.frecuencia = frecuencia;
        this.comienza = LocalDateTime.MIN;
        this.finaliza = LocalDateTime.MIN;
        this.duracion = duracion;
        this.alarmas = alarmas;
        this.id = id;
    }

    public void asignarTitulo (String titulo) {
        this.titulo = titulo;
    }

    public void asignarDescripcion (String descripcion) {
        this.descripcion = descripcion;
    }

    public void asignarComienza(LocalDateTime fechaHoraComienzo) {
        if (fechaHoraComienzo.isAfter(this.finaliza)) {
            this.comienza = fechaHoraComienzo;
            this.finaliza = fechaHoraComienzo;
        }
        else
            this.comienza = fechaHoraComienzo;
    }

    public void asignarFinaliza(LocalDateTime fechaHoraLimite) {
        if (this.comienza.isBefore(fechaHoraLimite) || fechaHoraLimite.equals(this.comienza))
            this.finaliza = fechaHoraLimite;
    }

    @Override
    public void asignarDiaCompleto(LocalDate dia) {
        this.comienza = LocalDateTime.of(dia, LocalTime.MIN);
        this.finaliza = LocalDateTime.of(dia.plusDays(1), LocalTime.MIN);
    }

    @Override
    public void asignarDuracionInfinita() {
        this.duracion = LocalDate.MAX;
    }

    @Override
    public void asignarDuracionHastaFecha(LocalDate fechaLimite) {
        this.duracion = fechaLimite;
    }
    public abstract List<Evento> repetir (List<Evento> lista, LocalDate maximo);

    public boolean estaEnEspera (LocalDateTime fechaActual) {
        return fechaActual.isBefore(this.comienza);
    }

    public boolean estaFinalizado (LocalDateTime fechaActual) {
        return fechaActual.isAfter(this.finaliza);
    }

    public boolean estaRealizandose (LocalDateTime fechaActual) {
        return (fechaActual.equals(this.comienza) || fechaActual.isAfter(this.comienza)) && (fechaActual.equals(this.finaliza) || fechaActual.isBefore(this.finaliza));
    }

    public void asignarAlarma(Alarma alarma) {
        alarmas.add(alarma);
    }

    public int obtenerId() {
        return id;
    }

    public abstract void ejecutar(LocalDateTime fechaHoraActual);
}
