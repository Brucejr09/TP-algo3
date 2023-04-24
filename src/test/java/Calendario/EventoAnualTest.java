package Calendario;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventoAnualTest {
    @Test
    public void estadoFinalizado() {
        Evento evento = new EventoAnual("viaje", "visita a los amigos", 1);
        evento.asignarComienza(LocalDateTime.of(2021, 2, 1, 13, 45));
        evento.asignarFinaliza(LocalDateTime.of(2021, 4, 15, 15, 30));

        assertFalse(evento.estaEnEspera(LocalDateTime.of(2023, 1, 1, 9, 59)));
        assertFalse(evento.estaRealizandose(LocalDateTime.of(2021, 2, 1, 13, 1)));
        assertTrue(evento.estaFinalizado(LocalDateTime.of(2021, 4, 15, 15, 45)));
    }

    @Test
    public void estadoEnEspera() {
        Evento evento = new EventoAnual("concierto", "bandas de rock", 1);
        evento.asignarDiaCompleto(LocalDate.of(2023, 4, 20));

        assertTrue(evento.estaEnEspera(LocalDateTime.of(2023, 4, 19, 23, 59)));
        assertFalse(evento.estaRealizandose(LocalDateTime.of(2023, 4, 21, 20, 15)));
        assertFalse(evento.estaFinalizado(LocalDateTime.of(2022, 4, 21, 0, 0)));
    }

    @Test
    public void comprueboLaIgualdadDeLaLista() {
        Evento evento = new EventoAnual("vacaciones", "verano en la costa", 5);
        evento.asignarDiaCompleto(LocalDate.of(2022, 1, 1));
        evento.asignarDuracionHastaFecha(LocalDate.of(2023, 2, 5));
        List<Evento> eventos = new ArrayList<>();
        eventos = evento.repetir(eventos, LocalDate.of(2023, 5, 20));

        assertEquals(eventos, evento.repetir(eventos, LocalDate.of(2023, 5, 20)));
    }

    @Test
    public void comprueboLaIgualdadDeLaListaConRepeticiones() {
        Evento evento = new EventoAnual("vacaciones", "verano en la costa", 5);
        evento.asignarDiaCompleto(LocalDate.of(2022, 1, 1));
        evento.asignarDuracionHastaRepetir(8);
        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento);
        eventos = evento.repetir(eventos, LocalDate.of(2022, 12, 14));

        assertEquals(eventos, evento.repetir(eventos, LocalDate.of(2022, 12, 14)));
    }

    @Test
    public void comprueboLaIgualdadDeLaListaConDuracionInfinita() {
        Evento evento = new EventoAnual("vacaciones", "verano en la costa", 5);
        evento.asignarDiaCompleto(LocalDate.of(2022, 1, 1));
        evento.asignarDuracionInfinita();
        List<Evento> eventos = new ArrayList<>();
        eventos = evento.repetir(eventos, LocalDate.of(2022, 12, 14));
        eventos.add(evento);

        assertEquals(eventos.size(), evento.repetir(eventos, LocalDate.of(2022, 12, 14)).size());
    }
}