package SeccionesInterfaz;

import Calendario.Actividad.Actividad;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ContenedorActividad extends VBox {

    private Actividad actividad;


    public ContenedorActividad(Actividad actividad){
        this.actividad = actividad;
        boolean esEvento = (actividad.getTitulo().substring(0,1).equals("E"));
        Label titulo = new Label(actividad.getTitulo().substring(2));

        Label intervalo = new Label(actividad.getIntervalo());

        this.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        titulo.setStyle("-fx-border-color: black; -fx-border-width: 0 0 2px 0;");




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
