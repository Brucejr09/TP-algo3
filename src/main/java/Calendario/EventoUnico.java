package Calendario;

import Calendario.Alarma.Alarma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoUnico extends Evento{

    public EventoUnico(String titulo, String descripcion, int id) {
        super(titulo, descripcion, 0, id);
        this.alarmas = new ArrayList<>();
    }


    @Override
    public List<Evento> repetir(List<Evento> lista, LocalDate maximo) {
        lista.add(this);
        return lista;
    }

    @Override
    public void ejecutar(LocalDateTime fechaHoraActual) {
        for (Alarma alarma:alarmas) { alarma.sonarAlarma(fechaHoraActual, comienza); }
    }

    @Override
    public void asignarDuracionInfinita() {
        this.duracion = LocalDate.MIN;
    }

    @Override
    public void asignarDuracionHastaFecha(LocalDate fechaLimite) {
        this.duracion = LocalDate.MIN;
    }

    @Override
    public void asignarDuracionHastaRepetir(long repeticiones) {
        this.duracion = LocalDate.MIN;
    }
}
