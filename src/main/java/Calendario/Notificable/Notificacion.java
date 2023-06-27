package Calendario.Notificable;

public class Notificacion extends Notificable{
    private String mensaje;

    public Notificacion (String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String notificar() {
        return this.mensaje;
    }
}
