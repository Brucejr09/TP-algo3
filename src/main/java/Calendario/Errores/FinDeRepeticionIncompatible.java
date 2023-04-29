package Calendario.Errores;

public class FinDeRepeticionIncompatible extends RuntimeException {
    public  FinDeRepeticionIncompatible(){
        super("La fecha y hora del final de la repeticion esta antes de el inicio del evento");
    }
}
