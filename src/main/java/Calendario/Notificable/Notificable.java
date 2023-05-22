package Calendario.Notificable;

import java.io.Serializable;

public abstract class Notificable implements Serializable {
    private int cantidadNotificables;

    public Notificable () {
        this.cantidadNotificables = 0;
    }
    public void notificar () {
        this.cantidadNotificables++;
    }

    public int obtenerCantidadNotificables () {
        return this.cantidadNotificables;
    }
}
