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
        LocalDate fechaActual = LocalDate.of(2023,6,5);
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

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 0);

        fechaActual = LocalDate.of(2023,6,9);

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

    @Test
    public void Test02RepeticionDiariaDeberiaAsignarLimiteCorrectamente() {
        int limiteDeOcurrencias = 3;
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        Repeticion tipoRepeticion = new Diaria(limiteDeOcurrencias,5);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);

        assertEquals(3, tipoRepeticion.obtenerLimiteDeOcurrencias());
    }

    @Test
    public void Test03EventoDiarioConLimiteDeOcurrenciasFuncionaCorrectamente() {
        int limiteDeOcurrencias = 3;
        Calendario calendario = new Calendario();
        LocalDate fechaActual = LocalDate.of(2023,6,8);
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        Repeticion tipoRepeticion = new Diaria(limiteDeOcurrencias,2);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);

        calendario.crearEvento("prueba","prueba", tipoRepeticion, intervalo);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 0);
        assertEquals(3, tipoRepeticion.obtenerLimiteDeOcurrencias());

        fechaActual = LocalDate.of(2023,6,9);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 1);

        fechaActual = LocalDate.of(2023,6,10);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 0);

        fechaActual = LocalDate.of(2023,6,11);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 1);

        fechaActual = LocalDate.of(2023,6,12);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 0);

        fechaActual = LocalDate.of(2023,6,13);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 1);

        fechaActual = LocalDate.of(2023,6,14);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 0);

        fechaActual = LocalDate.of(2023,6,15);

        //El modelo esta diseñado para que el evento se repita limiteDeOcurrencias + 1
        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 1);
    }

    @Test
    public void Test04EventoDiarioInfinitoConFrecuenciaDe10FuncionaCorrectamente() {
        Calendario calendario = new Calendario();
        LocalDate fechaActual = LocalDate.of(2023,6,7);
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        LocalDateTime finalRepeticion = LocalDateTime.MAX;
        Repeticion tipoRepeticion = new Diaria(finalRepeticion,10);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);

        calendario.crearEvento("prueba","prueba", tipoRepeticion, intervalo);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 0);

        fechaActual = LocalDate.of(2023,6,9);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 1);

        fechaActual = LocalDate.of(2023,6,10);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 0);

        fechaActual = LocalDate.of(2023,6,19);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 1);

        fechaActual = LocalDate.of(2023,6,20);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 0);

        fechaActual = LocalDate.of(2023,6,29);

        assertEquals(calendario.actividadesDelDia(fechaActual).size(), 1);
    }

}

