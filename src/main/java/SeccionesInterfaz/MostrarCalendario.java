package SeccionesInterfaz;

import Calendario.Calendario;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;

public class MostrarCalendario {
    private static final LocalDate FECHAACTUAL = LocalDate.now();
    private Label fecha;
    private final CargadorDeActividades cargador;

    public MostrarCalendario(Label fecha) {
        this.fecha = fecha;
        this.cargador = new CargadorDeActividades();
    }

    public void mostrarPorDia (Calendario calendario, Tab tabDia, VBox dia) {
        tabDia.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fecha.setText(FECHAACTUAL.getDayOfMonth() + "-" + FECHAACTUAL.getMonth().name() + "-" + FECHAACTUAL.getYear());
                cargador.cargarActividades(calendario, dia, FECHAACTUAL);
            }
        });
    }

    public void mostrarPorSemana (Calendario calendario, Tab tabSemana, ArrayList<VBox> semana) {
        tabSemana.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fecha.setText(FECHAACTUAL.getMonth().name() + "-" + FECHAACTUAL.getYear());
                cargador.cargarSemana(calendario, semana, FECHAACTUAL);
            }
        });
    }

    public void mostrarPorMes (Calendario calendario, Tab tabMes, ArrayList<HBox> mes) {
        tabMes.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fecha.setText(FECHAACTUAL.getMonth().name() + "-" + FECHAACTUAL.getYear());
                cargador.cargarMes(calendario, mes, FECHAACTUAL.minusDays(FECHAACTUAL.getDayOfMonth() - 1));
            }
        });
    }
}
