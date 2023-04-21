package Calendario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Actividad {
    protected String titulo;
    protected String descripcion;
    protected LocalDateTime comienza;
    protected LocalDateTime finaliza;

    public Actividad (String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public void comienzaActividad (LocalDateTime fechaHoraComienzo) {
        this.comienza = fechaHoraComienzo;
    }

    public void actividadDiaria (LocalDate dia) {
        this.comienza = LocalDateTime.of(dia, LocalTime.MIN);
        this.finaliza = LocalDateTime.of(dia.plusDays(1), LocalTime.MIN);
    }

    public void finalizaActividad (LocalDateTime fechaHoraLimite) {
        this.finaliza = fechaHoraLimite;
    }

    public abstract void ejecutar(LocalDateTime fechaHoraActual);
}