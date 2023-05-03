package Calendario.Alarma;

import Calendario.Intervalo;
import Calendario.Notificable.Notificable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Relativa extends Alarma {

    private int minutosAntes;
    public Relativa (Notificable tipoDeAviso, int minutos) {
        super(tipoDeAviso);
        this.minutosAntes = minutos;
    }

    @Override
    public boolean estaActiva(LocalDateTime fechaHoraActual, Intervalo intervaloActividad) {
        return intervaloActividad.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES) == minutosAntes;
    }
}
