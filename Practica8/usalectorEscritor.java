
/**
 *
 * @author ivan0
 */
public class usalectorEscritor {
    
    private static lectorEscritor monitor = new lectorEscritor();
    
    static class lector extends Thread{
        private final int id;
        public lector(int id){
            this.id = id;
        }
        @Override
        public void run(){
            while(true){
                try {
                    monitor.iniciaLectura(id);
                    sleep(500);
                    monitor.finLectura(id);
                    sleep(1000);
                } catch (InterruptedException E) {
                    System.err.println(E.getMessage());
                }
            }
        }
    }
    
    static class escritor extends Thread{
        private final int id;
        public escritor(int id){
            this.id = id;
        }
        @Override
        public void run(){
            while(true){
                try {
                    monitor.iniciaEscritura(id);
                    sleep(1000);
                    monitor.finEscritura(id);
                    sleep(1500);
                } catch (InterruptedException E) {
                    System.err.println(E.getMessage());
                }
            }
        }
    }
    
    public static void main(String[] args){
        int N = 5;
        lector[] lectores = new lector[N];
        escritor[] escritores = new escritor[N];
        for(int i=0;i<N;i++){
            lectores[i] = new lector(i);
            escritores[i] = new escritor(i);
            lectores[i].start();
            escritores[i].start();
        }
    }
}
