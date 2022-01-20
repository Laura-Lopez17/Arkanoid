/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpingpong.UI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pedro.ieslaencanta.com.dawpingpong.modelo.Campo;

/**
 *
 * @author Pedro
 */
public class CanvasLayer {

    private Canvas principal;
    private Canvas fondo;
    private Campo campo;

    private int alto;
    private int ancho;

    public CanvasLayer(int ancho, int alto) {
        this.alto = alto;
        this.ancho = ancho;
        this.principal = new Canvas(ancho, alto);
        this.fondo = new Canvas(ancho, alto);
    }

    public void paintPrincipal() {
        GraphicsContext gc = this.principal.getGraphicsContext2D();
        // gc.setFill(Color.BLACK);//.rgb(0, 0, 0, 0));
        gc.clearRect(0, 0, ancho, alto);
        gc.setFill(Color.RED);//rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
        if (this.campo != null) {
            //se dibuja la barra izquierda
            if (this.campo.getBarraizquierda() != null) {
                gc.fillRect(this.campo.getBarraizquierda().getPosicion().getX(),
                        this.campo.getBarraizquierda().getPosicion().getY(),
                        this.campo.getBarraizquierda().getAncho(),
                        this.campo.getBarraizquierda().getAlto());
            }
            //se dibuja la barra izquierda
            if (this.campo.getBarraderecha() != null) {
                gc.fillRect(this.campo.getBarraderecha().getPosicion().getX() - this.campo.getBarraderecha().getAncho(),
                        this.campo.getBarraderecha().getPosicion().getY(),
                        this.campo.getBarraderecha().getAncho(),
                        this.campo.getBarraderecha().getAlto());
            }
            gc.setFill(Color.BLUEVIOLET);
            //se pinta la pelota
            gc.fillOval((this.campo.getPelota().getX()),
                    (this.campo.getPelota().getY()), this.campo.getPelota().getRadio(), this.campo.getPelota().getRadio());

        }
    }

    public void paintBackground() {
        this.fondo.getGraphicsContext2D().setFill(Color.GREEN);
        //se dibuja el marco
        if (this.campo.getBorde() > 0) {
            this.fondo.getGraphicsContext2D().setFill(Color.GREEN);
            this.fondo.getGraphicsContext2D().fillRect(0, 0, this.campo.getBorde(), this.fondo.getHeight());//Campo.getALTO_CEDLA());
            this.fondo.getGraphicsContext2D().fillRect(this.fondo.getWidth() - this.campo.getBorde(), 0, this.campo.getBorde(), this.fondo.getWidth());//Campo.getALTO_CEDLA());
            this.fondo.getGraphicsContext2D().fillRect(0, 0, this.fondo.getWidth(), this.campo.getBorde());//Campo.getALTO_CEDLA());
            this.fondo.getGraphicsContext2D().fillRect(0, this.fondo.getHeight() - this.campo.getBorde(), this.fondo.getWidth(), this.campo.getBorde());//Campo.getALTO_CEDLA());

        }

    }

    /**
     * @return the principal
     */
    public Canvas getPrincipal() {
        return principal;
    }

    /**
     * @return the fondo
     */
    public Canvas getFondo() {
        return fondo;
    }

    /**
     * @return the campo
     */
    public Campo getCampo() {
        return campo;
    }

    /**
     * @param campo the campo to set
     */
    public void setCampo(Campo campo) {
        this.campo = campo;
    }

}
