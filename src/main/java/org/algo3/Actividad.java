package org.algo3;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Actividad {
    protected String titulo;
    protected String descripcion;
    protected LocalDateTime comienza;
    protected LocalDateTime finaliza;

    public Actividad (String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public void actividadDiaria (int anio, int mes, int dia) {
        this.comienza = this.comenzaActividad (anio, mes, dia);
        this.finaliza = LocalDateTime.of(anio, mes, dia, 23, 59, 59, 999999999);
    }

    public void finalizaActividad (int anio, int mes, int dia, int hora, int minuto) {
        this.finaliza = LocalDateTime.of(anio, mes, dia, hora, minuto);
    }

    private LocalDateTime comenzaActividad (int anio, int mes, int dia) {
        if (LocalDate.now().equals(LocalDate.of(anio, mes, dia)))
            return LocalDateTime.now();
        else
            return LocalDateTime.of(anio, mes, dia, 0, 0, 0, 0);
    }
}