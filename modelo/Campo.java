/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpingpong.modelo;

/**
 *
 * @author Natalio
 */
// Turnos
enum Turno {
    IZQUIERDA,
    DERECHA
}
public class Campo {
    // Creación de variables
    private static int width, height; // Medidas del campo
    private Barra barraizquierda, barraderecha; // Barras de Juagdores
    private Pelota pelota; // Pelota
    private int borde = 10; // Tamaño del borde del campo
    private int marcador1, marcador2; // Marcadores
    private Turno turno; // Turnos

    // Creación del campo
    public Campo(int anchopixels, int altopixels) {
        Campo.width = anchopixels; // Ancho del campo
        Campo.height = altopixels; // Alto del campo
        marcador1=0; // Iniciamos el marcador1 a 0
        marcador2=0; // Iniciamos el marcador2 a 0
        this.pelota = new Pelota(new Coordenada2D(Campo.getWidth() / 2 - 5, Campo.getHeight() / 2 - 5), 20); // Pelota
        this.barraizquierda = new Barra(new Coordenada2D(20, altopixels / 2 - 45), 15, 90); // Barra izquierda
        this.barraderecha = new Barra(new Coordenada2D(anchopixels - 20, altopixels / 2 - 45), 15, 90); // Barra derecha
        this.turno = Math.random() < 0.5 ? Turno.DERECHA : Turno.IZQUIERDA; // Turnos
    }

    private void initPelota() {
        this.turno = this.turno == Turno.DERECHA?Turno.IZQUIERDA:Turno.DERECHA;
        if(this.turno == Turno.DERECHA) {
            this.pelota.setAngulo(0);
        } else {
            this.pelota.setAngulo(180);
        }
        this.pelota.setX(Campo.getWidth() / 2 - 5);
        this.pelota.setY(Campo.getHeight() / 2 - 5);
    }

    // Detección de las colisiones de la barra derecha
    public boolean detectarColisionBarraDerecha() {
        boolean colision = false; // Las ccolisiones se declaran falsas
        float pos_barX = this.getBarraderecha().getPosicion().getX() - this.getBarraderecha().getAncho();
        float pos_pelX = this.getPelota().getX() + this.getPelota().getRadio();
        
        if (pos_barX < pos_pelX && (pos_barX - pos_pelX < 2)
        // Se encuentra por encima del borde inferior
        && (this.getBarraderecha().getPosicion().getY() + this.getBarraderecha().getAlto()) > this.pelota.getY()
        // Se encuentra por debajo del borde superior
        && this.getBarraderecha().getPosicion().getY() < this.pelota.getY()) {
            this.pelota.setX(pos_barX - this.getPelota().getRadio());
            colision = true;
        }
        return colision;
    }

    // Detección de las colisiones de la barra izquierda
    public boolean detectarColisionBarraIzquierda() {
        boolean colision = false; // Las ccolisiones se declaran falsas
        float pos_barX = this.getBarraizquierda().getPosicion().getX() + this.getBarraizquierda().getAncho();
        float pos_pelX = this.getPelota().getX();
        
        if (pos_barX > pos_pelX && (pos_barX - pos_pelX < 2)
        // Se encuentra po encima del borde inferior
        && (this.getBarraizquierda().getPosicion().getY() + this.getBarraizquierda().getAlto()) > this.pelota.getY()
        // Se encuentra por debajo del borde superior
        && this.getBarraizquierda().getPosicion().getY() < this.pelota.getY()) {
            this.pelota.setX(pos_barX);
            colision = true;
        }
        return colision;
    }

    // Se cambia la dirección de la pelota al chocar con la barra izquierda
    public void cambiarAnguloColisionBarraIzquierda() {
        float distancia = Math.abs(this.pelota.getY() - this.getBarraizquierda().getPosicion().getY());
        float aleatorio = (((float)Math.random() - 0.5f) * 10.0f);
        // Se divide en 5 partes
        int anchoparte = this.getBarraizquierda().getAlto() / 5;
        int seccion = (int) distancia / anchoparte;
        // La sección 4 y 0 cambian un angulo en 90 grados, 3 y 1 a 45 grados y 2 a 180
        switch (seccion) {
            case (0):
                this.pelota.setAngulo(295 + aleatorio);
                break;
            case (1):
                this.pelota.setAngulo(317.5f + aleatorio);
                break;
            case (2):
                this.pelota.setAngulo(0 + aleatorio);
                break;
            case (3):
                this.pelota.setAngulo(22.5f + aleatorio);
                break;
            case (4):
                this.pelota.setAngulo(45 + aleatorio);
                break;
        }
    }

