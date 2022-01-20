/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAW.arkanoid.modelo;

import javafx.geometry.Point2D;


/**
 *
 * @author Natalio
 */
public class Ladrillo {

    private int alto;
    private int ancho;
    private int dureza;
    private boolean fijo;
    private Point2D coordenada;
    public static int ANCHO = 32;
    public static int ALTO = 8;
    
    public Ladrillo() {
        this.coordenada = new Point2D(20,20);
    }

    public Ladrillo(Point2D posicion,int dureza, boolean fijo) {
        this.coordenada = coordenada;
        this.ancho = Ladrillo.ANCHO;
        this.alto = Ladrillo.ANCHO; 
        this.fijo = fijo;
        this.dureza=dureza;
    }
    
    /**
     * @return the posicion
     */
    public Point2D getPosicion() {
        return coordenada;
    }
    
    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }
    
    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }
    
    /**
     * @param coordenada the posicion to set
     */
    public void setPosicion(Point2D coordenada) {
        this.coordenada = coordenada;
    }
    
    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
    
    /**
     * @param alto the alto to set
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * @return the dureza
     */
    public int getDureza() {
        return dureza;
    }

    /**
     * @param dureza the dureza to set
     */
    public void setDureza(int dureza) {
        this.dureza = dureza;
    }

    /**
     * @return the fijo
     */
    public boolean isFijo() {
        return fijo;
    }

    /**
     * @param fijo the fijo to set
     */
    public void setFijo(boolean fijo) {
        this.fijo = fijo;
    }
}