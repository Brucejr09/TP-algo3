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
    private static final SpinnerValueFactory<Integer> FABRICACANTIDADREPETICIONES = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 365, 1);

    private static final SpinnerValueFactory<Integer> FABRICAHORACOMIENZO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
    private static final SpinnerValueFactory<Integer> FABRICAHORAFINALIZA = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
    private static final SpinnerValueFactory<Integer> FABRICAHORAVENCIMIENTO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);

    private static final SpinnerValueFactory<Integer> FABRICAMINUTOCOMIENZO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
    private static final SpinnerValueFactory<Integer> FABRICAMINUTOFINALIZA = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
    private static final SpinnerValueFactory<Integer> FABRICAMINUTOVENCIMIENTO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);


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
            cargarActividades();


        tabDia.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fechaActual = LocalDate.now();
                fecha.setText(fechaActual.getDayOfMonth() + "-" + fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                cargarActividades();
            }
        });

        tabSemana.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                fechaActual = LocalDate.now();
                fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
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
                    cargarActividades();
                }
                else if (tabSemana.isSelected()) {
                    fechaActual = fechaActual.minusWeeks(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
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
                    cargarActividades();
                }
                else if (tabSemana.isSelected()) {
                    fechaActual = fechaActual.plusWeeks(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
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

        fechaComienzo.setValue(fechaActual);
        fechaFinaliza.setValue(fechaActual);
        fechaDiaCompletoEvento.setValue(fechaActual);

        fechaVencimiento.setValue(fechaActual.plusDays(1));
        fechaDiaCompletoTarea.setValue(fechaActual);

        fechaRepeticion.setValue(fechaActual.plusDays(1));

        cantidadDias.getValueFactory().setValue(1);
        cantidadRepeticiones.getValueFactory().setValue(1);

        horaComienzo.getValueFactory().setValue(0);
        horaFinaliza.getValueFactory().setValue(2);
        horaVencimiento.getValueFactory().setValue(0);

        minutoComienzo.getValueFactory().setValue(0);
        minutoFinaliza.getValueFactory().setValue(0);
        minutoVencimiento.getValueFactory().setValue(0);

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
                    System.out.println(cantidadDias.getValue());
                    System.out.println(cantidadRepeticiones.getValue());
                    if (repeticionInfinita.isSelected()) {
                        System.out.println("entro aca");
                        repeticion = new Diaria(LocalDateTime.MAX, cantidadDias.getValue());
                    }else if (repeticionFecha.isSelected()) {
                        repeticion = new Diaria(LocalDateTime.of(fechaRepeticion.getValue(), LocalTime.MIN), cantidadDias.getValue());
                    }else{
                        repeticion = new Diaria(cantidadRepeticiones.getValue(), cantidadDias.getValue());
                    }
                }

                if (titledPaneDiaCompletoEvento.isExpanded()) {
                    LocalDateTime dia = LocalDateTime.of(fechaDiaCompletoEvento.getValue(), LocalTime.MIN);

                    intervalo = new Intervalo(dia);
                }
                
                nuevoCalendario.crearEvento(titulo.getText(), descripcion.getText(), repeticion, intervalo);

                cargarActividades();

                escenario.setTitle("Calendario");
                escenario.setScene(escena);
                escenario.show();
            }
        });

        crearTarea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cantidadDias.getValueFactory().setValue(1);
                cantidadRepeticiones.getValueFactory().setValue(1);

                horaComienzo.getValueFactory().setValue(0);
                horaFinaliza.getValueFactory().setValue(0);
                horaVencimiento.getValueFactory().setValue(0);

                minutoComienzo.getValueFactory().setValue(0);
                minutoFinaliza.getValueFactory().setValue(0);
                minutoVencimiento.getValueFactory().setValue(0);

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

                if (!(titledPaneTarea.isExpanded() || titledPaneDiaCompletoEvento.isExpanded())) {
                    nuevoCalendario.crearTarea(titulo.getText(), descripcion.getText(), fechaDiaCompletoTarea.getValue());
                }

                cargarActividades();

                escenario.setTitle("Calendario");
                escenario.setScene(escena);
                escenario.show();
            }
        });

        nuevoEscenario.setTitle("Calendario");
        nuevoEscenario.setScene(nuevaEscena);
        nuevoEscenario.show();
    }

    void cargarActividades () {
        dia.getChildren().clear();

        ArrayList<Actividad> actividadesCalendario = nuevoCalendario.actividadesDelDia(fechaActual);

        for (Actividad actividad : actividadesCalendario) {
            VBox nuevo = new VBox();
            Label titulo = new Label(actividad.getTitulo());
            Label descripcion = new Label(actividad.getDescripcion());
            nuevo.getChildren().add(titulo);
            nuevo.getChildren().add(descripcion);

            dia.getChildren().add(nuevo);
        }
    }
}
