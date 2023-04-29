package Calendario.Alarma;

import Calendario.Intervalo;

import java.time.LocalDateTime;

public class Absoluta extends Alarma {
    private LocalDateTime fechaHoraAlarma;

    public Absoluta (LocalDateTime fechaHoraAlarma) {
        this.fechaHoraAlarma = fechaHoraAlarma;
    }

    @Override
    public void sonarAlarma(LocalDateTime fechaHoraActual, Intervalo intervaloActividad) {
        if (fechaHoraActual.isEqual(fechaHoraAlarma)){
            alarmaActivada = true;
        }else{
            alarmaActivada = false;
        }
    }
}
