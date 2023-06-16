import Calendario.Intervalo;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class IntervaloTest {
    @Test
    public void Test01MetodoComienzaDespuesFuncionaCorrectamente() {
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2024, 4, 28, 2, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2024, 4, 28, 6, 0);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);

        assertTrue( intervalo.comienzaAhoraODespues(LocalDateTime.of(2024,4,28,1,45)));
        assertTrue( intervalo.comienzaAhoraODespues(LocalDateTime.of(2024,4,28,2,0)));
        assertFalse( intervalo.comienzaAhoraODespues(LocalDateTime.of(2024,4,28,2,15)));
    }

    @Test
    public void Test02MetodoCuantoFaltaParaComenzarFuncionaCorrectamente() {
        LocalDateTime fechaHoraActual = LocalDateTime.of(2023,2,28,5,0);
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2024, 4, 28, 2, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2024, 4, 28, 6, 0);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);

        assertEquals(intervalo.cuantoFaltaParaComenzar(fechaHoraActual, ChronoUnit.MONTHS),fechaHoraActual.until(fechaHoraComienzo,ChronoUnit.MONTHS));
    }

    @Test
    public void Test03MetodoHastaFuncionaCorrectamente() {
        LocalDateTime fechaHoraActual = LocalDateTime.of(2025,2,28,5,0);
        LocalDateTime fechaHoraComienzo = LocalDateTime.of(2024, 4, 28, 2, 0);
        LocalDateTime fechaHoraFin = LocalDateTime.of(2024, 4, 28, 6, 0);
        Intervalo intervalo = new Intervalo(fechaHoraComienzo, fechaHoraFin);

        assertEquals(intervalo.hasta(fechaHoraActual, ChronoUnit.DAYS),fechaHoraComienzo.until(fechaHoraActual,ChronoUnit.DAYS));
    }
}
