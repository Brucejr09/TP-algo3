package Calendario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoMensual extends Evento{
    public EventoMensual(String titulo, String descripcion, int frecuencia) {
        super(titulo, descripcion, frecuencia);
        this.frecuencia = 1;
    }

    @Override
    public List<Evento> repetir(List<Evento> lista, LocalDate maximo) {
        List<Evento> nuevaLista = new ArrayList<>(lista);
        LocalDateTime siguienteEvento = this.comienza.plusMonths(1);

        if ((this.comienza.toLocalDate().isBefore(maximo) || this.comienza.toLocalDate().equals(maximo)) && this.comienza.toLocalDate().isBefore(this.duracion)) {
            EventoMensual eventoRepetido = this;
            eventoRepetido.asignarComienza(siguienteEvento);
            siguienteEvento = this.finaliza.plusMonths(1);
            eventoRepetido.asignarFinaliza(siguienteEvento);
            nuevaLista.add(eventoRepetido);
            return eventoRepetido.repetir(nuevaLista, maximo);
        }
        return nuevaLista;
    }

    @Override
    public void asignarDuracionHastaRepetir(long repeticiones) {
        LocalDateTime fecha = this.comienza.plusMonths(repeticiones - this.frecuencia);
        this.duracion = fecha.toLocalDate();
    }
}
