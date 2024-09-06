package model;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
public class play extends Thread {

    private progreso h1;
    private progreso h2;
    private progreso h3;

    private volatile boolean correr = true;

    public play(progreso h1, progreso h2, progreso h3) {
        this.h1 = h1;
        this.h2 = h2;
        this.h3 = h3;
    }

    @Override
    public void run() {
        try {
            // Iniciar el primer hilo
            h1.start();
            // Esperar a que termine el primer hilo
            h1.join();

            // Iniciar el segundo hilo
            h2.start();
            // Esperar a que termine el segundo hilo
            h2.join();

            // Iniciar el tercer hilo
            h3.start();
            // Esperar a que termine el tercer hilo
            h3.join();
            detener();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para detener el hilo
    public void detener() {
        setCorrer(false);
    }

    /**
     * @return the correr
     */
    public boolean isCorrer() {
        return correr;
    }

    /**
     * @param correr the correr to set
     */
    public void setCorrer(boolean correr) {
        this.correr = correr;
    }
}
