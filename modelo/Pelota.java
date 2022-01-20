/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.modelo;

import javafx.geometry.Point2D;

/**
 *
 * @author Pedro
 */
public class Pelota {

    private Point2D posicion;
    private float angulo = 90;
    private float velocidad = 1f;
    private int radio = 8;

    public Pelota() {
        this.posicion = new Point2D(50,50);
        this.radio = 8;
    }

    public Pelota(Point2D posicion, int radio) {
        this.posicion = posicion;
        this.radio = radio;
    }

    public void mover() {
        float x = (float) ((float) getVelocidad() * Math.cos(Math.toRadians(getAngulo())));
        float y = (float) ((float) getVelocidad() * Math.sin(Math.toRadians(getAngulo())));
        this.posicion = this.getPosicion().add(x,y);
    }

    /**
     * @return the angulo
     */
    public float getAngulo() {
        return angulo;
    }

    /**
     * @param angulo the angulo to set
     */
    public void setAngulo(float angulo) {
        this.angulo = angulo;
        if(this.angulo<0){
            this.angulo+=360;
        }
        if (this.angulo >= 360) {
            this.angulo = this.angulo % 360;
        }
    }

    public void addAngulo(float angulo) {
        this.angulo += angulo;
        if (this.angulo >= 360) {
            this.angulo = this.angulo % 360;
        }
        if (this.angulo < 0) {
            this.angulo += 360;
        }
    }

    /**
     * @return the radio
     */
    public int getRadio() {
        return radio;
    }

    /**
     * @param radio the radio to set
     */
    public void setRadio(int radio) {
        this.radio = radio;
    }

    /**
     * @return the velocidad
     */
    public float getVelocidad() {
        return velocidad;
    }

    /**
     * @param velocidad the velocidad to set
     */
    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * @return the posicion
     */
    public Point2D getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(Point2D posicion) {
        this.posicion = posicion;
    }
}
