package controller;

// Librerias
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.play;
import model.progreso;
import model.reloj;
import view.view;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
public class controlador implements ActionListener {

    private view vista;

    public controlador(view vista) {
        this.vista = vista;
        inicializar();
    }

    private void inicializar() {
        vista.setVisible(true);
        vista.btn1.addActionListener(this);
        vista.btn2.addActionListener(this);
    }

    private void iniciarProgreso() {
        reloj timer = new reloj(this.vista);
        
        progreso hiloCorrer = new progreso(vista.pb1, 5, false, timer);
        progreso hiloNadar = new progreso(vista.pb2, 6, false, timer);
        progreso hiloSaltar = new progreso(vista.pb3, 7, true, timer);

        play gestorDeProgreso = new play(hiloCorrer, hiloNadar, hiloSaltar, timer, this.vista);
        gestorDeProgreso.start();
        timer.start();
    }

    private void saludo() {
        System.out.println("Hola mundo");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btn1) {
            iniciarProgreso();
//            System.out.println("Inicia la ejecucion de los hilos.");
        } else if (ae.getSource() == vista.btn2) {
            saludo();
        }
    }

}
