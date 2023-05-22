package CasosDeUso;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.Evento;
import Calendario.Alarma.Relativa;
import Calendario.Calendario;
import Calendario.Intervalo;
import Calendario.Notificable.CorreoElectronico;
import Calendario.Notificable.Notificable;
import Calendario.Repeticion.*;
import org.junit.Test;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EtapaDos {
    private LocalDateTime fechaHoraActual;
    private final String nombreArchivo = "archivo.dat";

    @Test
    public void TestComprueboQueLaSerializacionSeRealizaCorrectamente() throws IOException, ClassNotFoundException {
        fechaHoraActual = LocalDateTime.of(2000, 6, 20, 23, 15);
        Calendario calendario = new Calendario(nombreArchivo);
        calendario.crearTarea("Laburo","laburo todo el dia", LocalDate.of(2000,6,20));

        calendario.crearTarea("Limpieza","limpio mi casa",LocalDateTime.of(2012,12,12,21,21));

        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023,7,16,0,0);
        Repeticion tipoRepeticion = new Unica();
        Intervalo intervalo = new Intervalo(fechaHoraComienzo);
        Evento eventoACompletar = calendario.crearEvento("Cumpleaños","Cumple de alejandro", tipoRepeticion, intervalo);
        Notificable notificable = new CorreoElectronico("algo3@gmail.com");
        calendario.asignarAlarma(new Relativa(notificable,15), eventoACompletar.obtenerId());

        fechaHoraComienzo = LocalDateTime.of(2023, 3, 23, 14, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 3, 23, 16, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2023, 7, 13, 16, 0); //JUEVES
        ArrayList<DayOfWeek> seleccionDiasDeSemana = new ArrayList<>();
        seleccionDiasDeSemana.add(DayOfWeek.MONDAY);
        seleccionDiasDeSemana.add(DayOfWeek.THURSDAY);
        tipoRepeticion = new Semanal(finalRepeticion,seleccionDiasDeSemana);
        intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);
        eventoACompletar = calendario.crearEvento("Clases","clases de algoritmos", tipoRepeticion, intervalo);
        notificable = new CorreoElectronico("algo3@gmail.com");
        calendario.asignarAlarma(new Relativa(notificable,15), eventoACompletar.obtenerId());

        fechaHoraComienzo = LocalDateTime.of(2023, 3, 21, 0, 0);
        finalRepeticion = LocalDateTime.of(2024, 3, 21, 0, 0);
        tipoRepeticion = new Mensual(finalRepeticion,3);
        intervalo = new Intervalo(fechaHoraComienzo);
        eventoACompletar = calendario.crearEvento("Cambio de Estacion","en argentina da igual", tipoRepeticion, intervalo);
        notificable = new CorreoElectronico("algo3@gmail.com");
        calendario.asignarAlarma(new Relativa(notificable,15), eventoACompletar.obtenerId());

        fechaHoraComienzo = LocalDateTime.of(2024, 2, 29, 0, 0);
        finalRepeticion = LocalDateTime.MAX;
        tipoRepeticion = new Anual(finalRepeticion,4);
        intervalo = new Intervalo(fechaHoraComienzo);
        eventoACompletar = calendario.crearEvento("Cambio de Estacion","en argentina da igual", tipoRepeticion, intervalo);
        notificable = new CorreoElectronico("algo3@gmail.com");
        calendario.asignarAlarma(new Relativa(notificable, 15), eventoACompletar.obtenerId());

        calendario.serializar(nombreArchivo);

        Calendario calendarioDuplicado = new Calendario(nombreArchivo);

        for (int i = 0; i < calendario.getProxId(); i++) {
            Actividad primeraActividad = calendario.buscarActividad(i);
            Actividad primeraActividadDuplicada = calendarioDuplicado.buscarActividad(i);

            assertEquals(primeraActividad.hashCode(), primeraActividadDuplicada.hashCode());
        }
    }
}
