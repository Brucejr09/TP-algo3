import Calendario.Actividad.Actividad;
import Calendario.Calendario;
import Calendario.Repeticion.Diaria;
import Calendario.Repeticion.Repeticion;
import Calendario.Repeticion.Unica;
import Calendario.Intervalo;

import SeccionesInterfaz.CargadorDeActividades;
import SeccionesInterfaz.MostrarCalendario;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.time.*;
import java.util.ArrayList;

public class InterfazGrafica extends Application {
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
    private HBox semana1;

    @FXML
    private HBox semana2;

    @FXML
    private HBox semana3;

    @FXML
    private HBox semana4;

    @FXML
    private HBox semana5;

    @FXML
    private HBox semana6;

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

        CargadorDeActividades cargador = new CargadorDeActividades(nuevoCalendario);

        MostrarCalendario impresora = new MostrarCalendario(fechaActual, fecha, nuevoCalendario);

        if (nuevoCalendario.getProxId() > 0)
            cargador.cargarActividades(dia, fechaActual);

        impresora.mostrarPorDia(tabDia, dia);

        impresora.mostrarPorSemana(tabSemana, semDomingo, semLunes, semMartes, semMiercoles, semJueves, semViernes, semSabado);

        impresora.mostrarPorMes(tabMes, semana1, semana2, semana3, semana4, semana5, semana6);

        anterior.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (tabDia.isSelected()) {
                    fechaActual = fechaActual.minusDays(1);
                    fecha.setText(fechaActual.getDayOfMonth() + "-" + fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargador.cargarActividades(dia, fechaActual);
                }
                else if (tabSemana.isSelected()) {
                    fechaActual = fechaActual.minusWeeks(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargador.cargarSemana(semDomingo, semLunes, semMartes, semMiercoles, semJueves, semViernes, semSabado, fechaActual);
                }
                else {
                    fechaActual = fechaActual.minusMonths(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargador.cargarMes(fechaActual.minusDays(fechaActual.getDayOfMonth() - 1), semana1, semana2, semana3, semana4, semana5, semana6);
                }
            }
        });

        siguiente.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (tabDia.isSelected()) {
                    fechaActual = fechaActual.plusDays(1);
                    fecha.setText(fechaActual.getDayOfMonth() + "-" + fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargador.cargarActividades(dia, fechaActual);
                }
                else if (tabSemana.isSelected()) {
                    fechaActual = fechaActual.plusWeeks(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargador.cargarSemana(semDomingo, semLunes, semMartes, semMiercoles, semJueves, semViernes, semSabado, fechaActual);
                }
                else {
                    fechaActual = fechaActual.plusMonths(1);
                    fecha.setText(fechaActual.getMonth().name() + "-" + fechaActual.getYear());
                    cargador.cargarMes(fechaActual.minusDays(fechaActual.getDayOfMonth() - 1), semana1, semana2, semana3, semana4, semana5, semana6);
                }
            }
        });

        crear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    utilizarInterfaz(escenario, escena, cargador);
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

    private void utilizarInterfaz (Stage escenario, Scene escena, CargadorDeActividades cargador) throws IOException {
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
                    cargador.cargarActividades(dia, fechaActual);
                }
                else if (tabSemana.isSelected()) {
                    cargador.cargarSemana(semDomingo, semLunes, semMartes, semMiercoles, semJueves, semViernes, semSabado, fechaActual);
                }
                else {
                    cargador.cargarMes(fechaActual.minusDays(fechaActual.getDayOfMonth() - 1), semana1, semana2, semana3, semana4, semana5, semana6);
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
                    cargador.cargarActividades(dia, fechaActual);
                }
                else if (tabSemana.isSelected()) {
                    cargador.cargarSemana(semDomingo, semLunes, semMartes, semMiercoles, semJueves, semViernes, semSabado, fechaActual);
                }
                else {
                    cargador.cargarMes(fechaActual.minusDays(fechaActual.getDayOfMonth() - 1), semana1, semana2, semana3, semana4, semana5, semana6);
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
}
