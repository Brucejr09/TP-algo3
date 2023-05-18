package CasosDeUso;

import Calendario.Alarma.*;
import Calendario.Actividad.*;
import Calendario.Notificable.CorreoElectronico;
import Calendario.Notificable.Notificable;
import Calendario.Repeticion.*;
import Calendario.*;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EtapaUno {
    private LocalDateTime fechaHoraActual;

    @Test
    public void Test01TareaDeDiaCompletoSeMarcaComoCompletadaCorrectamente(){
        fechaHoraActual = LocalDateTime.of(2000,6,20,23,15);
        Calendario calendario = new Calendario();
        Tarea tareaACompletar = calendario.crearTarea("Laburo","laburo todo el dia",LocalDate.of(2000,6,20));

        this.avanzarQuinceMinutos(calendario); //aca la hora es 23:30

        assertFalse(tareaACompletar.estaCompletada());

        this.avanzarQuinceMinutos(calendario); //aca la hora es 23:45

        assertFalse(tareaACompletar.estaCompletada());

        this.avanzarQuinceMinutos(calendario); //aca son las 00:00 del 21 de junio

        assertTrue(tareaACompletar.estaCompletada());
    }

    @Test
    public void Test02TareaConFechaDeVencimientoSeMarcaComoCompletadaCorrectamente(){
        fechaHoraActual = LocalDateTime.of(2012,12,12,21,0);
        Calendario calendario = new Calendario();
        Tarea tareaACompletar = calendario.crearTarea("Limpieza","limpio mi casa",LocalDateTime.of(2012,12,12,21,21));

        this.avanzarQuinceMinutos(calendario);

        assertFalse(tareaACompletar.estaCompletada());

        this.avanzarQuinceMinutos(calendario);

        assertTrue(tareaACompletar.estaCompletada());

    }

    @Test
    public void Test03LaAlarmaSeActiva15minAntesDelVencimientoDeUnaTareaDeDiaCompleto(){
        fechaHoraActual = LocalDateTime.of(2023,7,16,23,15);
        Calendario calendario = new Calendario();
        Tarea tareaACompletar = calendario.crearTarea("Hacer Comida","voy a hacer un guiso",LocalDate.of(2023,7,16));
        Notificable notificable = new CorreoElectronico("algo3@gmail.com");
        calendario.asignarAlarma(new Relativa(notificable,15),tareaACompletar.obtenerId());

        this.verificarFuncionamientoCon(calendario, notificable, 0);
    }

    @Test
    public void Test04AlarmaSeActiva15minAntesDeUnEventoUnico(){
        fechaHoraActual = LocalDateTime.of(2023,7,15,23,15);
        Calendario calendario = new Calendario();
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023,7,16,0,0);
        Repeticion tipoRepeticion = new Unica();
        Intervalo intervalo = new Intervalo(fechaHoraComienzo);
        Evento eventoACompletar = calendario.crearEvento("Cumpleaños","Cumple de alejandro", tipoRepeticion, intervalo);
        Notificable notificable = new CorreoElectronico("algo3@gmail.com");
        calendario.asignarAlarma(new Relativa(notificable,15), eventoACompletar.obtenerId());

        this.verificarFuncionamientoCon(calendario, notificable, 0);
    }

    @Test
    public void Test05AlarmaSeActiva15minAntesDeUnEventoDiario(){
        fechaHoraActual = LocalDateTime.of(2023,7,9,11,15);
        Calendario calendario = new Calendario();
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 7, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 7, 9, 22, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2023, 7, 14, 22, 0);
        Repeticion tipoRepeticion = new Diaria(finalRepeticion,1);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);
        Evento eventoACompletar = calendario.crearEvento("E3","E3 primer dia", tipoRepeticion, intervalo);
        Notificable notificable = new CorreoElectronico("algo3@gmail.com");
        calendario.asignarAlarma(new Relativa(notificable,15), eventoACompletar.obtenerId());

        this.verificarFuncionamientoCon(calendario, notificable, 0);

        fechaHoraActual = LocalDateTime.of(2023,7,10,11,15);

        this.verificarFuncionamientoCon(calendario, notificable, 1);

        fechaHoraActual = LocalDateTime.of(2023,7,11,11,15);

        this.verificarFuncionamientoCon(calendario, notificable, 2);

        fechaHoraActual = LocalDateTime.of(2023,7,12,11,15);

        this.verificarFuncionamientoCon(calendario, notificable, 3);

        fechaHoraActual = LocalDateTime.of(2023,7,13,11,15);

        this.verificarFuncionamientoCon(calendario, notificable, 4);

        fechaHoraActual = LocalDateTime.of(2023,7,14,11,15);

        this.verificarFuncionamientoCon(calendario, notificable, 5);
    }

    @Test
    public void Test06AlarmaSeActiva15minAntesDeUnEventoSemanal(){
        fechaHoraActual = LocalDateTime.of(2023,3,23,13,15); //JUEVES
        Calendario calendario = new Calendario();
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 3, 23, 14, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 3, 23, 16, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2023, 7, 13, 16, 0); //JUEVES
        ArrayList<DayOfWeek> seleccionDiasDeSemana = new ArrayList<>();
        seleccionDiasDeSemana.add(DayOfWeek.MONDAY);
        seleccionDiasDeSemana.add(DayOfWeek.THURSDAY);
        Repeticion tipoRepeticion = new Semanal(finalRepeticion,seleccionDiasDeSemana);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);
        Evento eventoACompletar = calendario.crearEvento("Clases","clases de algoritmos", tipoRepeticion, intervalo);
        Notificable notificable = new CorreoElectronico("algo3@gmail.com");
        calendario.asignarAlarma(new Relativa(notificable,15), eventoACompletar.obtenerId());

        this.verificarFuncionamientoCon(calendario, notificable, 0);

        fechaHoraActual = LocalDateTime.of(2023,3,23,13,15);

        this.verificarFuncionamientoCon(calendario, notificable, 1);

        fechaHoraActual = LocalDateTime.of(2023,3,27,13,15);

        this.verificarFuncionamientoCon(calendario, notificable, 2);

        fechaHoraActual = LocalDateTime.of(2023,7,6,13,15);

        this.verificarFuncionamientoCon(calendario, notificable, 3);

        fechaHoraActual = LocalDateTime.of(2023,7,10,13,15);

        this.verificarFuncionamientoCon(calendario, notificable, 4);

        fechaHoraActual = LocalDateTime.of(2023,7,13,13,15);

        this.verificarFuncionamientoCon(calendario, notificable, 5);
    }

    @Test
    public void Test07AlarmaSeActiva15minAntesDeUnEventoMensual(){
        fechaHoraActual = LocalDateTime.of(2023,3,20,23,15);
        Calendario calendario = new Calendario();
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 3, 21, 0, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2024, 3, 21, 0, 0);
        Repeticion tipoRepeticion = new Mensual(finalRepeticion,3);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo);
        Evento eventoACompletar = calendario.crearEvento("Cambio de Estacion","en argentina da igual", tipoRepeticion, intervalo);
        Notificable notificable = new CorreoElectronico("algo3@gmail.com");
        calendario.asignarAlarma(new Relativa(notificable,15), eventoACompletar.obtenerId());

        this.verificarFuncionamientoCon(calendario, notificable, 0);

        fechaHoraActual = LocalDateTime.of(2023,3,20,23,15);

        this.verificarFuncionamientoCon(calendario, notificable, 1);

        fechaHoraActual = LocalDateTime.of(2023,6,20,23,15);

        this.verificarFuncionamientoCon(calendario, notificable, 2);

        fechaHoraActual = LocalDateTime.of(2023,9,20,23,15);

        this.verificarFuncionamientoCon(calendario, notificable, 3);

        fechaHoraActual = LocalDateTime.of(2023,12,20,23,15);

        this.verificarFuncionamientoCon(calendario, notificable, 4);

        fechaHoraActual = LocalDateTime.of(2024,3,20,23,15);

        this.verificarFuncionamientoCon(calendario, notificable, 5);
    }

    @Test
    public void Test08AlarmaSeActiva15minAntesDeUnEventoAnual(){
        fechaHoraActual = LocalDateTime.of(2024,2,28,23,15);
        Calendario calendario = new Calendario();
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2024, 2, 29, 0, 0);
        LocalDateTime finalRepeticion = LocalDateTime.MAX;
        Repeticion tipoRepeticion = new Anual(finalRepeticion,4);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo);
        Evento eventoACompletar = calendario.crearEvento("Cambio de Estacion","en argentina da igual", tipoRepeticion, intervalo);
        Notificable notificable = new CorreoElectronico("algo3@gmail.com");
        calendario.asignarAlarma(new Relativa(notificable, 15), eventoACompletar.obtenerId());

        this.verificarFuncionamientoCon(calendario, notificable, 0);

        fechaHoraActual = LocalDateTime.of(2028,2,28,23,15);

        this.verificarFuncionamientoCon(calendario, notificable, 1);

        fechaHoraActual = LocalDateTime.of(2032,2,28,23,15);

        this.verificarFuncionamientoCon(calendario, notificable, 2);

        fechaHoraActual = LocalDateTime.of(2052,2,28,23,15);

        this.verificarFuncionamientoCon(calendario, notificable, 3);

        fechaHoraActual = LocalDateTime.of(2092,2,28,23,15);

        this.verificarFuncionamientoCon(calendario, notificable, 4);

        fechaHoraActual = LocalDateTime.of(2096,2,28,23,15);

        this.verificarFuncionamientoCon(calendario, notificable, 5);
    }

    public void avanzarQuinceMinutos(Calendario calendario){
        this.fechaHoraActual = fechaHoraActual.plusMinutes(15);
        calendario.controlarActividades(this.fechaHoraActual);
    }

    public void verificarFuncionamientoCon(Calendario calendario, Notificable notificable, int cantidadDeNotificacionesActual){
        this.avanzarQuinceMinutos(calendario);

        assertEquals(cantidadDeNotificacionesActual, notificable.obtenerCantidadNotificables());

        this.avanzarQuinceMinutos(calendario);

        assertEquals(cantidadDeNotificacionesActual + 1, notificable.obtenerCantidadNotificables());

        this.avanzarQuinceMinutos(calendario);

        assertEquals(cantidadDeNotificacionesActual + 1, notificable.obtenerCantidadNotificables());
    }

}
