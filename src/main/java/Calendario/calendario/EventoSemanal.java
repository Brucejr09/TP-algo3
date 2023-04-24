package Calendario.calendario;//import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoSemanal extends Evento{
    public EventoSemanal(String titulo, String descripcion, int frecuencia) {
        super(titulo, descripcion, frecuencia);
    }

    @Override
    public List<Evento> repetir(List<Evento> lista, LocalDate maximo) {
        List<Evento> nuevaLista = new ArrayList<>(lista);
        LocalDateTime siguienteEvento = this.comienza.plusWeeks(this.frecuencia);

        if ((siguienteEvento.toLocalDate().isBefore(maximo) || siguienteEvento.toLocalDate().equals(maximo)) && siguienteEvento.toLocalDate().isBefore(this.duracion)) {
            EventoSemanal eventoRepetido = this;
            eventoRepetido.asignarComienza(siguienteEvento);
            siguienteEvento = this.finaliza.plusWeeks(this.frecuencia);
            eventoRepetido.asignarFinaliza(siguienteEvento);
            nuevaLista.add(eventoRepetido);
            return eventoRepetido.repetir(nuevaLista, maximo);
        }
        return nuevaLista;
    }

    @Override
    public void asignarDuracionHastaRepetir(long repeticiones) {
        LocalDateTime fecha = this.comienza.plusWeeks(repeticiones - this.frecuencia);
        this.duracion = fecha.toLocalDate();
    }

    /*public calendario.EventoSemanal repetir (String dia) {
        calendario.EventoSemanal eventoRepetido = this;
        eventoRepetido.comienzaActividad(fechaCambiada(dia, this.comienza));
        eventoRepetido.finalizaActividad(fechaCambiada(dia, this.finaliza));
        return eventoRepetido;
    }

    private LocalDateTime fechaCambiada (String dia, LocalDateTime fechaActual) {
        LocalDateTime nuevaFecha = fechaActual;
        boolean coincidio = true;

        while (!nuevaFecha.getDayOfWeek().equals(DayOfWeek.valueOf(dia))) {
            nuevaFecha = nuevaFecha.plusDays(1);
            coincidio = false;
        }
        if (coincidio)
            nuevaFecha = fechaActual.plusWeeks(1);

        return nuevaFecha;
    }*/
}
