package Calendario.calendario;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Alarma {
    ArrayList<LocalDateTime> horarios;

    public Alarma() {
        this.horarios = new ArrayList<>();
    }
    public void alarmaAbsoluta (LocalDateTime comienzo) {
        this.horarios.add(comienzo.minusDays(1));
    }

    public void alarmaPersonalizada (LocalDateTime comienzo, long minutos) {
        this.horarios.add(comienzo.minusMinutes(minutos));
    }

    public void modificarAlarma (LocalDateTime alarmaAModificar, LocalDateTime nuevaAlarma) {
        int i = 0;
        boolean modificado = false;

        while (i < this.horarios.size() && !modificado){
            if (this.horarios.get(i).equals(alarmaAModificar)) {
                this.horarios.set(i, nuevaAlarma);
                modificado = true;
            }
            else
                i++;
        }
    }
}
