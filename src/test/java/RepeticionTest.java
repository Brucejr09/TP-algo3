import Calendario.Errores.FinDeRepeticionIncompatible;
import Calendario.Intervalo;
import Calendario.Repeticion.*;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RepeticionTest {
    @Test
    public void Test01RepeticionDiariaTeDaLaSiguienteOcurrienciaCorrectamente() {
        LocalDateTime fechaHoraActual = LocalDateTime.of(2023,6,9,11,45);
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2023, 6, 14, 22, 0);
        Repeticion tipoRepeticion = new Diaria(finalRepeticion,1);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);

        Intervalo siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2023,6,10,11,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2023,6,14,11,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2023,6,14,22,0);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),-(15 * 4 * 10));
    }

    @Test
    public void Test02RepeticionSemanalTeDaLaSiguienteOcurrienciaCorrectamente() {
        LocalDateTime fechaHoraActual = LocalDateTime.of(2023,3,23,13,45); //JUEVES
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 3, 23, 14, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 3, 23, 16, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2023, 7, 13, 16, 0); //JUEVES
        ArrayList<DayOfWeek> seleccionDiasDeSemana = new ArrayList<>();
        seleccionDiasDeSemana.add(DayOfWeek.MONDAY);
        seleccionDiasDeSemana.add(DayOfWeek.THURSDAY);
        Repeticion tipoRepeticion = new Semanal(finalRepeticion,seleccionDiasDeSemana);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);

        Intervalo siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2023,3,23,13,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2023,3,27,13,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2023,7,13,13,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2023,7,13,16,00);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),-(15 * 4 * 2));
    }

    @Test
    public void Test03RepeticionMensualTeDaLaSiguienteOcurrienciaCorrectamente() {
        LocalDateTime fechaHoraActual = LocalDateTime.of(2023,3,20,23,45);
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 3, 21, 0, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2024, 3, 21, 0, 0);
        Repeticion tipoRepeticion = new Mensual(finalRepeticion,3);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);

        Intervalo siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2023,3,20,23,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2023,6,20,23,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2024,3,20,23,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2024,3,21,2,0);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),-(15 * 4 * 2));
    }

    @Test
    public void Test04RepeticionAnualTeDaLaSiguienteOcurrienciaCorrectamente() {
        LocalDateTime fechaHoraActual = LocalDateTime.of(2024,2,28,23,45);
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2024, 2, 29, 0, 0);
        LocalDateTime finalRepeticion = LocalDateTime.MAX;
        Repeticion tipoRepeticion = new Anual(finalRepeticion,4);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);

        Intervalo siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2028,2,28,23,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2032,2,28,23,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2092,2,28,23,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);

        fechaHoraActual = LocalDateTime.of(2096,2,28,23,45);
        siguienteOcurrencia = tipoRepeticion.darSiguienteOcurrencia(fechaHoraActual,intervalo);

        assertEquals(siguienteOcurrencia.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MINUTES),15);
    }

    @Test
    public void Test05SiFinDeRepeticionEstaAntesQueInicioDeIntervaloDeberiaLanzarError() {
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2023, 6, 9, 11, 45);
        Repeticion tipoRepeticion = new Diaria(finalRepeticion,1);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);

        assertThrows(FinDeRepeticionIncompatible.class, () -> tipoRepeticion.resolverCompatibilidadCon(intervalo));
    }

    @Test
    public void Test06RepeticionUnicaResuelveCompatibilidadDeberiaAsignarLimiteCorrectamente() {
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        Repeticion tipoRepeticion = new Unica();
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);

        assertEquals(0, tipoRepeticion.obtenerLimiteDeOcurrencias());
    }

    @Test
    public void Test07RepeticionDiariaResuelveCompatibilidadDeberiaAsignarLimiteCorrectamente() {
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2023, 6, 30, 22, 0);
        Repeticion tipoRepeticion = new Diaria(finalRepeticion,5);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);

        assertEquals(4, tipoRepeticion.obtenerLimiteDeOcurrencias());
    }

    @Test
    public void Test08RepeticionSemanalResuelveCompatibilidadDeberiaAsignarLimiteCorrectamente() {
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 11, 27, 12, 0); //LUNES
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 11, 27, 22, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2023, 12, 27, 22, 0);
        ArrayList<DayOfWeek> diasSemana = new ArrayList<>();
        diasSemana.add(DayOfWeek.MONDAY);
        diasSemana.add(DayOfWeek.WEDNESDAY);
        diasSemana.add(DayOfWeek.FRIDAY);
        Repeticion tipoRepeticion = new Semanal(finalRepeticion,diasSemana);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);
        long dias = fechaHoraComienzo.until(finalRepeticion, ChronoUnit.DAYS);
        int ocurrenciasEsperadas = (int)dias/7;
        ocurrenciasEsperadas = ocurrenciasEsperadas * diasSemana.size() + 1;

        assertEquals(ocurrenciasEsperadas, tipoRepeticion.obtenerLimiteDeOcurrencias());
    }

    @Test
    public void Test09RepeticionMensualResuelveCompatibilidadDeberiaAsignarLimiteCorrectamente() {
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2024, 6, 30, 22, 0);
        Repeticion tipoRepeticion = new Mensual(finalRepeticion,3);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);

        assertEquals(4, tipoRepeticion.obtenerLimiteDeOcurrencias());
    }

    @Test
    public void Test10RepeticionAnualResuelveCompatibilidadDeberiaAsignarLimiteCorrectamente() {
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        LocalDateTime finalRepeticion = LocalDateTime.of(2089, 6, 9, 22, 0);
        Repeticion tipoRepeticion = new Anual(finalRepeticion,6);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);
        tipoRepeticion.resolverCompatibilidadCon(intervalo);

        assertEquals(11, tipoRepeticion.obtenerLimiteDeOcurrencias());
    }

    @Test
    public void Test11RepeticionAsignaLimiteYResolverCompatibilidadNoDeberiaCambiarElLimiteAsignado() {
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2023, 6, 9, 12, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2023, 6, 9, 22, 0);
        ArrayList<DayOfWeek> diasSemana = new ArrayList<>();
        diasSemana.add(DayOfWeek.MONDAY);
        diasSemana.add(DayOfWeek.WEDNESDAY);
        diasSemana.add(DayOfWeek.FRIDAY);
        Repeticion tipoRepeticion1 = new Diaria(5,7);
        Repeticion tipoRepeticion2 = new Semanal(8,diasSemana);
        Repeticion tipoRepeticion3 = new Mensual(13,9);
        Repeticion tipoRepeticion4 = new Anual(16,4);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);

        tipoRepeticion1.resolverCompatibilidadCon(intervalo);
        tipoRepeticion2.resolverCompatibilidadCon(intervalo);
        tipoRepeticion3.resolverCompatibilidadCon(intervalo);
        tipoRepeticion4.resolverCompatibilidadCon(intervalo);

        assertEquals(5, tipoRepeticion1.obtenerLimiteDeOcurrencias());
        assertEquals(8, tipoRepeticion2.obtenerLimiteDeOcurrencias());
        assertEquals(13, tipoRepeticion3.obtenerLimiteDeOcurrencias());
        assertEquals(16, tipoRepeticion4.obtenerLimiteDeOcurrencias());
    }
}
