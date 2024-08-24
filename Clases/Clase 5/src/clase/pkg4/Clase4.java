package clase.pkg4;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
// Libraries
import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;

public class Clase4 {

    /*
        {
            [Josue, Rodolfo, Morales, Castillo],
            [Jose, Ricardo],
            [Kevin, Rodrigo, Sandoval, Hernandez]
        }
     */
    static ArrayList<String[]> students = new ArrayList<>();
    static ArrayList<EstudianteObjeto> studentsObjects = new ArrayList<>();

    static String[][] example_students = {
        {"202010033", "Rodolfo", "Morales", "jrodolfomc2002@gmail.com", "Masculino"},
        {"202010034", "Luisa", "Castillo", "luisac@gmail.com", "Femenino"},
        {"202010025", "Ayeser", "Juarez", "ayez@gmail.com", "Masculino"},
        {"202011535", "Esteban", "Ennati", "enox@gmail.com", "Masculino"},};
    static int code = 202300000;

    public static void main(String[] args) {
        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LOGIN.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String[] student : example_students) {
            students.add(student);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        studentsObjects = (ArrayList<EstudianteObjeto>) DeserializarEstudiantes();

        if (studentsObjects != null) {
            // Si el arraylist de estudiantes tiene valores entonces los voy a imprimir
            for (EstudianteObjeto currentStudent : studentsObjects) {
                System.out.println(currentStudent.toString());
                ConvertirStudentToString(currentStudent);
                code = currentStudent.getCarnet() + 1;
            }
        } else {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Aun no existe un archivo serializado.");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            studentsObjects = new ArrayList<>(); // Inicializa la lista si es nula
        }

        LOGIN login = new LOGIN();
    }

    public static Object[][] convertirDatos_students() {
        int filas = students.size();
        Object[][] arreglo_students = new Object[filas][5];
        for (int i = 0; i < filas; i++) {
            arreglo_students[i][0] = students.get(i)[0];
            arreglo_students[i][1] = students.get(i)[1];
            arreglo_students[i][2] = students.get(i)[2];
            arreglo_students[i][3] = students.get(i)[3];
            arreglo_students[i][4] = students.get(i)[4];
        }
        return arreglo_students;
    }

    public static void addStudent(String[] dataStudent) {
        students.add(dataStudent);
    }

    public static void addStudentObject(EstudianteObjeto student) {
        studentsObjects.add(student);
        SerializarEstudiantes();
    }

    public static void SerializarEstudiantes() {
        // Serialización de la lista
        try {
            // Creamos el archivo binario en la ruta especificada
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./Archivos/Students.bin"));
            // Escribimos nuestro ArrayList de tipo Student
            out.writeObject(studentsObjects);
            // Cerramos el archivo
            out.close();
            System.out.println("***********************************************************************");
            System.out.println("Lista de estudiantes serializada correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("***********************************************************************");
    }

    public static Object DeserializarEstudiantes() {
        // Deserialización de la lista
        try {
            // Abrimos el archivo binario en la ruta especificada
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("./Archivos/Students.bin"));
            // Leemos el objeto guardado (Arraylist de tipo Estudiantes) y lo guardamos en un Arryalist del mismo tipo
            ArrayList<EstudianteObjeto> students = (ArrayList<EstudianteObjeto>) in.readObject();
            // Cerramos el archivo
            in.close();
            System.out.println("Lista de estudiantes deserializada correctamente.");
            // Retornamos el objeto
            return students;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Si no existe ningun archivo o si ocurre un error, se retorna null
        return null;
    }

    public static void ConvertirStudentToString(EstudianteObjeto eo) {
        String[] student = new String[5];
        student[0] = String.valueOf(eo.getCarnet());
        student[1] = eo.getNombre();
        student[2] = eo.getApellido();
        student[3] = eo.getCorreo();
        student[4] = eo.getGenero();
        // Agregamos el vector string con la data del estudiante a la lista
        students.add(student);
    }
}
