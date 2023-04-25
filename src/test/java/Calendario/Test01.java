package Calendario;

import Calendario.Alarma.Alarma;
import Calendario.Alarma.Relativa;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class Test01 {
    @Test
    public void Test01CrearCalendarioConLocalDateTimeImprimeLaFechaCorrecta() {
        Calendario calendario = new Calendario(LocalDateTime.of(1999,7,16,14,30));

        assertEquals("1999-07-16", calendario.fechaActual());
    }

    @Test
    public void Test02Avanzar15minutosSeReflejaCorrectamenteEnElCalendario(){
        Calendario calendario = new Calendario(LocalDateTime.of(1999,7,16,14,30));

        assertEquals("14:30",calendario.horaActual());

        calendario.avanzarTiempo();

        assertEquals("14:45", calendario.horaActual());
    }

    @Test
    public void Test03Avanzar15minutosAUnCuartoParaElOtroDiaTeDevuelveLasCeroHoras(){
        Calendario calendario = new Calendario(LocalDateTime.of(2000,6,19,23,45));

        assertEquals("23:45",calendario.horaActual());

        calendario.avanzarTiempo();

        assertEquals("00:00", calendario.horaActual());
    }

    @Test
    public void Test04TareaDeDiaCompletoSeMarcaComoCompletadaCorrectamente(){
        Calendario calendario = new Calendario(LocalDateTime.of(2000,6,19,23,45));
        Tarea tareaACompletar = calendario.crearTarea("Laburo","laburo todo el dia",LocalDate.of(2000,6,20));

        assertFalse(tareaACompletar.estaCompletada());

        calendario.setFechaHoraLocal(LocalDateTime.of(2000,6,20,23,30));
        calendario.avanzarTiempo(); //aca la hora es 23:45

        assertFalse(tareaACompletar.estaCompletada());

        calendario.avanzarTiempo(); //aca son las 00:00 del 21 de junio

        assertTrue(tareaACompletar.estaCompletada());
    }

    @Test
    public void Test05TareaConFechaDeVencimientoSeMarcaComoCompletadaCorrectamente(){
        Calendario calendario = new Calendario(LocalDateTime.of(2012,12,12,21,15));
        Tarea tareaACompletar = calendario.crearTarea("Limpieza","limpio mi casa",LocalDateTime.of(2012,12,12,21,21));

        assertFalse(tareaACompletar.estaCompletada());

        calendario.avanzarTiempo();

        assertTrue(tareaACompletar.estaCompletada());
    }

    @Test
    public void Test06LaAlarmaSeActiva15minAntesDelComienzoDeUnaTarea(){
        Calendario calendario = new Calendario(LocalDateTime.of(2023,7,15,23,30));
        Tarea tareaACompletar = calendario.crearTarea("Hacer Comida","voy a hacer un guiso",LocalDate.of(2023,7,16));
        Alarma alarmaASonar = calendario.asignarAlarma(new Relativa(15),tareaACompletar.obtenerId());

        assertFalse(alarmaASonar.estaActivada());

        calendario.avanzarTiempo();

        assertTrue(alarmaASonar.estaActivada());
    }

    @Test
    public void Test07AlarmaSeActiva15minAntesDeUnEventoUnicoDeDiaCompleto(){
        Calendario calendario = new Calendario(LocalDateTime.of(2023,7,15,23,30));
        Evento eventoACompletar = calendario.crearEvento("Cumpleaños","Cumple de alejandro", LocalDate.of(2023, 7, 16));
        Alarma alarmaASonar = calendario.asignarAlarma(new Relativa(15), eventoACompletar.obtenerId());

        assertFalse(alarmaASonar.estaActivada());

        calendario.avanzarTiempo();

        assertTrue(alarmaASonar.estaActivada());
    }

/*
    public void Test07AlarmaSeActiva15minAntesDeUnEventoSemanalDeDiaCompleto(){
        Calendario calendario = new Calendario(LocalDateTime.of(2023,7,15,23,30));
        Evento eventoACompletar = calendario.crearEvento("Laburo","laburo fulltime", LocalDate.of(2023, 7, 16));
        Alarma alarmaASonar = calendario.asignarAlarma(new Relativa(15), eventoACompletar.obtenerId());

        assertFalse(alarmaASonar.estaActivada());

        calendario.avanzarTiempo();

        assertTrue(alarmaASonar.estaActivada());
    }
*/
}
