package Calendario.Alarma;

import Calendario.Intervalo;
import Calendario.Notificable.Notificable;

import java.time.LocalDateTime;

public class Absoluta extends Alarma {
    private LocalDateTime fechaHoraAlarma;

    public Absoluta (Notificable tipoDeAviso, LocalDateTime fechaHoraAlarma) {
        super(tipoDeAviso);
        this.fechaHoraAlarma = fechaHoraAlarma;
    }


    @Override
    public boolean estaActiva(LocalDateTime fechaHoraActual, Intervalo intervaloActividad) {
        return fechaHoraActual.isEqual(fechaHoraAlarma);
    }
}
