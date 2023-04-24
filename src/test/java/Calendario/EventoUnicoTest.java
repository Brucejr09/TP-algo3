package Calendario;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventoUnicoTest {
    @Test
    public void estadoPrevioAlEvento () {
        Evento evento = new EventoUnico("Acto escolar", "acto del 25 de mayo", 1);
        evento.asignarComienza(LocalDateTime.of(2023, 5, 25, 10, 00));
        evento.asignarFinaliza(LocalDateTime.of(2023, 5, 25, 12, 00));
        evento.asignarDuracionInfinita();

        assertTrue(evento.estaEnEspera(LocalDateTime.of(2023, 5, 25, 9, 00)));
        assertFalse(evento.estaRealizandose(LocalDateTime.of(2023, 5, 25, 9, 00)));
        assertFalse(evento.estaFinalizado(LocalDateTime.of(2023, 5, 25, 9, 00)));
    }

    @Test
    public void estadoDuranteElEvento() {
        Evento evento = new EventoUnico("Anio nuevo", "Anio nuevo 2022", 1);
        evento.asignarDiaCompleto(LocalDate.of(2022, 1, 1));
        evento.asignarDuracionHastaFecha(LocalDate.now());

        assertFalse(evento.estaEnEspera(LocalDateTime.of(2022, 12, 31, 23, 30)));
        assertTrue(evento.estaRealizandose(LocalDateTime.of(2022, 1, 1, 00, 00)));
        assertFalse(evento.estaFinalizado(LocalDateTime.of(2022, 1, 1, 9, 00)));
    }

    @Test
    public void estadoDespuesDelEvento() {
        Evento evento = new EventoUnico("vacaciones", "verano en la costa", 1);
        evento.asignarComienza(LocalDateTime.of(2023, 1, 1, 10, 00));
        evento.asignarFinaliza(LocalDateTime.of(2023, 2, 15, 15, 00));
        evento.asignarDuracionHastaRepetir(50);

        assertFalse(evento.estaEnEspera(LocalDateTime.of(2023, 1, 1, 15, 59)));
        assertFalse(evento.estaRealizandose(LocalDateTime.of(2023, 2, 15, 15, 1)));
        assertTrue(evento.estaFinalizado(LocalDateTime.of(2023, 5, 1, 9, 00)));
    }

    @Test
    public void comprueboLaIgualdadDeLaLista() {
        Evento evento = new EventoUnico("vacaciones", "verano en la costa", 1);
        evento.asignarDiaCompleto(LocalDate.of(2022, 1, 1));
        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento);

        assertEquals(eventos, evento.repetir(eventos, LocalDate.of(2022, 5, 14)));
    }
}