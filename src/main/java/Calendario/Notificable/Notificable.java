package Calendario.Notificable;

import java.io.Serializable;

public abstract class Notificable implements Serializable {
    private int cantidadNotificables;

    public Notificable () {
        this.cantidadNotificables = 0;
    }
    public abstract String notificar ();
    public void aumentarNotificacion () {
        cantidadNotificables++;
    }

    public int obtenerCantidadNotificables () {
        return this.cantidadNotificables;
    }
}
