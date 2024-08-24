package clase.pkg4;

import java.io.Serializable;

/**
 *
 * @author Rodolfo Morales - 202010033
 */
public class EstudianteObjeto implements Serializable {

    public static int contadorEstudiantes = 0;

    // Atributos del objeto
    private int carnet;
    private String nombre;
    private String apellido;
    private String correo;
    private String genero;

    // Constructor
    public EstudianteObjeto(int carnet, String nombre, String apellido, String correo, String genero) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.genero = genero;
    }

    // Por medio de esta funcion se retorna en un string los datos del objeto actual
    @Override
    public String toString() {
        return "Estudiante{"
            + "carnet= '" + this.carnet + '\''
            + ", nombre= '" + this.nombre + '\''
            + ", apellido= '" + this.apellido + '\''
            + ", correo= '" + this.correo + '\''
            + ", genero= " + this.genero
            + '}';
    }

    // Encapsulamiento
    /**
     * @return the carnet
     */
    public int getCarnet() {
        return carnet;
    }

    /**
     * @param carnet the carnet to set
     */
    public void setCarnet(int carnet) {
        this.carnet = carnet;
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
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

}
