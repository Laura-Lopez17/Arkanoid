/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.interfaz;

import DAW.arkanoid.modelo.Campo;
import DAW.arkanoid.modelo.EstadoCambiosJuego;
import DAW.arkanoid.modelo.Juego;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 *
 * @author Pedro
 */
public class JuegoUI {

    private String pathintro = "C:/nav.png";
    private Juego juego;
    private Image imagen;
    private Image imagen_fondo;
    private Image imagen_barrasypelota;
    private Image imagen_bloques;
    private BarraUI barraui;
    MediaPlayer sonido_tocabarra, sonido_tocaladrillo, sonido_muere, sonido_nivel;
    boolean debug;
    private String pathfondos = "fondos.png";
    private String pathbarras = "barras.png";
    private String pathbloques = "BlocksBackgrounds.png";
    private String pathsonidobarra = "sounds/golpe.wav";
    private String pathsonidofallo = "sounds/fallo.mp3";
    private String pathsonidonivel = "sounds/nuevonivel.mp3";
    private int colorladrillos[][][] = {
        { //blanco
            {0, 0}
        },
        { //naranja
            {16, 0}
        },
        { //celeste
            {32, 0}
        },
        { //verde
            {48, 0}
        },
        { //rojo
            {0, 8}
        },
        { //azul
            {16, 8}
        },
        { //violeta
            {32, 8}
        },
        { //amarillo
            {48, 8}
        },
        { //ris
            {0, 16}
        },
        { //dorado
            {0, 24}
        },};

    public JuegoUI(Juego j) {
        this.juego = j;
        ClassLoader classLoader = getClass().getClassLoader();

        this.debug = true;
      
        this.initimagenes(classLoader);
        this.barraui = new BarraUI(this.imagen_barrasypelota, this.juego.getCampo().getBarra());

        this.initsonidos(classLoader);
    }

    private void initimagenes(ClassLoader classLoader) {

        this.imagen_fondo = new Image(classLoader.getResource(this.pathfondos).toString());

        //System.out.println(new java.io.File(".").getAbsolutePath());
        this.imagen_bloques = new Image(classLoader.getResource(this.pathbloques).toString());
        this.imagen_barrasypelota = new Image(classLoader.getResource(this.pathbarras).toString());
        
       
    }

    private void initsonidos(ClassLoader classLoader) {
        System.out.println(classLoader.getResource(this.pathsonidobarra).toString());
        this.sonido_tocabarra = new MediaPlayer(new Media(classLoader.getResource(this.pathsonidobarra).toString()));
        this.sonido_muere = new MediaPlayer(new Media(classLoader.getResource(this.pathsonidofallo).toString()));
        this.sonido_nivel = new MediaPlayer(new Media(classLoader.getResource(this.pathsonidonivel).toString()));

    }

    public void paint(GraphicsContext principal, GraphicsContext fondo, EstadoCambiosJuego estado) {
        this.paintPrincipal(principal);
        if (estado == EstadoCambiosJuego.TOCALADRILLO) {
            this.paintBackground(fondo);
            this.sonido_tocabarra.setVolume(1.0d);
            sonido_tocabarra.seek(Duration.ZERO);
            this.sonido_tocabarra.play();

        }

    }

    private void paintPrincipal(GraphicsContext gc) {
        int color[];
        // gc.setFill(Color.BLACK);//.rgb(0, 0, 0, 0));
        gc.clearRect(0, 0, Campo.getWidth(), Campo.getHeight());
        gc.setFill(Color.RED);//rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
        if (this.juego != null) {
            //se dibuja la barra izquierda
            if (this.juego.getCampo().getBarra() != null) {
                this.barraui.pintar(gc);
                //this.barraui.inc();
                //gc.drawImage(this.imagen_barrasypelota, 31, 0, 32, 8, this.getJuego().getCampo().getBarra().getPosicion().getX(), this.getJuego().getCampo().getBarra().getPosicion().getY(), CanvasLayer.barra_ancho, CanvasLayer.barra_alto);//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),280,140);//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),23,8,247,240);
                gc.drawImage(this.imagen_barrasypelota, 0, 40, 5, 5, this.juego.getCampo().getPelota().getPosicion().getX(), this.juego.getCampo().getPelota().getPosicion().getY(), this.juego.getCampo().getPelota().getRadio(), this.juego.getCampo().getPelota().getRadio());//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),280,140);//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),23,8,247,240);
                if (debug) {
                    this.paintDebug(gc);
                }

            }

        }
    }

    public void paintDebug(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillText("Pelota: X:" + (int) this.juego.getCampo().getPelota().getPosicion().getX() + " Y:" + (int) this.juego.getCampo().getPelota().getPosicion().getY() + " R:" + this.juego.getCampo().getPelota().getRadio(), 20, 30);
        gc.fillText("Barra: X:" + this.juego.getCampo().getBarra().getPosicion().getX() + " Y:" + this.juego.getCampo().getBarra().getPosicion().getY() + " (W,H):" + this.juego.getCampo().getBarra().getAncho() + "," + this.juego.getCampo().getBarra().getAlto(), 20, 50);
        gc.fillText((int) this.juego.getCampo().getPelota().getPosicion().getX() + "," + (int) this.juego.getCampo().getPelota().getPosicion().getY(), this.juego.getCampo().getPelota().getPosicion().getX(), this.juego.getCampo().getPelota().getPosicion().getY());//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),280,140);//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),23,8,247,240);

    }

    public void paintBackground(GraphicsContext gc) {
        int borde = 10;
        gc.drawImage(this.imagen_fondo, 0, 0, 224, 240, 0, 0, 448, 480);
        /*if (this.juego != null) {
            matriz = this.juego.getCampo().getLadrillos();
            for (int i = 0; i < matriz.length; i++) {
                l = matriz[i];
                if(l != null && (l.getDureza() > 0 || l.isFijo())) {
                    color = this.coordenadaDureza(l);
                    
                    if (this.debug){
                        gc.setFill(Color.GREEN);
                    }
                }
            }
        }*/
        /*           -------Imágen------  ---------Coorednada X-------  --------Coordenada Y--------  X   Y  X   Y   X   Y  */
        gc.drawImage(this.imagen_bloques, this.colorladrillos[1][0][0], this.colorladrillos[1][0][1], 16, 8, 64, 64, 32, 16);
    }
}
