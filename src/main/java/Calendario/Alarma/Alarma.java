package Calendario.Alarma;

import Calendario.Intervalo;
import Calendario.Notificable.Notificable;

import java.time.LocalDateTime;

public abstract class Alarma {
    protected Notificable tipoDeAviso;

    protected Alarma (Notificable tipoDeAviso) {
        this.tipoDeAviso = tipoDeAviso;
    }

    public void sonarAlarma (LocalDateTime fechaHoraActual, Intervalo intervaloActividad) {
        if (estaActiva(fechaHoraActual, intervaloActividad)) tipoDeAviso.notificar();
    }

    protected abstract boolean estaActiva(LocalDateTime fechaHoraActual, Intervalo intervaloActividad);
}
