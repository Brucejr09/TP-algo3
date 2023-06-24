package Calendario;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public boolean comienzaAhoraODespues(LocalDateTime fechaHora){
        return comienzo.isAfter(fechaHora) || comienzo.isEqual(fechaHora);
    }

    public long cuantoFaltaParaComenzar(LocalDateTime fechaHoraActual, ChronoUnit unidad) {
        return fechaHoraActual.until(comienzo, unidad);
    }

    public long hasta(LocalDateTime fechaHoraActual, ChronoUnit unidad) {
        long cant = comienzo.until(fechaHoraActual, unidad);
        return (cant < 0)? 0:cant;
    }

    public Intervalo sumarDias(long cantDias) {
        return new Intervalo(comienzo.plusDays(cantDias), fin.plusDays(cantDias));
    }

    public DayOfWeek diaDeSemana() { return comienzo.getDayOfWeek(); }

    public Intervalo sumarMeses(long cantMeses) {
        return new Intervalo(comienzo.plusMonths(cantMeses), fin.plusMonths(cantMeses));
    }

    public Intervalo sumarAnios(long cantAnios) {
        return new Intervalo(comienzo.plusYears(cantAnios), fin.plusYears(cantAnios));
    }

    public boolean comienzaHoy(LocalDate dia) {
        return comienzo.toLocalDate().isEqual(dia);
    }

    public String toString(LocalDate fecha) {
        LocalTime horaInicio = comienzo.toLocalTime();
        if (comienzo.plusDays(1).isEqual(fin)) {
            return "Dia Completo";
        }
        LocalDateTime nuevoFin = LocalDateTime.of(fecha, comienzo.toLocalTime()).plusMinutes(comienzo.until(fin, ChronoUnit.MINUTES));
        return horaInicio.toString() + " - " + nuevoFin.toString();
    }
}
