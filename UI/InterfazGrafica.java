/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpingpong.UI;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import pedro.ieslaencanta.com.dawpingpong.modelo.Campo;

/**
 *
 * @author Pedro
 */
public class InterfazGrafica extends Application {

    int ancho = 800, alto = 400;
    CanvasLayer canvas;
    Campo campo;
    long last = 0;
    //va a 24 fps
    long delta = 2800000;//(long) (10e9 / 30);
    //la velocidad de la pelota
    int duplicar_velocidad = 25;
    int contador_velocidad = 0;

    @Override
    public void start(Stage stage) throws Exception {
        this.canvas = new CanvasLayer(this.ancho, this.alto);
        this.campo = new Campo(ancho, alto);
        this.campo.setVelocidad(1.0f);
        this.canvas.setCampo(this.campo);
        Pane root = new Pane(this.canvas.getFondo(), this.canvas.getPrincipal());
        Scene scene = new Scene(root);
        
        scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.DOWN) {
                campo.moveBarraDerechaAbajo();
            }
            if (e.getCode() == KeyCode.UP) {
                campo.moverBarraDerechaArriba();
            }
            if (e.getCode() == KeyCode.A) {
                campo.moverBarraIzquierdaAbajo();
            }
            if (e.getCode() == KeyCode.Q) {
                campo.moverBarraIzquierdaArriba();
            }
        });
        stage.setTitle("Pong Game DAW");
        stage.setScene(scene);
        stage.show();
        this.canvas.paintBackground();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - last >= delta) {
                    last = now;
                    campo.moverPelota();
                   
                    canvas.paintPrincipal();
                }
            }
        };
        timer.start();

    }

}