    // Se cambia la dirección de la pelota al chocar con la barra derecha
    public void cambiarAnguloColisionBarraDerecha() {
        float distancia = Math.abs(this.pelota.getY() - this.getBarraderecha().getPosicion().getY());
        // Se dan +- 5 grados aleatorios
        float aleatorio = 0.0f;
        // Se divide en 5 partes
        int anchoparte = this.getBarraizquierda().getAlto() / 5;
        int seccion = (int) distancia / anchoparte;
        // La sección 4 y 0 cambian un angulo en 90 grados, 3 y 1 a 45 grados y 2 a 180
        switch (seccion) {
            case (0):
                this.pelota.setAngulo(225 + aleatorio);
                break;
            case (1):
                this.pelota.setAngulo(202.5f + aleatorio);
                break;
            case (2):
                this.pelota.setAngulo(180 + aleatorio);
                break;
            case (3):
                this.pelota.setAngulo(157.5f + aleatorio);
                break;
            case (4):
                this.pelota.setAngulo(135 + aleatorio);
                break;
        }
    }

    private void Perder(Turno jugador) {
        if(jugador == Turno.DERECHA){
            this.marcador1++;
            this.initPelota();
        } else {
            this.marcador2++;
            this.initPelota();
        }
    }
    
    public void moverPelota() {
        this.pelota.mover();
        
        if (this.pelota.getX() < this.pelota.getRadio() / 2) {
            this.pelota.setAngulo(0);
            this.Perder(Turno.IZQUIERDA);
        }
        if (this.pelota.getX() > 800 - this.pelota.getRadio() - 10 / 2) {
            this.pelota.setAngulo(180);
            this.Perder(Turno.DERECHA);
        }
        if(this.detectarColisionBarraIzquierda())
            this.cambiarAnguloColisionBarraIzquierda();
        if(this.detectarColisionBarraDerecha())
            this.cambiarAnguloColisionBarraDerecha();
        
        //golpea el superior
        if (this.pelota.getY() < this.borde) {
            this.pelota.setAngulo(360 - this.pelota.getAngulo());
           this.pelota.setY(this.pelota.getRadio());
        }
        //golpea el inferior 
        if (this.pelota.getY() + this.pelota.getRadio() > Campo.getHeight() - this.borde){
            this.pelota.setY(Campo.getHeight() - this.pelota.getRadio() * 2);
            this.pelota.setAngulo(360 - this.pelota.getAngulo());
        }
    }

    public void moverBarraIzquierdaArriba() {
        this.barraizquierda.moverArriba();
        if (this.barraizquierda.getPosicion().getY() < this.borde) {
            this.barraizquierda.getPosicion().setY(this.borde);
        }
    }

    public void moverBarraIzquierdaAbajo() {
        this.barraizquierda.moverAbajo();
        if (this.barraizquierda.getPosicion().getY() + this.barraizquierda.getAlto() > Campo.height - this.borde) {
            this.barraizquierda.getPosicion().setY(Campo.height - this.borde - this.barraizquierda.getAlto());
        }
    }

    public void moverBarraDerechaArriba() {
        this.barraderecha.moverArriba();
        if (this.barraderecha.getPosicion().getY() < this.borde) {
            this.barraderecha.getPosicion().setY(this.borde);
        }
    }

    public void moveBarraDerechaAbajo() {
        this.barraderecha.moverAbajo();
        if (this.barraderecha.getPosicion().getY() + this.barraderecha.getAlto() > Campo.height - this.borde) {
            this.barraderecha.getPosicion().setY(Campo.height - this.borde - this.barraderecha.getAlto());
        }
    }

    public void rotateX() {
        this.pelota.rotateX();
    }

    public void setVelocidad(float velocidad) {
        this.pelota.setVelocidad(velocidad);
    }

    public float getVelocidad() {
        return this.pelota.getVelocidad();
    }

    /**
     * @return the barraizquierda
     */
    public Barra getBarraizquierda() {
        return barraizquierda;
    }

    /**
     * @return the barraderecha
     */
    public Barra getBarraderecha() {
        return barraderecha;
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
     * @param barraizquierda the barraizquierda to set
     */
    public void setBarraizquierda(Barra barraizquierda) {
        this.barraizquierda = barraizquierda;
    }

    /**
     * @param barraderecha the barraderecha to set
     */
    public void setBarraderecha(Barra barraderecha) {
        this.barraderecha = barraderecha;
    }
}
