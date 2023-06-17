package Calendario.Repeticion;

import Calendario.Intervalo;
import Calendario.Errores.FinDeRepeticionIncompatible;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Repeticion implements Serializable {

    protected LocalDateTime finRepeticion;
    protected long limiteDeOcurrencias;

    protected Repeticion(LocalDateTime finRepeticion){
        this.finRepeticion = finRepeticion;
        this.limiteDeOcurrencias = 0;
    }

    public Repeticion(int limiteDeOcurrencias) {
        this.limiteDeOcurrencias = limiteDeOcurrencias;
    }

    public void resolverCompatibilidadCon(Intervalo intervalo) {
        if (limiteDeOcurrencias == 0){
            if (intervalo.comienzaAhoraODespues(this.finRepeticion)){
                throw new FinDeRepeticionIncompatible();
            }
            this.limiteDeOcurrencias = this.calcularOcurrencia(finRepeticion, intervalo);
        }
    }

    public abstract Intervalo darSiguienteOcurrencia(LocalDateTime fechaHoraActual, Intervalo intervalo);

    protected abstract long calcularOcurrencia(LocalDateTime fechaHora, Intervalo intervalo);

    public long obtenerLimiteDeOcurrencias() {
        return limiteDeOcurrencias;
    }
}
