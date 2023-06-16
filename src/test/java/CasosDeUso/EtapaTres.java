package CasosDeUso;

import Calendario.Calendario;
import Calendario.Intervalo;
import Calendario.Repeticion.Diaria;
import Calendario.Repeticion.Repeticion;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class EtapaTres {

    @Test
    public void Test01PedirActividadesDelDiaFuncionaCorrectamente() {
        Calendario calendario = new Calendario();
        LocalDate fechaActual = LocalDate.of(2023,6,9);
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2023, 6, 19, 22, 0);
        Repeticion tipoRepeticion = new Diaria(finalRepeticion,5);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);

        calendario.crearEvento("prueba","prueba", tipoRepeticion, intervalo);

        fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        finalRepeticion = LocalDateTime.of(2023, 6, 17, 22, 0);
        tipoRepeticion = new Diaria(finalRepeticion,2);
        intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);

        calendario.crearEvento("prueba","prueba", tipoRepeticion, intervalo);

        fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 0, 1);
        fechaHoraFin = LocalDateTime.of(2023, 6, 9, 2, 0);
        finalRepeticion = LocalDateTime.MAX;
        tipoRepeticion = new Diaria(finalRepeticion,1);
        intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);

        calendario.crearEvento("prueba","prueba", tipoRepeticion, intervalo);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 3);

        fechaActual = LocalDate.of(2023,6,11);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 2);

        fechaActual = LocalDate.of(2023,6,13);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 2);

        fechaActual = LocalDate.of(2023,6,14);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 2);

        fechaActual = LocalDate.of(2023,6,15);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 2);

        fechaActual = LocalDate.of(2023,6,17);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 2);

        fechaActual = LocalDate.of(2023,6,19);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 2);

        fechaActual = LocalDate.of(2023,6,20);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 1);

    }
}
