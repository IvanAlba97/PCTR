
/**
 *
 * @author ivan0
 */
public class UsaRWFileMonitor extends Thread{
    
    private static final RWFileMonitor monitor = new RWFileMonitor();
    private int id, tipoHilo;
    
    public UsaRWFileMonitor(int id, int tipoHilo){
        this.id = id;
        this.tipoHilo = tipoHilo;
    }
    
    @Override
    public void run(){
        switch(tipoHilo){
            case 1:
                while(true){
                try{
                    monitor.startRead(id);
                    sleep(500);
                    monitor.endRead(id);
                    sleep(1000);
                }catch(InterruptedException E){
                    System.err.println(E.getMessage());
                }
            }
            case 2:
                while(true){
                try{
                    monitor.startWrite(id);
                    sleep(500);
                    monitor.endWrite(id);
                    sleep(1000);
                }catch(InterruptedException E){
                    System.err.println(E.getMessage());
                }
            }
        }
    }
    
    public static void main(String[] args){
        int N = 2;
        UsaRWFileMonitor[] hilos = new UsaRWFileMonitor[N];
        /*LECTORES*/
        for(int i=0;i<N;i++){
            hilos[i] = new UsaRWFileMonitor(i, 1);
            hilos[i].start();
        }
        /*ESCRITORES*/
        for(int i=0;i<N;i++){
            hilos[i] = new UsaRWFileMonitor(i, 2);
            hilos[i].start();
        }
    }
    
    /*  NO SE SI SERA MAS O MENOS EFICIENTE:
    static class lector extends Thread{
        
        private int id;

        private lector(int id) {
            this.id = id;
        }
        
        @Override
        public void run(){
            while(true){
                try{
                    monitor.startRead(id);
                    sleep(500);
                    monitor.endRead(id);
                    sleep(1000);
                }catch(InterruptedException E){
                    System.err.println(E.getMessage());
                }
            }
        }
    }
    
    static class escritor extends Thread{
        
        private int id;

        private escritor(int id) {
            this.id = id;
        }
        
        @Override
        public void run(){
            while(true){
                try{
                    monitor.startWrite(id);
                    sleep(500);
                    monitor.endWrite(id);
                    sleep(1000);
                }catch(InterruptedException E){
                    System.err.println(E.getMessage());
                }
            }
        }
    }
    
    public static void main(String[] args){
        int N = 2;
        lector[] lectores = new lector[N];
        escritor[] escritores = new escritor[N];
        for(int i=0;i<N;i++){
            lectores[i] = new lector(i);
            escritores[i] = new escritor(i);
            lectores[i].start();
            escritores[i].start();
        }
    }
    */
}
