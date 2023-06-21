package SeccionesInterfaz;

import Calendario.Calendario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;

public class BotonesPrincipales {
    private Label fecha;
    private final CargadorDeActividades cargador;

    public BotonesPrincipales(Label fecha) {
        this.fecha = fecha;
        this.cargador = new CargadorDeActividades();
    }

    public LocalDate cambioDePagina (Calendario calendario, Tab tabDia, Tab tabSemana, VBox dia, ArrayList<VBox> semana, ArrayList<HBox> mes, LocalDate cambioDia, LocalDate cambioSemana, LocalDate cambioMes) {
        LocalDate fechaActual;

        if (tabDia.isSelected()) {
            fechaActual = cambioDia;
            fecha.setText(fechaActual.getDayOfMonth() + "-" + fechaActual.getMonth().name() + "-" + fechaActual.getYear());
            cargador.cargarActividades(calendario, dia, fechaActual);
        }
        else if (tabSemana.isSelected()) {
            fechaActual = cambioSemana;
            fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
            cargador.cargarSemana(calendario, semana, fechaActual);
        }
        else {
            fechaActual = cambioMes;
            fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
            cargador.cargarMes(calendario, mes, fechaActual.minusDays(fechaActual.getDayOfMonth() - 1));
        }

        return fechaActual;
    }
}
