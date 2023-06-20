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

    private LocalDate fechaActual;
    private Label fecha;
    private CargadorDeActividades cargador;

    public BotonesPrincipales(LocalDate fechaActual, Label fecha, Calendario nuevoCalendario) {
        this.fechaActual = fechaActual;
        this.fecha = fecha;
        this.cargador = new CargadorDeActividades(nuevoCalendario);
    }

    public LocalDate cambioDePagina (Tab tabDia, Tab tabSemana, VBox dia, ArrayList<VBox> semana, ArrayList<HBox> mes, LocalDate cambioDia, LocalDate cambioSemana, LocalDate cambioMes) {
        if (tabDia.isSelected()) {
            fechaActual = cambioDia;
            fecha.setText(fechaActual.getDayOfMonth() + "-" + fechaActual.getMonth().name() + "-" + fechaActual.getYear());
            cargador.cargarActividades(dia, fechaActual);
        }
        else if (tabSemana.isSelected()) {
            fechaActual = cambioSemana;
            fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
            cargador.cargarSemana(semana.get(0), semana.get(1), semana.get(2), semana.get(3), semana.get(4), semana.get(5), semana.get(6), fechaActual);
        }
        else {
            fechaActual = cambioMes;
            fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
            cargador.cargarMes(fechaActual.minusDays(fechaActual.getDayOfMonth() - 1), mes.get(0), mes.get(1), mes.get(2), mes.get(3), mes.get(4), mes.get(5));
        }

        return fechaActual;
    }
}
