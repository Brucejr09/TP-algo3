package SeccionesInterfaz;

import Calendario.Intervalo;
import Calendario.Repeticion.Diaria;
import Calendario.Repeticion.Repeticion;
import Calendario.Repeticion.Unica;

import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ControladorDeCreacion {
    public void controlDeTexto (TextField titulo, TextArea descripcion) {
        if (titulo.getText().isEmpty())
            titulo.setText("sin titulo");

        if (descripcion.getText().isEmpty())
            descripcion.setText("");
    }

    public Repeticion creacionRepeticion (RadioButton unico, RadioButton repeticionInfinita, RadioButton repeticionFecha, Spinner<Integer> cantidadDias,
                                          DatePicker fechaRepeticion, Spinner<Integer> cantidadRepeticiones) {
        Repeticion repeticion;

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

        return repeticion;
    }

    public Intervalo creacionIntervalo (DatePicker fechaComienzo, DatePicker fechaFinaliza, DatePicker fechaDiaCompletoEvento,
                                        Spinner<Integer> horaComienzo, Spinner<Integer> horaFinaliza, Spinner<Integer> minutoComienzo,
                                        Spinner<Integer> minutoFinaliza, TitledPane titledPaneDiaCompletoEvento) {
        LocalDateTime comienza = LocalDateTime.of(fechaComienzo.getValue(), LocalTime.of(horaComienzo.getValue(), minutoComienzo.getValue()));
        LocalDateTime finaliza = LocalDateTime.of(fechaFinaliza.getValue(), LocalTime.of(horaFinaliza.getValue(), minutoFinaliza.getValue()));

        Intervalo intervalo = new Intervalo(comienza, finaliza);

        if (titledPaneDiaCompletoEvento.isExpanded()) {
            LocalDateTime dia = LocalDateTime.of(fechaDiaCompletoEvento.getValue(), LocalTime.MIN);

            intervalo = new Intervalo(dia);
        }

        return intervalo;
    }
}
