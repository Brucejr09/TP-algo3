package Calendario.Alarma;

import java.time.LocalDateTime;

public abstract class Alarma {
    protected boolean alarmaActivada;

    public Alarma () {
        this.alarmaActivada = false;
    }

    public abstract void sonarAlarma (LocalDateTime fechaHoraActual, LocalDateTime fechaHoraActividad);

    public boolean estaActivada (){
        return alarmaActivada;
    }
}
