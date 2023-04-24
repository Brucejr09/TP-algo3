package Calendario;

public class AlarmaNotificable extends Alarma{
    @Override
    public boolean estaActivada() {
        return this.alarmaActivada;
    }
}
