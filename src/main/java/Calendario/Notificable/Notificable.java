package Calendario.Notificable;

public abstract class Notificable {
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
