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
import javafx.scene.input.MouseEvent;
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

    @FXML
    private Button cancelarEvento;

    @FXML
    private Button modificarEvento;

    @FXML
    private Button eliminarEvento;

    @FXML
    private CheckBox agregarAlarmaEvento;

    @FXML
    private Spinner<Integer> cantidadTiempoAlarmaEvento;

    @FXML
    private Button cancelarTarea;

    @FXML
    private Button modificarTarea;

    @FXML
    private Button eliminarTarea;

    @FXML
    private CheckBox agregarAlarmaTarea;

    @FXML
    private Spinner<Integer> cantidadTiempoAlarmaTarea;

    @FXML
    private TextArea descripcionEvento;

    @FXML
    private DatePicker fechaComienzoEvento;

    @FXML
    private DatePicker fechaFinalizaEvento;

    @FXML
    private Spinner<Integer> horaComienzoEvento;

    @FXML
    private Spinner<Integer> horaFinalizaEvento;

    @FXML
    private Spinner<Integer> minutoComienzoEvento;

    @FXML
    private Spinner<Integer> minutoFinalizaEvento;

    @FXML
    private TextField tituloEvento;

    @FXML
    private CheckBox completarTarea;

    @FXML
    private TextArea descripcionTarea;

    @FXML
    private DatePicker fechaVencimientoTarea;

    @FXML
    private Spinner<Integer> horaVencimientoTarea;

    @FXML
    private Spinner<Integer> minutoVencimientoTarea;

    @FXML
    private TextField tituloTarea;


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

                if (tabDia.isSelected()) {
                    for (int i = 0; i < dia.getChildren().size(); i++) {
                        int indice = i;
                        dia.getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                modificador(dia, indice, escenario, escena, impresora, alertas);
                            }
                        });
                    }
                }
                else if (tabSemana.isSelected()) {
                    ArrayList<VBox> semana = generadorSemana();
                    for (VBox vBox : semana) {
                        for (int j = 0; j < vBox.getChildren().size(); j++) {
                            int indice = j;
                            vBox.getChildren().get(j).setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    modificador(vBox, indice, escenario, escena, impresora, alertas);
                                }
                            });
                        }
                    }
                }
                else {
                    ArrayList<HBox> mes = generadorMes();
                    for (HBox mess : mes) {
                        ArrayList<VBox> semana = cargador.cambioALista(mess);
                        for (VBox vBox : semana) {
                            for (int i = 0; i < vBox.getChildren().size(); i++) {
                                int indice = i;
                                vBox.getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent mouseEvent) {
                                        modificador(vBox, indice, escenario, escena, impresora, alertas);
                                    }
                                });
                            }
                        }
                    }
                }
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

    public void interfazEvento (Stage escenario, Scene escena, ContenedorActividad nuevo, int posicion,
                                MostrarCalendario impresora, ControladorDeAlarma alertas) throws IOException {
        FXMLLoader nuevoCargadorInterfaz = new FXMLLoader(getClass().getResource("evento.fxml"));
        nuevoCargadorInterfaz.setController(this);
        VBox nuevaInterfaz = nuevoCargadorInterfaz.load();
        Scene nuevaEscena = new Scene(nuevaInterfaz);

        nuevo.inicializar(fechaActual, cantidadTiempoAlarmaEvento, horaComienzoEvento, horaFinalizaEvento, minutoComienzoEvento, minutoFinalizaEvento,
                tituloEvento, descripcionEvento, fechaComienzoEvento, fechaFinalizaEvento);

        agregarAlarmaEvento.setSelected(false);

        cancelarEvento.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                escenario.setTitle("Calendario");
                escenario.setScene(escena);
                escenario.show();
            }
        });

        modificarEvento.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nuevoCalendario.setActividad(nuevo.cargar(tituloEvento, descripcionEvento, fechaComienzoEvento, fechaFinalizaEvento,
                        horaComienzoEvento, horaFinalizaEvento, minutoComienzoEvento, minutoFinalizaEvento), posicion);

                if (agregarAlarmaEvento.isSelected()) {
                    Alarma nueva = new Relativa(new Notificacion("La actividad comenzara en " + cantidadTiempoAlarmaEvento.getValue() + " minutos"), cantidadTiempoAlarmaEvento.getValue() - 1);
                    nuevoCalendario.asignarAlarma(nueva, posicion);
                }

                impresora.mostrarPorSeleccion(nuevoCalendario, tabDia, tabSemana, dia, generadorSemana(), generadorMes(), fechaActual);

                actividadesAControlar = new ArrayList<>(alertas.actividadesActualizadas(nuevoCalendario, fechaDeControl));

                escenario.setTitle("Calendario");
                escenario.setScene(escena);
                escenario.show();
            }
        });

        eliminarEvento.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nuevoCalendario.eliminarActividad(posicion);

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

    public void interfazTarea (Stage escenario, Scene escena, ContenedorActividad nuevo, int posicion,
                               MostrarCalendario impresora, ControladorDeAlarma alertas) throws IOException {
        FXMLLoader nuevoCargadorInterfaz = new FXMLLoader(getClass().getResource("tarea.fxml"));
        nuevoCargadorInterfaz.setController(this);
        VBox nuevaInterfaz = nuevoCargadorInterfaz.load();
        Scene nuevaEscena = new Scene(nuevaInterfaz);

        nuevo.inicializar(fechaActual, cantidadTiempoAlarmaTarea, tituloTarea, descripcionTarea, horaVencimientoTarea, minutoVencimientoTarea,
                fechaVencimientoTarea, completarTarea);

        agregarAlarmaTarea.setSelected(false);

        cancelarTarea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                escenario.setTitle("Calendario");
                escenario.setScene(escena);
                escenario.show();
            }
        });

        modificarTarea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nuevoCalendario.setActividad(nuevo.cargar(tituloTarea, descripcionTarea, fechaVencimientoTarea, horaVencimientoTarea,
                        minutoVencimientoTarea, completarTarea), posicion);

                if (agregarAlarmaTarea.isSelected()) {
                    Alarma nueva = new Relativa(new Notificacion("La actividad comenzara en " + cantidadTiempoAlarmaTarea.getValue() + " minutos"), cantidadTiempoAlarmaTarea.getValue() - 1);
                    nuevoCalendario.asignarAlarma(nueva, posicion);
                }

                impresora.mostrarPorSeleccion(nuevoCalendario, tabDia, tabSemana, dia, generadorSemana(), generadorMes(), fechaActual);

                actividadesAControlar = new ArrayList<>(alertas.actividadesActualizadas(nuevoCalendario, fechaDeControl));

                escenario.setTitle("Calendario");
                escenario.setScene(escena);
                escenario.show();
            }
        });

        eliminarTarea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nuevoCalendario.eliminarActividad(posicion);

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

    private void modificador (VBox vBox, int indice, Stage escenario, Scene escena, MostrarCalendario impresora, ControladorDeAlarma alertas) {
        ContenedorActividad nuevo = (ContenedorActividad) vBox.getChildren().get(indice);
        int posicion = nuevo.getActividad().obtenerId();

        if (nuevo.getEsEvento()) {
            try {
                interfazEvento(escenario, escena, nuevo, posicion, impresora, alertas);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                interfazTarea(escenario, escena, nuevo, posicion, impresora, alertas);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}