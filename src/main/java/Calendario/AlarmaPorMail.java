package Calendario;

public class AlarmaPorMail extends Alarma{

    @Override
    public boolean estaActivada() {
        return this.alarmaActivada;
    }
}
