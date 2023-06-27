import Calendario.Actividad.Actividad;
import Calendario.Alarma.Alarma;
import Calendario.Alarma.Relativa;
import Calendario.Calendario;
import Calendario.Notificable.Notificacion;
import Calendario.Repeticion.Repeticion;
import Calendario.Intervalo;

import SeccionesInterfaz.*;

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

    private LocalDate fechaDeControl;

    private ArrayList<Actividad> actividadesAControlar;

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

    @FXML
    private CheckBox alarmaActiva;

    @FXML
    private Spinner<Integer> cantidadTiempoAlarma;


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

        fecha.setText(LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonth().name() + "-" + LocalDate.now().getYear());

        CargadorDeActividades cargador = new CargadorDeActividades();

        ControladorDeAlarma alertas = new ControladorDeAlarma();

        MostrarCalendario impresora = new MostrarCalendario(fecha);

        BotonesPrincipales botones = new BotonesPrincipales(fecha);

        fechaDeControl = LocalDate.now();
        fechaActual = LocalDate.now();
        actividadesAControlar = new ArrayList<>(alertas.actividadesActualizadas(nuevoCalendario, fechaDeControl));

        if (nuevoCalendario.getProxId() > 0)
            cargador.cargarActividades(nuevoCalendario, dia, fechaActual);

        tabDia.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                impresora.mostrarPorDia(nuevoCalendario, dia, fechaActual);
            }
        });

        tabSemana.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                impresora.mostrarPorSemana(nuevoCalendario, generadorSemana(), fechaActual);
            }
        });

        tabMes.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                impresora.mostrarPorMes(nuevoCalendario, generadorMes(), fechaActual);
            }
        });

        anterior.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fechaActual = botones.cambioDePagina(nuevoCalendario, tabDia, tabSemana, dia, generadorSemana(), generadorMes(),
                        fechaActual.minusDays(1), fechaActual.minusWeeks(1), fechaActual.minusMonths(1));
            }
        });

        siguiente.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fechaActual = botones.cambioDePagina(nuevoCalendario, tabDia, tabSemana, dia, generadorSemana(), generadorMes(),
                        fechaActual.plusDays(1), fechaActual.plusWeeks(1), fechaActual.plusMonths(1));
            }
        });

        crear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    utilizarInterfaz(escenario, escena, impresora, alertas);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        AnimationTimer reloj = new AnimationTimer() {
            @Override
            public void handle(long tiempo) {
                hora.setText("%S".formatted(LocalTime.now()));

                if (!fechaDeControl.equals(LocalDate.now())) {
                    fechaDeControl = LocalDate.now();
                    actividadesAControlar = new ArrayList<>(alertas.actividadesActualizadas(nuevoCalendario, fechaDeControl));
                }

                alertas.chequeoDeAlarmas(actividadesAControlar, fechaDeControl);
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

    private void utilizarInterfaz (Stage escenario, Scene escena, MostrarCalendario impresora, ControladorDeAlarma alertas) throws IOException {
        FXMLLoader nuevoCargadorInterfaz = new FXMLLoader(getClass().getResource("crear.fxml"));
        nuevoCargadorInterfaz.setController(this);
        VBox nuevaInterfaz = nuevoCargadorInterfaz.load();
        Scene nuevaEscena = new Scene(nuevaInterfaz);

        ControladorDeCreacion creacion = new ControladorDeCreacion();

        Inicializador inicio = new Inicializador();

        inicio.inicializadorCantidades(cantidadDias, cantidadRepeticiones, cantidadTiempoAlarma);

        inicio.inicializadorHoras(horaComienzo, horaFinaliza, horaVencimiento);

        inicio.inicializadorMinutos(minutoComienzo, minutoFinaliza, minutoVencimiento);

        inicio.inicializadorFechas(fechaActual, fechaComienzo, fechaFinaliza, fechaDiaCompletoEvento, fechaVencimiento, fechaDiaCompletoTarea, fechaRepeticion);

        repeticionInfinita.setSelected(true);
        unico.setSelected(true);
        alarmaActiva.setSelected(false);

        crearEvento.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                creacion.controlDeTexto(titulo, descripcion);

                Repeticion repeticion = creacion.creacionRepeticion(unico, repeticionInfinita, repeticionFecha, cantidadDias, fechaRepeticion, cantidadRepeticiones);

                Intervalo intervalo = creacion.creacionIntervalo(fechaComienzo, fechaFinaliza, fechaDiaCompletoEvento, horaComienzo, horaFinaliza,
                        minutoComienzo, minutoFinaliza, titledPaneDiaCompletoEvento);
                
                nuevoCalendario.crearEvento(titulo.getText(), descripcion.getText(), repeticion, intervalo);

                if (alarmaActiva.isSelected()) {
                    Alarma nueva = new Relativa(new Notificacion("La actividad comenzara en " + cantidadTiempoAlarma.getValue() + " minutos"), cantidadTiempoAlarma.getValue() - 1);
                    nuevoCalendario.asignarAlarma(nueva, nuevoCalendario.getProxId() - 1);
                }

                impresora.mostrarPorSeleccion(nuevoCalendario, tabDia, tabSemana, dia, generadorSemana(), generadorMes(), fechaActual);

                actividadesAControlar = new ArrayList<>(alertas.actividadesActualizadas(nuevoCalendario, fechaDeControl));

                escenario.setTitle("Calendario");
                escenario.setScene(escena);
                escenario.show();
            }
        });

        crearTarea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                creacion.controlDeTexto(titulo, descripcion);

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

                if (alarmaActiva.isSelected()) {
                    Alarma nueva = new Relativa(new Notificacion("La actividad comenzara en " + cantidadTiempoAlarma.getValue() + " minutos"), cantidadTiempoAlarma.getValue() - 1);
                    nuevoCalendario.asignarAlarma(nueva, nuevoCalendario.getProxId() - 1);
                }

                impresora.mostrarPorSeleccion(nuevoCalendario, tabDia, tabSemana, dia, generadorSemana(), generadorMes(), fechaActual);

                actividadesAControlar = new ArrayList<>(alertas.actividadesActualizadas(nuevoCalendario, fechaDeControl));

                escenario.setTitle("Calendario");
                escenario.setScene(escena);
                escenario.show();
            }
        });

        escenario.setTitle("Calendario");
        escenario.setScene(nuevaEscena);
        escenario.show();
    }

    private ArrayList<VBox> generadorSemana () {
        ArrayList<VBox> semana = new ArrayList<>();
        semana.add(semDomingo);
        semana.add(semLunes);
        semana.add(semMartes);
        semana.add(semMiercoles);
        semana.add(semJueves);
        semana.add(semViernes);
        semana.add(semSabado);
        return semana;
    }

    private ArrayList<HBox> generadorMes () {
        ArrayList<HBox> mes = new ArrayList<>();
        mes.add(semana1);
        mes.add(semana2);
        mes.add(semana3);
        mes.add(semana4);
        mes.add(semana5);
        mes.add(semana6);
        return mes;
    }
}
