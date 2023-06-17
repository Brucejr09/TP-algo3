package Calendario.Repeticion;

import Calendario.Intervalo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Diaria extends Repeticion{
    private final int frecuencia;

    public Diaria(LocalDateTime finalRepeticion, int frecuencia) {
        super(finalRepeticion);
        this.frecuencia = frecuencia;
    }

    public Diaria(int limiteDeOcurrencias, int frecuencia) {
        super(limiteDeOcurrencias);
        this.frecuencia = frecuencia;
    }

    @Override
    public Intervalo darSiguienteOcurrencia(LocalDateTime fechaHoraActual, Intervalo intervalo) {
        long numeroDeOcurrencia = this.calcularOcurrencia(fechaHoraActual, intervalo);
        if (numeroDeOcurrencia>=limiteDeOcurrencias){ return intervalo.sumarDias(limiteDeOcurrencias * frecuencia);}

        Intervalo intervaloSiguienteOcurrencia = intervalo.sumarDias(numeroDeOcurrencia * frecuencia);

        if (!intervaloSiguienteOcurrencia.comienzaAhoraODespues(fechaHoraActual))
             intervaloSiguienteOcurrencia = intervaloSiguienteOcurrencia.sumarDias(frecuencia);

        return intervaloSiguienteOcurrencia;
    }

    protected long calcularOcurrencia(LocalDateTime fechaHora, Intervalo intervalo){
        long dias = intervalo.hasta(fechaHora, ChronoUnit.DAYS);
        long ocurrencia = dias/frecuencia;

        return ocurrencia;
    }
}
