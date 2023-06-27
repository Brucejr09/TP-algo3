package SeccionesInterfaz;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.time.LocalDate;

public class Inicializador {
    private static final SpinnerValueFactory<Integer> FABRICACANTIDADDIAS = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 365, 1);
    private static final SpinnerValueFactory<Integer> FABRICACANTIDADREPETICIONES = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 365, 2);
    private static final SpinnerValueFactory<Integer> FABRICACANTIDADTIEMPOALARMA = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1440, 1);

    private static final SpinnerValueFactory<Integer> FABRICAHORACOMIENZO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
    private static final SpinnerValueFactory<Integer> FABRICAHORAFINALIZA = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
    private static final SpinnerValueFactory<Integer> FABRICAHORAVENCIMIENTO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);

    private static final SpinnerValueFactory<Integer> FABRICAMINUTOCOMIENZO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
    private static final SpinnerValueFactory<Integer> FABRICAMINUTOFINALIZA = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
    private static final SpinnerValueFactory<Integer> FABRICAMINUTOVENCIMIENTO = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);

    public void inicializadorCantidades (Spinner<Integer> cantidadDias, Spinner<Integer> cantidadRepeticiones, Spinner<Integer> cantidadTiempoAlarma) {
        cantidadDias.setValueFactory(FABRICACANTIDADDIAS);
        cantidadRepeticiones.setValueFactory(FABRICACANTIDADREPETICIONES);
        cantidadTiempoAlarma.setValueFactory(FABRICACANTIDADTIEMPOALARMA);

        cantidadDias.getValueFactory().setValue(1);
        cantidadRepeticiones.getValueFactory().setValue(2);
        cantidadTiempoAlarma.getValueFactory().setValue(1);
    }

    public void inicializadorHoras (Spinner<Integer> horaComienzo, Spinner<Integer> horaFinaliza, Spinner<Integer> horaVencimiento) {
        horaComienzo.setValueFactory(FABRICAHORACOMIENZO);
        horaFinaliza.setValueFactory(FABRICAHORAFINALIZA);
        horaVencimiento.setValueFactory(FABRICAHORAVENCIMIENTO);

        horaComienzo.getValueFactory().setValue(0);
        horaFinaliza.getValueFactory().setValue(0);
        horaVencimiento.getValueFactory().setValue(0);
    }

    public void inicializadorMinutos (Spinner<Integer> minutoComienzo, Spinner<Integer> minutoFinaliza, Spinner<Integer> minutoVencimiento) {
        minutoComienzo.setValueFactory(FABRICAMINUTOCOMIENZO);
        minutoFinaliza.setValueFactory(FABRICAMINUTOFINALIZA);
        minutoVencimiento.setValueFactory(FABRICAMINUTOVENCIMIENTO);

        minutoComienzo.getValueFactory().setValue(0);
        minutoFinaliza.getValueFactory().setValue(0);
        minutoVencimiento.getValueFactory().setValue(0);
    }

    public void inicializadorFechas (LocalDate fecha, DatePicker fechaComienzo, DatePicker fechaFinaliza, DatePicker fechaDiaCompletoEvento,
                                     DatePicker fechaVencimiento, DatePicker fechaDiaCompletoTarea, DatePicker fechaRepeticion) {
        fechaComienzo.setValue(fecha);
        fechaFinaliza.setValue(fecha.plusDays(1));
        fechaDiaCompletoEvento.setValue(fecha);

        fechaVencimiento.setValue(fecha.plusDays(1));
        fechaDiaCompletoTarea.setValue(fecha);

        fechaRepeticion.setValue(fecha.plusDays(1));
    }
}
