/**
 * Esta clase contiene los atributos y metodos para el incremento de las variables n(sincronizada) y m(no sincronizada).
 * @author Iván Alba Gómez
 * @version 3.0
 * @see usaHeterogenea.java
 */
public class heterogenea {

    public static int n = 0, m = 0;
   
    /**
     * Método modificador sincronizado.
     */
    public synchronized void incN() { n++; }

    /**
     * Método modificador no sincronizado.
     */
    public void incM() { m++; }

    /**
     * Método observador.
     */
    public int getN() { return n; }

    /**
     * Método observador.
     */
    public int getM() { return m; }
   
}
