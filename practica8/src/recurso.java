 /**
 * Esta clase contiene los atributos y metodos para el manejo de un recurso compartido.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see lectorEscritor.java
 * @see usaLectorEscritor.java
 */
public class recurso {

    private static long n = 0;

    /**
     * Constructor vacío
     */
    public recurso() {}

    /**
     * Método modificador
     */
    public void inc() { n++; }

    /**
     * Método observador
     */
    public long observer() { return n; }
}