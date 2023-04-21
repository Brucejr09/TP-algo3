package Calendario;

public interface Frecuencia<T> {
    T Diariamente (long dias);
    T Semanalmente (String dia);
    T Mensualmente ();
    T Anualmente ();
}