import Calendario.Alarma.Absoluta;
import Calendario.Alarma.Alarma;
import Calendario.Alarma.Relativa;
import Calendario.Intervalo;
import Calendario.Notificable.CorreoElectronico;
import Calendario.Notificable.Notificable;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AlarmaTest {

    @Test
    public void test01AlarmaRelativaNotificaEnElMomentoCorrecto(){
        Intervalo intervalo = new Intervalo(LocalDateTime.of(2020,3,24,0,0));
        Notificable notificable = new CorreoElectronico("algo3@gmail.com");
        Alarma alarma = new Relativa(notificable, 75);

        alarma.sonarAlarma(LocalDateTime.of(2020,3,23,22,15),intervalo);

        assertEquals(0, notificable.obtenerCantidadNotificables());

        alarma.sonarAlarma(LocalDateTime.of(2020,3,26,22,45),intervalo);

        assertEquals(0, notificable.obtenerCantidadNotificables());

        alarma.sonarAlarma(LocalDateTime.of(2020,3,23,22,45),intervalo);

        assertEquals(1, notificable.obtenerCantidadNotificables());
    }

    @Test
    public void test02AlarmaAbsolutaNotificaEnElMomentoCorrecto(){
        Intervalo intervalo = new Intervalo(LocalDateTime.of(2021,9,24,0,0));
        Notificable notificable = new CorreoElectronico("algo3@gmail.com");
        Alarma alarma = new Absoluta(notificable, LocalDateTime.of(2021,7,13,19,15));

        alarma.sonarAlarma(LocalDateTime.of(2021,3,23,22,15),intervalo);

        assertEquals(0, notificable.obtenerCantidadNotificables());

        alarma.sonarAlarma(LocalDateTime.of(2021,8,26,22,45),intervalo);

        assertEquals(0, notificable.obtenerCantidadNotificables());

        alarma.sonarAlarma(LocalDateTime.of(2021,7,13,19,15),intervalo);

        assertEquals(1, notificable.obtenerCantidadNotificables());
    }
}
