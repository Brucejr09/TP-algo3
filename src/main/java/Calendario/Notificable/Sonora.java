package Calendario.Notificable;

public class Sonora extends Notificable{
    String sonido;

    public Sonora (String sonido) {
        this.sonido = sonido;
    }

    @Override
    public String notificar() {
        return sonido;
    }
}
