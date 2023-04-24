package Calendario;

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

    public Evento (String titulo, String descripcion, int frecuencia) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.frecuencia = frecuencia;
        this.comienza = LocalDateTime.MIN;
        this.finaliza = LocalDateTime.MIN;
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
}
