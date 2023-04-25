package Calendario;

import Calendario.Alarma.Alarma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoAnual extends Evento{
    public EventoAnual(String titulo, String descripcion, int frecuencia, int id) {
        super(titulo, descripcion, frecuencia, id);
        this.frecuencia = 1;
    }

    public EventoAnual (String titulo, String descripcion, int frecuencia, LocalDate duracion, ArrayList<Alarma> alarmas, int id) {
        super(titulo, descripcion, frecuencia, duracion, alarmas, id);
    }

    @Override
    public List<Evento> repetir(List<Evento> lista, LocalDate maximo) {
        List<Evento> nuevaLista = new ArrayList<>(lista);
        LocalDateTime siguienteEvento = this.comienza.plusYears(this.frecuencia);

        if ((this.comienza.toLocalDate().isBefore(maximo) || this.comienza.toLocalDate().equals(maximo)) && this.comienza.toLocalDate().isBefore(this.duracion)) {
            EventoAnual eventoRepetido = this;
            eventoRepetido.asignarComienza(siguienteEvento);
            siguienteEvento = this.finaliza.plusYears(this.frecuencia);
            eventoRepetido.asignarFinaliza(siguienteEvento);
            nuevaLista.add(eventoRepetido);
            return eventoRepetido.repetir(nuevaLista, maximo);
        }
        return nuevaLista;
    }

    @Override
    public void ejecutar(LocalDateTime fechaHoraActual) {

    }

    @Override
    public void asignarDuracionHastaRepetir(long repeticiones) {
        LocalDateTime fecha = this.comienza.plusYears(repeticiones - this.frecuencia);
        this.duracion = fecha.toLocalDate();
    }
}
