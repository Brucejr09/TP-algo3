package SeccionesInterfaz;

import Calendario.Actividad.Actividad;
import Calendario.Alarma.Alarma;
import Calendario.Calendario;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorDeAlarma {
    public ArrayList<Actividad> actividadesActualizadas (Calendario calendario, LocalDate fecha) {
        ArrayList<Actividad> actualizadas = calendario.actividadesDelDia(fecha);
        actualizadas.addAll(calendario.actividadesDelDia(fecha.plusDays(1)));
        return actualizadas;
    }

    public void chequeoDeAlarmas (ArrayList<Actividad> actividades, LocalDate fecha) {
        for (Actividad actividad : actividades) {
            ArrayList<Alarma> alarmas = actividad.getAlarmas();
            for (Alarma alarma: alarmas) {
                if (actividad.soyDelDia(fecha)){
                    if (alarma.sonarAlarma(LocalDateTime.now(), actividad.obtenerIntervalo(fecha)) == 1) {
                        this.activarAlarma(alarma);
                    }
                } else if (actividad.soyDelDia(fecha.plusDays(1))) {
                    if (alarma.sonarAlarma(LocalDateTime.now(), actividad.obtenerIntervalo(fecha.plusDays(1))) == 1) {
                        this.activarAlarma(alarma);
                    }
                }
            }
        }
    }

    private void activarAlarma (Alarma alarma) {
        Alert notificacion = new Alert(Alert.AlertType.INFORMATION);
        notificacion.setTitle("Alarma");
        notificacion.setContentText(alarma.getTipoDeAviso().notificar());
        notificacion.show();
    }
}
