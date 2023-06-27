package Calendario.Actividad;

import Calendario.Alarma.Alarma;
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

    @Override
    public String getTitulo() {
        return "T-" + titulo;
    }

    @Override
    public Intervalo obtenerIntervalo(LocalDate fecha) {
        Intervalo intervalo = new Intervalo(fechaDeVencimiento);
        return intervalo;
    }

    public void asignarVencimiento(LocalDateTime fechaHoraVencimiento) {
        this.fechaDeVencimiento = fechaHoraVencimiento;
    }

    @Override
    public void asignarDiaCompleto(LocalDate dia) {
        this.fechaDeVencimiento = LocalDateTime.of(dia, LocalTime.MAX);
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

    @Override
    public boolean soyDelDia(LocalDate dia) {
        return fechaDeVencimiento.toLocalDate().isEqual(dia);
    }

    @Override
    public String getIntervalo(LocalDate fecha) {
        String estado = (completada? "Completa":"Por completar");
        if (fechaDeVencimiento.toLocalTime().equals(LocalTime.MAX)) return "Dia Completo - " + estado;
        return fechaDeVencimiento.toLocalTime().toString() + " - " + estado;
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