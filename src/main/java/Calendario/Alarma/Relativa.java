package Calendario.Alarma;

import Calendario.Alarma.Alarma;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Relativa extends Alarma {

    private int minutosAntes;
    public Relativa (int minutos) {
        this.minutosAntes = minutos;
    }

    @Override
    public void sonarAlarma(LocalDateTime fechaHoraActual, LocalDateTime fechaHoraActividad) {
        if (fechaHoraActual.isEqual(fechaHoraActividad.minusMinutes(minutosAntes))){
            alarmaActivada = true;
        }
    }
}
