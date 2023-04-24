package Calendario.calendario;

import java.time.LocalDate;

public interface FechaLimite {
    void asignarDuracionInfinita();
    void asignarDuracionHastaFecha(LocalDate fechaLimite);
    void asignarDuracionHastaRepetir(long repeticiones);
}
