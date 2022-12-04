 /**
     * Esta clase contiene los atributos y metodos para la creación de hebras que usen el recurso compartido a través de un monitor .
     * @author Iván Alba Gómez
     * @version 3.0
     * @see recurso.java
     * @see lectorEscritor.java
     */
public class usaLectorEscritor {
    
    private static lectorEscritor monitor = new lectorEscritor();
    private static recurso rec = new recurso();
    
     /**
     * Esta clase contiene los atributos y metodos para el manejo de lectores que usen el recurso compartido a través del monitor.
     * @author Iván Alba Gómez
     * @version 3.0
     * @see recurso.java
     * @see lectorEscritor.java
     */
    static class lector extends Thread {

        private final int id;

        /**
         * Constructor
         */
        public lector(int id) {
            this.id = id;
        }

        /**
         * Método que encapsula el código a ejecutar concurrentemente.
         */
        @Override
        public void run(){
            for(int i = 0; i < 1000000; i++) {
                try {
                    monitor.iniciaLectura(id);
                    rec.observer();
                    monitor.finLectura(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
     /**
     * Esta clase contiene los atributos y metodos para el manejo de escritores que usen el recurso compartido a través del monitor.
     * @author Iván Alba Gómez
     * @version 3.0
     * @see recurso.java
     * @see lectorEscritor.java
     */
    static class escritor extends Thread {

        private final int id;

        /**
         * Constructor
         */
        public escritor(int id){
            this.id = id;
        }

        /**
         * Método que encapsula el código a ejecutar concurrentemente.
         */
        @Override
        public void run(){
            for(int i = 0; i < 1000000; i++) {
                try {
                    monitor.iniciaEscritura(id);
                    rec.inc();
                    monitor.finEscritura(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Método principal
     */
    public static void main(String[] args) throws Exception {

        int N = 5;
        lector[] lectores = new lector[N];
        escritor[] escritores = new escritor[N];

        for(int i = 0; i < N; i++) {
            lectores[i] = new lector(i);
            escritores[i] = new escritor(i);
            lectores[i].start();
            escritores[i].start();
        }
        
        for(int i = 0; i < N; i++) {
            lectores[i].join();
            escritores[i] .join();
        }

        System.out.println("VARIABLE N: " + rec.observer());
    }
}

