package SeccionesInterfaz;

import Calendario.Actividad.Actividad;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContenedorActividad extends VBox {

    private Actividad actividad;


    public ContenedorActividad(Actividad actividad, LocalDate fecha){
        this.actividad = actividad;
        boolean esEvento = (actividad.getTitulo().substring(0,1).equals("E"));
        Label titulo = new Label(actividad.getTitulo().substring(2));
        titulo.setFont(Font.font(null, FontWeight.BOLD, 12));

        Label intervalo = new Label(actividad.getIntervalo(fecha));

        this.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        titulo.setStyle("-fx-border-color: black; -fx-border-width: 0 0 1px 0;");




        //FXMLLoader nuevoCargadorInterfaz = new FXMLLoader(getClass().getResource("crear.fxml"));
        //nuevoCargadorInterfaz.setController(this);
        //try {
        //    VBox nuevaInterfaz = nuevoCargadorInterfaz.load();
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}

        if (esEvento){
            this.setBackground(new Background(new BackgroundFill(Color.AQUA,CornerRadii.EMPTY, Insets.EMPTY)));
        }else {
            this.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY, Insets.EMPTY)));
        }

        this.getChildren().addAll(titulo, intervalo);
    }


}
