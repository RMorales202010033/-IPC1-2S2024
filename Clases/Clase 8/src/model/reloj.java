package model;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import view.view;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
public class reloj extends Thread {

    private volatile boolean running = true;
    private int segundos = 0;
    private int minutos = 0;
    private view vista;

    public reloj(view vista) {
        this.vista = vista;
        inicializar();
    }

    private void inicializar() {
        this.vista.lbl4 = new JLabel("Tiempo: 00:00");
        this.vista.lbl4.setBounds(580, 25, 120, 40);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        this.vista.lbl4.setBorder(border);
        this.vista.lbl4.setVisible(true);
        this.vista.add(this.vista.lbl4);
        this.vista.repaint();
    }

    @Override
    public void run() {
        while (running) {
            try {
                sleep(1000); // miliseconds
                segundos++;

                if (segundos == 60) {
                    segundos = 0;
                    minutos++;
                }
                actualizarReloj();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void detenerReloj() {
        this.running = false;
        this.vista.remove(this.vista.lbl4);
        this.vista.repaint();
    }

    private void actualizarReloj() {
        String tiempo = String.format("%02d:%02d", minutos, segundos);
        this.vista.lbl4.setText("Tiempo: " + tiempo);
    }

}
