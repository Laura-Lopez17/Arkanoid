/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAW.arkanoid.modelo;

import DAW.arkanoid.interfaz.CanvasLayer;
import javafx.geometry.Point2D;

/**
 *
 * @author Pedro
 */
enum EstadoJuego {
    PENDIETEINICIAR,
    INICIADO
}

public class Juego {
    private Campo campo;
    public static int ANCHO = 448;
    public static int ALTO = 480;
   
    public Juego() {
        this.campo = new Campo(Juego.ANCHO, Juego.ALTO);
        this.campo.setVelocidad(1.0f);
        this.campo.getBarra().setAlto(16);
        this.campo.getBarra().setAncho(64);
    }

    /**
     * ciclo del juego, se mueve la pelota, comprueba colision con barra y ladrillos
     * si es con ladrillos se devuelve true para repintar el fondo.
     * @param pulsados
     * @return 
     */
    public EstadoCambiosJuego ciclo(boolean pulsados[]) {
        EstadoCambiosJuego vuelta = EstadoCambiosJuego.NADA;
       
        if (pulsados[0]) {
            this.moverBarraIzquierda();
        }
        if (pulsados[1]) {
            this.moverBarraDerecha();
        }
        // Si devuelve true es que toca fondo
        if(this.moverPelota())
            vuelta = EstadoCambiosJuego.TOCABORDE;
       
        return vuelta;
    }
    
    // Movimiento de la pelota y sus colisiones con el entorno
    public boolean moverPelota() {
        boolean tocafondo = false; // Colisión en false
        this.campo.getPelota().mover(); // La pelota se mueve
        
        // Colision lado Izquierdo
        if (this.campo.getPelota().getPosicion().getX() < this.campo.getPelota().getRadio() / 2) {
            this.campo.getPelota().setAngulo(30);
        }
        
        // Colision lado Derecho
        if (this.campo.getPelota().getPosicion().getX() > Juego.ANCHO - this.campo.getPelota().getRadio() - this.campo.getBorde() / 2) {
            this.campo.getPelota().setAngulo(360 - this.campo.getPelota().getAngulo());
            this.campo.getPelota().setAngulo(130);
        }
        
        // Colision lado Superior
        if (this.campo.getPelota().getPosicion().getY() < this.campo.getBorde()) {
            this.campo.getPelota().setAngulo(360 - this.campo.getPelota().getAngulo());
            this.campo.getPelota().setAngulo(80);
        }
        
        // Colision con la barra
        if(this.detectarColisionBarra()){
            this.campo.getPelota().setAngulo(-90);
        }
       
        return tocafondo;
    }
     
    // Se cambia la dirección de la pelota al chocar con la barra izquierda
    /*public void cambiarAnguloColisionBarra() {
        float distancia = (float)Math.abs((float)this.campo.getPelota().getPosicion().getY() - this.campo.getBarra().getPosicion().getY());
        float aleatorio = (((float)Math.random() - 0.5f) * 10.0f);
        
        int anchoparte = this.campo.getBarra().getAlto() / 5;
        int seccion = (int) distancia / anchoparte;
        
        switch (seccion) {
            case (0):
                this.campo.getPelota().setAngulo(295 + aleatorio);
                break;
            case (1):
                this.campo.getPelota().setAngulo(317.5f + aleatorio);
                break;
            case (2):
                this.campo.getPelota().setAngulo(-45 + aleatorio);
                break;
            case (3):
                this.campo.getPelota().setAngulo(22.5f + aleatorio);
                break;
            case (4):
                this.campo.getPelota().setAngulo(45 + aleatorio);
                break;
        }
    }*/

    // Detección de las colisiones de la barra
    public boolean detectarColisionBarra() {
        boolean colision = false;
        float pos_barX = (float)this.campo.getBarra().getPosicion().getY() + this.campo.getBarra().getAlto() - this.campo.getBorde();
        float pos_pelX = (float)this.campo.getPelota().getPosicion().getY();
        
        if (pos_barX > pos_pelX && (pos_barX - pos_pelX < 2)
        // Colision de lado izquierdo hacia el borde izquierdo
        && (this.campo.getBarra().getPosicion().getX() + this.campo.getBarra().getAlto()) > this.campo.getPelota().getPosicion().getX()
        // Colision de lado derecho hacia el borde derecho
        && this.campo.getBarra().getPosicion().getX() < this.campo.getPelota().getPosicion().getX()) {
            this.campo.getPelota().getPosicion().add(0,pos_barX);
            colision = true;
        }
        
        return colision;
    }
    
    public void moverBarraIzquierda() {
        if (this.campo.getBarra().getPosicion().getX() > this.campo.getBorde())
            this.campo.getBarra().moverIzquierda();
    }
    
    public void moverBarraDerecha() {
        if (this.campo.getBarra().getPosicion().getX() + this.campo.getBarra().getAncho() < Campo.getWidth() - this.campo.getBorde())
            this.campo.getBarra().moverDerecha();
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
   
    public static void setANCHO(int ANCHO) {
        Juego.ANCHO = ANCHO;
    }

    public static void setALTO(int ALTO) {
        Juego.ALTO = ALTO;
    }
}
