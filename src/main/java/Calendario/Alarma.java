package Calendario;

import java.time.LocalDateTime;

public abstract class Alarma {
    private LocalDateTime fechaHorario;
    protected boolean alarmaActivada;

    public Alarma () {
        this.alarmaActivada = false;
    }
    public void alarmaAbsoluta (LocalDateTime comienzo) {
        this.fechaHorario = comienzo.minusDays(1);
    }

    public void alarmaPersonalizada (LocalDateTime comienzo, long minutos) {
        this.fechaHorario = comienzo.minusMinutes(minutos);
    }

    public void activarAlarma (LocalDateTime fechaHoraActual) {
        if (fechaHoraActual.equals(this.fechaHorario))
            this.alarmaActivada = true;
    }

    public abstract boolean estaActivada ();
}
