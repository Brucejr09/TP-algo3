package Calendario.Repeticion;

import Calendario.Intervalo;

import java.time.LocalDateTime;

public class Unica extends Repeticion{
    public Unica(){
        super(LocalDateTime.MAX);
    }

    @Override
    public Intervalo darSiguienteOcurrencia(LocalDateTime fechaHoraActual, Intervalo intervalo) {
        return intervalo;
    }

    @Override
    protected int calcularOcurrencia(LocalDateTime fechaHoraActual, Intervalo intervalo) { return 0; }

}
