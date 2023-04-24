import Calendario.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Calendario {
    private LocalDate fechaActualizacion;
    private List<Tarea> tareas;
    private List<Evento> eventos;

    public Calendario() {
        this.tareas = new ArrayList<>();
        this.eventos = new ArrayList<>();
        this.fechaActualizacion = LocalDate.now().plusWeeks(1);
    }
    public void crear (Tarea tarea) {
        this.tareas.add(tarea);
    }
    public void crear (Evento evento) {
        this.eventos.add(evento);
        this.eventos = evento.repetir(this.eventos, this.fechaActualizacion);
    }
    public Tarea modificar (Tarea tareaAModificar, String titulo, String descripcion, LocalDateTime vencimiento, LocalDate dia) {
        int indiceTarea = this.tareas.indexOf(tareaAModificar);
        Tarea tarea;

        if (indiceTarea == -1)
            tarea = null;
        else {
            tarea = this.tareas.get(indiceTarea);

            if (titulo != null)
                tarea.asignarTitulo(titulo);
            if (descripcion != null)
                tarea.asignarDescripcion(descripcion);
            if (!(vencimiento != null && dia != null)) {
                if (vencimiento != null)
                    tarea.asignarVencimiento(vencimiento);
                else
                    tarea.asignarDiaCompleto(dia);
            }

            this.tareas.set(indiceTarea, tarea);
        }

        return tarea;
    }
    public void modificar () {}
    public boolean eliminar (Tarea tareaAEliminar) {
        return this.tareas.remove(tareaAEliminar);
    }

    public boolean eliminar (Evento eventoAEliminar) {
        return this.eventos.remove(eventoAEliminar);
    }
}