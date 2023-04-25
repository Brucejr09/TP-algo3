package Calendario.Alarma;

import Calendario.Alarma.Alarma;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Absoluta extends Alarma {
    private LocalDateTime fechaHoraAlarma;

    public Absoluta (LocalDateTime fechaHoraAlarma) {
        this.fechaHoraAlarma = fechaHoraAlarma;
    }

    @Override
    public void sonarAlarma(LocalDateTime fechaHoraActual, LocalDateTime fechaHoraActividad) {
        if (fechaHoraActual.isEqual(fechaHoraAlarma)){
            alarmaActivada = true;
        }
    }
}
