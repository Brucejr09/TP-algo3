package Calendario.Actividad;

import Calendario.Alarma.Alarma;
import Calendario.Intervalo;
import Calendario.Repeticion.Repeticion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Evento extends Actividad{
    private boolean yaTermino;
    private Repeticion tipoRepeticion;

    protected Intervalo intervalo;

    public Evento(int id, String titulo, String descripcion, Repeticion tipoRepeticion, Intervalo intervalo) {
        super(id, titulo, descripcion);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);
        this.tipoRepeticion = tipoRepeticion;
        this.intervalo = intervalo;
        this.yaTermino = false;
    }

    public void asignarAlarma(Alarma alarma) {
        alarmas.add(alarma);
    }

    public int obtenerId() {
        return id;
    }

    public void controlar(LocalDateTime fechaHoraActual){
        if (yaTermino){ return; }
        Intervalo intervaloSiguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual, intervalo);
        if (!intervaloSiguienteOcurrencia.comienzaDespues(fechaHoraActual)){ yaTermino = true; }
        for (Alarma alarma: alarmas) {
            alarma.sonarAlarma(fechaHoraActual, intervaloSiguienteOcurrencia);
        }
    }

    @Override
    public void asignarDiaCompleto(LocalDate dia) {
        Intervalo nuevoIntervalo = new Intervalo(LocalDateTime.of(dia, LocalTime.MIN), LocalDateTime.of(dia.plusDays(1), LocalTime.MIN));
        tipoRepeticion.resolverCompatibilidadCon(nuevoIntervalo);
        this.intervalo = nuevoIntervalo;
    }
}
