package Calendario.Notificable;

public class CorreoElectronico extends Notificable{
    private String direccionDeCorreo;
    public CorreoElectronico (String direccionDeCorreo) {
        this.direccionDeCorreo = direccionDeCorreo;
    }

    @Override
    public String notificar() {
        return direccionDeCorreo;
    }
}
