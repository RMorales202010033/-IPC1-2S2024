package clase.pkg4;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
// Libraries
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
// Librerias para generar report HTML
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;
import java.io.*;

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

//    static String[][] example_students = {
//        {"202010033", "Rodolfo", "Morales", "jrodolfomc2002@gmail.com", "Masculino"},
//        {"202010034", "Luisa", "Castillo", "luisac@gmail.com", "Femenino"},
//        {"202010025", "Ayeser", "Juarez", "ayez@gmail.com", "Masculino"},
//        {"202011535", "Esteban", "Ennati", "enox@gmail.com", "Masculino"},};
    static int code = 202300000;

    public static void main(String[] args) {
        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LOGIN.class.getName()).log(Level.SEVERE, null, ex);
        }

//        for (String[] student : example_students) {
//            students.add(student);
//        }
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

    public static void lecturaCSV(JFrame frame) {

        JFileChooser fileChooser = new JFileChooser();

        //Filtro para que unicamente deje seleccionar archivos CSV 
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
        fileChooser.setFileFilter(filter);

        // Mostrar el diálogo de selección de archivos
        int result = fileChooser.showOpenDialog(frame);

        // Verificar si se seleccionó un archivo
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("=======================================================================");
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());

            // Leer el archivo CSV y mostrar su contenido como tabla
            try {
                Scanner scanner = new Scanner(selectedFile);
                boolean v1 = true;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine(); // "Carnet, Nombre, Apellido, Correo, Genero"
                    String[] parts = line.split(","); // ["Carnet", " Nombre", " Apellido", " Correo", " Genero"]

                    for (int i = 0; i < parts.length; i++) {
                        parts[i] = parts[i].trim();
                    }

                    for (String part : parts) {
                        System.out.print(part + "\t | \t");
                    }
                    System.out.println();

                    if (v1) {
                        v1 = !v1;
                    } else {
                        EstudianteObjeto student = new EstudianteObjeto(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]);
                        addStudentObject(student);
                        ConvertirStudentToString(student);
                    }
                }
                scanner.close();
                frame.dispose();
                ESTUDIANTE student = new ESTUDIANTE();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            System.out.println("=======================================================================");
            System.out.println("");
        }

    }

    public static void generateHtml() {
        // Fecha en la que se generó
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("MMM/dd/yyyy 'at' hh:mm a");
        ZonedDateTime datetime = ZonedDateTime.now();
        String generacion = datetime.format(formato);
        //Declaracion de variables
        String RH = "", RF = "", RB = "";

        RH = "<!DOCTYPE html>\r\n"
            + "\t<html>\r\n"
            + "\t\t<head>\r\n"
            + "\t\t\t<meta charset=\"ISO-8859-1\"><!--codififcaion de caracteres ñ y á-->\r\n"
            + "\t\t\t<meta name=\"name\" content=\"Reporte de alumnos\"><!--nombre de la pagina-->\r\n"
            + "\t\t\t<meta name=\"description\" content=\"Rodolfo Morales\"><!--autor de la pagina-->\r\n"
            + "\t\t\t<meta name=\"keywods\" content=\"uno,dos,tres\"><!--Palabras claavez para, separadas por comas-->\r\n"
            + "\t\t\t<meta name=\"robots\" content=\"Index, Follow\"><!--Mejora la busqueda-->\r\n"
            + "\t\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><!--visibilidaad en diferentes pantallas -->\r\n"
            + "\t\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\"/><!--css /estilo/tipo/ruta relativa -->\r\n"
            + "\t\t\t<title>Reporte Alumnos Clase 6 </title><!--Titulo visible de la pagina-->\r\n"
            + "\t\t</head>\r\n"
            + "\r\n"
            + "\r\n"
            + "\t\t<body>\r\n"
            + "\t\t\t<center><!--centra todos lo que este dentro-->\r\n"
            + "\t\t\t\t<div class=\"te1\">\r\n"
            + "\t\t\t\t\t<h1><b> REPORTE ALUMNOS ASIGNADOS </b></h1>\r\n"
            + "\t\t\t\t</div>\r\n";

        RB = ""
            + "\t\t\t\t<h2 class=\"te2\"><b> Tabla de alumnos asignados: </b></h2>\r\n"
            + "\t\t\t\t<table id=\"t01\">"
            + "\r\n"
            + "\t\t\t\t\t<tr>\r\n";

        int iteracion = 0;
        for (EstudianteObjeto estudiante : studentsObjects) {
            RB += "\t\t\t\t\t\t";
            RB += "<td>" + estudiante.getCarnet() + "</td>";
            RB += "<td>" + estudiante.getNombre() + "</td>";
            RB += "<td>" + estudiante.getApellido() + "</td>";
            RB += "<td>" + estudiante.getCorreo() + "</td>";
            RB += "<td>" + estudiante.getGenero() + "</td>";
            if ((studentsObjects.size() - 1) != iteracion) {
                RB += "\r\n\t\t\t\t\t</tr>\r\n\t\t\t\t\t<tr>\r\n";
            }
            iteracion++;
        }
        
        RB += "\r\n\t\t\t\t\t</tr>\r\n"
            + "\t\t\t\t</table>\r\n";

        //Resultado cifrado
        RB += ""
            + "\t\t\t\t<div class=\"greporte\">\r\n"
            //Generacion Reporte, tiempo en que fue creado
            + "\t\t\t\t\t<h3><b> Información Generación del Reporte: </b></h3>\r\n"
            + "\t\t\t\t\t"
            + generacion
            + "\r\n"
            + "\t\t\t\t\t<br>\r\n"
            + "\t\t\t\t</div>"
            + "";
        //Cierre HTML 
        RF = "\r\n"
            + "\t\t\t</center>\r\n"
            + "\t\t</body>\r\n"
            + "\t</html>";

        try {
            // Crea el archivo en la ruta y con el formato especificado
            FileWriter archivo = new FileWriter("./Reportes/Reporte1.html");
            // Escribe los string en el archivo
            archivo.write(RH + RB + RF);
            // Por ultimo se cierra el archivo
            archivo.close();
            System.out.println("-> " + "El reporte fue generado exitosamente, puede visualizarlo en la siguiente ruta relativa:");
            System.out.println("./Reportes/Reporte1");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
