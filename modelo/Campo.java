/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.modelo;

import javafx.geometry.Point2D;

/*enum EstadoJuego {
    PENDIETEINICIAR,
    INICIADO
}*/

/**
 *
 * @author Pedro
 */
public class Campo {
    private static int width, height;
    private Barra barra;
    private final Pelota pelota;
    public static int BORDE = 16;
    public int borde = Campo.BORDE;
    private Ladrillo[] ladrillo;
   
    public Campo(int anchopixels, int altopixels) {
        Campo.width = anchopixels;
        Campo.height = altopixels;
        
        this.barra = new Barra(new Point2D(anchopixels / 2 - 33, altopixels - 29), 30, 14);
        this.pelota = new Pelota(new Point2D(0, 0), 10);//this.barra.getPosicion().getX() + this.barra.getAncho() / 2 - 5, this.barra.getPosicion().getY() - 5), 10);//altopixels - 23), 10);
        this.pelota.setAngulo(70);
    }
    
    public void crearLadrillos() {
        this.ladrillo=new Ladrillo[10];
        int x = 20;
        int y = 20;
        for (int i = 0; i < this.ladrillo.length; i++) {
            this.ladrillo[i] = new Ladrillo(new Point2D(x+Ladrillo.ANCHO*i,y),(i % 8)+1,false);
        }
    }

    public Ladrillo[] getLadrillo() {
        return ladrillo;
    }

    public void setLadrillo(Ladrillo[] ladrillo) {
        this.ladrillo = ladrillo;
    }

    public void setVelocidad(float velocidad) {
        this.pelota.setVelocidad(velocidad);
    }

    public float getVelocidad() {
        return this.pelota.getVelocidad();
    }

    /**
     * @return the barra
     */
    public Barra getBarra() {
        return barra;
    }

    /**
     * @return the pelota
     */
    public Pelota getPelota() {
        return pelota;
    }

    /**
     * @return the borde
     */
    public int getBorde() {
        return borde;
    }

    /**
     * @return the width
     */
    public static int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public static int getHeight() {
        return height;
    }

    /**
     * @param aWidth the width to set
     */
    public static void setWidth(int aWidth) {
        width = aWidth;
    }

    /**
     * @param aHeight the height to set
     */
    public static void setHeight(int aHeight) {
        height = aHeight;
    }

    /**
     * @param borde the borde to set
     */
    public void setBorde(int borde) {
        this.borde = borde;
    }

    /**
     * @param barra the barraizquierda to set
     */
    public void setBarra(Barra barra) {
        this.barra = barra;
    }

    private void setLadrillos(Ladrillo[] ladrillo) {
        this.ladrillo = ladrillo;
    }
}
