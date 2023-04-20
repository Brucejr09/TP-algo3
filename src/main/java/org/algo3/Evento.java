package org.algo3;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Evento extends Actividad{
    public Evento (String nombre, String descripcion) {
        super(nombre, descripcion);
    }

    public void comienzaEvento (int anio, int mes, int dia, int hora, int minuto) {
        this.comienza = LocalDateTime.of(anio, mes, dia, hora, minuto);
    }

    public void finalizaEvento () {
        this.finaliza = LocalDateTime.MAX;
    }

    public Evento repetirDiariamente (long dias) {
        Evento eventoRepetido = new Evento(this.titulo, this.descripcion);
        LocalDateTime nuevaFecha = this.comienza.plusDays(dias);
        eventoRepetido.comienzaEvento(nuevaFecha.getYear(), nuevaFecha.getMonthValue(), nuevaFecha.getDayOfMonth(), nuevaFecha.getHour(), nuevaFecha.getMinute());
        return eventoRepetido;
    }

    public Evento repetirSemanalmente (String dia) {
        Evento eventoRepetido = new Evento(this.titulo, this.descripcion);
        LocalDateTime nuevaFecha = this.comienza;
        boolean coincidio = true;

        while (!nuevaFecha.getDayOfWeek().equals(DayOfWeek.valueOf(dia))) {
            nuevaFecha = nuevaFecha.plusDays(1);
            coincidio = false;
        }
        if (coincidio)
            nuevaFecha = this.comienza.plusWeeks(1);

        eventoRepetido.comienzaEvento(nuevaFecha.getYear(), nuevaFecha.getMonthValue(), nuevaFecha.getDayOfMonth(), nuevaFecha.getHour(), nuevaFecha.getMinute());
        return eventoRepetido;
    }

    public Evento repetirMensualmente () {
        Evento eventoRepetido = new Evento(this.titulo, this.descripcion);
        LocalDateTime nuevaFecha = this.comienza.plusMonths(1);
        eventoRepetido.comienzaEvento(nuevaFecha.getYear(), nuevaFecha.getMonthValue(), nuevaFecha.getDayOfMonth(), nuevaFecha.getHour(), nuevaFecha.getMinute());
        return eventoRepetido;
    }

    public Evento repetirAnualmente () {
        Evento eventoRepetido = new Evento(this.titulo, this.descripcion);
        LocalDateTime nuevaFecha = this.comienza.plusYears(1);
        eventoRepetido.comienzaEvento(nuevaFecha.getYear(), nuevaFecha.getMonthValue(), nuevaFecha.getDayOfMonth(), nuevaFecha.getHour(), nuevaFecha.getMinute());
        return eventoRepetido;
    }
}}
