package clase.pkg4;

// Libraries
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.text.MessageFormat;
import java.util.Map;
import javax.swing.*;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

// Librer�as para el uso de gr�ficos
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
public class ESTUDIANTE extends JFrame implements ActionListener, ChangeListener {

    // JLabel hace referencia a las etiquetas de texto
    JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;
    // JButton hace referencia a los botones
    JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12;
    //JTabbedPane	
    JTabbedPane panel;
    //JPanels
    JPanel jp1, jp2, jp3, jp4;
    //Atributos para la tabla
    JTable tabla_oficial;
    JScrollPane sp1;

    boolean vb1 = true;

    public ESTUDIANTE() {
        initComponents();
    }

    private void initComponents() {
        panel = new JTabbedPane(JTabbedPane.TOP);
        jp1 = new JPanel(null);
        panel.addTab("Students", jp1);
        jp2 = new JPanel();
        jp2.setBackground(Color.yellow);
        jp2.setLayout(null);
        panel.addTab("Tab 2", jp2);
        jp3 = new JPanel();
        jp3.setBackground(Color.yellow);
        jp3.setLayout(null);
        panel.addTab("Tab 3", jp3);
        jp4 = new JPanel();
        panel.addTab("Logout", jp4);
        panel.addChangeListener(this);
///////////////////////////////////////////// STUDENTS //////////////////////////////////////////////////////////////////////////////////////////		
        //BTN1	
        btn1 = new JButton("Crear");
        btn1.setBounds(800, 25, 257, 50);
        btn1.setVisible(true);
        btn1.setEnabled(true);
        btn1.addActionListener(this);
        jp1.add(btn1);

        //BTN2	
        btn2 = new JButton("Cargar Estudiantes");
        btn2.setBounds(800, 100, 257, 50);
        btn2.setVisible(true);
        btn2.setEnabled(true);
        btn2.addActionListener(this);
        jp1.add(btn2);

        //BTN3
        btn3 = new JButton("Generar HTML");
        btn3.setBounds(800, 175, 257, 50);
        btn3.setVisible(true);
        btn3.setEnabled(true);
        btn3.addActionListener(this);
        jp1.add(btn3);

        // Gr�ficas
        // Estilos de graficas: http://www.java2s.com/Code/Java/Chart/CatalogChart.htm
        // Insertar nuestra data (valor, "categoria", "Leyenda de la columna")
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        datos.setValue(80, "IPC 1", "Alumno 1");
        datos.setValue(70, "IPC 1", "Alumno 2");
        datos.setValue(95, "IPC 1", "Alumno 3");
        datos.setValue(30, "IPC 2", "Alumno 4");
        datos.setValue(50, "IPC 2", "Alumno 5");
        datos.setValue(65, "IPC 2", "Alumno 6");

        // Instancear gr�fico de barras 3D
        JFreeChart grafico_barras = ChartFactory.createBarChart3D(
            "Calificaciones alumnos IPC1 e IPC2", // T�tulo del grafico
            "Estudiantes", // Nombre de las barras o columnas
            "Calificacion", // Nombre de la numeracion
            datos, // Datos del grafico
            PlotOrientation.VERTICAL, // Orientacion
            true, // Leyenda de barras individuales por color
            true, // Herramientas
            false // Url del grafico
        );

        // Creaci�n de un ChartPanel el cual almacenar� nuestro gr�fico
        ChartPanel cPanel = new ChartPanel(grafico_barras);
        // Habilitamos el scroll
        cPanel.setMouseWheelEnabled(true);
        // Asignamos la posici�n y las dimensiones de nuestro ChartPanel
        cPanel.setBounds(800, 250, 450, 300);
        // Agregamos a nuestra pesta�a el ChartPanel con nuestro gr�fico
        jp1.add(cPanel);

        // Ejemplo 2 (Gr�fica de pie)
        DefaultPieDataset datos2 = new DefaultPieDataset();
        datos2.setValue("Java", new Double(43.2));
        datos2.setValue("Visual Basic", new Double(10.0));
        datos2.setValue("C/C++", new Double(17.5));
        datos2.setValue("PHP", new Double(32.5));
        datos2.setValue("Perl", new Double(1.0));

        // Instancear gr�fica de pie 3D
        JFreeChart grafico_pie = ChartFactory.createPieChart3D(
            "Lenguajes de programaci�n m�s usados", // chart title
            datos2, // data
            true, // include legend
            true,
            false
        );
        // Configuraciones adicionales a nuestro gr�fico de pie
        PiePlot3D plot = (PiePlot3D) grafico_pie.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");

        // Creaci�n de un ChartPanel el cual almacenar� nuestro gr�fico
        ChartPanel cPanel2 = new ChartPanel(grafico_pie);
        // Habilitamos es scroll
        cPanel2.setMouseWheelEnabled(true);
        // Asignamos la posici�n y las dimensiones de nuestro ChartPanel
        cPanel2.setBounds(800, 575, 450, 300);
        // Agregamos a nuestra pesta�a el ChartPanel con nuestro gr�fico
        jp1.add(cPanel2);

        //LBL4
        lbl4 = new JLabel("Listado Oficial Estudiantes");
        lbl4.setBounds(25, 25, 750, 50);
        lbl4.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.lbl4.setBackground(Color.LIGHT_GRAY);
        this.lbl4.setOpaque(true);
        lbl4.setVerticalAlignment(SwingConstants.CENTER);
        lbl4.setHorizontalAlignment(SwingConstants.CENTER);
        lbl4.setFont(new Font(lbl4.getFont().getFontName(), Font.BOLD, 24));
        Font font = lbl4.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        lbl4.setFont(font.deriveFont(attributes));
        lbl4.setVisible(true);
        jp1.add(lbl4);
        //TABLE		
        String[] titulos_c = {"C�digo", "Nombre", "Apellido", "Correo", "G�nero"};
        tabla_oficial = new JTable(Clase4.convertirDatos_students(), titulos_c);
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < titulos_c.length; i++) {
            tabla_oficial.getColumnModel().getColumn(i).setCellRenderer(Alinear);
        }
        tabla_oficial.setEnabled(false);
        resizeColumnWidth(tabla_oficial);
        sp1 = new JScrollPane(tabla_oficial);
        sp1.setBounds(25, 80, 750, 570);
        sp1.setVisible(true);
        jp1.add(sp1);
        //LBL1
        lbl1 = new JLabel();
        lbl1.setBounds(0, 0, 1364, 675);
        ImageIcon iprofesores = new ImageIcon(getClass().getResource("./Images/profesor.jpg"));
        Image imgEscalada = iprofesores.getImage().getScaledInstance(lbl1.getWidth(), lbl1.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        lbl1.setIcon(iconoEscalado);
        lbl1.setVisible(true);
        jp1.add(lbl1);

/////////////////////////////////////////////////// TAB2 //////////////////////////////////////////////////////////////////////////////////////////		
        //LBL2
        lbl2 = new JLabel();
        lbl2.setBounds(0, 0, 1364, 675);
        ImageIcon icursos = new ImageIcon(getClass().getResource("./Images/cursos.jpg"));
        Image imgEscalada2 = icursos.getImage().getScaledInstance(lbl2.getWidth(), lbl2.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado2 = new ImageIcon(imgEscalada2);
        lbl2.setIcon(iconoEscalado2);
        lbl2.setVisible(true);
        jp2.add(lbl2);

///////////////////////////////////////////// TAB3 //////////////////////////////////////////////////////////////////////////////////////////		
        //LBL3
        lbl3 = new JLabel();
        lbl3.setBounds(0, 0, 1364, 675);
        ImageIcon ialumnos = new ImageIcon(getClass().getResource("./Images/alumnos.jpg"));
        Image imgEscalada3 = ialumnos.getImage().getScaledInstance(lbl3.getWidth(), lbl3.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado3 = new ImageIcon(imgEscalada3);
        lbl3.setIcon(iconoEscalado3);
        lbl3.setVisible(true);
        jp3.add(lbl3);

        //LOGIN Window
        this.setTitle("M�DULO DE ESTUDIANTES");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().add(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btn1) {
            CREATE_STUDENT create_student = new CREATE_STUDENT();
            this.dispose();
        } else if (ae.getSource() == btn2) {
            Clase4.lecturaCSV(this);
        } else if (ae.getSource() == btn3) {
            Clase4.generateHtml();
        }
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        if (ce.getSource() instanceof JTabbedPane) {
            JTabbedPane pane = (JTabbedPane) ce.getSource();
            if (pane.getSelectedIndex() == 3) {
                LOGIN login = new LOGIN();
                this.dispose();
            }
        }
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

}
