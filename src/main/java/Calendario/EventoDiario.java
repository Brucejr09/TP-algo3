package Calendario;

import Calendario.Alarma.Alarma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoDiario extends Evento{
    public EventoDiario(String titulo, String descripcion, int frecuencia, int id) {
        super(titulo, descripcion, frecuencia, id);
    }

    public EventoDiario (String titulo, String descripcion, int frecuencia, LocalDate duracion, ArrayList<Alarma> alarmas, int id) {
        super(titulo, descripcion, frecuencia, duracion, alarmas, id);
    }
    @Override
    public List<Evento> repetir(List<Evento> lista, LocalDate maximo) {
        List<Evento> nuevaLista = new ArrayList<>(lista);
        LocalDateTime siguienteEvento = this.comienza.plusDays(this.frecuencia);

        if ((siguienteEvento.toLocalDate().isBefore(maximo) || siguienteEvento.toLocalDate().equals(maximo)) && siguienteEvento.toLocalDate().isBefore(this.duracion)) {
            EventoDiario eventoRepetido = new EventoDiario(this.titulo, this.descripcion, this.frecuencia, this.duracion, this.alarmas, this.id);
            eventoRepetido.asignarComienza(siguienteEvento);
            siguienteEvento = this.finaliza.plusDays(this.frecuencia);
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
        LocalDateTime fecha = this.comienza.plusDays(this.frecuencia * repeticiones);
        this.duracion = fecha.toLocalDate();
    }
}
