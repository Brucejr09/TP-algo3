package Calendario.Repeticion;

import Calendario.Intervalo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Mensual extends Repeticion {
    private final int frecuencia;

    public Mensual(LocalDateTime finalRepeticion, int frecuencia) {
        super(finalRepeticion);
        this.frecuencia = frecuencia;
    }

    public Mensual(int limiteDeOcurrencias, int frecuencia) {
        super(limiteDeOcurrencias);
        this.frecuencia = frecuencia;
    }

    @Override
    public Intervalo darSiguienteOcurrencia(LocalDateTime fechaHoraActual, Intervalo intervalo) {
        int numeroDeOcurrencia = this.calcularOcurrencia(fechaHoraActual, intervalo);
        if (numeroDeOcurrencia>=limiteDeOcurrencias){ return intervalo.sumarMeses(limiteDeOcurrencias * frecuencia);}

        Intervalo intervaloSiguienteOcurrencia = intervalo.sumarMeses(numeroDeOcurrencia * frecuencia);

        if (!intervaloSiguienteOcurrencia.comienzaAhoraODespues(fechaHoraActual))
            intervaloSiguienteOcurrencia = intervaloSiguienteOcurrencia.sumarMeses(frecuencia);

        return intervaloSiguienteOcurrencia;
    }

    @Override
    protected int calcularOcurrencia(LocalDateTime fechaHora, Intervalo intervalo) {
        long meses = intervalo.hasta(fechaHora, ChronoUnit.MONTHS);
        long ocurrencia = meses/frecuencia;

        return (int)ocurrencia;
    }
}
