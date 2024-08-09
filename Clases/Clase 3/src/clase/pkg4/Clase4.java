package clase.pkg4;

import java.util.ArrayList;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
public class Clase4 {

    public static ArrayList<Persona> personas = new ArrayList<Persona>();

    public static void main(String[] args) {
        // CREANDO OBJETOS PERSONA
//        Persona persona1 = new Persona();
        Persona persona1 = new Persona("Rodolfo Morales", 'M', 1.70, 12345678, 22);
//        Persona persona1 = new Persona("Rodolfo Morales", 'M', 1.70, 22);
        persona1.hablar("Esto es lo que dice la persona 1.");
        persona1.MostrarDatos();
        personas.add(persona1);
        System.out.println("---------------------------------------------");
        Persona persona2 = new Persona("Ricardo Orellana", 'M', 1.85, 213456789, 20);
        persona2.hablar("Esto es lo que dice la persona 2.");
        persona2.MostrarDatos();
        personas.add(persona2);
        System.out.println("---------------------------------------------");
        persona1.MostrarDatos();
        System.out.println("---------------------------------------------");

        Persona personaTemporal = new Persona("Persona temporal", 'F', 1.65, 213486745, 21);
        personaTemporal.MostrarDatos();
        System.out.println("---------------------------------------------");
        personas.add(personaTemporal);

        for (Persona personaAuxiliar : personas) {
            System.out.println(personaAuxiliar.getNombre());
        }

        for (int i = 0; i < personas.size(); i++) {
            System.out.println("CUI de la persona actual: " + personas.get(i).getCUI());
        }

        System.out.println("***********************************************************************");
        Estudiante student1 = new Estudiante("Isaac Chang", 'M', 1.85, 12345670, 20, 201801573, "Ciencias y Sistemas");
        student1.MostrarDatos();
        System.out.println("===================================================================");
        student1.NotaIPC(88);
        System.out.println("===================================================================");
        student1.NotaIPC(55);
        System.out.println("===================================================================");
        student1.setCursos("IPC 1");
        student1.setCursos("Aplicada 1");
        student1.setCursos("Aplicada 3");
        student1.setCursos("Logica de sistemas");
        student1.setCursos("Fisica 1");
        student1.mostrarCursos();
        student1.setCursos("Aplicada 2");
    }

}
