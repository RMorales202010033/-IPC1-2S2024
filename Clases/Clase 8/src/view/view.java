package view;

// Librerias
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
public class view extends JFrame {

    // JButton hace referencia a los botones
    public JButton btn1, btn2;
    // JLabel hace referencia a las etiquetas de texto
    public JLabel lbl1, lbl2, lbl3, lbl4;
    // JProgressBar hace referencia a las barras de progreso que muestran un porcentaje
    public JProgressBar pb1, pb2, pb3;

    public view() {
        initComponents();
    }

    private void initComponents() {
        //BTN1	
        btn1 = new JButton("Iniciar");
        btn1.setBounds(100, 600, 200, 50);
        btn1.setVisible(true);
        btn1.setEnabled(true);
        this.add(btn1);

        //BTN2	
        btn2 = new JButton("Saludar");
        btn2.setBounds(350, 600, 200, 50);
        btn2.setVisible(true);
        btn2.setEnabled(true);
        this.add(btn2);

        // LBL1
        lbl1 = new JLabel("Correr");
        lbl1.setVisible(true);
        lbl1.setBounds(100, 200, 200, 50);
        this.add(lbl1);

        // LBL2
        lbl2 = new JLabel("Nadar");
        lbl2.setVisible(true);
        lbl2.setBounds(100, 300, 200, 50);
        this.add(lbl2);

        // LBL3
        lbl3 = new JLabel("Saltar");
        lbl3.setVisible(true);
        lbl3.setBounds(100, 400, 200, 50);
        this.add(lbl3);

        // PB1
        pb1 = new JProgressBar();
        pb1.setStringPainted(true);
        pb1.setVisible(true);
        pb1.setBounds(250, 200, 500, 50);
        this.add(pb1);

        // PB2
        pb2 = new JProgressBar();
        pb2.setStringPainted(true);
        pb2.setVisible(true);
        pb2.setBounds(250, 300, 500, 50);
        this.add(pb2);

        // PB3
        pb3 = new JProgressBar();
        pb3.setStringPainted(true);
        pb3.setVisible(true);
        pb3.setBounds(250, 400, 500, 50);
        this.add(pb3);

        //View Window
        this.setTitle("MÓDULO DE PRUEBA DE HILOS");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
//        this.setVisible(true);
    }
}
