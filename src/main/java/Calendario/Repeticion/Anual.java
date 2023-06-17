package Calendario.Repeticion;

import Calendario.Intervalo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Anual extends Repeticion {
    private final int frecuencia;

    public Anual(LocalDateTime finalRepeticion, int frecuencia) {
        super(finalRepeticion);
        this.frecuencia = frecuencia;
    }

    public Anual(int limiteDeOcurrencias, int frecuencia) {
        super(limiteDeOcurrencias);
        this.frecuencia = frecuencia;
    }

    @Override
    public Intervalo darSiguienteOcurrencia(LocalDateTime fechaHoraActual, Intervalo intervalo) {
        long numeroDeOcurrencia = this.calcularOcurrencia(fechaHoraActual, intervalo);
        if ( (numeroDeOcurrencia == 0) && (intervalo.comienzaAhoraODespues(fechaHoraActual)) ){ return intervalo; }
        if (numeroDeOcurrencia>=limiteDeOcurrencias){ return intervalo.sumarAnios(limiteDeOcurrencias * frecuencia);}

        Intervalo intervaloSiguienteOcurrencia = intervalo.sumarAnios(numeroDeOcurrencia * frecuencia);

        if (!intervaloSiguienteOcurrencia.comienzaAhoraODespues(fechaHoraActual))
            intervaloSiguienteOcurrencia = intervaloSiguienteOcurrencia.sumarAnios(frecuencia);

        return intervaloSiguienteOcurrencia;
    }

    @Override
    protected long calcularOcurrencia(LocalDateTime fechaHora, Intervalo intervalo) {
        long anios = intervalo.hasta(fechaHora, ChronoUnit.YEARS);
        long ocurrencia = anios/frecuencia;

        return (int)ocurrencia;
    }
}
