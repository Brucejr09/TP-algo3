package Calendario.Alarma;

import Calendario.Intervalo;

import java.time.LocalDateTime;

public abstract class Alarma {
    protected boolean alarmaActivada;

    protected Alarma () {
        this.alarmaActivada = false;
    }

    public abstract void sonarAlarma (LocalDateTime fechaHoraActual, Intervalo intervaloActividad);

    public boolean estaActivada (){
        return alarmaActivada;
    }
}
