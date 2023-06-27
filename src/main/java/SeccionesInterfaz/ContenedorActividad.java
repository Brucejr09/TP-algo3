package SeccionesInterfaz;

import Calendario.Actividad.Actividad;
import Calendario.Actividad.Evento;
import Calendario.Actividad.Tarea;
import Calendario.Intervalo;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ContenedorActividad extends VBox {

    private Actividad actividad;

    private boolean esEvento;

    private static final SpinnerValueFactory<Integer> FABRICACANTIDADTIEMPOALARMA = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1440, 1);


    public ContenedorActividad(Actividad actividad, LocalDate fecha){
        this.actividad = actividad;
        this.esEvento = (actividad.getTitulo().substring(0,1).equals("E"));
        Label titulo = new Label(actividad.getTitulo().substring(2));
        titulo.setFont(Font.font(null, FontWeight.BOLD, 12));

        Label intervalo = new Label(actividad.getIntervalo(fecha));

        this.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        titulo.setStyle("-fx-border-color: black; -fx-border-width: 0 0 1px 0;");

        if (this.esEvento){
            this.setBackground(new Background(new BackgroundFill(Color.AQUA,CornerRadii.EMPTY, Insets.EMPTY)));
        }else {
            this.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY, Insets.EMPTY)));
        }

        this.getChildren().addAll(titulo, intervalo);
    }

    public Actividad getActividad() {
        return this.actividad;
    }

    public boolean getEsEvento () {
        return this.esEvento;
    }

    public Evento cargar(TextField tituloEvento, TextArea descripcionEvento, DatePicker fechaComienzo, DatePicker fechaFinaliza,
                               Spinner<Integer> horaComienzo, Spinner<Integer> horaFinaliza, Spinner<Integer> minutoComienzo, Spinner<Integer> minutoFinaliza) {
        LocalDateTime inicio = LocalDateTime.of(fechaComienzo.getValue(), LocalTime.of(horaComienzo.getValue(), minutoComienzo.getValue()));
        LocalDateTime fin = LocalDateTime.of(fechaFinaliza.getValue(), LocalTime.of(horaFinaliza.getValue(), minutoFinaliza.getValue()));
        Intervalo intervalo = new Intervalo(inicio, fin);
        return new Evento(actividad.obtenerId(), tituloEvento.getText(), descripcionEvento.getText(), ((Evento)actividad).getTipoRepeticion(), intervalo);
    }

    public Tarea cargar(TextField tituloTarea, TextArea descripcionTarea, DatePicker fechaVencimiento,
                             Spinner<Integer> horaVencimiento, Spinner<Integer> minutoVencimiento, CheckBox completarTarea) {
        Tarea tarea = new Tarea(actividad.obtenerId(), tituloTarea.getText(), descripcionTarea.getText());
        LocalDate dia = fechaVencimiento.getValue();
        LocalTime horario = LocalTime.of(horaVencimiento.getValue(), minutoVencimiento.getValue());
        tarea.asignarVencimiento(LocalDateTime.of(dia, horario));
        tarea.setCompletada(completarTarea.isSelected());
        return tarea;
    }

    public void inicializar (LocalDate fecha, Spinner<Integer> cantidad, Spinner<Integer> horaComienzo,
                                    Spinner<Integer> horaFinaliza, Spinner<Integer> minutoComienzo, Spinner<Integer> minutoFinaliza,
                                    TextField tituloEvento, TextArea descripcionEvento, DatePicker fechaComienzo, DatePicker fechaFinaliza) {
        tituloEvento.setText(actividad.getTitulo().substring(2));
        descripcionEvento.setText(actividad.getDescripcion());

        Inicializador inicio = new Inicializador();

        inicio.inicializadorEvento(cantidad, horaComienzo, horaFinaliza, minutoComienzo, minutoFinaliza);

        horaComienzo.getValueFactory().setValue(actividad.obtenerIntervalo(fecha).getComienzo().toLocalTime().getHour());
        horaFinaliza.getValueFactory().setValue(actividad.obtenerIntervalo(fecha).getFin().toLocalTime().getHour());

        minutoComienzo.getValueFactory().setValue(actividad.obtenerIntervalo(fecha).getComienzo().toLocalTime().getMinute());
        minutoFinaliza.getValueFactory().setValue(actividad.obtenerIntervalo(fecha).getComienzo().toLocalTime().getMinute());

        cantidad.setValueFactory(FABRICACANTIDADTIEMPOALARMA);
        cantidad.getValueFactory().setValue(1);

        fechaComienzo.setValue(actividad.obtenerIntervalo(fecha).getComienzo().toLocalDate());
        fechaFinaliza.setValue(actividad.obtenerIntervalo(fecha).getFin().toLocalDate());
    }

    public void inicializar (LocalDate fecha, Spinner<Integer> cantidad, TextField tituloTarea, TextArea descripcionTarea, Spinner<Integer> horaVencimiento,
                                   Spinner<Integer> minutoVencimiento, DatePicker fechaVencimiento, CheckBox completarTarea) {
        tituloTarea.setText(actividad.getTitulo().substring(2));
        descripcionTarea.setText(actividad.getDescripcion());

        Inicializador inicio = new Inicializador();

        inicio.inicializadorTarea(cantidad, horaVencimiento, minutoVencimiento);

        horaVencimiento.getValueFactory().setValue(actividad.obtenerIntervalo(fecha).getComienzo().toLocalTime().getHour());

        minutoVencimiento.getValueFactory().setValue(actividad.obtenerIntervalo(fecha).getComienzo().toLocalTime().getMinute());

        fechaVencimiento.setValue(actividad.obtenerIntervalo(fecha).getComienzo().toLocalDate());

        completarTarea.setSelected(((Tarea)actividad).estaCompletada());
    }


}
