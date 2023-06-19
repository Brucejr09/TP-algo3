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
    private Calendario nuevoCalendario;

    private static final String DOMINGO = "Domingo";
    private static final String LUNES = "Lunes";
    private static final String MARTES = "Martes";
    private static final String MIERCOLES = "Miercoles";
    private static final String JUEVES = "Jueves";
    private static final String VIERNES = "Viernes";
    private static final String SABADO = "Sabado";

    public CargadorDeActividades(Calendario calendario) {
        this.nuevoCalendario = calendario;
    }

    public void cargarActividades (VBox columna, LocalDate fecha) {
        columna.getChildren().clear();

        ArrayList<Actividad> actividadesCalendario = this.nuevoCalendario.actividadesDelDia(fecha);

        for (Actividad actividad : actividadesCalendario) {
            VBox nuevo = new VBox();
            Label titulo = new Label(actividad.getTitulo());
            Label descripcion = new Label(actividad.getDescripcion());
            nuevo.getChildren().add(titulo);
            nuevo.getChildren().add(descripcion);

            columna.getChildren().add(nuevo);
        }
    }

    public void cargarSemana (VBox semDomingo, VBox semLunes, VBox semMartes, VBox semMiercoles, VBox semJueves, VBox semViernes, VBox semSabado, LocalDate fechaPibot) {
        switch (fechaPibot.getDayOfWeek()) {
            case SUNDAY -> {
                cargarActividades(semDomingo, fechaPibot, DOMINGO);
                cargarActividades(semLunes, fechaPibot.plusDays(1), LUNES);
                cargarActividades(semMartes, fechaPibot.plusDays(2), MARTES);
                cargarActividades(semMiercoles, fechaPibot.plusDays(3), MIERCOLES);
                cargarActividades(semJueves, fechaPibot.plusDays(4), JUEVES);
                cargarActividades(semViernes, fechaPibot.plusDays(5), VIERNES);
                cargarActividades(semSabado, fechaPibot.plusDays(6), SABADO);
            }
            case MONDAY -> {
                cargarActividades(semDomingo, fechaPibot.minusDays(1), DOMINGO);
                cargarActividades(semLunes, fechaPibot, LUNES);
                cargarActividades(semMartes, fechaPibot.plusDays(1), MARTES);
                cargarActividades(semMiercoles, fechaPibot.plusDays(2), MIERCOLES);
                cargarActividades(semJueves, fechaPibot.plusDays(3), JUEVES);
                cargarActividades(semViernes, fechaPibot.plusDays(4), VIERNES);
                cargarActividades(semSabado, fechaPibot.plusDays(5), SABADO);
            }
            case TUESDAY -> {
                cargarActividades(semDomingo, fechaPibot.minusDays(2), DOMINGO);
                cargarActividades(semLunes, fechaPibot.minusDays(1), LUNES);
                cargarActividades(semMartes, fechaPibot, MARTES);
                cargarActividades(semMiercoles, fechaPibot.plusDays(1), MIERCOLES);
                cargarActividades(semJueves, fechaPibot.plusDays(2), JUEVES);
                cargarActividades(semViernes, fechaPibot.plusDays(3), VIERNES);
                cargarActividades(semSabado, fechaPibot.plusDays(4), SABADO);
            }
            case WEDNESDAY -> {
                cargarActividades(semDomingo, fechaPibot.minusDays(3), DOMINGO);
                cargarActividades(semLunes, fechaPibot.minusDays(2), LUNES);
                cargarActividades(semMartes, fechaPibot.minusDays(1), MARTES);
                cargarActividades(semMiercoles, fechaPibot, MIERCOLES);
                cargarActividades(semJueves, fechaPibot.plusDays(1), JUEVES);
                cargarActividades(semViernes, fechaPibot.plusDays(2), VIERNES);
                cargarActividades(semSabado, fechaPibot.plusDays(3), SABADO);
            }
            case THURSDAY -> {
                cargarActividades(semDomingo, fechaPibot.minusDays(4), DOMINGO);
                cargarActividades(semLunes, fechaPibot.minusDays(3), LUNES);
                cargarActividades(semMartes, fechaPibot.minusDays(2), MARTES);
                cargarActividades(semMiercoles, fechaPibot.minusDays(1), MIERCOLES);
                cargarActividades(semJueves, fechaPibot, JUEVES);
                cargarActividades(semViernes, fechaPibot.plusDays(1), VIERNES);
                cargarActividades(semSabado, fechaPibot.plusDays(2), SABADO);
            }
            case FRIDAY -> {
                cargarActividades(semDomingo, fechaPibot.minusDays(5), DOMINGO);
                cargarActividades(semLunes, fechaPibot.minusDays(4), LUNES);
                cargarActividades(semMartes, fechaPibot.minusDays(3), MARTES);
                cargarActividades(semMiercoles, fechaPibot.minusDays(2), MIERCOLES);
                cargarActividades(semJueves, fechaPibot.minusDays(1), JUEVES);
                cargarActividades(semViernes, fechaPibot, VIERNES);
                cargarActividades(semSabado, fechaPibot.plusDays(1), SABADO);
            }
            default -> {
                cargarActividades(semDomingo, fechaPibot.minusDays(6), DOMINGO);
                cargarActividades(semLunes, fechaPibot.minusDays(5), LUNES);
                cargarActividades(semMartes, fechaPibot.minusDays(4), MARTES);
                cargarActividades(semMiercoles, fechaPibot.minusDays(3), MIERCOLES);
                cargarActividades(semJueves, fechaPibot.minusDays(2), JUEVES);
                cargarActividades(semViernes, fechaPibot.minusDays(1), VIERNES);
                cargarActividades(semSabado, fechaPibot, SABADO);
            }
        }
    }

    public void cargarMes (LocalDate fechaInicial, HBox semana1, HBox semana2, HBox semana3, HBox semana4, HBox semana5, HBox semana6) {
        LocalDate controlador = fechaInicial.plusWeeks(4);
        cargarSemanaVacia(semana1);
        cargarSemanaVacia(semana2);
        cargarSemanaVacia(semana3);
        cargarSemanaVacia(semana4);
        cargarSemanaVacia(semana5);
        cargarSemanaVacia(semana6);


        cargarSemana((VBox)semana1.getChildren().get(0), (VBox)semana1.getChildren().get(1), (VBox)semana1.getChildren().get(2),
                (VBox)semana1.getChildren().get(3), (VBox)semana1.getChildren().get(4), (VBox)semana1.getChildren().get(5),
                (VBox)semana1.getChildren().get(6), fechaInicial);
        cargarSemana((VBox)semana2.getChildren().get(0), (VBox)semana2.getChildren().get(1), (VBox)semana2.getChildren().get(2),
                (VBox)semana2.getChildren().get(3), (VBox)semana2.getChildren().get(4), (VBox)semana2.getChildren().get(5),
                (VBox)semana2.getChildren().get(6), fechaInicial.plusWeeks(1));
        cargarSemana((VBox)semana3.getChildren().get(0), (VBox)semana3.getChildren().get(1), (VBox)semana3.getChildren().get(2),
                (VBox)semana3.getChildren().get(3), (VBox)semana3.getChildren().get(4), (VBox)semana3.getChildren().get(5),
                (VBox)semana3.getChildren().get(6), fechaInicial.plusWeeks(2));
        cargarSemana((VBox)semana4.getChildren().get(0), (VBox)semana4.getChildren().get(1), (VBox)semana4.getChildren().get(2),
                (VBox)semana4.getChildren().get(3), (VBox)semana4.getChildren().get(4), (VBox)semana4.getChildren().get(5),
                (VBox)semana4.getChildren().get(6), fechaInicial.plusWeeks(3));
        cargarSemana((VBox)semana5.getChildren().get(0), (VBox)semana5.getChildren().get(1), (VBox)semana5.getChildren().get(2),
                (VBox)semana5.getChildren().get(3), (VBox)semana5.getChildren().get(4), (VBox)semana5.getChildren().get(5),
                (VBox)semana5.getChildren().get(6), fechaInicial.plusWeeks(4));

        if (controlador.getMonth().equals(Month.FEBRUARY)) {
            for (int i = 0; i < 7; i++) {
                ((VBox)semana6.getChildren().get(i)).getChildren().clear();
            }
        }
        else {
            if (controlador.getDayOfWeek().equals(DayOfWeek.SATURDAY) || ( (controlador.getDayOfWeek().equals(DayOfWeek.FRIDAY)) && (controlador.plusDays(2).getDayOfMonth() == 31) )) {
                cargarSemana((VBox)semana6.getChildren().get(0), (VBox)semana6.getChildren().get(1), (VBox)semana6.getChildren().get(2),
                        (VBox)semana6.getChildren().get(3), (VBox)semana6.getChildren().get(4), (VBox)semana6.getChildren().get(5),
                        (VBox)semana6.getChildren().get(6), fechaInicial.plusWeeks(5));
            }
            else {
                for (int i = 0; i < 7; i++) {
                    ((VBox)semana6.getChildren().get(i)).getChildren().clear();
                }
            }
        }
    }

    private void cargarActividades (VBox columna, LocalDate fecha, String dia) {
        columna.getChildren().clear();

        Label diaConFecha = new Label(dia + " " + fecha.getDayOfMonth());
        columna.getChildren().add(diaConFecha);

        ArrayList<Actividad> actividadesCalendario = this.nuevoCalendario.actividadesDelDia(fecha);

        for (Actividad actividad : actividadesCalendario) {
            VBox nuevo = new VBox();
            Label titulo = new Label(actividad.getTitulo());
            Label descripcion = new Label(actividad.getDescripcion());
            nuevo.getChildren().add(titulo);
            nuevo.getChildren().add(descripcion);

            columna.getChildren().add(nuevo);
        }
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
