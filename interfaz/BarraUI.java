/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.interfaz;

import DAW.arkanoid.modelo.Barra;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Pedro
 */
public class BarraUI {

    private Barra barra;
    private int estado;
    private static int NUMESTADOS = 6;
    private Point2D dibujobarras[];
    private Image imagen;
    private long last;
    private long tope = 500;
  
    public BarraUI(Image imagen, Barra barra) {
        this.estado = 0;
        this.mapear();
        this.imagen = imagen;
        this.barra = barra;
        this.last = System.currentTimeMillis();
    }

    private void mapear() {
        this.dibujobarras = new Point2D[BarraUI.NUMESTADOS];
        for (int i = 0; i < this.dibujobarras.length; i++) {
            this.dibujobarras[i] = new Point2D(31, i * 8);
        }
    }

    public void pintar(GraphicsContext gc) {
        long now=System.currentTimeMillis();
        //System.out.println(this.last+" "+now);
        if(now -this.last>this.tope){
            this.inc();
            this.last=now;
        }
        gc.drawImage(this.imagen, this.dibujobarras[this.estado].getX(), this.dibujobarras[this.estado].getY(), 32, 8, this.barra.getPosicion().getX(), this.barra.getPosicion().getY(), this.barra.getAncho(), this.barra.getAlto());//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),280,140);//this.campo.getBarra().getPosicion().getX(),this.campo.getBarra().getPosicion().getY(),23,8,247,240);
    }

    public void inc() {
        this.estado++;
        if (this.estado >= BarraUI.NUMESTADOS) {
            this.estado = 0;
        }
    }

}
