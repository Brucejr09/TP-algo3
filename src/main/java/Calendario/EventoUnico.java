package Calendario;

import java.time.LocalDate;
import java.util.List;

public class EventoUnico extends Evento{

    public EventoUnico(String titulo, String descripcion, int frecuencia) {
        super(titulo, descripcion, frecuencia);
        this.frecuencia = 0;
    }

    @Override
    public List<Evento> repetir(List<Evento> lista, LocalDate maximo) {
        return lista;
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