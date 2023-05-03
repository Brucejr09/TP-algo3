package Calendario.Actividad;

import Calendario.Alarma.Alarma;
import Calendario.DiaCompleto;
import Calendario.Intervalo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tarea extends Actividad {
    private LocalDateTime fechaDeVencimiento;
    private boolean completada;

    public Tarea (int id, String titulo, String descripcion) {
        super(id ,titulo, descripcion);
        this.completada = false;
    }

    public void asignarVencimiento(LocalDateTime fechaHoraVencimiento) {
        this.fechaDeVencimiento = fechaHoraVencimiento;
    }

    @Override
    public void asignarDiaCompleto(LocalDate dia) {
        this.fechaDeVencimiento = LocalDateTime.of(dia.plusDays(1), LocalTime.MIN);
    }
    public void completarTarea() {
        this.completada = true;
    }

    public void controlar(LocalDateTime fechaHoraActual) {
        if (this.estaCompletada()){ return; }
        Intervalo intervalo = new Intervalo(fechaDeVencimiento, fechaDeVencimiento);
        for (Alarma alarma:alarmas) { alarma.sonarAlarma(fechaHoraActual, intervalo); }
        if (fechaHoraActual.isAfter(fechaDeVencimiento) || fechaHoraActual.equals(fechaDeVencimiento)) { completarTarea(); }
    }

    public boolean estaCompletada() {
        return completada;
    }

    public void asignarAlarma(Alarma alarma){
        alarmas.add(alarma);
    }

    public int obtenerId() {
        return id;
    }
}