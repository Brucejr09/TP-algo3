import Calendario.Actividad.Tarea;
import Calendario.Alarma.Absoluta;
import Calendario.Alarma.Alarma;
import Calendario.Alarma.Relativa;
import Calendario.Intervalo;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AlarmaTest {

    @Test
    public void test01AlarmaRelativaSuenaEnElMomentoCorrecto(){
        Intervalo intervalo = new Intervalo(LocalDateTime.of(2020,3,24,0,0));
        Alarma alarma = new Relativa(75);

        alarma.sonarAlarma(LocalDateTime.of(2020,3,23,22,45),intervalo);

        assertTrue(alarma.estaActivada());

        alarma.sonarAlarma(LocalDateTime.of(2020,3,23,22,15),intervalo);

        assertFalse(alarma.estaActivada());

        alarma.sonarAlarma(LocalDateTime.of(2020,3,26,22,45),intervalo);

        assertFalse(alarma.estaActivada());
    }

    @Test
    public void test02AlarmaAbsolutaSuenaEnElMomentoCorrecto(){
        Intervalo intervalo = new Intervalo(LocalDateTime.of(2021,9,24,0,0));
        Alarma alarma = new Absoluta(LocalDateTime.of(2021,7,13,19,15));

        alarma.sonarAlarma(LocalDateTime.of(2021,7,13,19,15),intervalo);

        assertTrue(alarma.estaActivada());

        alarma.sonarAlarma(LocalDateTime.of(2021,3,23,22,15),intervalo);

        assertFalse(alarma.estaActivada());

        alarma.sonarAlarma(LocalDateTime.of(2021,8,26,22,45),intervalo);

        assertFalse(alarma.estaActivada());
    }
}
