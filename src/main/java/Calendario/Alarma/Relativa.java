package Calendario.Alarma;

import Calendario.Intervalo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Relativa extends Alarma {

    private int minutosAntes;
    public Relativa (int minutos) {
        this.minutosAntes = minutos;
    }

    @Override
    public void sonarAlarma(LocalDateTime fechaHoraActual, Intervalo intervaloActividad) {
        if (intervaloActividad.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES) == minutosAntes){
            alarmaActivada = true;
        }else{
            alarmaActivada = false;
        }
    }
}
