package Calendario;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventoDiarioTest {
    @Test
    public void estadoNoRealizandose() {
        Evento evento = new EventoDiario("vacaciones", "verano en la costa", 1);
        evento.asignarComienza(LocalDateTime.of(2023, 1, 1, 10, 00));
        evento.asignarFinaliza(LocalDateTime.of(2023, 2, 15, 15, 00));

        assertTrue(evento.estaEnEspera(LocalDateTime.of(2023, 1, 1, 9, 59)));
        assertFalse(evento.estaRealizandose(LocalDateTime.of(2024, 2, 15, 15, 1)));
        assertTrue(evento.estaFinalizado(LocalDateTime.of(2023, 2, 15, 15, 01)));
    }

    @Test
    public void estadoEnPlenoEvento() {
        Evento evento = new EventoDiario("concierto", "bandas de rock", 1);
        evento.asignarDiaCompleto(LocalDate.of(2023, 4, 20));

        assertTrue(evento.estaRealizandose(LocalDateTime.of(2023, 4, 20, 0, 0)));
        assertTrue(evento.estaRealizandose(LocalDateTime.of(2023, 4, 20, 20, 15)));
        assertTrue(evento.estaRealizandose(LocalDateTime.of(2023, 4, 21, 0, 0)));
    }

    @Test
    public void comprueboLaIgualdadDeLaLista() {
        Evento evento = new EventoDiario("vacaciones", "verano en la costa", 5);
        evento.asignarDiaCompleto(LocalDate.of(2022, 1, 1));
        evento.asignarDuracionHastaFecha(LocalDate.of(2022, 2, 5));
        List<Evento> eventos = new ArrayList<>();
        eventos = evento.repetir(eventos, LocalDate.of(2022, 5, 14));

        assertEquals(eventos, evento.repetir(eventos, LocalDate.of(2022, 5, 14)));
    }

    @Test
    public void comprueboLaIgualdadDeLaListaConRepeticiones() {
        Evento evento = new EventoDiario("vacaciones", "verano en la costa", 5);
        evento.asignarDiaCompleto(LocalDate.of(2022, 1, 1));
        evento.asignarDuracionHastaRepetir(8);
        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento);
        eventos = evento.repetir(eventos, LocalDate.of(2022, 12, 14));

        assertEquals(8, eventos.size());
    }

    @Test
    public void comprueboLaIgualdadDeLaListaConDuracionInfinita() {
        Evento evento = new EventoDiario("vacaciones", "verano en la costa", 5);
        evento.asignarDiaCompleto(LocalDate.of(2022, 1, 1));
        evento.asignarDuracionInfinita();
        List<Evento> eventos = new ArrayList<>();
        eventos = evento.repetir(eventos, LocalDate.of(2022, 12, 14));

        assertEquals(eventos, evento.repetir(eventos, LocalDate.of(2022, 12, 14)));
    }
}