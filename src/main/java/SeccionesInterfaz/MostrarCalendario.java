package SeccionesInterfaz;

import Calendario.Calendario;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;

public class MostrarCalendario {
    private Label fecha;
    private final CargadorDeActividades cargador;

    public MostrarCalendario(Label fecha) {
        this.fecha = fecha;
        this.cargador = new CargadorDeActividades();
    }

    public void mostrarPorDia (Calendario calendario, VBox dia, LocalDate fechaActual) {
        fecha.setText(fechaActual.getDayOfMonth() + "-" + fechaActual.getMonth().name() + "-" + fechaActual.getYear());
        cargador.cargarActividades(calendario, dia, fechaActual);
    }

    public void mostrarPorSemana (Calendario calendario, ArrayList<VBox> semana, LocalDate fechaActual) {
        fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
        cargador.cargarSemana(calendario, semana, fechaActual);
    }

    public void mostrarPorMes (Calendario calendario, ArrayList<HBox> mes, LocalDate fechaActual) {
        fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
        cargador.cargarMes(calendario, mes, fechaActual.minusDays(fechaActual.getDayOfMonth() - 1));
    }

    public void mostrarPorSeleccion (Calendario calendario, Tab tabDia, Tab tabSemana, VBox dia, ArrayList<VBox> semana, ArrayList<HBox> mes, LocalDate fecha) {
        if (tabDia.isSelected()) {
            cargador.cargarActividades(calendario, dia, fecha);
        }
        else if (tabSemana.isSelected()) {
            cargador.cargarSemana(calendario, semana, fecha);
        }
        else {
            cargador.cargarMes(calendario, mes, fecha.minusDays(fecha.getDayOfMonth() - 1));
        }
    }
}
