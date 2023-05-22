package Calendario;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Intervalo implements Serializable {
    private final LocalDateTime comienzo;
    private final LocalDateTime fin;

    public Intervalo(LocalDateTime fechaHoraComienzo) {
        this.comienzo = fechaHoraComienzo;
        this.fin = fechaHoraComienzo.plusDays(1);
    }

    public Intervalo(LocalDateTime comienza, LocalDateTime finaliza) {
        this.comienzo = comienza;
        this.fin = finaliza;
    }

    public boolean comienzaDespues(LocalDateTime fechaHora){
        return comienzo.isAfter(fechaHora);
    }

    public long cuantoFaltaParaComenzar(LocalDateTime fechaHoraActual, ChronoUnit unidad) {
        return fechaHoraActual.until(comienzo, unidad);
    }

    public long hasta(LocalDateTime fechaHoraActual, ChronoUnit unidad) {
        return comienzo.until(fechaHoraActual, unidad);
    }

    public Intervalo sumarDias(int cantDias) {
        return new Intervalo(comienzo.plusDays(cantDias), fin.plusDays(cantDias));
    }

    public DayOfWeek diaDeSemana() { return comienzo.getDayOfWeek(); }

    public Intervalo sumarMeses(int cantMeses) {
        return new Intervalo(comienzo.plusMonths(cantMeses), fin.plusMonths(cantMeses));
    }

    public Intervalo sumarAnios(int cantAnios) {
        return new Intervalo(comienzo.plusYears(cantAnios), fin.plusYears(cantAnios));
    }
}
