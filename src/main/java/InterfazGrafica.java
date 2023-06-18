import Calendario.Actividad.Actividad;
import Calendario.Calendario;
import Calendario.Repeticion.Diaria;
import Calendario.Repeticion.Repeticion;
import Calendario.Repeticion.Unica;
import Calendario.Intervalo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InterfazGrafica extends Application implements Initializable {
    private Calendario nuevoCalendario;

    private LocalDate fechaActual;

    private static final SpinnerValueFactory<Integer> FABRICACANTIDADDIAS = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 365, 1);
    private static final SpinnerValueFactory<Integer> FABRICACANTIDADREPETICIONES = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 365, 2);

    private static final SpinnerValueFactory<Integer> FABRICAHORACOMIENZO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
    private static final SpinnerValueFactory<Integer> FABRICAHORAFINALIZA = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
    private static final SpinnerValueFactory<Integer> FABRICAHORAVENCIMIENTO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);

    private static final SpinnerValueFactory<Integer> FABRICAMINUTOCOMIENZO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
    private static final SpinnerValueFactory<Integer> FABRICAMINUTOFINALIZA = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
    private static final SpinnerValueFactory<Integer> FABRICAMINUTOVENCIMIENTO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);

    private static final String DOMINGO = "Domingo";
    private static final String LUNES = "Lunes";
    private static final String MARTES = "Martes";
    private static final String MIERCOLES = "Miercoles";
    private static final String JUEVES = "Jueves";
    private static final String VIERNES = "Viernes";
    private static final String SABADO = "Sabado";

    @FXML
    private Button anterior;

    @FXML
    private Button crear;

    @FXML
    private VBox dia;

    @FXML
    private Label fecha;

    @FXML
    private Label hora;

    @FXML
    private VBox mesDomingo1;

    @FXML
    private VBox mesDomingo2;

    @FXML
    private VBox mesDomingo3;

    @FXML
    private VBox mesDomingo4;

    @FXML
    private VBox mesDomingo5;

    @FXML
    private VBox mesDomingo6;

    @FXML
    private VBox mesJueves1;

    @FXML
    private VBox mesJueves2;

    @FXML
    private VBox mesJueves3;

    @FXML
    private VBox mesJueves4;

    @FXML
    private VBox mesJueves5;

    @FXML
    private VBox mesJueves6;

    @FXML
    private VBox mesLunes1;

    @FXML
    private VBox mesLunes2;

    @FXML
    private VBox mesLunes3;

    @FXML
    private VBox mesLunes4;

    @FXML
    private VBox mesLunes5;

    @FXML
    private VBox mesLunes6;

    @FXML
    private VBox mesMartes1;

    @FXML
    private VBox mesMartes2;

    @FXML
    private VBox mesMartes3;

    @FXML
    private VBox mesMartes4;

    @FXML
    private VBox mesMartes5;

    @FXML
    private VBox mesMartes6;

    @FXML
    private VBox mesMiercoles1;

    @FXML
    private VBox mesMiercoles2;

    @FXML
    private VBox mesMiercoles3;

    @FXML
    private VBox mesMiercoles4;

    @FXML
    private VBox mesMiercoles5;

    @FXML
    private VBox mesMiercoles6;

    @FXML
    private VBox mesSabado1;

    @FXML
    private VBox mesSabado2;

    @FXML
    private VBox mesSabado3;

    @FXML
    private VBox mesSabado4;

    @FXML
    private VBox mesSabado5;

    @FXML
    private VBox mesSabado6;

    @FXML
    private VBox mesViernes1;

    @FXML
    private VBox mesViernes2;

    @FXML
    private VBox mesViernes3;

    @FXML
    private VBox mesViernes4;

    @FXML
    private VBox mesViernes5;

    @FXML
    private VBox mesViernes6;

    @FXML
    private VBox semDomingo;

    @FXML
    private VBox semJueves;

    @FXML
    private VBox semLunes;

    @FXML
    private VBox semMartes;

    @FXML
    private VBox semMiercoles;

    @FXML
    private VBox semSabado;

    @FXML
    private VBox semViernes;

    @FXML
    private Button siguiente;

    @FXML
    private Tab tabDia;

    @FXML
    private Tab tabMes;

    @FXML
    private Tab tabSemana;

    @FXML
    private Button crearEvento;

    @FXML
    private Button crearTarea;

    @FXML
    private DatePicker fechaComienzo;

    @FXML
    private DatePicker fechaDiaCompletoEvento;

    @FXML
    private DatePicker fechaDiaCompletoTarea;

    @FXML
    private DatePicker fechaFinaliza;

    @FXML
    private DatePicker fechaRepeticion;

    @FXML
    private DatePicker fechaVencimiento;

    @FXML
    private RadioButton unico;

    @FXML
    private RadioButton diario;

    @FXML
    private RadioButton repeticionCantidad;

    @FXML
    private RadioButton repeticionFecha;

    @FXML
    private RadioButton repeticionInfinita;

    @FXML
    private Spinner<Integer> cantidadDias;

    @FXML
    private Spinner<Integer> cantidadRepeticiones;

    @FXML
    private Spinner<Integer> horaComienzo;

    @FXML
    private Spinner<Integer> horaFinaliza;

    @FXML
    private Spinner<Integer> horaVencimiento;

    @FXML
    private Spinner<Integer> minutoComienzo;

    @FXML
    private Spinner<Integer> minutoFinaliza;

    @FXML
    private Spinner<Integer> minutoVencimiento;

    @FXML
    private ToggleGroup repeticion;

    @FXML
    private ToggleGroup tipo;

    @FXML
    private TitledPane titledPaneDiaCompletoEvento;

    @FXML
    private TitledPane titledPaneDiaCompletoTarea;

    @FXML
    private TitledPane titledPaneEvento;

    @FXML
    private TitledPane titledPaneTarea;

    @FXML
    private TextField titulo;

    @FXML
    private TextArea descripcion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void start(Stage escenario) throws Exception {
        FXMLLoader cargadorInterfaz = new FXMLLoader(getClass().getResource("estiloTpAlgo3.fxml"));
        cargadorInterfaz.setController(this);
        VBox interfaz = cargadorInterfaz.load();

        Scene escena = new Scene(interfaz);

        nuevoCalendario = new Calendario();

        try {
            String nombreArchivo = System.getProperty("user.dir") + "//archivo.dat";
            FileInputStream archivo = new FileInputStream(nombreArchivo);
            BufferedInputStream bufferArchivo = new BufferedInputStream(archivo);
            nuevoCalendario.deSerializar(bufferArchivo);
        } catch (IOException | ClassNotFoundException e) {}

        fechaActual = LocalDate.now();
        fecha.setText(LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonth().name() + "-" + LocalDate.now().getYear());

        if (nuevoCalendario.getProxId() > 0)
            cargarActividades(dia, fechaActual);

        tabDia.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fechaActual = LocalDate.now();
                fecha.setText(fechaActual.getDayOfMonth() + "-" + fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                cargarActividades(dia, fechaActual);
            }
        });

        tabSemana.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fechaActual = LocalDate.now();
                fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                cargarSemana(semDomingo, semLunes, semMartes, semMiercoles, semJueves, semViernes, semSabado, fechaActual);
            }
        });

        tabMes.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fechaActual = LocalDate.now();
                fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                cargarMes(fechaActual.minusDays(fechaActual.getDayOfMonth() - 1));
            }
        });

        anterior.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (tabDia.isSelected()) {
                    fechaActual = fechaActual.minusDays(1);
                    fecha.setText(fechaActual.getDayOfMonth() + "-" + fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargarActividades(dia, fechaActual);
                }
                else if (tabSemana.isSelected()) {
                    fechaActual = fechaActual.minusWeeks(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargarSemana(semDomingo, semLunes, semMartes, semMiercoles, semJueves, semViernes, semSabado, fechaActual);
                }
                else {
                    fechaActual = fechaActual.minusMonths(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargarMes(fechaActual.minusDays(fechaActual.getDayOfMonth() - 1));
                }
            }
        });

        siguiente.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (tabDia.isSelected()) {
                    fechaActual = fechaActual.plusDays(1);
                    fecha.setText(fechaActual.getDayOfMonth() + "-" + fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargarActividades(dia, fechaActual);
                }
                else if (tabSemana.isSelected()) {
                    fechaActual = fechaActual.plusWeeks(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargarSemana(semDomingo, semLunes, semMartes, semMiercoles, semJueves, semViernes, semSabado, fechaActual);
                }
                else {
                    fechaActual = fechaActual.plusMonths(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargarMes(fechaActual.minusDays(fechaActual.getDayOfMonth() - 1));
                }
            }
        });

        crear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    utilizarInterfaz(escenario, escena);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        AnimationTimer reloj = new AnimationTimer() {
            @Override
            public void handle(long tiempo) {
                hora.setText("%S".formatted(LocalTime.now()));
            }
        };

        reloj.start();

        escenario.setTitle("Calendario");
        escenario.setScene(escena);
        escenario.show();
    }

    @Override
    public void stop() throws IOException {
        String nombreArchivo = System.getProperty("user.dir") + "//archivo.dat";
        FileOutputStream archivo = new FileOutputStream(nombreArchivo);
        BufferedOutputStream bufferArchivo = new BufferedOutputStream(archivo);
        nuevoCalendario.serializar(bufferArchivo);
    }

    private void utilizarInterfaz (Stage escenario, Scene escena) throws IOException {
        Stage nuevoEscenario = (Stage) crear.getScene().getWindow();
        FXMLLoader nuevoCargadorInterfaz = new FXMLLoader(getClass().getResource("crear.fxml"));
        nuevoCargadorInterfaz.setController(this);
        VBox nuevaInterfaz = nuevoCargadorInterfaz.load();
        Scene nuevaEscena = new Scene(nuevaInterfaz);

        cantidadDias.setValueFactory(FABRICACANTIDADDIAS);
        cantidadRepeticiones.setValueFactory(FABRICACANTIDADREPETICIONES);

        horaComienzo.setValueFactory(FABRICAHORACOMIENZO);
        horaFinaliza.setValueFactory(FABRICAHORAFINALIZA);
        horaVencimiento.setValueFactory(FABRICAHORAVENCIMIENTO);

        minutoComienzo.setValueFactory(FABRICAMINUTOCOMIENZO);
        minutoFinaliza.setValueFactory(FABRICAMINUTOFINALIZA);
        minutoVencimiento.setValueFactory(FABRICAMINUTOVENCIMIENTO);



        cantidadDias.getValueFactory().setValue(1);
        cantidadRepeticiones.getValueFactory().setValue(2);

        horaComienzo.getValueFactory().setValue(0);
        horaFinaliza.getValueFactory().setValue(0);
        horaVencimiento.getValueFactory().setValue(0);

        minutoComienzo.getValueFactory().setValue(0);
        minutoFinaliza.getValueFactory().setValue(0);
        minutoVencimiento.getValueFactory().setValue(0);



        fechaComienzo.setValue(fechaActual);
        fechaFinaliza.setValue(fechaActual.plusDays(1));
        fechaDiaCompletoEvento.setValue(fechaActual);

        fechaVencimiento.setValue(fechaActual.plusDays(1));
        fechaDiaCompletoTarea.setValue(fechaActual);

        fechaRepeticion.setValue(fechaActual.plusDays(1));

        repeticionInfinita.setSelected(true);
        unico.setSelected(true);

        crearEvento.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                Repeticion repeticion;

                LocalDateTime comienza = LocalDateTime.of(fechaComienzo.getValue(), LocalTime.of(horaComienzo.getValue(), minutoComienzo.getValue()));
                LocalDateTime finaliza = LocalDateTime.of(fechaFinaliza.getValue(), LocalTime.of(horaFinaliza.getValue(), minutoFinaliza.getValue()));

                Intervalo intervalo = new Intervalo(comienza, finaliza);

                if (titulo.getText().isEmpty())
                    titulo.setText("sin titulo");

                if (descripcion.getText().isEmpty())
                    descripcion.setText("");

                if (unico.isSelected())
                    repeticion = new Unica();
                else {
                    if (repeticionInfinita.isSelected()) {
                        repeticion = new Diaria(LocalDateTime.MAX, cantidadDias.getValue());
                    }else if (repeticionFecha.isSelected()) {
                        repeticion = new Diaria(LocalDateTime.of(fechaRepeticion.getValue(), LocalTime.MIN), cantidadDias.getValue());
                    }else{
                        repeticion = new Diaria(cantidadRepeticiones.getValue() - 1, cantidadDias.getValue());
                    }
                }

                if (titledPaneDiaCompletoEvento.isExpanded()) {
                    LocalDateTime dia = LocalDateTime.of(fechaDiaCompletoEvento.getValue(), LocalTime.MIN);

                    intervalo = new Intervalo(dia);
                }
                
                nuevoCalendario.crearEvento(titulo.getText(), descripcion.getText(), repeticion, intervalo);

                if (tabDia.isSelected()) {
                    cargarActividades(dia, fechaActual);
                }
                else if (tabSemana.isSelected()) {
                    cargarSemana(semDomingo, semLunes, semMartes, semMiercoles, semJueves, semViernes, semSabado, fechaActual);
                }
                else {
                    cargarMes(fechaActual.minusDays(fechaActual.getDayOfMonth() - 1));
                }

                escenario.setTitle("Calendario");
                escenario.setScene(escena);
                escenario.show();
            }
        });

        crearTarea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (titulo.getText().isEmpty())
                    titulo.setText("sin titulo");

                if (descripcion.getText().isEmpty())
                    descripcion.setText("");

                if (titledPaneTarea.isExpanded()) {
                    nuevoCalendario.crearTarea(titulo.getText(), descripcion.getText(),
                            LocalDateTime.of(fechaVencimiento.getValue(), LocalTime.of(horaVencimiento.getValue(), minutoVencimiento.getValue())));
                }

                if (titledPaneDiaCompletoTarea.isExpanded()) {
                    nuevoCalendario.crearTarea(titulo.getText(), descripcion.getText(), fechaDiaCompletoTarea.getValue());
                }

                if (!titledPaneTarea.isExpanded() && !titledPaneDiaCompletoTarea.isExpanded()) {
                    nuevoCalendario.crearTarea(titulo.getText(), descripcion.getText(), fechaDiaCompletoTarea.getValue());
                }

                if (tabDia.isSelected()) {
                    cargarActividades(dia, fechaActual);
                }
                else if (tabSemana.isSelected()) {
                    cargarSemana(semDomingo, semLunes, semMartes, semMiercoles, semJueves, semViernes, semSabado, fechaActual);
                }
                else {
                    cargarMes(fechaActual.minusDays(fechaActual.getDayOfMonth() - 1));
                }

                escenario.setTitle("Calendario");
                escenario.setScene(escena);
                escenario.show();
            }
        });

        nuevoEscenario.setTitle("Calendario");
        nuevoEscenario.setScene(nuevaEscena);
        nuevoEscenario.show();
    }

    private void cargarActividades (VBox columna, LocalDate fecha) {
        columna.getChildren().clear();

        ArrayList<Actividad> actividadesCalendario = nuevoCalendario.actividadesDelDia(fecha);

        for (Actividad actividad : actividadesCalendario) {
            VBox nuevo = new VBox();
            Label titulo = new Label(actividad.getTitulo());
            Label descripcion = new Label(actividad.getDescripcion());
            nuevo.getChildren().add(titulo);
            nuevo.getChildren().add(descripcion);

            columna.getChildren().add(nuevo);
        }
    }

    private void cargarActividades (VBox columna, LocalDate fecha, String dia) {
        columna.getChildren().clear();

        Label diaConFecha = new Label(dia + " " + fecha.getDayOfMonth());
        columna.getChildren().add(diaConFecha);

        ArrayList<Actividad> actividadesCalendario = nuevoCalendario.actividadesDelDia(fecha);

        for (Actividad actividad : actividadesCalendario) {
            VBox nuevo = new VBox();
            Label titulo = new Label(actividad.getTitulo());
            Label descripcion = new Label(actividad.getDescripcion());
            nuevo.getChildren().add(titulo);
            nuevo.getChildren().add(descripcion);

            columna.getChildren().add(nuevo);
        }
    }

    private void cargarSemana (VBox semDomingo, VBox semLunes, VBox semMartes, VBox semMiercoles, VBox semJueves, VBox semViernes, VBox semSabado, LocalDate fechaPibot) {
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

    private void cargarMes (LocalDate fechaInicial) {
        LocalDate controlador = fechaInicial.plusWeeks(4);

        cargarSemana(mesDomingo1, mesLunes1, mesMartes1, mesMiercoles1, mesJueves1, mesViernes1, mesSabado1, fechaInicial);
        cargarSemana(mesDomingo2, mesLunes2, mesMartes2, mesMiercoles2, mesJueves2, mesViernes2, mesSabado2, fechaInicial.plusWeeks(1));
        cargarSemana(mesDomingo3, mesLunes3, mesMartes3, mesMiercoles3, mesJueves3, mesViernes3, mesSabado3, fechaInicial.plusDays(2));
        cargarSemana(mesDomingo4, mesLunes4, mesMartes4, mesMiercoles4, mesJueves4, mesViernes4, mesSabado4, fechaInicial.plusWeeks(3));
        cargarSemana(mesDomingo5, mesLunes5, mesMartes5, mesMiercoles5, mesJueves5, mesViernes5, mesSabado5, fechaInicial.plusWeeks(4));

        if (controlador.getMonth().equals(Month.FEBRUARY)) {
            mesDomingo6.getChildren().clear();
            mesLunes6.getChildren().clear();
            mesMartes6.getChildren().clear();
            mesMiercoles6.getChildren().clear();
            mesJueves6.getChildren().clear();
            mesViernes6.getChildren().clear();
            mesSabado6.getChildren().clear();
        }
        else {
            if (controlador.getDayOfWeek().equals(DayOfWeek.SATURDAY) || ( (controlador.getDayOfWeek().equals(DayOfWeek.FRIDAY)) && (controlador.plusDays(2).getDayOfMonth() == 31) )) {
                cargarSemana(mesDomingo6, mesLunes6, mesMartes6, mesMiercoles6, mesJueves6, mesViernes6, mesSabado6, fechaInicial.plusWeeks(5));
            }
            else {
                mesDomingo6.getChildren().clear();
                mesLunes6.getChildren().clear();
                mesMartes6.getChildren().clear();
                mesMiercoles6.getChildren().clear();
                mesJueves6.getChildren().clear();
                mesViernes6.getChildren().clear();
                mesSabado6.getChildren().clear();
            }
        }
    }
}
