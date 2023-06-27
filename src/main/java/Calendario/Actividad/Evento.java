package Calendario.Actividad;

import Calendario.Alarma.Alarma;
import Calendario.Intervalo;
import Calendario.Repeticion.Repeticion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Evento extends Actividad{
    private Repeticion tipoRepeticion;

    protected Intervalo intervalo;

    public Evento(int id, String titulo, String descripcion, Repeticion tipoRepeticion, Intervalo intervalo) {
        super(id, titulo, descripcion);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);
        this.tipoRepeticion = tipoRepeticion;
        this.intervalo = intervalo;
    }

    @Override
    public String getTitulo() {
        return "E-" + titulo;
    }

    @Override
    public Intervalo obtenerIntervalo(LocalDate fecha) {
        return this.intervalo.intervaloActual(fecha);
    }

    public void asignarAlarma(Alarma alarma) {
        alarmas.add(alarma);
    }

    public int obtenerId() {
        return id;
    }

    public void controlar(LocalDateTime fechaHoraActual){
        Intervalo intervaloSiguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual, intervalo);
        if (!intervaloSiguienteOcurrencia.comienzaAhoraODespues(fechaHoraActual)){ return; }
        for (Alarma alarma: alarmas) {
            alarma.sonarAlarma(fechaHoraActual, intervaloSiguienteOcurrencia);
        }
    }

    @Override
    public boolean soyDelDia(LocalDate dia) {
        Intervalo intervaloSiguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(LocalDateTime.of(dia, LocalTime.MIN), intervalo);
        return intervaloSiguienteOcurrencia.comienzaHoy(dia) ;
    }

    @Override
    public String getIntervalo(LocalDate fecha) {
        return intervalo.toString(fecha);
    }

    @Override
    public void asignarDiaCompleto(LocalDate dia) {
        Intervalo nuevoIntervalo = new Intervalo(LocalDateTime.of(dia, LocalTime.MIN), LocalDateTime.of(dia.plusDays(1), LocalTime.MIN));
        tipoRepeticion.resolverCompatibilidadCon(nuevoIntervalo);
        this.intervalo = nuevoIntervalo;
    }
}
