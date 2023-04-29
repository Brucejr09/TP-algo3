package Calendario.Repeticion;

import Calendario.Intervalo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Anual extends Repeticion {
    private int frecuencia;

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
        int numeroDeOcurrencia = this.calcularOcurrencia(fechaHoraActual, intervalo);
        if ( (numeroDeOcurrencia == 0) && (intervalo.comienzaDespues(fechaHoraActual)) ){ return intervalo; }
        if (numeroDeOcurrencia>=limiteDeOcurrencias){ return intervalo.sumarAnios(limiteDeOcurrencias * frecuencia);}

        int aniosTotales = numeroDeOcurrencia * frecuencia + frecuencia;

        return intervalo.sumarAnios(aniosTotales);
    }

    @Override
    protected int calcularOcurrencia(LocalDateTime fechaHora, Intervalo intervalo) {
        long anios = intervalo.hasta(fechaHora, ChronoUnit.YEARS);
        long ocurrencia = anios/frecuencia;

        return (int)ocurrencia;
    }
}
