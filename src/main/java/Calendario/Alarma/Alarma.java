package Calendario.Alarma;

import Calendario.Intervalo;
import Calendario.Notificable.Notificable;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Alarma implements Serializable {
    protected Notificable tipoDeAviso;

    protected Alarma (Notificable tipoDeAviso) {
        this.tipoDeAviso = tipoDeAviso;
    }

    public Notificable getTipoDeAviso() {
        return tipoDeAviso;
    }

    public int sonarAlarma (LocalDateTime fechaHoraActual, Intervalo intervaloActividad) {
        if (estaActiva(fechaHoraActual, intervaloActividad)) {
            tipoDeAviso.aumentarNotificacion();
            return tipoDeAviso.obtenerCantidadNotificables();
        }
        return 0;
    }

    protected abstract boolean estaActiva(LocalDateTime fechaHoraActual, Intervalo intervaloActividad);
}
