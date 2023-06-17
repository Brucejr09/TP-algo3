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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private Label mesDomingo;

    @FXML
    private VBox mesJueves;

    @FXML
    private VBox mesLunes;

    @FXML
    private VBox mesMartes;

    @FXML
    private VBox mesMiercoles;

    @FXML
    private VBox mesSabado;

    @FXML
    private VBox mesViernes;

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
        System.out.println("pasa por aca");

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
                cargarSemana();
            }
        });

        tabMes.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fechaActual = LocalDate.now();
                fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
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
                    cargarSemana();
                }
                else {
                    fechaActual = fechaActual.minusMonths(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
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
                    cargarSemana();
                }
                else {
                    fechaActual = fechaActual.plusMonths(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
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
                    cargarSemana();
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
                    cargarSemana();
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

    void cargarActividades (VBox columna, LocalDate fecha) {
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

    void cargarActividades (VBox columna, LocalDate fecha, String dia) {
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

    private void cargarSemana () {
        switch (fechaActual.getDayOfWeek()) {
            case SUNDAY -> {
                cargarActividades(semDomingo, fechaActual, DOMINGO);
                cargarActividades(semLunes, fechaActual.plusDays(1), LUNES);
                cargarActividades(semMartes, fechaActual.plusDays(2), MARTES);
                cargarActividades(semMiercoles, fechaActual.plusDays(3), MIERCOLES);
                cargarActividades(semJueves, fechaActual.plusDays(4), JUEVES);
                cargarActividades(semViernes, fechaActual.plusDays(5), VIERNES);
                cargarActividades(semSabado, fechaActual.plusDays(6), SABADO);
            }
            case MONDAY -> {
                cargarActividades(semDomingo, fechaActual.minusDays(1), DOMINGO);
                cargarActividades(semLunes, fechaActual, LUNES);
                cargarActividades(semMartes, fechaActual.plusDays(1), MARTES);
                cargarActividades(semMiercoles, fechaActual.plusDays(2), MIERCOLES);
                cargarActividades(semJueves, fechaActual.plusDays(3), JUEVES);
                cargarActividades(semViernes, fechaActual.plusDays(4), VIERNES);
                cargarActividades(semSabado, fechaActual.plusDays(5), SABADO);
            }
            case TUESDAY -> {
                cargarActividades(semDomingo, fechaActual.minusDays(2), DOMINGO);
                cargarActividades(semLunes, fechaActual.minusDays(1), LUNES);
                cargarActividades(semMartes, fechaActual, MARTES);
                cargarActividades(semMiercoles, fechaActual.plusDays(1), MIERCOLES);
                cargarActividades(semJueves, fechaActual.plusDays(2), JUEVES);
                cargarActividades(semViernes, fechaActual.plusDays(3), VIERNES);
                cargarActividades(semSabado, fechaActual.plusDays(4), SABADO);
            }
            case WEDNESDAY -> {
                cargarActividades(semDomingo, fechaActual.minusDays(3), DOMINGO);
                cargarActividades(semLunes, fechaActual.minusDays(2), LUNES);
                cargarActividades(semMartes, fechaActual.minusDays(1), MARTES);
                cargarActividades(semMiercoles, fechaActual, MIERCOLES);
                cargarActividades(semJueves, fechaActual.plusDays(1), JUEVES);
                cargarActividades(semViernes, fechaActual.plusDays(2), VIERNES);
                cargarActividades(semSabado, fechaActual.plusDays(3), SABADO);
            }
            case THURSDAY -> {
                cargarActividades(semDomingo, fechaActual.minusDays(4), DOMINGO);
                cargarActividades(semLunes, fechaActual.minusDays(3), LUNES);
                cargarActividades(semMartes, fechaActual.minusDays(2), MARTES);
                cargarActividades(semMiercoles, fechaActual.minusDays(1), MIERCOLES);
                cargarActividades(semJueves, fechaActual, JUEVES);
                cargarActividades(semViernes, fechaActual.plusDays(1), VIERNES);
                cargarActividades(semSabado, fechaActual.plusDays(2), SABADO);
            }
            case FRIDAY -> {
                cargarActividades(semDomingo, fechaActual.minusDays(5), DOMINGO);
                cargarActividades(semLunes, fechaActual.minusDays(4), LUNES);
                cargarActividades(semMartes, fechaActual.minusDays(3), MARTES);
                cargarActividades(semMiercoles, fechaActual.minusDays(2), MIERCOLES);
                cargarActividades(semJueves, fechaActual.minusDays(1), JUEVES);
                cargarActividades(semViernes, fechaActual, VIERNES);
                cargarActividades(semSabado, fechaActual.plusDays(1), SABADO);
            }
            default -> {
                cargarActividades(semDomingo, fechaActual.minusDays(6), DOMINGO);
                cargarActividades(semLunes, fechaActual.minusDays(5), LUNES);
                cargarActividades(semMartes, fechaActual.minusDays(4), MARTES);
                cargarActividades(semMiercoles, fechaActual.minusDays(3), MIERCOLES);
                cargarActividades(semJueves, fechaActual.minusDays(2), JUEVES);
                cargarActividades(semViernes, fechaActual.minusDays(1), VIERNES);
                cargarActividades(semSabado, fechaActual, SABADO);
            }
        }
    }
}
