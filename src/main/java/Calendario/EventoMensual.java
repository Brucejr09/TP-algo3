package Calendario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoMensual extends Evento{
    public EventoMensual(String titulo, String descripcion, int frecuencia, int id) {
        super(titulo, descripcion, frecuencia, id);
        this.frecuencia = 1;
    }

    public EventoMensual (String titulo, String descripcion, int frecuencia, LocalDate duracion, ArrayList<Alarma> alarmas, int id) {
        super(titulo, descripcion, frecuencia, duracion, alarmas, id);
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
