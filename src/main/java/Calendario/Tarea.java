package Calendario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Tarea implements DiaCompleto {
    private String titulo;
    private String descripcion;
    private LocalDateTime comienza;
    private LocalDateTime finaliza;
    private ArrayList<Alarma> alarmas;
    private boolean completada;
    public Tarea (String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.alarmas = new ArrayList<>();
        this.completada = false;
    }
    public void asignarTitulo (String titulo) {
        this.titulo = titulo;
    }

    public void asignarDescripcion (String descripcion) {
        this.descripcion = descripcion;
    }
    public void asignarVencimiento(LocalDateTime fechaHoraVencimiento) {
        this.comienza = fechaHoraVencimiento;
        this.finaliza = fechaHoraVencimiento;
    }

    @Override
    public void asignarDiaCompleto(LocalDate dia) {
        this.comienza = LocalDateTime.of(dia, LocalTime.MIN);
        this.finaliza = LocalDateTime.of(dia.plusDays(1), LocalTime.MIN);
    }
    public void completarTarea() {
        this.completada = true;
    }

    public void ejecutar(LocalDateTime fechaHoraActual) {
        for (Alarma alarma:alarmas) { alarma.activarAlarma(fechaHoraActual); }
        if (fechaHoraActual.isAfter(finaliza) || fechaHoraActual.equals(finaliza)) { completarTarea(); }
    }

    public boolean estaCompletada() {
        return completada;
    }

    public void asignarAlarma(Alarma alarma, int minutosAntes){
        alarma.alarmaPersonalizada(comienza, minutosAntes);
        alarmas.add(alarma);
    }
}