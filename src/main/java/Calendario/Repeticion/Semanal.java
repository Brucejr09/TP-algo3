package Calendario.Repeticion;

import Calendario.Intervalo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Semanal extends Repeticion {

    private ArrayList<DayOfWeek> seleccionDiasDeSemana;

    public Semanal(LocalDateTime finalRepeticion, ArrayList<DayOfWeek> seleccionDiasDeSemana) {
        super(finalRepeticion);
        this.seleccionDiasDeSemana = new ArrayList<>(seleccionDiasDeSemana.stream().distinct().collect(Collectors.toList()));
    }

    public Semanal(int limiteDeOcurrencias, ArrayList<DayOfWeek> seleccionDiasDeSemana) {
        super(limiteDeOcurrencias);
        this.seleccionDiasDeSemana = new ArrayList<>(seleccionDiasDeSemana.stream().distinct().collect(Collectors.toList()));
    }

    @Override
    public Intervalo darSiguienteOcurrencia(LocalDateTime fechaHoraActual, Intervalo intervalo) {
        long semanas = intervalo.hasta(fechaHoraActual, ChronoUnit.DAYS) / 7;
        int ocurrencia = (int)semanas;
        Intervalo nuevoIntervalo = intervalo.sumarDias(ocurrencia*7);
        ocurrencia = ocurrencia * seleccionDiasDeSemana.size();

        for (int i = 0; i < 8; i++) {
            if ( seleccionDiasDeSemana.contains(nuevoIntervalo.diaDeSemana()) ){
                if (ocurrencia>=limiteDeOcurrencias){ return nuevoIntervalo; }
                if (nuevoIntervalo.comienzaAhoraODespues(fechaHoraActual)){
                    return nuevoIntervalo;
                }else{
                    ocurrencia++;
                }
            }
            nuevoIntervalo = nuevoIntervalo.sumarDias(1);
        }

        return nuevoIntervalo;
    }

    protected long calcularOcurrencia(LocalDateTime fechaHora, Intervalo intervalo){
        long semanas = intervalo.hasta(fechaHora, ChronoUnit.DAYS) / 7;
        int ocurrencia = (int)semanas;
        Intervalo nuevoIntervalo = intervalo.sumarDias(ocurrencia*7);
        ocurrencia = ocurrencia * seleccionDiasDeSemana.size();

        for (int i = 0; i < 7; i++) {
            nuevoIntervalo = nuevoIntervalo.sumarDias(1);
            if ( (seleccionDiasDeSemana.contains(nuevoIntervalo.diaDeSemana())) && (!nuevoIntervalo.comienzaAhoraODespues(fechaHora)) ){ocurrencia++;}
        }

        return ocurrencia;
    }


}
