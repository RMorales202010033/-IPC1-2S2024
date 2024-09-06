package model;

// Librerias a utilizar
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
public class progreso extends Thread {

    private JProgressBar barraProgreso;
    private int tiempoTotal;
    private double progreso;
    private volatile boolean correr = true;

    public progreso(JProgressBar barraProgreso, int tiempoTotal) {
        this.barraProgreso = barraProgreso;
        this.tiempoTotal = tiempoTotal;
        this.progreso = 0;
    }

    // Metodo run, el cual va a ejecutar nuestro hilo con la logica que coloquemos
    @Override
    public void run() {
        int segundos = 0;
        double intervalo = 100 / tiempoTotal;
        
        while (isCorrer()) {
            try {
                System.out.println("Progress bar con tiempo: " + tiempoTotal);
                segundos = +1;
                progreso += intervalo;
                if (segundos == tiempoTotal || progreso >= 100) {
                    progreso = 100;
                    detener();
                }

                SwingUtilities.invokeLater(() -> barraProgreso.setValue((int) progreso));
                Thread.sleep(1000); // Milisegundos

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Metodo para detener el hilo
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
