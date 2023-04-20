package org.algo3;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Evento extends Actividad implements Frecuencia<LocalDateTime> {
    private LocalDateTime duracion;
    private int repeticiones;
    public Evento (String nombre, String descripcion) {
        super(nombre, descripcion);
        this.repeticiones = 0;
    }

    public LocalDateTime Diariamente (long dias) {
        LocalDateTime nuevaFecha = this.finaliza.plusDays(dias);
        return LocalDateTime.of(nuevaFecha.toLocalDate(), this.comienza.toLocalTime());
    }

    public LocalDateTime Semanalmente (String dia) {
        LocalDateTime nuevaFecha = this.comienza;
        boolean coincidio = true;

        while (!nuevaFecha.getDayOfWeek().equals(DayOfWeek.valueOf(dia))) {
            nuevaFecha = nuevaFecha.plusDays(1);
            coincidio = false;
        }
        if (coincidio)
            nuevaFecha = this.comienza.plusWeeks(1);

        return nuevaFecha;
    }

    public LocalDateTime Mensualmente () {
        return this.comienza.plusMonths(1);
    }

    public LocalDateTime Anualmente () {
        return this.comienza.plusYears(1);
    }

    public void comienzaEvento (LocalDate fechaApertura, LocalTime horarioApertura) {
        this.comienza = LocalDateTime.of(fechaApertura, horarioApertura);
    }

    public void duracionInfinita () {
        this.duracion = LocalDateTime.MAX;
    }

    public void duracionHastaFecha (LocalDate fechaLimite, LocalTime horarioLimite) {
        this.duracion = LocalDateTime.of(fechaLimite, horarioLimite);
    }

    public void duracionHastaRepetir (int repeticiones) {
        if (repeticiones != this.repeticiones)
            this.duracion = this.finaliza;
    }

    public Evento repetirEvento (LocalDateTime frecuencia) {
        Evento eventoRepetido = new Evento(this.titulo, this.descripcion);
        eventoRepetido.comienzaEvento(frecuencia.toLocalDate(), frecuencia.toLocalTime());
        eventoRepetido.asignarFinaliza(this.finaliza);
        this.repeticiones++;
        return eventoRepetido;
    }

    private void asignarFinaliza (LocalDateTime finaliza) {
        this.finaliza = finaliza;
    }
}
