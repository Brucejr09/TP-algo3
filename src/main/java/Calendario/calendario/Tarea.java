package Calendario.calendario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tarea implements DiaCompleto {
    private String titulo;
    private String descripcion;
    private LocalDateTime comienza;
    private LocalDateTime finaliza;
    private Alarma alarmas;
    private boolean completada;
    public Tarea (String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
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
}