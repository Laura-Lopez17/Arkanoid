/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.interfaz;

import DAW.arkanoid.modelo.Campo;
import DAW.arkanoid.modelo.EstadoCambiosJuego;
import DAW.arkanoid.modelo.Juego;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author Pedro
 */
public class InterfazGrafica extends Application {

    int ancho = 448, alto = 480;
    CanvasLayer canvas;

    long last = 0;
    //va a 24 fps
    long delta = 800000;//(long) (10e9 / 30);
    //la velocidad de la pelota
    int duplicar_velocidad = 25;
    int contador_velocidad = 0;
    private Juego juego;
   
    private JuegoUI juegoUI;
    private boolean pulsados[];
    boolean parar = false;
    boolean stop = false;

    @Override
    public void start(Stage stage) throws Exception {
        this.juego = new Juego();

      
        this.juegoUI = new JuegoUI(this.juego);
        this.canvas = new CanvasLayer(this.ancho, this.alto, this.juego);//pInterfazGrafica.class.getResource("fondos.png").toURI().toString());
        // this.canvas.setJuego(juego);
        this.pulsados = new boolean[2];
        Pane root = new Pane(this.canvas.getFondo(), this.canvas.getPrincipal());
        Scene scene = new Scene(root);
        scene.setOnMouseClicked(e -> {
            this.parar = !this.parar;
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                this.pulsados[0] = false;

            }
            if (e.getCode() == KeyCode.RIGHT) {
                this.pulsados[1] = false;
            }
        });
        scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.LEFT) {
                this.pulsados[0] = true;

            }
            if (e.getCode() == KeyCode.RIGHT) {
                this.pulsados[1] = true;
            }

        });
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                stop = true;
                System.exit(0);
            }
        });
        stage.setTitle("Arkanoid Game DAW");
        stage.setScene(scene);
        stage.show();

        this.GameLoop();

    }

    @Override
    public void stop() {
        try {
            super.stop();
            this.stop = true;
        } catch (Exception ex) {
            Logger.getLogger(InterfazGrafica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    private synchronized void RunEstadoJuego(InterfazGrafica i) {
        EstadoCambiosJuego r;

        if (!i.parar) {
            r = i.juego.ciclo(pulsados);

            this.juegoUI.paint(this.canvas.getPrincipal().getGraphicsContext2D(), this.canvas.getFondo().getGraphicsContext2D(), r);
        }

    }

    private void GameLoop() {
        InterfazGrafica i = this;

        Thread taskThread = new Thread(new Runnable() {

            @Override
            public void run() {
                //para pintar el juego por primera vez
                juegoUI.paint(canvas.getPrincipal().getGraphicsContext2D(), canvas.getFondo().getGraphicsContext2D(), EstadoCambiosJuego.TOCALADRILLO);

                while (true) {
                    try {

                        RunEstadoJuego(i);
                        Thread.sleep(5);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        }
        );

        taskThread.start();
    }

}
