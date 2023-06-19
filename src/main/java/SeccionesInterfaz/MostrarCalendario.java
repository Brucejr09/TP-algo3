package SeccionesInterfaz;

import Calendario.Calendario;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class MostrarCalendario {
    private LocalDate fechaActual;
    private Label fecha;
    private CargadorDeActividades cargador;

    public MostrarCalendario(LocalDate fechaActual, Label fecha, Calendario nuevoCalendario) {
        this.fechaActual = fechaActual;
        this.fecha = fecha;
        this.cargador = new CargadorDeActividades(nuevoCalendario);
    }

    public void mostrarPorDia (Tab tabDia, VBox dia) {
        tabDia.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fechaActual = LocalDate.now();
                fecha.setText(fechaActual.getDayOfMonth() + "-" + fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                cargador.cargarActividades(dia, fechaActual);
            }
        });
    }

    public void mostrarPorSemana (Tab tabSemana, VBox semDomingo, VBox semLunes, VBox semMartes, VBox semMiercoles, VBox semJueves, VBox semViernes, VBox semSabado) {
        tabSemana.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fechaActual = LocalDate.now();
                fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                cargador.cargarSemana(semDomingo, semLunes, semMartes, semMiercoles, semJueves, semViernes, semSabado, fechaActual);
            }
        });
    }

    public void mostrarPorMes (Tab tabMes, HBox semana1, HBox semana2, HBox semana3, HBox semana4, HBox semana5, HBox semana6) {
        tabMes.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fechaActual = LocalDate.now();
                fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                cargador.cargarMes(fechaActual.minusDays(fechaActual.getDayOfMonth() - 1), semana1, semana2, semana3, semana4, semana5, semana6);
            }
        });
    }
}
