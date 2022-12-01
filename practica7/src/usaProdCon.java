/**
 * Esta clase contiene los atributos y metodos para el manejo de productores y consumidores.
 * @author Iván Alba Gómez
 * @version 3.0
 * @see prodCon.java
 */
public class usaProdCon {
    
    static prodCon monitor = new prodCon();
    
    /**
     * Esta clase contiene los atributos y metodos para el manejo de productores.
     * @author Iván Alba Gómez
     * @version 3.0
     * @see prodCon.java
     */
    static class productor extends Thread {
        
        /**
         * Constructor vacío.
         */
        public productor() {}

        /**
         * Método que encapsula el código a ejecutar concurrentemente.
         */
        @Override
        public void run() {
            while(true) {
                try {
                    monitor.Take();
                    monitor.Append(1);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Esta clase contiene los atributos y metodos para el manejo de consumidores.
     * @author Iván Alba Gómez
     * @version 3.0
     * @see prodCon.java
     */
    static class consumidor extends Thread {
        
        /**
         * Constructor vacío.
         */
        public consumidor() {}

        /**
         * Método que encapsula el código a ejecutar concurrentemente.
         */
        @Override
        public void run() {
            while(true) {
                try {
                    monitor.Take();
                    monitor.Append(1);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Método principal.
     */
    public static void main(String[] args) {
        int N = 5;
        productor[] productores = new productor[N];
        consumidor[] consumidores = new consumidor[N];
        for(int i = 0; i < N; i++) {
            productores[i] = new productor();
            consumidores[i] = new consumidor();
        }
    }
}
