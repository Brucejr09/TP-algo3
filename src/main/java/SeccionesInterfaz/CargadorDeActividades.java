package SeccionesInterfaz;

import Calendario.Actividad.Actividad;
import Calendario.Calendario;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class CargadorDeActividades {
    private static final String DOMINGO = "Domingo";
    private static final String LUNES = "Lunes";
    private static final String MARTES = "Martes";
    private static final String MIERCOLES = "Miercoles";
    private static final String JUEVES = "Jueves";
    private static final String VIERNES = "Viernes";
    private static final String SABADO = "Sabado";

    public void cargarActividades (Calendario calendario, VBox columna, LocalDate fecha) {
        columna.getChildren().clear();

        ArrayList<Actividad> actividadesCalendario = calendario.actividadesDelDia(fecha);

        for (Actividad actividad : actividadesCalendario) {
            VBox nuevo = new VBox();
            Label titulo = new Label(actividad.getTitulo());
            Label descripcion = new Label(actividad.getDescripcion());
            nuevo.getChildren().add(titulo);
            nuevo.getChildren().add(descripcion);

            columna.getChildren().add(nuevo);
        }
    }

    public void cargarSemana (Calendario calendario, ArrayList<VBox> semana, LocalDate fechaPibot) {
        switch (fechaPibot.getDayOfWeek()) {
            case SUNDAY -> {
                cargarActividades(calendario, semana.get(0), fechaPibot, DOMINGO);
                cargarActividades(calendario, semana.get(1), fechaPibot.plusDays(1), LUNES);
                cargarActividades(calendario, semana.get(2), fechaPibot.plusDays(2), MARTES);
                cargarActividades(calendario, semana.get(3), fechaPibot.plusDays(3), MIERCOLES);
                cargarActividades(calendario, semana.get(4), fechaPibot.plusDays(4), JUEVES);
                cargarActividades(calendario, semana.get(5), fechaPibot.plusDays(5), VIERNES);
                cargarActividades(calendario, semana.get(6), fechaPibot.plusDays(6), SABADO);
            }
            case MONDAY -> {
                cargarActividades(calendario, semana.get(0), fechaPibot.minusDays(1), DOMINGO);
                cargarActividades(calendario, semana.get(1), fechaPibot, LUNES);
                cargarActividades(calendario, semana.get(2), fechaPibot.plusDays(1), MARTES);
                cargarActividades(calendario, semana.get(3), fechaPibot.plusDays(2), MIERCOLES);
                cargarActividades(calendario, semana.get(4), fechaPibot.plusDays(3), JUEVES);
                cargarActividades(calendario, semana.get(5), fechaPibot.plusDays(4), VIERNES);
                cargarActividades(calendario, semana.get(6), fechaPibot.plusDays(5), SABADO);
            }
            case TUESDAY -> {
                cargarActividades(calendario, semana.get(0), fechaPibot.minusDays(2), DOMINGO);
                cargarActividades(calendario, semana.get(1), fechaPibot.minusDays(1), LUNES);
                cargarActividades(calendario, semana.get(2), fechaPibot, MARTES);
                cargarActividades(calendario, semana.get(3), fechaPibot.plusDays(1), MIERCOLES);
                cargarActividades(calendario, semana.get(4), fechaPibot.plusDays(2), JUEVES);
                cargarActividades(calendario, semana.get(5), fechaPibot.plusDays(3), VIERNES);
                cargarActividades(calendario, semana.get(6), fechaPibot.plusDays(4), SABADO);
            }
            case WEDNESDAY -> {
                cargarActividades(calendario, semana.get(0), fechaPibot.minusDays(3), DOMINGO);
                cargarActividades(calendario, semana.get(1), fechaPibot.minusDays(2), LUNES);
                cargarActividades(calendario, semana.get(2), fechaPibot.minusDays(1), MARTES);
                cargarActividades(calendario, semana.get(3), fechaPibot, MIERCOLES);
                cargarActividades(calendario, semana.get(4), fechaPibot.plusDays(1), JUEVES);
                cargarActividades(calendario, semana.get(5), fechaPibot.plusDays(2), VIERNES);
                cargarActividades(calendario, semana.get(6), fechaPibot.plusDays(3), SABADO);
            }
            case THURSDAY -> {
                cargarActividades(calendario, semana.get(0), fechaPibot.minusDays(4), DOMINGO);
                cargarActividades(calendario, semana.get(1), fechaPibot.minusDays(3), LUNES);
                cargarActividades(calendario, semana.get(2), fechaPibot.minusDays(2), MARTES);
                cargarActividades(calendario, semana.get(3), fechaPibot.minusDays(1), MIERCOLES);
                cargarActividades(calendario, semana.get(4), fechaPibot, JUEVES);
                cargarActividades(calendario, semana.get(5), fechaPibot.plusDays(1), VIERNES);
                cargarActividades(calendario, semana.get(6), fechaPibot.plusDays(2), SABADO);
            }
            case FRIDAY -> {
                cargarActividades(calendario, semana.get(0), fechaPibot.minusDays(5), DOMINGO);
                cargarActividades(calendario, semana.get(1), fechaPibot.minusDays(4), LUNES);
                cargarActividades(calendario, semana.get(2), fechaPibot.minusDays(3), MARTES);
                cargarActividades(calendario, semana.get(3), fechaPibot.minusDays(2), MIERCOLES);
                cargarActividades(calendario, semana.get(4), fechaPibot.minusDays(1), JUEVES);
                cargarActividades(calendario, semana.get(5), fechaPibot, VIERNES);
                cargarActividades(calendario, semana.get(6), fechaPibot.plusDays(1), SABADO);
            }
            default -> {
                cargarActividades(calendario, semana.get(0), fechaPibot.minusDays(6), DOMINGO);
                cargarActividades(calendario, semana.get(1), fechaPibot.minusDays(5), LUNES);
                cargarActividades(calendario, semana.get(2), fechaPibot.minusDays(4), MARTES);
                cargarActividades(calendario, semana.get(3), fechaPibot.minusDays(3), MIERCOLES);
                cargarActividades(calendario, semana.get(4), fechaPibot.minusDays(2), JUEVES);
                cargarActividades(calendario, semana.get(5), fechaPibot.minusDays(1), VIERNES);
                cargarActividades(calendario, semana.get(6), fechaPibot, SABADO);
            }
        }
    }

    public void cargarMes (Calendario calendario, ArrayList<HBox> mes, LocalDate fechaInicial) {
        LocalDate controlador = fechaInicial.plusWeeks(4);

        for (HBox mess : mes) {
            cargarSemanaVacia(mess);
        }

        for (int i = 0; i < mes.size(); i++) {
            ArrayList<VBox> semana = cambioALista(mes.get(i));
            cargarSemana(calendario, semana, fechaInicial.plusWeeks(i));
        }

        if (controlador.getMonth().equals(Month.FEBRUARY)) {
            for (int i = 0; i < 7; i++) {
                ArrayList<VBox> semana = cambioALista(mes.get(5));
                semana.get(i).getChildren().clear();
            }
        }
        else {
            if (controlador.getDayOfWeek().equals(DayOfWeek.SATURDAY) || ( (controlador.getDayOfWeek().equals(DayOfWeek.FRIDAY)) && (controlador.plusDays(2).getDayOfMonth() == 31) )) {
                ArrayList<VBox> semana = cambioALista(mes.get(5));
                cargarSemana(calendario, semana, fechaInicial.plusWeeks(5));
            }
            else {
                for (int i = 0; i < 7; i++) {
                    ArrayList<VBox> semana = cambioALista(mes.get(5));
                    semana.get(i).getChildren().clear();
                }
            }
        }
    }

    private void cargarActividades (Calendario calendario, VBox columna, LocalDate fecha, String dia) {
        columna.getChildren().clear();

        Label diaConFecha = new Label(dia + " " + fecha.getDayOfMonth());
        columna.getChildren().add(diaConFecha);

        ArrayList<Actividad> actividadesCalendario = calendario.actividadesDelDia(fecha);

        for (Actividad actividad : actividadesCalendario) {
            VBox nuevo = new VBox();
            Label titulo = new Label(actividad.getTitulo());
            Label descripcion = new Label(actividad.getDescripcion());
            nuevo.getChildren().add(titulo);
            nuevo.getChildren().add(descripcion);

            columna.getChildren().add(nuevo);
        }
    }

    private ArrayList<VBox> cambioALista (HBox semana) {
        ArrayList<VBox> lista = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            lista.add((VBox)semana.getChildren().get(i));
        }
        return lista;
    }

    private void cargarSemanaVacia (HBox semana) {
        semana.getChildren().clear();
        for (int i = 0; i < 7; i++) {
            semana.getChildren().add(crearDia());
        }
    }

    private VBox crearDia () {
        VBox dia = new VBox();
        dia.setPrefWidth(178);
        dia.setPrefHeight(200);
        return dia;
    }
}
