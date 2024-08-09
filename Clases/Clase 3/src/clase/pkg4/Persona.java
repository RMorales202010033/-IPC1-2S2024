package clase.pkg4;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
public class Persona {

    private String nombre;
    private char genero;
    private double altura;
    private long CUI;
    private int edad;

    public Persona() {
    }

    public Persona(String nombre, char genero, double altura, long CUI, int edad) {
        this.nombre = nombre;
        this.genero = genero;
        this.altura = altura;
        this.CUI = CUI;
        this.edad = edad;
    }

    public Persona(String nombre, char genero, double altura, int edad) {
        this.nombre = nombre;
        this.genero = genero;
        this.altura = altura;
        this.edad = edad;
        this.CUI = 1000;
    }

    public void hablar(String mensaje) {
        System.out.println("Yo estoy hablando lo siguiente: " + mensaje);
    }

    public void MostrarDatos() {
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Edad: " + this.getEdad());
        System.out.println("Genero: " + this.getGenero());
        System.out.println("Altura: " + this.getAltura());
        System.out.println("CUI: " + this.getCUI());
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the genero
     */
    public char getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(char genero) {
        this.genero = genero;
    }

    /**
     * @return the altura
     */
    public double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * @return the CUI
     */
    public long getCUI() {
        return CUI;
    }

    /**
     * @param CUI the CUI to set
     */
    public void setCUI(long CUI) {
        this.CUI = CUI;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

}
