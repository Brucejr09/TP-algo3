import Calendario.Actividad.Evento;
import Calendario.Actividad.Tarea;
import Calendario.Alarma.Absoluta;
import Calendario.Alarma.Alarma;
import Calendario.Alarma.Relativa;
import Calendario.Intervalo;
import Calendario.Notificable.CorreoElectronico;
import Calendario.Notificable.Notificable;
import Calendario.Notificable.Notificacion;
import Calendario.Notificable.Sonora;
import Calendario.Repeticion.Anual;
import Calendario.Repeticion.Repeticion;
import Calendario.Repeticion.Unica;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ActividadTest {

    @Test
    public void test01ActividadDevuelveElIdCorrecto(){
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2024, 2, 29, 0, 0);
        Repeticion tipoRepeticion = new Anual(LocalDateTime.MAX,4);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo);
        Evento evento = new Evento(0,"prueba1","prueba1", tipoRepeticion, intervalo);
        Tarea tarea = new Tarea(1,"prueba2","prueba2");

        assertEquals(evento.obtenerId(),0);
        assertEquals(1, tarea.obtenerId());
    }

    @Test
    public void test02TareaDeDiaCompletoControlaAlarmaCorrectamente(){
        Tarea tarea = new Tarea(1,"prueba2","prueba2");
        Notificable notificable = new CorreoElectronico("algo3@gmail.com");
        Alarma alarma = new Relativa(notificable, 30);
        tarea.asignarDiaCompleto(LocalDate.of(2023,6,19));
        tarea.asignarAlarma(alarma);

        tarea.controlar(LocalDateTime.of(2023,6,19,23,45));

        assertEquals(0, notificable.obtenerCantidadNotificables());

        tarea.controlar(LocalDateTime.of(2023,6,19,23,15));

        assertEquals(0, notificable.obtenerCantidadNotificables());

        tarea.controlar(LocalDateTime.of(2023,6,19,23,29));

        assertEquals(1, notificable.obtenerCantidadNotificables());
    }

    @Test
    public void test03TareaConVencimientoControlaAlarmaCorrectamente(){
        Tarea tarea = new Tarea(1,"prueba2","prueba2");
        Notificable notificable = new CorreoElectronico("algo3@gmail.com");
        Alarma alarma = new Absoluta(notificable, LocalDateTime.of(2023,6,14,22,15));
        tarea.asignarVencimiento(LocalDateTime.of(2023,6,15,0,0));
        tarea.asignarAlarma(alarma);

        tarea.controlar(LocalDateTime.of(2023,6,14,22,30));

        assertEquals(0, notificable.obtenerCantidadNotificables());

        tarea.controlar(LocalDateTime.of(2023,6,14,22,0));

        assertEquals(0, notificable.obtenerCantidadNotificables());

        tarea.controlar(LocalDateTime.of(2023,6,14,22,15));

        assertEquals(1, notificable.obtenerCantidadNotificables());
    }

    @Test
    public void test04TareaDiaCompletoSeCompletaCorrectamente(){
        Tarea tarea = new Tarea(1,"prueba2","prueba2");
        tarea.asignarDiaCompleto(LocalDate.of(2023,6,19));

        tarea.controlar(LocalDateTime.of(2023,6,19,22,15));

        assertFalse(tarea.estaCompletada());

        tarea.controlar(LocalDateTime.of(2023,6,19,23,30));

        assertFalse(tarea.estaCompletada());

        tarea.controlar(LocalDateTime.of(2023,6,20,0,0));

        assertTrue(tarea.estaCompletada());

        tarea.controlar(LocalDateTime.of(2023,6,26,22,0));

        assertTrue(tarea.estaCompletada());
    }

    @Test
    public void test05TareaConVencimientoSeCompletaCorrectamente(){
        Tarea tarea = new Tarea(1,"prueba2","prueba2");
        tarea.asignarVencimiento(LocalDateTime.of(2023,9,21,0,0));

        tarea.controlar(LocalDateTime.of(2023,7,1,22,15));

        assertFalse(tarea.estaCompletada());

        tarea.controlar(LocalDateTime.of(2023,9,19,23,30));

        assertFalse(tarea.estaCompletada());

        tarea.controlar(LocalDateTime.of(2023,9,21,0,0));

        assertTrue(tarea.estaCompletada());

        tarea.controlar(LocalDateTime.of(2023,9,26,22,0));

        assertTrue(tarea.estaCompletada());
    }

    @Test
    public void test06EventoDiaCompletoControlaAlarmaCorrectamente(){
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2024, 1, 29, 2, 30);
        Repeticion tipoRepeticion = new Unica();
        Intervalo intervalo = new Intervalo(fechaHoraComienzo);
        Notificable notificable = new Sonora("ding dong");
        Alarma alarma = new Relativa(notificable, 30);
        Evento evento = new Evento(0,"prueba1","prueba1", tipoRepeticion, intervalo);
        evento.asignarAlarma(alarma);

        evento.controlar(LocalDateTime.of(2024,1,29,2,15));

        assertEquals(0, notificable.obtenerCantidadNotificables());

        evento.controlar(LocalDateTime.of(2024,1,29,1,45));

        assertEquals(0, notificable.obtenerCantidadNotificables());

        evento.controlar(LocalDateTime.of(2024,1,29,2,0));

        assertEquals(1, notificable.obtenerCantidadNotificables());
    }

    @Test
    public void test07EventoIntervaloArbitrarioControlaAlarmaCorrectamente(){
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2024, 6, 1, 2, 30);
        LocalDateTime fechaHoraFinaliza = LocalDateTime.of(2024, 7, 15, 16, 30);
        Repeticion tipoRepeticion = new Unica();
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFinaliza);
        Notificable notificable = new Notificacion("tenes que realizar prueba1");
        Alarma alarma = new Absoluta(notificable, LocalDateTime.of(2024,5,26,14,45));
        Evento evento = new Evento(0,"prueba1","prueba1", tipoRepeticion, intervalo);
        evento.asignarAlarma(alarma);

        evento.controlar(LocalDateTime.of(2024,6,1,2,0));

        assertEquals(0, notificable.obtenerCantidadNotificables());

        evento.controlar(LocalDateTime.of(2024,6,1,2,15));

        assertEquals(0, notificable.obtenerCantidadNotificables());

        evento.controlar(LocalDateTime.of(2024,5,26,14,45));

        assertEquals(1, notificable.obtenerCantidadNotificables());
    }

}
