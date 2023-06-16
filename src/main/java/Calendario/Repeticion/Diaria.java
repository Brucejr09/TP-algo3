package Calendario.Repeticion;

import Calendario.Intervalo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Diaria extends Repeticion{
    private int frecuencia;

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
        int numeroDeOcurrencia = this.calcularOcurrencia(fechaHoraActual, intervalo);
        if ( (numeroDeOcurrencia == 0) && (intervalo.comienzaAhoraODespues(fechaHoraActual)) ){ return intervalo; }
        if (numeroDeOcurrencia>=limiteDeOcurrencias){ return intervalo.sumarDias(limiteDeOcurrencias * frecuencia);}

        int diasTotales = numeroDeOcurrencia * frecuencia + frecuencia;

        return intervalo.sumarDias(diasTotales);
    }

    protected int calcularOcurrencia(LocalDateTime fechaHora, Intervalo intervalo){
        long dias = intervalo.hasta(fechaHora, ChronoUnit.DAYS);
        long ocurrencia = dias/frecuencia;

        return (int)ocurrencia;
    }
}
